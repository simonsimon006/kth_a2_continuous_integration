package kth_a2_continuous_integration;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


public class EmailSenderTest {
    EmailSender emailSender;

    @BeforeEach
	void setUp() {
		emailSender = new EmailSender();
	}

    /**
	 * Tests sendEmail to return true when mail and password is correct
     * and the recipient exits.
	 * 
	 * @return Needs to evaluate to true.
	 */
    @Test
    void sendEmailPositiveTest() {
        String to = "oscols@kth.se";
        String from = "dd2480group19@gmail.com";
        String subject = "Sending email";
        String text = "TESTING TESTING";
        assertTrue(emailSender.sendEmail(to, from, subject, text), "sendEmail did not return true when it should");
    }

    /**
	 * Tests sendEmail to return false when mail and password is correct
     * but the recipient does NOT exits.
	 * 
	 * @return Needs to evaluate to false.
	 */
    @Test
    void sendEmailNegativeTest() {
        String to = "dd2480aaaaaa@gmail.com";
        String from = "dd2480group19@gmail.com";
        String subject = "Sending email";
        String text = "TESTING TESTING";
        assertTrue(emailSender.sendEmail(to, from, subject, text), "sendEmail did not return false when it should");
    }
}