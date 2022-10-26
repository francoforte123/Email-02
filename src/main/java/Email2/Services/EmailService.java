package Email2.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendTo(String text, String userEmail) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(userEmail);
        helper.setSubject("new message!");
        helper.setText("<h1>Hello!</h1> <h2>good day!</h2> <h3>" + text + "</h3> " +
                "<img src='cid:saluto' width=600>", true);
        ClassPathResource image = new ClassPathResource("saluto.png");
        helper.addInline("saluto", image);
        javaMailSender.send(message);
    }
}
