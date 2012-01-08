create table users (
  id bigint primary key not null,
  version bigint default 0,
  username varchar (100) not null
);