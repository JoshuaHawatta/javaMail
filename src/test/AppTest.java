package src.test;

import org.junit.Test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class AppTest {
    @Test
    public void addSmtpProperties() {
        try {
            final String userName = "joshuahawatta.javaweb@gmail.com";
            final String password = "dsoawwwabyksbvpu";

            Properties GMAIL_SMTP = this.setGmailProperties();
            Session session = Session.getInstance(GMAIL_SMTP, this.createAuthenticator(userName, password));
            Message email = new MimeMessage(session);
            Address[] users = InternetAddress.parse(
                "joshuahawatta@gmail.com, emilianoazevedo72@gmail.com, lucianasilva.email@gmail.com,"
            );

            email.setFrom(new InternetAddress(userName, "Joshua Hawatta - Curso Java")); //<FROM>_EMAIL
            email.setRecipients(Message.RecipientType.TO, users); //<TO>_EMAIL
            email.setSubject("Bello, meu amiguinho!"); //SUBJECT
            email.setText("Não se assuste, é um e-mail que enviei via Java, bisou!"); //EMAIL_BODY

            Transport.send(email);
        } catch(Exception exception) {
            System.out.println("ERRO - " + exception.getMessage() + "\r\nCAUSA - " + exception.getCause());
            exception.printStackTrace();
        }
    }

    public Authenticator createAuthenticator(String userName, String password) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
    }

    public Properties setGmailProperties() {
        Properties SMTP_PROPS = new Properties();

        SMTP_PROPS.put("mail.smtp.auth", "true"); //AUTHORIZATION
        SMTP_PROPS.put("mail.smtp.ssl.trust", "*"); //TRUST_AUTHORS
        SMTP_PROPS.put("mail.smtp.starttls", "true"); //AUTHENTICATION
        SMTP_PROPS.put("mail.smtp.host", "smtp.gmail.com"); //GMAIL_HOST
        SMTP_PROPS.put("mail.smtp.port", "465"); //GMAIL_PORT
        SMTP_PROPS.put("mail.smtp.socketFactory.port", "465"); //SOCKET_PORT
        SMTP_PROPS.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SOCKET_CLASS_CONECTION

        return SMTP_PROPS;
    }
}
