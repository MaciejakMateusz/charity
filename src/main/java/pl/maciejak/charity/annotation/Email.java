package pl.maciejak.charity.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import pl.maciejak.charity.annotation.validator.EmailValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    String message() default "Niepoprawny format adresu email";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
