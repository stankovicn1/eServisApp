package Glavni;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {

    public static void sendEmail(String to, String subject, String messageText) {
        // Outlook email i lozinka
        String from = "diplomskiop2@outlook.com";
        String password = "op2diplomski";  // Tvoja lozinka ili lozinka aplikacije ako koristiš dvostepenu autentifikaciju

        // Podešavanje Properties za SMTP server Outlook-a
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp-mail.outlook.com");  // Outlook SMTP server
        properties.put("mail.smtp.port", "587");  // Port za TLS
        properties.put("mail.smtp.auth", "true");  // Autentifikacija
        properties.put("mail.smtp.starttls.enable", "true");  // Omogućavanje TLS

        // Kreiranje sesije sa autentifikacijom
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Kreiranje poruke
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(messageText);

            // Slanje poruke
            Transport.send(message);
            System.out.println("Email uspešno poslat!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
