-- liquibase formatted sql

-- changeset magal:1663029341519-1
ALTER TABLE users
    ADD profile_image VARCHAR(255);

