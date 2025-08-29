package notificationservice.broker;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import notificationservice.service.UserService;

@Service
public class UserEventConsumer {

    private final UserService userService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public UserEventConsumer(UserService userService) {
        this.userService = userService;
    }

    @KafkaListener(topics = "user-events", groupId = "notification-group")
    public void consume(String message) {
        try {
            UserEvent event = objectMapper.readValue(message, UserEvent.class);
            userService.sendEmail(event.getEmail(), event.getOperation());
        } catch (Exception e) {
            // Логируем ошибки
            e.printStackTrace();
        }
    }

    @Data
    private static class UserEvent {
        private String operation;
        private String email;

    }
}
