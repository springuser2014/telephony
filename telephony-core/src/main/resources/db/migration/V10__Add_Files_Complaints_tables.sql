

CREATE TABLE files (
	id bigint NOT NULL,
	label character varying(100) NOT NULL,
	filename character varying(200) NOT NULL,
	mime_type character varying(100) NOT NULL,
	size_in_bytes bigint,
	content bytea NOT NULL
);

CREATE TABLE complaints (
	id bigint NOT NULL,
	complaint_type char(2) NOT NULL, 
	description text NOT NULL	
);


CREATE SEQUENCE  files_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;
    
SELECT pg_catalog.setval('files_seq', 1, false);
    
CREATE SEQUENCE  complaints_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

SELECT pg_catalog.setval('complaints_seq', 1, false);
    
ALTER TABLE complaints OWNER TO postgres;
ALTER TABLE files OWNER TO postgres;

-- PRIMARY KEYS 

ALTER TABLE ONLY  complaints
    ADD CONSTRAINT complaints_pkey PRIMARY KEY (id);
    
ALTER TABLE ONLY  files
    ADD CONSTRAINT files_pkey PRIMARY KEY (id);

-- UNIQUER CONSTRAINTS

ALTER TABLE ONLY  files
    ADD CONSTRAINT file_label_key UNIQUE (label);
    