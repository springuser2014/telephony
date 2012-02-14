--
-- dane testowe do tabeli uzytkownicy
--

insert into users (
  id, version, email, password,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) VALUES

  (1, 0, 'pawelhenek@gmail.com', 'asdasdQWasdasdQW',
  CURRENT_DATE, 1,
  null, null,
  null, null
);
--
insert into users (
  id, version, email, password,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) VALUES

  (2, 0, 'pawel.henek@gmail.com', 'wertyQWEwertyQW',
  CURRENT_DATE, 1,
  null, null,
  null, null
);
--
insert into users (
  id, version, email, password,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) VALUES

  (3, 0, 'pablisso@gmail.com', 'yuio1234yuio1234',
  CURRENT_DATE, 1,
  null, null,
  null, null
);
--
insert into users (
  id, version, email, password,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) VALUES

  (4, 0, 'phenek@gmail.com', 'rtyuio24rtyuio24',
  CURRENT_DATE, 1,
  null, null,
  null, null
);
--
insert into users (
  id, version, email, password,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) VALUES

  (5, 0, 'pablohenek@gmail.com', 'TYUIOP23TYUIOP23',
  CURRENT_DATE, 1,
  null, null,
  CURRENT_DATE, 1
);

--
-- dane testowe do tabeli uzytkownicy
--
insert into user_roles (
  id, version, role_name,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values

  (1, 1, 'admin',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

insert into user_roles (
  id, version, role_name,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values

  (2, 1, 'pracownik',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

insert into user_roles (
  id, version, role_name,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values

  (3, 1, 'handlarz',
   CURRENT_DATE, 1,
   null, null,
   null, null
);
--
-- dane testowe do tabeli roli użytkowników w systemie
--

-- uzytkownik 1 ma wszystkie uprawnienia
insert into role_to_user (id, version, user_id, user_role_id)
values (1, 0, 1, 1);

insert into role_to_user (id, version, user_id, user_role_id)
values (2, 0, 1, 2);

insert into role_to_user (id, version, user_id, user_role_id)
values (3, 0, 1, 3);

-- uzytkownik 2 ma wszystkie uprawnienia
insert into role_to_user (id, version, user_id, user_role_id)
values (4, 0, 2, 1);

insert into role_to_user (id, version, user_id, user_role_id)
values (5, 0, 2, 2);

insert into role_to_user (id, version, user_id, user_role_id)
values (6, 0, 2, 3);

-- uzytkownik 3 ma dwa uprawnienia
insert into role_to_user (id, version, user_id, user_role_id)
values (7, 0, 3, 1);

insert into role_to_user (id, version, user_id, user_role_id)
values (8, 0, 3, 2);

-- uzytkownik 4 ma dwa uprawnienia
insert into role_to_user (id, version, user_id, user_role_id)
values (9, 0, 4, 2);

insert into role_to_user (id, version, user_id, user_role_id)
values (10, 0, 4, 3);

-- uzytkownik 5 ma jedno uprawnienie
insert into role_to_user (id, version, user_id, user_role_id)
values (11, 0, 5, 2);

--
-- dane testowe do tabeli ze sklepami (magazynami)
--
insert into stores (
  id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values

  (1, 1, 'katowice',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

insert into stores (
  id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values

  (2, 1, 'warszawa',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

insert into stores (
  id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values

  (3, 1, 'gubin',
   CURRENT_DATE, 1,
   null, null,
   CURRENT_DATE, 1
);

insert into stores (
  id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values

  (4, 1, 'suszec',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

--
-- dane testowe do tabeli ze sklepami (magazynami)
--

insert into user_to_store (id, version, user_id, store_id ) values
 (1, 0, 1, 1),
 (2, 0, 1, 2),
 (3, 0, 1, 3),
 (4, 0, 1, 4),

 (5, 0, 2, 1),
 (6, 0, 2, 2),
 (7, 0, 2, 3),
 (8, 0, 2, 4),

 (9, 0, 3, 1),
 (10, 0, 3, 2),
 (11, 0, 3, 3),

 (12, 0, 4, 2),
 (13, 0, 4, 3),
 (14, 0, 4, 4),

 (15, 0, 5, 3),
 (16, 0, 5, 4);

--
-- dane testowe do tabeli dostaw
--

insert into deliveries
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    1, 1, 'styczen 11',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

insert into deliveries
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    2, 1, 'styczen 12',
   CURRENT_DATE, 4,
   null, null,
   null, null
);


insert into deliveries
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    3, 1, 'styczen 13',
   CURRENT_DATE, 2,
   null, null,
   null, null
);

insert into deliveries
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    4, 1, 'styczen 13',
   CURRENT_DATE, 2,
   null, null,
   null, null
);

insert into deliveries
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    5, 1, 'styczen 20',
   CURRENT_DATE, 3,
   null, null,
   null, null
);

insert into deliveries
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    6, 1, 'styczen 31',
   CURRENT_DATE, 3,
   null, null,
   null, null
);

insert into deliveries
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    7, 1, 'luty 1',
   CURRENT_DATE, 4,
   null, null,
   null, null
);

insert into deliveries
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    8, 1, 'styczen 11',
   CURRENT_DATE, 2,
   null, null,
   null, null
);

--
--  dane testowe do tabeli sprzedazy
--

insert into sales
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    1, 1, 'Sprzedaz do Wawy - styczen',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

insert into sales
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    2, 1, 'Sprzedaz do Wawy - marzec',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

insert into sales
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    3, 1, 'Sprzedaz do Katowic ',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

insert into sales
  (id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values
  (
    4, 1, 'Sprzedaz do Szczecina',
   CURRENT_DATE, 1,
   null, null,
   null, null
);

--
-- dane testowe do tabeli produktów !
--

insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(1 , 1, '123456789000000', 1, 1, 'bialy,', null, 'nokia', 'n500',
 1000, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (2 , 1, '123456789000001', 1, 1, 'czarny,', null, 'nokia', 'n500',
 1000, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (3 , 1, '123456789000002', 1, 1, 'bialy,', null, 'nokia', 'n500',
 1000, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (4 , 1, '123456789000003', 1, 1, 'bialy,', null, 'nokia', 'n500',
 1000, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (5 , 1, '123456789000004', 1, 1, 'bialy,', null, 'nokia', 'n500',
 1000, 1200, CURRENT_DATE, 2, null, null, null, null );


insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(6 , 1, '123456789000005', 1, 2, 'bialy,', null, 'nokia', 'm600',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (7 , 1, '123456789000006', 1, 2, 'czarny,', null, 'nokia', 'm600',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (8 , 1, '123456789000007', 1, 2, 'bialy,', null, 'nokia', 'n500',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (9 , 1, '123456789000008', 1, 2, 'bialy,', 1, 'nokia', 'm600',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (10 , 1, '123456789000009', 1, 2, 'bialy,', 1, 'nokia', 'm600',
 950, 1200, CURRENT_DATE, 2, null, null, null, null );


insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(11 , 1, '123456789000011', 2, 3, 'bialy,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null ),
 (12 , 1, '123456789000012', 2, 3, 'czarny,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null ),
 (13 , 1, '123456789000013', 4, 3, 'bialy,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null ),
 (14 , 1, '123456789000014', 4, 3, 'bialy,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null ),
 (15 , 1, '123456789000015', 4, 3, 'bialy,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null );

insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(16 , 1, '123456789000016', 2, 4, 'bialy,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null ),
 (17 , 1, '123456789000017', 2, 4, 'czarny,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null ),
 (18 , 1, '123456789000018', 4, 4, 'bialy,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null ),
 (19 , 1, '123456789000019', 4, 4, 'bialy,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null ),
 (20 , 1, '123456789000020', 4, 4, 'bialy,', 2, 'samsung', 'galaxy s',
 1950, 2400, CURRENT_DATE, 2, null, null, null, null );

insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(21 , 1, '123456789000021', 2, 5, 'bialy,', 3, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (22 , 1, '123456789000022', 2, 5, 'czarny,', 3, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (23 , 1, '123456789000023', 4, 5, 'bialy,', 3, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (24 , 1, '123456789000024', 4, 5, 'bialy,', 3, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (25 , 1, '123456789000025', 4, 5, 'bialy,', 3, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null );

insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(26 , 1, '123456789000026', 2, 6, 'bialy,', null, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 2, null, null, null, null ),
 (27 , 1, '123456789000027', 2, 6, 'czarny,', null, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 2, null, null, null, null ),
 (28 , 1, '123456789000028', 4, 6, 'bialy,', null, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 2, null, null, null, null ),
 (29 , 1, '123456789000029', 4, 6, 'bialy,', null, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 2, null, null, null, null ),
 (30 , 1, '123456789000030', 4, 6, 'bialy,', null, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 2, null, null, null, null );


insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(31 , 1, '123456789000031', 2, 7, 'bialy,', 4, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (32 , 1, '123456789000032', 2, 7, 'czarny,', 4, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (33 , 1, '123456789000033', 4, 7, 'bialy,', 4, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (34 , 1, '123456789000034', 4, 7, 'bialy,', null, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null ),
 (35 , 1, '123456789000035', 4, 7, 'bialy,', null, 'samsung', 'galaxy s',
 950, 1200, CURRENT_DATE, 2, null, null, null, null );

insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(36 , 1, '123456789000036', 2, 7, 'bialy,', 4, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 4, null, null, null, null ),
 (37 , 1, '123456789000037', 2, 7, 'czarny,', 4, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 4, null, null, null, null ),
 (38 , 1, '123456789000038', 4, 7, 'bialy,', null, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 4, null, null, null, null ),
 (39 , 1, '123456789000039', 4, 7, 'bialy,', null, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 4, null, null, null, null ),
 (40 , 1, '123456789000040', 4, 7, 'bialy,', null, 'samsung', 'ace',
 500, 800, CURRENT_DATE, 4, null, null, null, null );


insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(41 , 1, '123456789000041', 2, 8, 'bialy,', 4, 'sony', 'vaio',
 950, 1200, CURRENT_DATE, 5, null, null, null, null ),
 (42 , 1, '123456789000042', 2, 8, 'czarny,', 4, 'sony', 'vaio',
 950, 1200, CURRENT_DATE, 5, null, null, null, null ),
 (43 , 1, '123456789000043', 4, 8, 'bialy,', 4, 'sony', 'vaio',
 950, 1200, CURRENT_DATE, 5, null, null, null, null ),
 (44 , 1, '123456789000044', 4, 8, 'bialy,', null, 'sony', 'vaio',
 950, 1200, CURRENT_DATE, 5, null, null, null, null ),
 (45 , 1, '123456789000045', 4, 8, 'bialy,', null, 'sony', 'vaio',
 950, 1200, CURRENT_DATE, 5, null, null, null, null );

insert into products
(id, version, imei , store_id, delivery_id, color, sale_id, producer, model,
price_in, price_out, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by )
values
(46 , 1, '123456789000046', 2, 8, 'bialy,', 4, 'sony', 'vaio 2',
 500, 800, CURRENT_DATE, 5, null, null, null, null ),
 (47 , 1, '123456789000047', 2, 8, 'czarny,', 4, 'sony', 'vaio 2',
 500, 800, CURRENT_DATE, 5, null, null, null, null ),
 (48 , 1, '123456789000048', 4, 8, 'bialy,', null, 'sony', 'vaio 2',
 500, 800, CURRENT_DATE, 5, null, null, null, null ),
 (49 , 1, '123456789000049', 4, 8, 'bialy,', null, 'sony', 'vaio 2',
 500, 800, CURRENT_DATE, 5, null, null, null, null ),
 (50 , 1, '123456789000050', 4, 8, 'bialy,', null, 'sony', 'vaio 2',
 500, 800, CURRENT_DATE, 5, null, null, null, null );


