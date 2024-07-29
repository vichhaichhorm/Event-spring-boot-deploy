CREATE DATABASE event_ticketing_system_db;

CREATE TABLE IF NOT EXISTS venues
(
    venue_id   SERIAL PRIMARY KEY,
    venue_name VARCHAR(250) NOT NULL,
    location   VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS events
(
    event_id   SERIAL PRIMARY KEY,
    event_name VARCHAR(250) NOT NULL,
    event_date TIMESTAMP    NOT NULL,
    venue_id   INT          NOT NULL,
    CONSTRAINT venue_fk FOREIGN KEY (venue_id) REFERENCES venues (venue_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS attendees
(
    attendee_id   SERIAL PRIMARY KEY,
    attendee_name VARCHAR(250) NOT NULL,
    email         VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS event_attendee
(
    id          SERIAL PRIMARY KEY,
    event_id    INT NOT NULL,
    attendee_id INT NOT NULL,
    CONSTRAINT event_fk FOREIGN KEY (event_id) REFERENCES events (event_id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT attendee_fk FOREIGN KEY (attendee_id) REFERENCES attendees (attendee_id) ON DELETE CASCADE ON UPDATE CASCADE
);