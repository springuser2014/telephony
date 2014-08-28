ALTER TABLE product_taxes ADD COLUMN id bigint NOT NULL DEFAULT 0;

CREATE SEQUENCE product_taxes_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE product_taxes_seq OWNER TO postgres;

SELECT pg_catalog.setval('product_taxes_seq', 1, false);

CREATE OR REPLACE FUNCTION migrate_product_taxes() RETURNS void AS $$
DECLARE
    _id bigint;
    _product_id bigint;
    _tax_id bigint;
    _from_date timestamp;
    _to_date timestamp;
    
    row_pt product_taxes%ROWTYPE;
BEGIN
    
    FOR row_pt IN SELECT * FROM product_taxes
    LOOP        
        _product_id := row_pt.product_id;
        _tax_id := row_pt.tax_id;
        _from_date := row_pt.from_date;
        _to_date := row_pt.to_date;
        
        _id := nextval('product_taxes_seq');
        
        IF 
            _to_date IS NOT NULL
        THEN
        
	        UPDATE product_taxes SET id = _id 
	            WHERE product_id = _product_id 
	            AND tax_id = _tax_id
	            AND from_date = _from_date
	            AND to_date = _to_date;
	    ELSIF
	       _to_date IS NULL
	    THEN
	    
            UPDATE product_taxes SET id = _id 
                WHERE product_id = _product_id 
                AND tax_id = _tax_id
                AND from_date = _from_date
                AND to_date IS NULL;
        
        END IF;
    END LOOP;
    
END;
$$ LANGUAGE plpgsql;

SELECT migrate_product_taxes();

ALTER TABLE product_taxes ADD PRIMARY KEY (id);