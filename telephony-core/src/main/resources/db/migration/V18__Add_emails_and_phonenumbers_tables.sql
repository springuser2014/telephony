CREATE TABLE emails (
    contact_id bigint NOT NULL,
    content character varying(100) NOT NULL
);

CREATE TABLE phonenumbers (
    contact_id bigint NOT NULL,
    content character varying(100) NOT NULL
);

ALTER TABLE ONLY  phonenumbers
    ADD CONSTRAINT phonenumbers_contact_id_fkey FOREIGN KEY (contact_id) REFERENCES contacts(id);
    
ALTER TABLE ONLY  emails
    ADD CONSTRAINT emails_contact_id_fkey FOREIGN KEY (contact_id) REFERENCES contacts(id);

    