package spring.boot.optic.okulist.service.emailsender;

import spring.boot.optic.okulist.model.Order;

public interface EmailService {
    void sendStatusChangeEmail(String userEmail, Order.Status newStatus);

    void sendVerificationCodeEmail(String userEmail, String verificationCode);

    void sendPasswordChangeConfirmation(String userEmail);

    void sendOrderProcessingEmail(String userEmail, Order.Status status);
}
