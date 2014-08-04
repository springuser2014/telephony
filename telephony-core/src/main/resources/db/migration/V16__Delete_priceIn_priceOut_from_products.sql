ALTER TABLE pricings ALTER COLUMN from_date DROP NOT NULL;
ALTER TABLE pricings ALTER COLUMN to_date DROP NOT NULL;

ALTER TABLE products ADD COLUMN price float8;

CREATE OR REPLACE FUNCTION migrate_pricing() RETURNS void AS $$
DECLARE
    tmp_priceIn bigint;
    tmp_priceOut bigint;
    row_product products%ROWTYPE;
    product_id bigint;
    
BEGIN
	
	FOR row_product IN SELECT * FROM products
	LOOP
	   product_id := row_product.id;	   
	   tmp_priceOut := row_product.price_out;
	   tmp_priceIn := row_product.price_in;
	   
	   IF tmp_priceOut IS NULL THEN
	       tmp_priceOut := 0;
	   END IF;
	   
	   INSERT INTO pricings (id, product_id, from_date, to_date, rate)
	   VALUES (nextval('pricings_seq'), product_id, null, null, tmp_priceOut/100);
	   
	   UPDATE products SET price = ( tmp_priceIn / 100 ) WHERE id = product_id;
	   
    END LOOP;

END;
$$ LANGUAGE plpgsql;

SELECT migrate_pricing();

ALTER TABLE products DROP column price_out;
ALTER TABLE products DROP column price_in;
ALTER TABLE products RENAME column price TO price_in;
ALTER TABLE products ALTER COLUMN price_in SET NOT NULL;