ALTER TABLE products 
ADD COLUMN model_id bigint NULL;

ALTER TABLE ONLY products
	ADD CONSTRAINT products_model_id_fkey FOREIGN KEY (model_id) REFERENCES models(id);

