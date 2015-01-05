ALTER TABLE complaints ADD COLUMN product_id BIGINT NULL;
ALTER TABLE complaints ADD COLUMN sale_id BIGINT NULL;

ALTER TABLE ONLY complaints
ADD CONSTRAINT complaints_product_id_fkey FOREIGN KEY (product_id) REFERENCES products(id)
ON DELETE NO ACTION;

ALTER TABLE ONLY complaints
ADD CONSTRAINT complaints_sale_id_fkey FOREIGN KEY (sale_id) REFERENCES products(id)
ON DELETE NO ACTION;

-- DATA MIGRATION

CREATE OR REPLACE FUNCTION migrate_complaints() RETURNS void AS $$
DECLARE
  row_complaint  complaints%ROWTYPE;
  row_product    products%ROWTYPE;
  row_sale       sales%ROWTYPE;
  complaintid    bigint;
  item_id        character varying(40);
  complaint_type character varying(1);
BEGIN

  FOR row_complaint IN SELECT *
                       FROM complaints
  LOOP
    complaintid := row_complaint.id;
    item_id := row_complaint.item_id;
    complaint_type := row_complaint.complaint_type;

    IF complaint_type = 'P' THEN
      SELECT * INTO row_product FROM products p WHERE p.imei LIKE '%' || trim(BOTH ' ' from item_id) || '%';

      IF row_product IS NOT NULL THEN
        UPDATE complaints SET product_id = row_product.id WHERE id = complaintid;
      END IF;

      row_product := NULL;

    ELSIF complaint_type = 'S' THEN

      SELECT * INTO row_sale FROM sales s WHERE s.id = CAST(item_id as BIGINT);

        IF row_sale IS NOT NULL THEN
          UPDATE complaints SET sale_id = row_sale.id WHERE id = complaintid;
        END IF;

      row_sale := NULL;

    END IF;

  END LOOP;

END;
$$ LANGUAGE plpgsql;

SELECT migrate_complaints();
DROP FUNCTION migrate_complaints();

UPDATE complaints SET product_id = 2 WHERE item_id = '123456789000001';
UPDATE complaints SET product_id = 3 WHERE item_id = '123456789000002';
UPDATE complaints SET product_id = 4 WHERE item_id = '123456789000003';
UPDATE complaints SET product_id = 6 WHERE item_id = '123456789000005';


ALTER TABLE complaints DROP COLUMN item_id;
ALTER TABLE complaints DROP COLUMN contact_id;