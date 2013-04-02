
-- USERS

insert into users (id, email, password, created_at, session_id, session_validity, created_by) values (
    1, 'user1@gmail.com', 'rfaysdhaiufsiuf', now(), 'asdweasdweasdweasdweasdweasdwe21', now(), 1
);

insert into users (id, email, password, created_at, session_id, session_validity, created_by) values (
    2, 'user2@gmail.com', 'sdaysdhaiufsiua', now(), 'asdweasdweasdweasdweasdweasdwe21', now(), 1
);

insert into users (id, email, password, created_at, session_id, session_validity, created_by) values (
    3, 'boss@gmail.com', 'zwaysdhaiufsiko', now(), 'asdweasdweasdweasdweasdweasdwe21', now(), 1
);

insert into users (id, email, password, created_at, session_id, session_validity, created_by) values (
    4, 'manager@gmail.com', 'wertsdhnbgfsiko', now(), 'asdweasdweasdweasdweasdweasdwe21', now(), 1
);

-- STORES

insert into stores(id, label, created_at, created_by) values (
    1, 'cieszyn', now(), 1
);

insert into stores(id, label, created_at, created_by) values (
    2, 'raciborz', now(), 1
);


-- USERS TO STORES

insert into user_stores(id, user_id, store_id, created_at, created_by) values (
  1, 1, 1, now(), 1
);

insert into user_stores(id, user_id, store_id, created_at, created_by) values (
  2, 2, 1, now(), 1
);

insert into user_stores(id, user_id, store_id, created_at, created_by) values (
  3, 3, 1, now(), 1
);

insert into user_stores(id, user_id, store_id, created_at, created_by) values (
  4, 4, 1, now(), 1
);

insert into user_stores(id, user_id, store_id, created_at, created_by) values (
  5, 1, 2, now(), 1
);

insert into user_stores(id, user_id, store_id, created_at, created_by) values (
  6, 2, 2, now(), 1
);

-- ROLES

insert into roles(id, name, created_at, created_by) values (
    1, 'salesman', now(), 1
);

insert into roles(id, name, created_at, created_by) values (
    2, 'boss', now(), 1

);

insert into roles(id, name, created_at, created_by) values (
    3, 'shop_manager', now(), 1
);

-- USERS TO ROLES
insert into user_roles(id, user_id, role_id, created_at, created_by) values (
    1, 1, 1, now(), 1
);

insert into user_roles(id, user_id, role_id, created_at, created_by) values (
    2, 2, 2, now(), 1
);

insert into user_roles(id, user_id, role_id, created_at, created_by) values (
    3, 3, 3, now(), 1
);

insert into user_roles(id, user_id, role_id, created_at, created_by) values (
    4, 4, 1, now(), 1
);

-- CONTACTS

insert into contacts(id, label, details, created_at, created_by) values (
    1, 'adam', 'krotki opis', now(), 1
);

insert into contacts(id, label, details, created_at, created_by) values (
    2, 'leszek', 'krotki opis', now(), 1
);

insert into contacts(id, label, details, created_at, created_by) values (
    3, 'grzegorz', 'krotki opis', now(), 1
);

-- DELIVERIES

insert into deliveries(id, label, created_at, date_in, created_by, store_id, contact_id) values (
  1, 'nowy rok cieszyn 1', now(), now(), 1, 1, 1
);

insert into deliveries(id, label, created_at, date_in, created_by, store_id, contact_id) values (
  2, 'nowy rok cieszyn 2', now(), now(), 1, 1, 2
);

insert into deliveries(id, label, created_at, date_in, created_by, store_id, contact_id) values (
  3, 'nowy rok cieszyn 3', now(), now(), 1, 1, 2
);

insert into deliveries(id, label, created_at, date_in, created_by, store_id, contact_id) values (
  4, 'nowy rok raciborz 1', now(), now(), 1, 2, 2
);

insert into deliveries(id, label, created_at, date_in, created_by, store_id, contact_id) values (
  5, 'nowy rok raciborz 2', now(), now(), 1, 2, 3
);

-- SALES

insert into sales(id, label, date_out, created_at, created_by, store_id, contact_id) values(
    1, 'nowy rok cieszyn', now(), now(), 1, 1, 1
);

insert into sales(id, label, date_out, created_at, created_by, store_id, contact_id) values(
    2, 'nowy rok raciborz', now(), now(), 1, 2, 2
);

-- PRODUCTS

insert into products(id, imei, store_id, delivery_id, sale_id, producer, model, color, price_in, price_out, created_at, created_by) values
  (1, '123456789000000', 1, 1, 2, 'nokia', '6610s', 'black', 10000, NULL, now(), 1),
  (2, '123456789000001', 1, 1, NULL, 'nokia', '6610s', 'black', 10000, NULL, now(), 1),
  (3, '123456789000002', 1, 1, NULL, 'nokia', '6610s', 'white', 10000, NULL, now(), 1),
  (4, '123456789000003', 1, 1, NULL, 'nokia', '6610s', 'white', 10000, NULL, now(), 1),
  (5, '123456789000004', 1, 1, 2, 'nokia', '6600n', 'black', 10000, NULL, now(), 1),
  (6, '123456789000005', 1, 1, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1),
  (7, '123456789000006', 1, 1, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1),
  (8, '123456789000007', 1, 1, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1);

