-- FOREIGN KEYS 

ALTER TABLE ONLY  models
	ADD CONSTRAINT models_producer_id_fkey FOREIGN KEY (producer_id) REFERENCES producers(id);
    