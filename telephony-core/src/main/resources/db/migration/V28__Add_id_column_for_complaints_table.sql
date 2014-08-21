ALTER TABLE complaints ADD COLUMN item_id character varying(40) NOT NULL;

ALTER TABLE complaint_comments DROP CONSTRAINT complaint_comments_complaint_id_fkey,
ADD CONSTRAINT complaint_comments_complaint_id_fkey FOREIGN KEY (complaint_id)
REFERENCES complaints(id)
ON DELETE CASCADE;