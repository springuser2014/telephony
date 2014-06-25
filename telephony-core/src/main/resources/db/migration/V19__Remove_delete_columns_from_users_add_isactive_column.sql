ALTER TABLE users DROP column deleted_at;
ALTER TABLE users DROP column deleted_by;

ALTER TABLE users ADD column isactive boolean not null default false;