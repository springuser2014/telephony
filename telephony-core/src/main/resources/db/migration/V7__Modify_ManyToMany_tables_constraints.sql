
-- REMOVE OLD FK CONSTRAINTS FOR MANY-TO-MANY RELATIONS

ALTER TABLE user_roles DROP CONSTRAINT user_roles_user_id_fkey ;

ALTER TABLE user_roles DROP CONSTRAINT user_roles_role_id_fkey ;

ALTER TABLE user_stores DROP CONSTRAINT user_stores_store_id_fkey ;

ALTER TABLE user_stores DROP CONSTRAINT user_stores_user_id_fkey ;

ALTER TABLE store_roles DROP CONSTRAINT store_roles_store_id_fkey ;

ALTER TABLE store_roles DROP CONSTRAINT store_roles_role_id_fkey ;

-- SET UP NEW CONSTRAINTS 

ALTER TABLE ONLY  user_roles
    ADD CONSTRAINT user_roles_user_id_fkey 
    FOREIGN KEY (user_id) REFERENCES users(id)
    ON UPDATE CASCADE ON DELETE CASCADE
    ;

ALTER TABLE ONLY  user_roles
    ADD CONSTRAINT user_roles_role_id_fkey 
    FOREIGN KEY (role_id) REFERENCES roles(id)
    ON UPDATE CASCADE ON DELETE CASCADE
    ;

ALTER TABLE ONLY  user_stores
    ADD CONSTRAINT user_stores_store_id_fkey 
    FOREIGN KEY (store_id) REFERENCES stores(id)
    ON UPDATE CASCADE ON DELETE CASCADE
    ;

ALTER TABLE ONLY  user_stores
    ADD CONSTRAINT user_stores_user_id_fkey 
    FOREIGN KEY (user_id) REFERENCES users(id)
    ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE ONLY store_roles
	ADD CONSTRAINT store_roles_store_id_fkey 
	FOREIGN KEY (store_id) REFERENCES stores(id)
	ON UPDATE CASCADE ON DELETE CASCADE;
	
ALTER TABLE ONLY store_roles
	ADD CONSTRAINT store_roles_role_id_fkey 
	FOREIGN KEY (role_id) REFERENCES roles(id)
	ON UPDATE CASCADE ON DELETE CASCADE
	;
	
--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
