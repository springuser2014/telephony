
SET statement_timeout = 0;
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE  test_entity_table (
    id bigint NOT NULL,
    label character varying(100),
    reported_date timestamp without time zone NOT NULL
);


ALTER TABLE  test_entity_table OWNER TO postgres;


CREATE SEQUENCE  test_entity_table_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


SELECT pg_catalog.setval('test_entity_table_seq', 1, false);
    
ALTER TABLE  test_entity_table_seq OWNER TO postgres;


ALTER TABLE ONLY  test_entity_table
    ADD CONSTRAINT test_entity_table_pkey PRIMARY KEY (id);

INSERT INTO test_entity_table VALUES 
( nextval ('public.test_entity_table_seq'),'pierwszy', timestamp '2000-01-01 00:00:00' );


INSERT INTO test_entity_table VALUES 
( nextval ('public.test_entity_table_seq'),'drugi', timestamp '2000-01-01 00:00:00' );


INSERT INTO test_entity_table VALUES 
( nextval ('public.test_entity_table_seq'),'trzeci', timestamp '2000-01-01 00:00:00' );
