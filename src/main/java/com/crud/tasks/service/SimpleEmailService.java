package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service
public class SimpleEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    public void send(final Mail mail ) {
        LOGGER.info("Starting email preparation...");
        try {
            //SimpleMailMessage mailMessage = createMail(mail);
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent");
        } catch (MailException e) {
            LOGGER.info("Failed to process email sending: ", e.getMessage(), e );
        }
    }

    private MimeMessagePreparator createMimeMessage (final Mail mail){
        return mimeMessage -> {
            MimeMessageHelper messageHelper =  new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTaskInformationMail(mail.getMessage()), true);
        };
    }

    private SimpleMailMessage createMail (Mail mail){
        SimpleMailMessage creatingMailMessage =  new SimpleMailMessage();
        creatingMailMessage.setTo(mail.getMailTo());
        creatingMailMessage.setSubject(mail.getSubject());
        creatingMailMessage.setText(mail.getMessage());
        if(mail.getToCC() != "") creatingMailMessage.setCc(mail.getToCC());
        return creatingMailMessage;
    }
}
