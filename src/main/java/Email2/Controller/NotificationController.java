package Email2.Controller;

import Email2.Entities.Student;
import Email2.Services.EmailService;
import Email2.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    EmailService emailService;

    @Autowired
    StudentService studentService;

    @GetMapping
    public ResponseEntity<String> sendEmail(@RequestParam String text, @RequestParam String userId) {
        try {
            Student student = studentService.getStudentById(userId);
            if (student == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not find the student");
            }
            emailService.sendTo(text, student.getEmail());
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
