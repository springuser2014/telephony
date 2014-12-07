UPDATE contacts SET
  address_line1 = 'Wypoczynkowa 20B', city = 'Rybnik', zip_code  = '44-200', country = 'POL'
WHERE id = 1;

UPDATE contacts SET
  address_line1 = 'Rybnicka 100', city = 'Gliwice', zip_code  = '44-100', country = 'POL'
WHERE id = 2;

UPDATE contacts SET
  address_line1 = 'Gliwicka 200', city = 'Rybnik', zip_code  = '44-207', country = 'POL'
WHERE id = 3;

INSERT INTO phonenumbers (contact_id, content, prefix) VALUES (1, '661666661', '+48');
INSERT INTO phonenumbers (contact_id, content, prefix) VALUES (1, '500600700', '+48');

INSERT INTO phonenumbers (contact_id, content, prefix) VALUES (2, '600700800', '+48');
INSERT INTO phonenumbers (contact_id, content, prefix) VALUES (2, '700800900', '+48');

INSERT INTO phonenumbers (contact_id, content, prefix) VALUES (2, '511622733', '+48');

INSERT INTO emails (contact_id, content) VALUES (1, 'adam@email.com');
INSERT INTO emails (contact_id, content) VALUES (1, 'adam@mail.com');

INSERT INTO emails (contact_id, content) VALUES (2, 'leszek@email.com');

INSERT INTO emails (contact_id, content) VALUES (3, 'grzegorz@email.com');
INSERT INTO emails (contact_id, content) VALUES (3, 'grzes@email.com');

-- taken from http://www.interfax.net/en/help/faxnumber_format
INSERT INTO faxes (contact_id, content) VALUES (1, '+1-212-9876543');
INSERT INTO faxes (contact_id, content) VALUES (1, '+44-208-1234567');

INSERT INTO faxes (contact_id, content) VALUES (2, '0161 999 8888');
INSERT INTO faxes (contact_id, content) VALUES (2, '+44 (161) 999 8888');

INSERT INTO faxes (contact_id, content) VALUES (3, '222 8888');
INSERT INTO faxes (contact_id, content) VALUES (3, '1-212-222 8888');
INSERT INTO faxes (contact_id, content) VALUES (3, '+1 (212) 222 8888');
