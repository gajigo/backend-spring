ALTER TABLE events
    DROP COLUMN location_id;

ALTER TABLE events
    ADD COLUMN location VARCHAR(255);