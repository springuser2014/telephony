ALTER TABLE products DROP COLUMN model;
ALTER TABLE products DROP COLUMN producer;

ALTER TABLE taxes ALTER COLUMN from_date DROP NOT NULL;
ALTER TABLE taxes ALTER COLUMN to_date DROP NOT NULL;


ALTER TABLE product_taxes ALTER COLUMN from_date DROP NOT NULL;
ALTER TABLE product_taxes ALTER COLUMN to_date DROP NOT NULL;