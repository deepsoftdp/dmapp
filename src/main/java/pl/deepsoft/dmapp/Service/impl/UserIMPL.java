package pl.deepsoft.dmapp.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.deepsoft.dmapp.Dto.LoginDTO;
import pl.deepsoft.dmapp.Dto.UserDTO;
import pl.deepsoft.dmapp.Entity.User;
import pl.deepsoft.dmapp.Repo.UserRepo;
import pl.deepsoft.dmapp.Response.LoginResponse;
import pl.deepsoft.dmapp.Service.UserService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import pl.deepsoft.dmapp.Service.EmailService;
@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Override
    public String addUser(UserDTO userDTO) {
        User existingUser = userRepo.findByMail(userDTO.getMail());
        if (existingUser != null) {
            return "Użytkownik o podanym adresie e-mail już istnieje.";
        } else {
            User user = new User(
                    userDTO.getIdUsers(),
                    userDTO.getName(),
                    userDTO.getMail(),
                    this.passwordEncoder.encode(userDTO.getPassword())

            );
            user.setActive(false);

            user.setRoles(Collections.singletonList("ROLE_USER").toString());
            userRepo.save(user);

            return "Zarejestrowano pomyślnie";
        }
    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepo.findByMail(loginDTO.getMail());

        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight) {
                Optional<User> user = userRepo.findOneByMailAndPassword(loginDTO.getMail(), encodedPassword);

                if (user.isPresent()) {
                    if (user1.isActive()) {
                        String userRole = user1.getRoles();
                        return new LoginResponse("Zalogowano pomyślnie", true, userRole);
                    } else {
                        return new LoginResponse("Konto nie jest aktywne", false);
                    }
                } else {
                    return new LoginResponse("Logowanie nieudane", false);
                }
            } else {
                return new LoginResponse("Hasło nieprawidłowe", false);
            }
        } else {
            return new LoginResponse("Email nie istnieje", false);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }
    @Override
    public boolean activateUser(int userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(true);
            userRepo.save(user);
            emailService.sendActivationEmail(user);
        } else {
            throw new RuntimeException("Użytkownik o podanym identyfikatorze nie istnieje.");
        }

        return false;
    }

    @Override
    public boolean deactivateUser(int userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setActive(false);
            userRepo.save(user);
        } else {
            throw new RuntimeException("Użytkownik o podanym identyfikatorze nie istnieje.");
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userRepo.delete(user);
        } else {
            throw new RuntimeException("Użytkownik o podanym identyfikatorze nie istnieje.");
        }

        return false;
    }

}


