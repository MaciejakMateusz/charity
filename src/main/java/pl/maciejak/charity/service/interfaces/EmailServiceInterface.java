package pl.maciejak.charity.service.interfaces;

public interface EmailServiceInterface {
    void passwordRecovery(String to);

    void contactForm(String from, String subject, String text);
}
