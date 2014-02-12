

CREATE TABLE taxes (
	id bigint NOT NULL,
	rate NUMERIC(2) NOT NULL,
	from_date timestamp without time zone NOT NULL,
	to_date timestamp without time zone NOT NULL
);

CREATE TABLE pricings (
	id bigint NOT NULL,
	product_id bigint NOT NULL,
	rate NUMERIC(2) NOT NULL,
	from_date timestamp without time zone NOT NULL,
	to_date timestamp without time zone NOT NULL
);


CREATE TABLE product_taxes (
	product_id bigint NOT NULL,
	tax_id bigint NOT NULL,	
	from_date timestamp without time zone NOT NULL,
	to_date timestamp without time zone NOT NULL
);

ALTER TABLE taxes OWNER TO postgres;
ALTER TABLE pricings OWNER TO postgres;
ALTER TABLE product_taxes OWNER TO postgres;
 
CREATE SEQUENCE  taxes_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE  taxes_seq OWNER TO postgres;

SELECT pg_catalog.setval('taxes_seq', 1, false);
 
CREATE SEQUENCE  pricings_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE  pricings_seq OWNER TO postgres;

SELECT pg_catalog.setval('pricings_seq', 1, false);

-- PRIMARY KEYS 

ALTER TABLE ONLY  taxes
    ADD CONSTRAINT taxes_pkey PRIMARY KEY (id);
    
ALTER TABLE ONLY  pricings
    ADD CONSTRAINT pricings_pkey PRIMARY KEY (id);
   
    
-- ADD FOREIGN KEYS

ALTER TABLE ONLY product_taxes
    ADD CONSTRAINT product_taxes_product_id_fkey FOREIGN KEY (product_id) REFERENCES products(id);
    
ALTER TABLE ONLY product_taxes
    ADD CONSTRAINT product_taxes_tax_id_fkey FOREIGN KEY (tax_id) REFERENCES taxes(id);
    
ALTER TABLE ONLY pricings
	ADD CONSTRAINT pricings_product_id_fkey FOREIGN KEY (product_id) REFERENCES products(id);
    