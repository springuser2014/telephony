SET statement_timeout = 0;
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

ALTER TABLE deliveries DROP COLUMN deleted_by;
ALTER TABLE deliveries DROP COLUMN deleted_at;


ALTER TABLE  products DROP COLUMN deleted_by;
ALTER TABLE  products  DROP COLUMN deleted_at;


ALTER TABLE  sales DROP COLUMN deleted_by;
ALTER TABLE  sales DROP COLUMN deleted_at;


ALTER TABLE  stores DROP COLUMN deleted_by;
ALTER TABLE  stores DROP COLUMN deleted_at;


ALTER TABLE  roles DROP COLUMN deleted_by;
ALTER TABLE  roles DROP COLUMN deleted_at;


ALTER TABLE  user_roles DROP COLUMN deleted_by;
ALTER TABLE  user_roles DROP COLUMN deleted_at;


ALTER TABLE  user_stores DROP COLUMN deleted_by;
ALTER TABLE  user_stores DROP COLUMN deleted_at;


ALTER TABLE  contacts DROP COLUMN deleted_by;
ALTER TABLE  contacts DROP COLUMN deleted_at;

-- remove system_logs

DROP TABLE  system_logs;

-- remove system_log_seq

DROP SEQUENCE  system_logs_seq;