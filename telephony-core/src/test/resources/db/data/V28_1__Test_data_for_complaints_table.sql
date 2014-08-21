
-- Product complaints

INSERT INTO complaints (id, description, reported_date, status, unique_hash, title, contact_id, complaint_type, item_id)
VALUES ( nextval('complaints_seq'), 'Description of product complaint 1', timestamp '2014-06-01 00:00:00', 'JUST_REPORTED', 'ABC123456789000000', 'Title of product complaint 1', 1, 'P', '123456789000000'); 

INSERT INTO complaints (id, description, reported_date, status, unique_hash, title, contact_id, complaint_type, item_id)
VALUES ( nextval('complaints_seq'), 'Description of product complaint 2', timestamp '2014-05-01 00:00:00', 'JUST_REPORTED', 'ABC123456789000001', 'Title of product complaint 2', 1, 'P', '123456789000001'); 

INSERT INTO complaints (id, description, reported_date, status, unique_hash, title, contact_id, complaint_type, item_id)
VALUES ( nextval('complaints_seq'), 'Description of product complaint 3', timestamp '2014-04-01 00:00:00', 'IN_PROGRESS', 'ABC123456789000002', 'Title of product complaint 3', 1, 'P', '123456789000002'); 

INSERT INTO complaints (id, description, reported_date, status, unique_hash, title, contact_id, complaint_type, item_id)
VALUES ( nextval('complaints_seq'), 'Description of product complaint 4', timestamp '2014-03-01 00:00:00', 'ACCEPTED', 'ABC123456789000003', 'Title of product complaint 4', 2, 'P', '123456789000003');

INSERT INTO complaints (id, description, reported_date, status, unique_hash, title, contact_id, complaint_type, item_id)
VALUES ( nextval('complaints_seq'), 'Description of product complaint 5', timestamp '2014-02-01 00:00:00', 'REJECTED', 'ABC123456789000004', 'Title of product complaint 5', 2, 'P', '123456789000004'); 

INSERT INTO complaints (id, description, reported_date, status, unique_hash, title, contact_id, complaint_type, item_id)
VALUES ( nextval('complaints_seq'), 'Description of product complaint 6', timestamp '2014-01-01 00:00:00', 'RESOLVED', 'ABC123456789000005', 'Title of product complaint 6', 2, 'P', '123456789000005');

-- Sale complaints
INSERT INTO complaints (id, description, reported_date, status, unique_hash, title, contact_id, complaint_type, item_id)
VALUES ( nextval('complaints_seq'), 'Description of sale complaint 1', timestamp '2014-06-01 00:00:00', 'JUST_REPORTED', 'ABC123456789000006', 'Title of sale complaint 1', 1, 'S', '1'); 

INSERT INTO complaints (id, description, reported_date, status, unique_hash, title, contact_id, complaint_type, item_id)
VALUES ( nextval('complaints_seq'), 'Description of sale complaint 2', timestamp '2014-05-01 00:00:00', 'JUST_REPORTED', 'ABC123456789000007', 'Title of sale complaint 2', 2, 'S', '2'); 

-- Products complaints comments

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 1, 'Pawel', 'Complaints comment 1 content', timestamp '2014-06-02 00:00:00');

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 1, 'Marek', 'Complaints comment 2 content', timestamp '2014-06-03 00:00:00');

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 2, 'Pawel', 'Complaints comment 3 content', timestamp '2014-06-02 00:00:00');

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 2, 'Marek', 'Complaints comment 4 content', timestamp '2014-06-03 00:00:00');

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 3, 'Pawel', 'Complaints comment 5 content', timestamp '2014-06-02 00:00:00');

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 3, 'Marek', 'Complaints comment 6 content', timestamp '2014-06-03 00:00:00');

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 4, 'Pawel', 'Complaints comment 7 content', timestamp '2014-06-02 00:00:00');

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 4, 'Marek', 'Complaints comment 8 content', timestamp '2014-06-03 00:00:00');

-- Sales complaints comments

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 7, 'Pawel', 'Complaints comment 9 content', timestamp '2014-06-02 00:00:00');

INSERT INTO complaint_comments (id, complaint_id, author, content, reported_date) 
VALUES (nextval('complaint_comments_seq'), 7, 'Marek', 'Complaints comment 10 content', timestamp '2014-06-03 00:00:00');


