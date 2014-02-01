CREATE TABLE producers (
	id bigint NOT NULL,
	label character varying(100) NOT NULL
);

CREATE TABLE models (
	id bigint NOT NULL,
	label character varying(100) NOT NULL,
	producer_id bigint NOT NULL
);


CREATE SEQUENCE  producers_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
SELECT pg_catalog.setval('producers_seq', 1, false);
    
CREATE SEQUENCE  models_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT pg_catalog.setval('models_seq', 1, false);
    
ALTER TABLE producers OWNER TO postgres;
ALTER TABLE models OWNER TO postgres;

-- PRIMARY KEYS 

ALTER TABLE ONLY  producers
    ADD CONSTRAINT producers_pkey PRIMARY KEY (id);
    
ALTER TABLE ONLY  models
    ADD CONSTRAINT models_pkey PRIMARY KEY (id);

-- UNIQUER CONSTRAINTS

ALTER TABLE ONLY  producers
    ADD CONSTRAINT producers_label_key UNIQUE (label);
    
ALTER TABLE ONLY  models
    ADD CONSTRAINT models_label_key UNIQUE (label);

-- FOREIGN KEYS 

ALTER TABLE ONLY  models
    ADD CONSTRAINT models_producer_id_fkey FOREIGN KEY (producer_id) REFERENCES producers(id);
    