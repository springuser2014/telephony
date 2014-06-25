ALTER TABLE pricings DROP COLUMN rate;
ALTER TABLE taxes DROP COLUMN rate;

ALTER TABLE pricings ADD COLUMN rate float8 not null;
ALTER TABLE taxes ADD COLUMN rate float8 not null;