package spring.boot.optic.okulist.service.emailsender;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.boot.optic.okulist.exception.MessageSenderException;
import spring.boot.optic.okulist.model.Order;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Value("${mail.smtp.host}")
    private String smtpHost;
    @Value("${mail.smtp.port}")
    private int smtpPort;
    @Value("${mail.smtp.auth}")
    private String auth;
    @Value("${mail.smtp.starttls.enable}")
    private String starttlsEnable;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;

    @Override
    public void sendStatusChangeEmail(String userEmail, Order.Status newStatus) {
        try {
            Session session = prepareSession();
            MimeMessage message = createMessage(session, userEmail, newStatus);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new MessageSenderException("Error sending email....");
        }
    }

    private Session prepareSession() {
        Properties properties = prepareProperties();
        return Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    private Properties prepareProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", auth);
        properties.put("mail.smtp.starttls.enable", starttlsEnable);
        return properties;
    }

    private MimeMessage createMessage(Session session, String userEmail, Order.Status newStatus)
            throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSubject("Order Status Change ! ");
        Multipart multipart = createMultipart(newStatus);
        message.setContent(multipart);
        return message;
    }

    private Multipart createMultipart(Order.Status newStatus)
            throws MessagingException {
        Multipart multipart = new MimeMultipart();
        addTextPart(multipart, newStatus);
        return multipart;
    }

    private void addTextPart(Multipart multipart, Order.Status newStatus)
            throws MessagingException {
        BodyPart textPart = new MimeBodyPart();
        String emailText = "The status of your order has been changed to : " + newStatus;
        textPart.setText(emailText);
        multipart.addBodyPart(textPart);
    }

    @Override
    public void sendVerificationCodeEmail(String userEmail, String verificationCode) {
        try {
            Session session = prepareSession();
            MimeMessage message = createVerificationCodeMessage(session,
                    userEmail,
                    verificationCode);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new MessageSenderException("Error sending email....");
        }
    }

    private MimeMessage createVerificationCodeMessage(Session session,
                                                      String userEmail,
                                                      String verificationCode)
            throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSubject("Verification Code");
        Multipart multipart = createVerificationCodeMultipart(verificationCode);
        message.setContent(multipart);
        return message;
    }

    private Multipart createVerificationCodeMultipart(String verificationCode)
            throws MessagingException {
        Multipart multipart = new MimeMultipart();
        addVerificationCodeTextPart(multipart, verificationCode);
        return multipart;
    }

    private void addVerificationCodeTextPart(Multipart multipart, String verificationCode)
            throws MessagingException {
        BodyPart textPart = new MimeBodyPart();
        String emailText = "Your verification code is: " + verificationCode;
        textPart.setText(emailText);
        multipart.addBodyPart(textPart);
    }

    @Override
    public void sendPasswordChangeConfirmation(String userEmail) {
        try {
            Session session = prepareSession();
            MimeMessage message = createPasswordChangeConfirmationMessage(session, userEmail);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new MessageSenderException("Error sending email....");
        }
    }

    private MimeMessage createPasswordChangeConfirmationMessage(Session session, String userEmail)
            throws MessagingException {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));
        message.setSubject("Password Change Confirmation");
        Multipart multipart = createPasswordChangeConfirmationMultipart();
        message.setContent(multipart);
        return message;
    }

    private Multipart createPasswordChangeConfirmationMultipart()
            throws MessagingException {
        Multipart multipart = new MimeMultipart();
        addPasswordChangeConfirmationTextPart(multipart);
        return multipart;
    }

    private void addPasswordChangeConfirmationTextPart(Multipart multipart)
            throws MessagingException {
        BodyPart textPart = new MimeBodyPart();
        String emailText = "Your password has been successfully changed.";
        textPart.setText(emailText);
        multipart.addBodyPart(textPart);
    }

    public void sendOrderProcessingEmail(String userEmail, Order.Status status) {
        try {
            Session session = prepareSession();
            MimeMessage message = createMessage(session, userEmail, Order.Status.PROCESSING);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new MessageSenderException("Error sending email....");
        }
    }
}
