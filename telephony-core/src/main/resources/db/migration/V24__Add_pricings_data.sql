DROP FUNCTION migrate_models_and_producers();
DROP FUNCTION migrate_pricing();

CREATE OR REPLACE FUNCTION migrate_pricing() RETURNS void AS $$
DECLARE
    tmp_priceIn float8;
    row_product products%ROWTYPE;
    id bigint;
    
BEGIN
    
    FOR row_product IN SELECT * FROM products
    LOOP
       id := row_product.id;       
       tmp_priceIn := row_product.price_in;
              
       UPDATE pricings p SET rate = (tmp_priceIn * 1.2) WHERE p.product_id = product_id;
       
    END LOOP;

END;
$$ LANGUAGE plpgsql;

SELECT migrate_pricing();
DROP FUNCTION migrate_pricing();
