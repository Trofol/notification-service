package controllerTest;


import notificationservice.controller.NotificationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import notificationservice.service.UserService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = NotificationController.class)
@ContextConfiguration(classes = notificationservice.UserServiceApplication.class)
public class NotificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testSendNotification() throws Exception {
        String emailJson = "{\"email\":\"test@example.com\",\"operation\":\"CREATE\"}";

        mockMvc.perform(post("/api/notifications/send")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(emailJson))
                .andExpect(status().isOk());

        // Проверяем, что метод sendEmail вызван с правильными параметрами
        verify(userService, times(1)).sendEmail("test@example.com", "CREATE");
    }
}

