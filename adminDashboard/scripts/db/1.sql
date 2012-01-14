create table users (
  id bigint primary key not null,
  version int default 0,
  email varchar (100) not null,
  password varchar(32),
  UNIQUE (email)
);