-- CONTACTS
ALTER TABLE contacts DROP column created_at;
ALTER TABLE contacts DROP column created_by;

-- DELIVERIES
ALTER TABLE deliveries DROP column created_at;
ALTER TABLE deliveries DROP column created_by;

-- PRODUCTS 
ALTER TABLE products DROP column created_at;
ALTER TABLE products DROP column created_by;

-- ROLES 
ALTER TABLE roles DROP column created_at;
ALTER TABLE roles DROP column created_by;

-- SALES
ALTER TABLE sales DROP column created_at;
ALTER TABLE sales DROP column created_by;

-- STORES
ALTER TABLE stores DROP column created_at;
ALTER TABLE stores DROP column created_by;

-- USERS
ALTER TABLE users DROP column created_at;
ALTER TABLE users DROP column created_by;

-- USER_ROLES
ALTER TABLE user_roles DROP column id;
ALTER TABLE user_roles DROP column created_at;
ALTER TABLE user_roles DROP column created_by;

CREATE UNIQUE INDEX user_roles_unique ON user_roles(role_id, user_id);


-- USER_STORES
ALTER TABLE user_stores DROP column id;
ALTER TABLE user_stores DROP column created_at;
ALTER TABLE user_stores DROP column created_by;

CREATE UNIQUE INDEX user_stores_unique ON user_stores(store_id, user_id);


-- STORE_ROLES
ALTER TABLE store_roles DROP column id;
ALTER TABLE store_roles DROP column created_at;
ALTER TABLE store_roles DROP column created_by;

CREATE UNIQUE INDEX store_roles_unique ON store_roles(store_id, role_id);


-- TODO : add drop sequences