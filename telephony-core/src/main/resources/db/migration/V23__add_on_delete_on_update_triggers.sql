ALTER TABLE product_taxes DROP CONSTRAINT product_taxes_tax_id_fkey;

ALTER TABLE product_taxes ADD CONSTRAINT   product_taxes_tax_id_fkey
FOREIGN KEY (tax_id) REFERENCES taxes(id) ON DELETE CASCADE;

