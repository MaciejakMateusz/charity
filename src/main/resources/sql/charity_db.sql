CREATE DATABASE charity_donation;
USE charity_donation;

INSERT INTO charity_donation.roles (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO charity_donation.roles (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO charity_donation.institutions (name, description) VALUE ('Dbam o Zdrowie', 'Pomoc dzieciom z ubogich rodzin.');
INSERT INTO charity_donation.institutions (name, description) VALUE ('A kogo', 'Pomoc wybudzaniu dzieci ze śpiączki.');
INSERT INTO charity_donation.institutions (name, description) VALUE ('Dla dzieci', 'Pomoc osobom znajdującym się w trudnej sytuacji życiowej.');
INSERT INTO charity_donation.institutions (name, description) VALUE ('Bez domu', 'Pomoc dla osób nie posiadających miejsca zamieszkania.');