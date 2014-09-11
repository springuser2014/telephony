INSERT INTO product_taxes (id, product_id, tax_id, from_date, to_date) VALUES
( nextval('product_taxes_seq'), 41, 5, date '2013-07-01', null );

INSERT INTO product_taxes (id, product_id, tax_id, from_date, to_date) VALUES
( nextval('product_taxes_seq'), 42, 5, date '2013-07-01', null );

INSERT INTO pricings (id, product_id, rate, from_date, to_date) VALUES
( nextval('pricings_seq'), 41, 100, date '2013-07-01', null );

INSERT INTO pricings (id, product_id, rate, from_date, to_date) VALUES
( nextval('pricings_seq'), 42, 100, date '2013-07-01', null);