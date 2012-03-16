--
-- dane do tabeli użytkownicy
--

insert into users (
  id, version, email, password,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) VALUES

  (nextval('base_entity_seq'), 0, 'Maslana', 'asdasdQWasdasdQW',
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

  (nextval('base_entity_seq'), 0, 'Anusiak', 'wertyQWEwertyQW',
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

  (nextval('base_entity_seq'), 0, 'Konieczko', 'yuio1234yuio1234',
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

  (nextval('base_entity_seq'), 0, 'Czesio', 'rtyuio24rtyuio24',
  CURRENT_DATE, 1,
  null, null,
  null, null
);

insert into users (
  id, version, email, password,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) VALUES

  (nextval('base_entity_seq'), 0, 'Angela', 'TYUIOP23TYUIOP23',
  CURRENT_DATE, 1,
  null, null,
  null, null
);



-- dane do tabeli ze sklepami (magazynami)
--
insert into stores (
  id, version, label,
  created_at, created_by,
  edited_at, edited_by,
  deleted_at, deleted_by
  ) values

  (nextval('base_entity_seq'), 0, 'Tomek',
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

  (nextval('base_entity_seq'), 0, 'Darek',
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

  (nextval('base_entity_seq'), 0, 'Sklep',
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

  (nextval('base_entity_seq'), 0, 'Przemek',
   CURRENT_DATE, 1,
   null, null,
   null, null
);
