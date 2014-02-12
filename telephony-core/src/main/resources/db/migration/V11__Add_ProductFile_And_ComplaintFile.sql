

CREATE TABLE product_files (
	product_id bigint NOT NULL,
	file_id bigint NOT NULL
);

CREATE TABLE complaint_files (
	complaint_id bigint NOT NULL,
	file_id bigint NOT NULL	
);
    
ALTER TABLE product_files OWNER TO postgres;
ALTER TABLE complaint_files OWNER TO postgres;

-- FOREIGN KEYS 

ALTER TABLE ONLY product_files
    ADD CONSTRAINT product_files_product_id_fkey FOREIGN KEY (product_id) REFERENCES products(id);
    
ALTER TABLE ONLY complaint_files
    ADD CONSTRAINT complaint_files_complaint_id_fkey FOREIGN KEY (complaint_id) REFERENCES complaints(id);
    
ALTER TABLE ONLY product_files
    ADD CONSTRAINT product_files_id_fkey FOREIGN KEY (file_id) REFERENCES files(id);
       
ALTER TABLE ONLY complaint_files
    ADD CONSTRAINT product_files_id_fkey FOREIGN KEY (file_id) REFERENCES files(id);
    