insert into products(id, imei, store_id, delivery_id, sale_id, producer, model, color, price_in, price_out, created_at, created_by) values
  (9, '123456789000010', 1, 2, 2, 'nokia', '3310', 'blue', 10000, NULL, now(), 1),
  (10, '123456789000011', 1, 2, NULL, 'nokia', '3310', 'blue', 10000, NULL, now(), 1),
  (11, '123456789000012', 1, 2, NULL, 'nokia', '3310', 'blue', 10000, NULL, now(), 1),
  (12, '123456789000013', 1, 2, NULL, 'nokia', '3310', 'blue', 10000, NULL, now(), 1),
  (13, '123456789000014', 1, 2, 2, 'nokia', '3310', 'red', 10000, NULL, now(), 1),
  (14, '123456789000015', 1, 2, NULL, 'nokia', '3310', 'red', 10000, NULL, now(), 1),
  (15, '123456789000016', 1, 2, NULL, 'nokia', '3310', 'red', 10000, NULL, now(), 1),
  (16, '123456789000017', 1, 2, NULL, 'nokia', '3310', 'red', 10000, NULL, now(), 1);

insert into products(id, imei, store_id, delivery_id, sale_id, producer, model, color, price_in, price_out, created_at, created_by) values
  (17, '123456789000020', 1, 3, 2, 'apple', 'iphone 4s', 'black', 10000, NULL, now(), 1),
  (18, '123456789000021', 1, 3, NULL, 'apple', 'iphone 4s', 'black', 10000, NULL, now(), 1),
  (19, '123456789000022', 1, 3, NULL, 'apple', 'iphone 4s', 'white', 10000, NULL, now(), 1),
  (20, '123456789000023', 1, 3, NULL, 'apple', 'iphone 4s', 'white', 10000, NULL, now(), 1),
  (21, '123456789000024', 1, 3, 2, 'apple', 'iphone 5g', 'black', 10000, NULL, now(), 1),
  (22, '123456789000025', 1, 3, NULL, 'apple', 'iphone 5g', 'black', 10000, NULL, now(), 1),
  (23, '123456789000026', 1, 3, NULL, 'apple', 'iphone 5g', 'white', 10000, NULL, now(), 1),
  (24, '123456789000027', 1, 3, NULL, 'apple', 'iphone 5g', 'white', 10000, NULL, now(), 1);

insert into products(id, imei, store_id, delivery_id, sale_id, producer, model, color, price_in, price_out, created_at, created_by) values
  (25, '123456789000030', 2, 4, 1, 'nokia', '6610s', 'black', 10000, NULL, now(), 1),
  (26, '123456789000031', 2, 4, NULL, 'nokia', '6610s', 'black', 10000, NULL, now(), 1),
  (27, '123456789000032', 2, 4, NULL, 'nokia', '6610s', 'white', 10000, NULL, now(), 1),
  (28, '123456789000033', 2, 4, NULL, 'nokia', '6610s', 'white', 10000, NULL, now(), 1),
  (29, '123456789000034', 2, 4, 1, 'nokia', '6600n', 'black', 10000, NULL, now(), 1),
  (30, '123456789000035', 2, 4, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1),
  (31, '123456789000036', 2, 4, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1),
  (32, '123456789000037', 2, 4, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1);

insert into products(id, imei, store_id, delivery_id, sale_id, producer, model, color, price_in, price_out, created_at, created_by) values
  (33, '123456789000040', 2, 5, 1, 'apple', 'iphone 4s', 'black', 10000, NULL, now(), 1),
  (34, '123456789000041', 2, 5, NULL, 'apple', 'iphone 4s', 'black', 10000, NULL, now(), 1),
  (35, '123456789000042', 2, 5, NULL, 'apple', 'iphone 4s', 'white', 10000, NULL, now(), 1),
  (36, '123456789000043', 2, 5, NULL, 'apple', 'iphone 4s', 'white', 10000, NULL, now(), 1),
  (37, '123456789000044', 2, 5, 1, 'apple', 'iphone 3g', 'black', 10000, NULL, now(), 1),
  (38, '123456789000045', 2, 5, NULL, 'apple', 'iphone 3g', 'black', 10000, NULL, now(), 1),
  (39, '123456789000046', 2, 5, NULL, 'apple', 'iphone 3g', 'white', 10000, NULL, now(), 1),
  (40, '123456789000047', 2, 5, NULL, 'apple', 'iphone 3g', 'white', 10000, NULL, now(), 1);






