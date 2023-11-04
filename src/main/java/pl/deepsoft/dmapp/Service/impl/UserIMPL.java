package pl.deepsoft.dmapp.Service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.deepsoft.dmapp.Dto.LoginDTO;
import pl.deepsoft.dmapp.Dto.UserDTO;
import pl.deepsoft.dmapp.Entity.User;
import pl.deepsoft.dmapp.Exception.AppException;
import pl.deepsoft.dmapp.Exception.BadRequestException;
import pl.deepsoft.dmapp.Exception.NotAcceptableException;
import pl.deepsoft.dmapp.Exception.NotFoundException;
import pl.deepsoft.dmapp.Repo.UserRepo;
import pl.deepsoft.dmapp.Response.LoginResponse;
import pl.deepsoft.dmapp.Service.UserService;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
            throw new BadRequestException("Użytkownik o podanym adresie e-mail: "+userDTO.getMail()+" istnieje.");
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
                        throw new AppException("Konto "+ user1.getMail() + " nie jest aktywne");
                    }
                } else {
                    throw new NotAcceptableException("Logowanie nieudane");
                }
            } else {
                throw new BadRequestException("Hasło nieprawidłowe");
            }
        } else {
            throw new NotFoundException("Email " +user1.getMail() +" nie istnieje");
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
            throw new NotAcceptableException("Użytkownik o podanym identyfikatorze nie istnieje.");
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
            throw new AppException("Użytkownik o podanym identyfikatorze nie istnieje.");
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
            throw new AppException("Użytkownik o podanym identyfikatorze nie istnieje.");
        }

        return false;
    }

}


