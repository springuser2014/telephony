
CREATE OR REPLACE FUNCTION migrate_pricing() RETURNS void AS $$
DECLARE
    row_product products%ROWTYPE;
    row_delivery deliveries%ROWTYPE;
    product_id_ bigint;
    delivery_id bigint;
BEGIN
    
    FOR row_product IN SELECT * FROM products
    LOOP
       product_id_ := row_product.id;       
       delivery_id := row_product.delivery_id;
       
       SELECT * INTO row_delivery FROM deliveries WHERE id = delivery_id;
       
       --INSERT INTO pricings (id, product_id, from_date, to_date, rate)
       --VALUES (nextval('pricings_seq'), product_id, null, null, tmp_priceOut/100);
       
       UPDATE pricings p SET from_date = row_delivery.date_in WHERE p.product_id = product_id_;
       
       IF delivery_id = 1 THEN       

          UPDATE pricings p SET to_date = (row_delivery.date_in + interval '3 year')  
          WHERE p.product_id = product_id_;
          
          INSERT INTO pricings (id, product_id, from_date, to_date, rate)
          VALUES (nextval('pricings_seq'), product_id_, (row_delivery.date_in + interval '3 year'), (row_delivery.date_in + interval '5 year'), 130.0);
          
          INSERT INTO pricings (id, product_id, from_date, to_date, rate)
          VALUES (nextval('pricings_seq'), product_id_, (row_delivery.date_in + interval '5 year'), (row_delivery.date_in + interval '7 year'), 140.0);

          INSERT INTO pricings (id, product_id, from_date, to_date, rate)
          VALUES (nextval('pricings_seq'), product_id_, (row_delivery.date_in + interval '7 year'), (row_delivery.date_in + interval '9 year'), 145.0);

          INSERT INTO pricings (id, product_id, from_date, to_date, rate)
          VALUES (nextval('pricings_seq'), product_id_, (row_delivery.date_in + interval '9 year'), null, 145.0);

       ELSIF delivery_id = 2 THEN
       
          UPDATE pricings p SET to_date = (row_delivery.date_in + interval '2 year')  
          WHERE p.product_id = product_id_;
       
          INSERT INTO pricings (id, product_id, from_date, to_date, rate)
          VALUES (nextval('pricings_seq'), product_id_, (row_delivery.date_in + interval '2 year'), null, 130.0);
                    
       ELSIF delivery_id = 3 THEN
       
          UPDATE pricings p SET to_date = (row_delivery.date_in + interval '1 year')  
          WHERE p.product_id = product_id_;
             
          INSERT INTO pricings (id, product_id, from_date, to_date, rate)
          VALUES (nextval('pricings_seq'), product_id_, (row_delivery.date_in + interval '1 year'), (row_delivery.date_in + interval '2 year'), 140.0);
                 
          INSERT INTO pricings (id, product_id, from_date, to_date, rate)
          VALUES (nextval('pricings_seq'), product_id_, (row_delivery.date_in + interval '2 year'), null, 150.0);
       
       ELSIF delivery_id = 4 THEN
       
          UPDATE pricings p SET to_date = (row_delivery.date_in + interval '1 year')  
          WHERE p.product_id = product_id_;
                 
          INSERT INTO pricings (id, product_id, from_date, to_date, rate)
          VALUES (nextval('pricings_seq'), product_id_, (row_delivery.date_in + interval '1 year'), null, 145.0);
       
       ELSIF delivery_id = 5 THEN
       
          UPDATE pricings p SET to_date = (row_delivery.date_in + interval '6 months')  
          WHERE p.product_id = product_id_;
       
          INSERT INTO pricings (id, product_id, from_date, to_date, rate)
          VALUES (nextval('pricings_seq'), product_id_, (row_delivery.date_in + interval '6 months'), null, 155.0);
          
       END IF;
               
    END LOOP;

END;
$$ LANGUAGE plpgsql;

SELECT migrate_pricing();
DROP FUNCTION migrate_pricing();
