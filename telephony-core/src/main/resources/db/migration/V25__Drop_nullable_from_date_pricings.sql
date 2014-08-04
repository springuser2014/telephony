ALTER TABLE pricings ALTER COLUMN from_date SET NOT NULL;

ALTER TABLE pricings DROP CONSTRAINT pricings_product_id_fkey;

ALTER TABLE pricings ADD CONSTRAINT pricings_product_id_fkey FOREIGN KEY (product_id)
REFERENCES products (id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE CASCADE;
