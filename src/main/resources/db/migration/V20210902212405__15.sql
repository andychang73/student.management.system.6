ALTER TABLE homework RENAME COLUMN homework TO questions;
ALTER TABLE homework ADD COLUMN answers JSON NOT NULL AFTER questions;