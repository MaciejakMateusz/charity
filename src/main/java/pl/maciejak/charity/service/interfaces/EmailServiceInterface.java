package pl.maciejak.charity.service.interfaces;

public interface EmailServiceInterface {
    void sendSimpleMessage(
            String to, String subject, String text);
}
