CREATE DATABASE charity_donation;
USE charity_donation;

INSERT INTO charity_donation.roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO charity_donation.roles (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO charity_donation.institutions (name, description) VALUE ('Fundacja "Dbam o Zdrowie"', 'Pomoc dzieciom z ubogich rodzin.');
INSERT INTO charity_donation.institutions (name, description) VALUE ('Fundacja "A kogo"', 'Pomoc w wybudzaniu dzieci ze śpiączki.');
INSERT INTO charity_donation.institutions (name, description) VALUE ('Fundacja "Dla dzieci"', 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej.');
INSERT INTO charity_donation.institutions (name, description) VALUE ('Fundacja "Bez domu"', 'Pomoc dla osób nie posiadających miejsca zamieszkania.');

INSERT INTO charity_donation.categories (name) VALUE ('ubrania, które nadają się do ponownego użycia');
INSERT INTO charity_donation.categories (name) VALUE ('ubrania do wyrzucenia');
INSERT INTO charity_donation.categories (name) VALUE ('zabawki');
INSERT INTO charity_donation.categories (name) VALUE ('książki');
INSERT INTO charity_donation.categories (name) VALUE ('inne');