CREATE DATABASE charity_donation;
USE charity_donation;

INSERT INTO charity_donation.roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO charity_donation.roles (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO charity_donation.institutions (name, description) VALUES ('"Dbam o Zdrowie"', 'Pomoc dzieciom z ubogich rodzin.');
INSERT INTO charity_donation.institutions (name, description) VALUES ('"A kogo"', 'Pomoc w wybudzaniu dzieci ze śpiączki.');
INSERT INTO charity_donation.institutions (name, description) VALUES ('"Dla dzieci"', 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej.');
INSERT INTO charity_donation.institutions (name, description) VALUES ('"Bez domu"', 'Pomoc dla osób nie posiadających miejsca zamieszkania.');

INSERT INTO charity_donation.categories (name) VALUES ('ubrania, które nadają się do ponownego użycia');
INSERT INTO charity_donation.categories (name) VALUES ('ubrania do wyrzucenia');
INSERT INTO charity_donation.categories (name) VALUES ('zabawki');
INSERT INTO charity_donation.categories (name) VALUES ('książki');
INSERT INTO charity_donation.categories (name) VALUES ('inne');