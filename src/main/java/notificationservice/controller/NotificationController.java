package notificationservice.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import notificationservice.service.UserService;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final UserService userService;

    public NotificationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) {
        userService.sendEmail(request.getEmail(), request.getOperation());
        return ResponseEntity.ok("Email sent");
    }
    @Data
    public static class NotificationRequest {
        private String email;
        private String operation;
    }
}
