package kth_a2_continuous_integration;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.*;
import javax.mail.Transport;

import java.util.Properties;

/*
 * Class to enable email to be sent upon builds.
 */
public class EmailSender {

    /**
     * Function to send a mail from dd2480group19@gmail.com to the selected recipient.
     * 
     * @param to the recipient
     * @param from the sender
     * @param subject the subject of the mail
     * @param text the text of the mail
     * @return true if email was sent successfully, false otherwise
     */
    public boolean sendEmail(String to, String from, String subject, String text) {
        boolean flag = false;

        // smtp properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.host", "smtp.gmail.com");

        String username = "dd2480group19@gmail.com";
        String password = "mmqtxacpwfruadnx";

        // Session
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

}
