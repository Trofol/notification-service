package notificationservice.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final JavaMailSender mailSender;

    public UserService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, String operation) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Уведомление от сайта");

        if ("CREATE".equalsIgnoreCase(operation)) {
            mailMessage.setText("Здравствуйте! Ваш аккаунт на сайте был успешно создан.");
        } else if ("DELETE".equalsIgnoreCase(operation)) {
            mailMessage.setText("Здравствуйте! Ваш аккаунт был удалён.");
        } else {
            mailMessage.setText("Здравствуйте! Есть обновления по вашему аккаунту.");
        }

        mailSender.send(mailMessage);
    }
}

