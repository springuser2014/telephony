CREATE OR REPLACE FUNCTION add_taxes() RETURNS void AS $$
DECLARE
    
    product_id bigint;
    product_row products%ROWTYPE;
    
    id1 bigint;
    id2 bigint;
    id3 bigint;
    id4 bigint;
    id5 bigint;
    id6 bigint;
    id7 bigint;
BEGIN
    
    id1 := nextval('taxes_seq');
	INSERT INTO taxes (id, from_date, to_date, rate) 
	VALUES ( id1, null, date '2000-01-01' , 15.0 );
	
	id2 := nextval('taxes_seq');
	INSERT INTO taxes (id, from_date, to_date, rate) 
	VALUES ( id2, date '2000-01-01', date '2010-01-01' , 15.0 );
	
	id3 := nextval('taxes_seq');
	INSERT INTO taxes (id, from_date, to_date, rate) 
	VALUES ( id3, date '2010-01-01', date '2012-01-01' , 19.0 );
	
	id4 := nextval('taxes_seq');
	INSERT INTO taxes (id, from_date, to_date, rate) 
	VALUES ( id4, date '2010-01-01', date '2013-01-01' , 21.0 );
	
	id5 := nextval('taxes_seq');
	INSERT INTO taxes (id, from_date, to_date, rate) 
	VALUES ( id5, date '2012-01-01', date '2015-01-01' , 22.0 );
	
	id6 := nextval('taxes_seq');
	INSERT INTO taxes (id, from_date, to_date, rate) 
	VALUES ( id6, date '2013-01-01', date '2015-01-01' , 23.0 );
	
	id7 := nextval('taxes_seq');
	INSERT INTO taxes (id, from_date, to_date, rate) 
    VALUES ( id7, date '2015-01-01', null, 25.0 );
    
    FOR product_row IN SELECT * FROM products
    WHERE 1 = 1
    LOOP
    
        product_id := product_row.id;
        
        IF product_id <= 8 THEN
        
	        INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
	        VALUES (product_id, id2, date '2005-01-01', date '2010-01-01');
	        
            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id3, date '2010-01-01', date '2011-01-01');

            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id4, date '2011-01-01', date '2012-01-01');

            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id5, date '2012-01-01', date '2014-01-01');
	        
            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id6, date '2014-01-01', null);
                   
	    ELSIF product_id <= 16 THEN
	    
            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id4, date '2011-01-01', date '2012-01-01');

            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id5, date '2012-01-01', date '2014-01-01');
            
            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id6, date '2014-01-01', null);
        
	    ELSIF product_id <= 24 THEN

            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id5, date '2012-07-01', date '2014-01-01');
            
            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id6, date '2014-01-01', null);
        
	    ELSIF product_id <= 32 THEN
	    
            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id5, date '2013-07-01', date '2014-01-01');
            
            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id6, date '2014-01-01', null);
                
	    ELSIF product_id <= 40 THEN
        
            INSERT INTO product_taxes (product_id, tax_id, from_date, to_date)
            VALUES (product_id, id6, date '2014-01-01', null);
        
        END IF;
    
    END LOOP;
    RETURN;    

END;
$$ LANGUAGE plpgsql;

SELECT add_taxes();
