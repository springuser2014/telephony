SET statement_timeout = 0;
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

INSERT INTO store_roles (id, store_id, role_id, created_at, created_by)
values (1, 1, 1, now(), 1) ;

INSERT INTO store_roles (id, store_id, role_id, created_at, created_by)
values (2, 1, 2, now(), 1) ;

INSERT INTO store_roles (id, store_id, role_id, created_at, created_by)
values (3, 2, 2, now(), 1) ;

INSERT INTO store_roles (id, store_id, role_id, created_at, created_by)
values (4, 2, 3, now(), 1) ;

SELECT pg_catalog.setval('store_roles_seq', 5, false);


REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;