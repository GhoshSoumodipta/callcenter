package at.ac.tuwien.sepm.groupphase.backend.service;

import at.ac.tuwien.sepm.groupphase.backend.domain.model.MsgUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailService.class);
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String mailUser;
    @Value("${spring.mail.password}")
    private String mailPassword;

    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendConfirmMail(@Valid MsgUser myUser, String confirmUrl){
        if(confirmUrl != null && !confirmUrl.isBlank()){
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(myUser.getEmail());
            msg.setSubject("Video Calling Confirmation Mail");
            String url = confirmUrl + "/" + myUser.getUuid();
            msg.setText(String.format("Welcome!! Please use thhis link(%s) to confirm your account", url));
            this.javaMailSender.send(msg);
            logger.info("Confirm Mail send to: "+myUser.getEmail());
        }
    }
}
