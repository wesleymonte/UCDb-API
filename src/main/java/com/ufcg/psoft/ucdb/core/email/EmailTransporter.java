package com.ufcg.psoft.ucdb.core.email;

import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EmailTransporter {

    private final String email;
    private final String password;
    private final String registrationMessage = "Parabéns, você foi cadastrado com sucesso";
    private final String registrationSubject = "Cadastro no UCDb";

    private static final Logger LOGGER = LogManager.getLogger(EmailTransporter.class);

    public EmailTransporter(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void sendRegistrationEmail(String email){
        this.sendEmail(this.registrationMessage, this.registrationSubject, email);
    }

    private Session createSessionMail() {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", 465);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.port", 465);

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        session.setDebug(true);

        return session;
    }

    private void sendEmail(String msg, String subject, String email) {

        String sender = this.email;

        Message message = new MimeMessage(createSessionMail());
        try {
            message.setFrom(new InternetAddress(sender));

            String recipient = email.trim();

            Address[] toUser = InternetAddress.parse(recipient);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(subject);
            message.setContent(msg, "text/html");

            Transport.send(message);
            LOGGER.info("Email sent to [" + email + "] successfully");
        } catch (MessagingException m){
            LOGGER.error("Error sending email to [" + email + "]");
            LOGGER.error(m);
        }


    }
}
