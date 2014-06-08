ALTER TABLE complaint_comments ADD COLUMN reported_date timestamp without time zone NOT NULL;
ALTER TABLE complaints DROP COLUMN user_unique_hash;
ALTER TABLE complaints RENAME COLUMN reporter_unique_hash TO unique_hash; 
ALTER TABLE complaints ADD COLUMN title character varying(100) NOT NULL ; 
ALTER TABLE complaints DROP COLUMN complaint_type;
ALTER TABLE complaints ADD COLUMN contact_id bigint NOT NULL;

ALTER TABLE ONLY complaints
    ADD CONSTRAINT complaints_contact_id_fkey FOREIGN KEY (contact_id) REFERENCES contacts(id);
