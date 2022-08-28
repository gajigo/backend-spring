-- liquibase formatted sql

-- changeset helci:280820221803-1
INSERT INTO users
    (created, updated, description, name, admin, email, password, username)
VALUES (now(), now(), '', 'Admin', true, 'admin@gajigo.com', '', 'admin');