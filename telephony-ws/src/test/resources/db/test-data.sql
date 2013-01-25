
-- USERS

insert into users values (
    1, 'user1@gmail.com', 'rfaysdhaiufsiuf', now(), 'asdweasdweasdweasdweasdweasdwe21', now(), 1, NULL, NULL
--  nextval('users_seq'), 'pawelhenek@gmail.com', 'rfaysdhaiufsiuf', now(), 1, NULL, NULL
);

insert into users values (
    2, 'user2@gmail.com', 'sdaysdhaiufsiua', now(), 'asdweasdweasdweasdweasdweasdwe21', now(), 1, NULL, NULL
--  nextval('users_seq'), 'andrzejhenek@gmail.com', 'sdaysdhaiufsiua', now(), 1, NULL, NULL
);

insert into users values (
    3, 'boss@gmail.com', 'zwaysdhaiufsiko', now(), 'asdweasdweasdweasdweasdweasdwe21', now(), 1, NULL, NULL
-- nextval('users_seq'), 'roberthenek@gmail.com', 'zwaysdhaiufsiko', now(), 1, NULL, NULL
);

insert into users values (
    4, 'manager@gmail.com', 'wertsdhnbgfsiko', now(), 'asdweasdweasdweasdweasdweasdwe21', now(), 1, NULL, NULL
-- nextval('users_seq'), 'jolantahenek@gmail.com', 'wertsdhnbgfsiko', now(), 1, NULL, NULL
);

-- STORES

insert into stores values (
    1, 'cieszyn', now(), 1, NULL, NULL
-- nextval('stores_seq'), 'cieszyn', now(), 1, NULL, NULL
);

insert into stores values (
    2, 'raciborz', now(), 1, NULL, NULL
-- nextval('stores_seq'), 'raciborz', now(), 1, NULL, NULL
);


-- USERS TO STORES

insert into user_stores values (
  1, 1, 1, now(), 1, NULL, NULL
-- nextval('user_stores_seq'), 1, 1, now(), 1, NULL, NULL
);

insert into user_stores values (
  2, 2, 1, now(), 1, NULL, NULL
-- nextval('user_stores_seq'), 2, 1, now(), 1, NULL, NULL
);

insert into user_stores values (
  3, 3, 1, now(), 1, NULL, NULL
-- nextval('user_stores_seq'), 3, 1, now(), 1, NULL, NULL
);

insert into user_stores values (
  4, 4, 1, now(), 1, NULL, NULL
-- nextval('user_stores_seq'), 4, 1, now(), 1, NULL, NULL
);

insert into user_stores values (
  5, 1, 2, now(), 1, NULL, NULL
-- nextval('user_stores_seq'), 1, 2, now(), 1, NULL, NULL
);

insert into user_stores values (
  6, 2, 2, now(), 1, NULL, NULL
-- nextval('user_stores_seq'), 2, 2, now(), 1, NULL, NULL
);

-- ROLES

insert into roles values (
    1, 'salesman', now(), 1, NULL, NULL
-- nextval('roles_seq'), 'salesman', now(), 1, NULL, NULL
);

insert into roles values (
    2, 'boss', now(), 1, NULL, NULL
-- nextval('roles_seq'), 'boss', now(), 1, NULL, NULL

);

insert into roles values (
    3, 'shop_manager', now(), 1, NULL, NULL
-- nextval('roles_seq'), 'shop_manager', now(), 1, NULL, NULL
);

-- USERS TO ROLES
insert into user_roles values (
    1, 1, 1, now(), 1, NULL, NULL
-- nextval('user_roles_seq'), 1, 1, now(), 1, NULL, NULL
);

insert into user_roles values (
    2, 2, 2, now(), 1, NULL, NULL
-- nextval('user_roles_seq'), 2, 2, now(), 1, NULL, NULL
);

insert into user_roles values (
    3, 3, 3, now(), 1, NULL, NULL
-- nextval('user_roles_seq'), 3, 3, now(), 1, NULL, NULL
);

insert into user_roles values (
    4, 4, 1, now(), 1, NULL, NULL
-- nextval('user_roles_seq'), 4, 1, now(), 1, NULL, NULL
);

-- CONTACTS

insert into contacts values (
    1, 'adam', 'krotki opis', now(), 1, NULL, NULL
-- nextval('contacts_seq'), 'adam', 'krotki opis', now(), 1, NULL, NULL
);

insert into contacts values (
    2, 'leszek', 'krotki opis', now(), 1, NULL, NULL
-- nextval('contacts_seq'), 'contacts', 'krotki opis', now(), 1, NULL, NULL
);

insert into contacts values (
    3, 'grzegorz', 'krotki opis', now(), 1, NULL, NULL
-- nextval('contacts_seq'), 'grzegorz', 'krotki opis', now(), 1, NULL, NULL
);

-- DELIVERIES

insert into deliveries values (
  1, 'nowy rok cieszyn 1', now(), now(), 1, NULL, NULL, 1, 1
-- nextval('deliveries_seq'), 'nowy rok cieszyn 1', now(), now(), 1, NULL, NULL, 1, 1
);

insert into deliveries values (
  2, 'nowy rok cieszyn 2', now(), now(), 1, NULL, NULL, 1, 2
-- nextval('deliveries_seq'), 'nowy rok cieszyn 2', now(), now(), 1, NULL, NULL, 1, 1
);

