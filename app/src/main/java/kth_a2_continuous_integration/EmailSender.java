package kth_a2_continuous_integration;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.StreamProvider;

import java.util.Properties;

/**
 * Class to enable email to be sent upon builds, by use of method sendEmail().
 */
public class EmailSender {

    /**
     * Sends an email from dd2480group19@gmail.com to the selected recipient.
     * @param to        the recipient
     * @param from      the sender
     * @param subject   the subject of the mail
     * @param text      the text of the mail
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

        // Do not put any sensitive information below, not secure
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
