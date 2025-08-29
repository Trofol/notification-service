package serviceTest;


import notificationservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {

    private final JavaMailSender mailSender = mock(JavaMailSender.class);
    private final UserService userService = new UserService(mailSender);

    @Test
    void testSendEmailCreate() {
        userService.sendEmail("user@example.com", "CREATE");

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        SimpleMailMessage message = captor.getValue();
        assertThat(message.getTo()).containsExactly("user@example.com");
        assertThat(message.getText()).isEqualTo("Здравствуйте! Ваш аккаунт на сайте был успешно создан.");
    }

    @Test
    void testSendEmailDelete() {
        userService.sendEmail("user@example.com", "DELETE");

        ArgumentCaptor<SimpleMailMessage> captor = ArgumentCaptor.forClass(SimpleMailMessage.class);
        verify(mailSender).send(captor.capture());

        SimpleMailMessage message = captor.getValue();
        assertThat(message.getTo()).containsExactly("user@example.com");
        assertThat(message.getText()).isEqualTo("Здравствуйте! Ваш аккаунт был удалён.");
    }
}
