
-- CONTACTS REMOVING FKs RULES  

ALTER TABLE deliveries DROP CONSTRAINT deliveries_contact_id_fkey;

ALTER TABLE ONLY  deliveries
    ADD CONSTRAINT deliveries_contact_id_fkey 
    FOREIGN KEY (contact_id) REFERENCES contacts(id)
    ON UPDATE SET NULL 
    ON DELETE SET NULL;
    
ALTER TABLE ONLY deliveries ALTER COLUMN contact_id DROP NOT NULL;
	

ALTER TABLE sales DROP CONSTRAINT sales_contact_id_fkey;

ALTER TABLE ONLY  sales
    ADD CONSTRAINT sales_contact_id_fkey 
    FOREIGN KEY (contact_id) REFERENCES contacts(id)
    ON UPDATE SET NULL
    ON DELETE SET NULL;
    
    ALTER TABLE ONLY sales ALTER COLUMN contact_id DROP NOT NULL;

-- SALES , DELIVERIES REMOVING FKs RULES

ALTER TABLE products DROP CONSTRAINT 
	products_delivery_id_fkey;
	
ALTER TABLE ONLY  products
    ADD CONSTRAINT products_delivery_id_fkey 
    FOREIGN KEY (delivery_id) REFERENCES deliveries(id)
    ON UPDATE SET NULL 
    ON DELETE SET NULL;
    
ALTER TABLE ONLY products ALTER COLUMN delivery_id DROP NOT NULL;


ALTER TABLE products DROP CONSTRAINT 
	products_sale_id_fkey;
	
ALTER TABLE ONLY  products
    ADD CONSTRAINT products_sale_id_fkey 
    FOREIGN KEY (sale_id) REFERENCES sales(id)
    ON UPDATE SET NULL 
    ON DELETE SET NULL;
    
ALTER TABLE ONLY products ALTER COLUMN sale_id DROP NOT NULL;
    
    
REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
