ALTER TABLE product_taxes DROP CONSTRAINT product_taxes_product_id_fkey, 
ADD CONSTRAINT product_taxes_product_id_fkey FOREIGN KEY (product_id)
REFERENCES products(id) MATCH SIMPLE
ON UPDATE NO ACTION ON DELETE CASCADE;
    

ALTER TABLE pricings ADD CHECK (from_date < to_date);
ALTER TABLE product_taxes ADD CHECK (from_date < to_date);
ALTER TABLE taxes ADD CHECK (from_date < to_date);