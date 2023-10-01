package pl.maciejak.charity.service.interfaces;

public interface EmailServiceInterface {
    void sendMessage(String to, String subject, String text);

    void contactForm(String from, String subject, String text);
}