insert into deliveries values (
  3, 'nowy rok cieszyn 3', now(), now(), 1, NULL, NULL, 1, 2
-- nextval('deliveries_seq'), 'nowy rok cieszyn 3', now(), now(), 1, NULL, NULL, 1, 2
);

insert into deliveries values (
  4, 'nowy rok raciborz 1', now(), now(), 1, NULL, NULL, 2, 2
-- nextval('deliveries_seq'), 'nowy rok raciborz 1', now(), now(), 1, NULL, NULL, 1, 2
);

insert into deliveries values (
  5, 'nowy rok raciborz 2', now(), now(), 1, NULL, NULL, 2, 3
-- nextval('deliveries_seq'), 'nowy rok raciborz 2', now(), now(), 1, NULL, NULL, 1, 2
);

-- SALES

insert into sales values (
    1, 'nowy rok cieszyn', now(), now(), 1, NULL, NULL, 1, 1
);

insert into sales values (
    2, 'nowy rok raciborz', now(), now(), 1, NULL, NULL, 2, 2
);

-- PRODUCTS

insert into products values
  (1, '123456789000000', 1, 1, 2, 'nokia', '6610s', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (2, '123456789000001', 1, 1, NULL, 'nokia', '6610s', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (3, '123456789000002', 1, 1, NULL, 'nokia', '6610s', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (4, '123456789000003', 1, 1, NULL, 'nokia', '6610s', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (5, '123456789000004', 1, 1, 2, 'nokia', '6600n', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (6, '123456789000005', 1, 1, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (7, '123456789000006', 1, 1, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (8, '123456789000007', 1, 1, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1, NULL, NULL);

insert into products values
  (9, '123456789000010', 1, 2, 2, 'nokia', '3310', 'blue', 10000, NULL, now(), 1, NULL, NULL),
  (10, '123456789000011', 1, 2, NULL, 'nokia', '3310', 'blue', 10000, NULL, now(), 1, NULL, NULL),
  (11, '123456789000012', 1, 2, NULL, 'nokia', '3310', 'blue', 10000, NULL, now(), 1, NULL, NULL),
  (12, '123456789000013', 1, 2, NULL, 'nokia', '3310', 'blue', 10000, NULL, now(), 1, NULL, NULL),
  (13, '123456789000014', 1, 2, 2, 'nokia', '3310', 'red', 10000, NULL, now(), 1, NULL, NULL),
  (14, '123456789000015', 1, 2, NULL, 'nokia', '3310', 'red', 10000, NULL, now(), 1, NULL, NULL),
  (15, '123456789000016', 1, 2, NULL, 'nokia', '3310', 'red', 10000, NULL, now(), 1, NULL, NULL),
  (16, '123456789000017', 1, 2, NULL, 'nokia', '3310', 'red', 10000, NULL, now(), 1, NULL, NULL);

insert into products values
  (17, '123456789000020', 1, 3, 2, 'apple', 'iphone 4s', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (18, '123456789000021', 1, 3, NULL, 'apple', 'iphone 4s', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (19, '123456789000022', 1, 3, NULL, 'apple', 'iphone 4s', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (20, '123456789000023', 1, 3, NULL, 'apple', 'iphone 4s', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (21, '123456789000024', 1, 3, 2, 'apple', 'iphone 5g', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (22, '123456789000025', 1, 3, NULL, 'apple', 'iphone 5g', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (23, '123456789000026', 1, 3, NULL, 'apple', 'iphone 5g', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (24, '123456789000027', 1, 3, NULL, 'apple', 'iphone 5g', 'white', 10000, NULL, now(), 1, NULL, NULL);

insert into products values
  (25, '123456789000030', 2, 4, 1, 'nokia', '6610s', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (26, '123456789000031', 2, 4, NULL, 'nokia', '6610s', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (27, '123456789000032', 2, 4, NULL, 'nokia', '6610s', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (28, '123456789000033', 2, 4, NULL, 'nokia', '6610s', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (29, '123456789000034', 2, 4, 1, 'nokia', '6600n', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (30, '123456789000035', 2, 4, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (31, '123456789000036', 2, 4, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (32, '123456789000037', 2, 4, NULL, 'nokia', '6600n', 'black', 10000, NULL, now(), 1, NULL, NULL);

insert into products values
  (33, '123456789000040', 2, 5, 1, 'apple', 'iphone 4s', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (34, '123456789000041', 2, 5, NULL, 'apple', 'iphone 4s', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (35, '123456789000042', 2, 5, NULL, 'apple', 'iphone 4s', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (36, '123456789000043', 2, 5, NULL, 'apple', 'iphone 4s', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (37, '123456789000044', 2, 5, 1, 'apple', 'iphone 3g', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (38, '123456789000045', 2, 5, NULL, 'apple', 'iphone 3g', 'black', 10000, NULL, now(), 1, NULL, NULL),
  (39, '123456789000046', 2, 5, NULL, 'apple', 'iphone 3g', 'white', 10000, NULL, now(), 1, NULL, NULL),
  (40, '123456789000047', 2, 5, NULL, 'apple', 'iphone 3g', 'white', 10000, NULL, now(), 1, NULL, NULL);







