
ALTER TABLE contacts ADD COLUMN address_line1 VARCHAR(100) NOT NULL DEFAULT ' ';
ALTER TABLE contacts ADD COLUMN address_line2 VARCHAR(100) NOT NULL DEFAULT ' ';
ALTER TABLE contacts ADD COLUMN zip_code VARCHAR(10) NOT NULL DEFAULT ' ';
ALTER TABLE contacts ADD COLUMN city VARCHAR(20) NOT NULL DEFAULT ' ';
ALTER TABLE contacts ADD COLUMN country VARCHAR(3) NOT NULL DEFAULT ' ';

ALTER TABLE phonenumbers ADD COLUMN prefix VARCHAR (10) NOT NULL DEFAULT '+48';

CREATE TABLE faxes (
  contact_id bigint NOT NULL,
  content character varying(100) NOT NULL
);

ALTER TABLE ONLY faxes
ADD CONSTRAINT faxes_contact_id_fkey FOREIGN KEY (contact_id) REFERENCES contacts(id)
ON DELETE CASCADE;

