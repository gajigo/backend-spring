-- liquibase formatted sql

-- changeset helcio:1662567187515-23
ALTER TABLE role
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-24
ALTER TABLE role
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-25
ALTER TABLE cities
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-26
ALTER TABLE cities
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-27
ALTER TABLE countries
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-28
ALTER TABLE countries
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-29
ALTER TABLE documents
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-30
ALTER TABLE documents
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-31
ALTER TABLE events
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-32
ALTER TABLE events
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-33
ALTER TABLE languages
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-34
ALTER TABLE languages
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-35
ALTER TABLE lectures
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-36
ALTER TABLE lectures
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-37
ALTER TABLE rooms
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-38
ALTER TABLE rooms
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-39
ALTER TABLE states
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-40
ALTER TABLE states
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-41
ALTER TABLE tags
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-42
ALTER TABLE tags
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-43
ALTER TABLE users
    ADD active BOOLEAN DEFAULT TRUE;

-- changeset helcio:1662567187515-44
ALTER TABLE users
    ALTER COLUMN active SET NOT NULL;

-- changeset helcio:1662567187515-45
ALTER TABLE cities
    DROP COLUMN remove_date;
ALTER TABLE cities
    DROP COLUMN removed;

-- changeset helcio:1662567187515-46
ALTER TABLE countries
    DROP COLUMN remove_date;
ALTER TABLE countries
    DROP COLUMN removed;

-- changeset helcio:1662567187515-47
ALTER TABLE documents
    DROP COLUMN remove_date;
ALTER TABLE documents
    DROP COLUMN removed;

-- changeset helcio:1662567187515-48
ALTER TABLE events
    DROP COLUMN remove_date;
ALTER TABLE events
    DROP COLUMN removed;

-- changeset helcio:1662567187515-49
ALTER TABLE languages
    DROP COLUMN remove_date;
ALTER TABLE languages
    DROP COLUMN removed;

-- changeset helcio:1662567187515-50
ALTER TABLE lectures
    DROP COLUMN remove_date;
ALTER TABLE lectures
    DROP COLUMN removed;

-- changeset helcio:1662567187515-51
ALTER TABLE role
    DROP COLUMN remove_date;
ALTER TABLE role
    DROP COLUMN removed;

-- changeset helcio:1662567187515-52
ALTER TABLE rooms
    DROP COLUMN remove_date;
ALTER TABLE rooms
    DROP COLUMN removed;

-- changeset helcio:1662567187515-53
ALTER TABLE states
    DROP COLUMN remove_date;
ALTER TABLE states
    DROP COLUMN removed;

-- changeset helcio:1662567187515-54
ALTER TABLE tags
    DROP COLUMN remove_date;
ALTER TABLE tags
    DROP COLUMN removed;

-- changeset helcio:1662567187515-55
ALTER TABLE users
    DROP COLUMN remove_date;
ALTER TABLE users
    DROP COLUMN removed;

-- changeset helcio:1662567187515-2
ALTER TABLE role
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-4
ALTER TABLE cities
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-6
ALTER TABLE countries
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-8
ALTER TABLE documents
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-10
ALTER TABLE events
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-12
ALTER TABLE languages
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-14
ALTER TABLE lectures
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-16
ALTER TABLE rooms
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-18
ALTER TABLE states
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-20
ALTER TABLE tags
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

-- changeset helcio:1662567187515-22
ALTER TABLE users
    ADD removed TIMESTAMP WITHOUT TIME ZONE;

