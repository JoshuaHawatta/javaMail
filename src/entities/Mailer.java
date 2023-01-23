package src.entities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Mailer {
    private final List<String> users = new ArrayList<>();

    public Authenticator createAuthenticator(String userName, String password) {
        return new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
    }

    public Properties setGmailProperties() {
        Properties gmailProperties = new Properties();

        gmailProperties.put("mail.smtp.auth", "true"); //AUTHORIZATION
        gmailProperties.put("mail.smtp.ssl.trust", "*"); //TRUST_AUTHORS
        gmailProperties.put("mail.smtp.starttls", "true"); //AUTHENTICATION
        gmailProperties.put("mail.smtp.host", "smtp.gmail.com"); //GMAIL_HOST
        gmailProperties.put("mail.smtp.port", "465"); //GMAIL_PORT
        gmailProperties.put("mail.smtp.socketFactory.port", "465"); //SOCKET_PORT
        gmailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SOCKET_CLASS_CONECTION

        return gmailProperties;
    }

    public void sendEmail(String subject, String text, String... personalName) {
        try {
            final String password = "dsoawwwabyksbvpu";
            final String userName = "joshuahawatta.javaweb@gmail.com";

            Session session = Session.getInstance(this.setGmailProperties(), this.createAuthenticator(userName, password));
            Message email = new MimeMessage(session);
            Address[] usersToSend = InternetAddress.parse(this.getUsersToSend());

            if(personalName.length > 0) email.setFrom(new InternetAddress(userName, personalName[0]));
            else email.setFrom(new InternetAddress(userName));

            email.setRecipients(Message.RecipientType.TO, usersToSend);
            email.setSubject(subject);
            email.setText(text);

            Transport.send(email);
        } catch(Exception e) {
            ExceptionHandler.printExceptionData(e);
        }
    }

    public void addUser (String user) {
        this.users.add(user);
    }

    public String getUsersToSend() {
        if (this.users.size() == 0) new ExceptionHandler("array");

        StringBuilder usersEmails = new StringBuilder();
        for (String user : this.users) usersEmails.append(user).append(", ");

        return usersEmails.toString();
    }
}
