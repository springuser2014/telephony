/*

ALTER TABLE wtf ADD COLUMN regex character varying(200);

CREATE TABLE public.smsp (
    id bigint PRIMARY KEY NOT NULL,
    tx integer NOT NULL DEFAULT 0,
    create_time timestamp without time zone NOT NULL,
	sender_msisdn character varying(9) NOT NULL
) WITHOUT OIDS;

CREATE SEQUENCE public.smsp_seq START WITH 1 INCREMENT BY 1 NO MAXVALUE NO MINVALUE CACHE 1;

powyżej trochę przykładowego kodu

 */
--------------------------------------------


-- Użytkownicy
create table users (
  id bigint primary key not null,
  version int default 0,
  email varchar (100) not null,
  password varchar(32),

  created_at timestamp not null,
  created_by bigint not null,

  edited_at timestamp null,
  edited_by bigint null,

  deleted_at timestamp null,
  deleted_by bigint null,

  UNIQUE (email)
);

-- Uprawnienia użytkowników
create table user_roles (

  id bigint primary key not null,
  version int default 0,
  role_name varchar(20) not null,

  created_at timestamp not null,
  created_by bigint not null,

  edited_at timestamp null,
  edited_by bigint null,

  deleted_at timestamp null,
  deleted_by bigint null,

  UNIQUE (role_name)
);

-- Połączenie użytkowników z rolami

create table role_to_user (

  id bigint primary key not null,

  user_id        bigint not null references users(id) ,
  user_role_id   bigint not null references user_roles(id)

--   created_at timestamp not null,
--   created_by id bigint not null,
--
--   edited_at timestamp null,
--   edited_by bigint null,
--
--   deleted_at timestamp not null,
--   deleted_by id bigint null
);


-- Sklepy/magazyny
create table stores (

  id bigint primary key not null,
  version int default 0,

  label text not null,

  created_at timestamp not null,
  created_by bigint not null,

  edited_at timestamp null,
  edited_by bigint null,

  deleted_at timestamp null,
  deleted_by bigint null,

  UNIQUE(label)
);

-- Przychodzace (hurtowo dodawane) produkty zostają zapisane jako określona dostawa
create table deliveries (

  id bigint primary key not null,
  version int default 0,
  label varchar(100),

  created_at timestamp not null,
  created_by bigint not null,

  edited_at timestamp null,
  edited_by bigint null,

  deleted_at timestamp null,
  deleted_by bigint null

);

-- Produkty
create table products (

  id bigint primary key not null,
  version int default 0,

  imei varchar(15) not null,
  store_id bigint not null references stores(id),
  delivery_id bigint not null references deliveries(id),
  producer varchar(100) not null,
  model varchar(100) not null,

  color varchar(20),
  status varchar(20) not null,

  price_in bigint not null,
  price_out bigint,

  created_at timestamp not null,
  created_by bigint not null,

  edited_at timestamp null,
  edited_by bigint null,

  deleted_at timestamp null,
  deleted_by bigint null,

  UNIQUE (imei)
);

-- Przypisanie uzytkownikow do sklepu
create table user_to_store (

  id bigint primary key not null,
  version int default 0,

  user_id    bigint not null references users(id) ,
  store_id   bigint not null references stores(id)

--   created_at timestamp not null,
--   created_by id bigint not null,
--
--   edited_at timestamp null,
--   edited_by bigint null,
--
--   deleted_at timestamp not null,
--   deleted_by id bigint null
);
