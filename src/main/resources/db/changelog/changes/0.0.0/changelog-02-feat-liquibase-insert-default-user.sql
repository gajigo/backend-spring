-- liquibase formatted sql

-- changeset helci:280820221803-1
INSERT INTO users
    (created, updated, description, name, admin, email, password, username)
VALUES (now(), now(), '', 'Admin', true, 'admin@gajigo.com', '{bcrypt}$2a$10$bJltVLK.iz6unVBxFoaWZ.1s7zwzyx3rZu61idDpDU7MXHfk77XlK', 'admin');