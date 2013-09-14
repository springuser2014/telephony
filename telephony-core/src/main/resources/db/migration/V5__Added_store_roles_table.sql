SET statement_timeout = 0;
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;


CREATE TABLE  store_roles (
    id bigint NOT NULL,
    store_id bigint NOT NULL,
    role_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL    
);

ALTER TABLE store_roles OWNER TO postgres;

CREATE SEQUENCE store_roles_seq
	START WITH 1
	INCREMENT BY 1
	NO MAXVALUE
	NO MINVALUE
	CACHE 1;
	
ALTER TABLE store_roles_seq OWNER TO postgres;

SELECT pg_catalog.setval('store_roles_seq', 1, false);

ALTER TABLE ONLY store_roles
	ADD CONSTRAINT store_roles_pkey PRIMARY KEY (id);
	
ALTER TABLE ONLY store_roles
	ADD CONSTRAINT store_roles_store_id_fkey FOREIGN KEY (store_id) REFERENCES stores(id);
	
ALTER TABLE ONLY store_roles
	ADD CONSTRAINT store_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES roles(id);
	
	
REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
