package pl.deepsoft.dmapp.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.deepsoft.dmapp.Entity.User;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendActivationEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getMail());
        mailMessage.setSubject("Aktywacja konta");
        mailMessage.setText("Twoje konto zostało aktywowane przez administratora.");

        javaMailSender.send(mailMessage);
    }
}