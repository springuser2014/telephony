--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: deliveries; Type: TABLE; Schema: public; Owner: gam3r; Tablespace: 
--


CREATE TABLE deliveries (
    id bigint NOT NULL,
    label character varying(100),
    date_in timestamp without time zone NOT NULL,
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    deleted_at timestamp without time zone,
    deleted_by bigint,
    store_id bigint NOT NULL,
    contact_id bigint NOT NULL
);


ALTER TABLE public.deliveries OWNER TO gam3r;

--
-- Name: deliveries_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE deliveries_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.deliveries_seq OWNER TO postgres;

--
-- Name: deliveries_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('deliveries_seq', 1, false);

--
-- Name: products; Type: TABLE; Schema: public; Owner: gam3r; Tablespace: 
--

CREATE TABLE products (
    id bigint NOT NULL,
    version integer DEFAULT 0,
    imei character varying(100) NOT NULL,
    store_id bigint NOT NULL,
    delivery_id bigint NOT NULL,
    sale_id bigint,
    producer character varying(100) NOT NULL,
    model character varying(100) NOT NULL,
    color character varying(20),
    price_in bigint NOT NULL,
    price_out bigint,
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    deleted_at timestamp without time zone,
    deleted_by bigint
);


ALTER TABLE public.products OWNER TO gam3r;

--
-- Name: products_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE products_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.products_seq OWNER TO postgres;

--
-- Name: products_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('products_seq', 1, false);

--
-- Name: sales; Type: TABLE; Schema: public; Owner: gam3r; Tablespace: 
--

CREATE TABLE sales (
    id bigint NOT NULL,
    label character varying(100),
    date_out timestamp without time zone NOT NULL,
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    deleted_at timestamp without time zone,
    deleted_by bigint,
    store_id bigint NOT NULL,
    contact_id bigint NOT NULL
);


ALTER TABLE public.sales OWNER TO gam3r;

--
-- Name: sales_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE sales_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.sales_seq OWNER TO postgres;

--
-- Name: sales_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('sales_seq', 1, false);


--
-- Name: stores; Type: TABLE; Schema: public; Owner: gam3r; Tablespace: 
--

CREATE TABLE stores (
    id bigint NOT NULL,
    label text NOT NULL,
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    deleted_at timestamp without time zone,
    deleted_by bigint
);


ALTER TABLE public.stores OWNER TO gam3r;

--
-- Name: stores_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE stores_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.stores_seq OWNER TO postgres;

--
-- Name: stores_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('stores_seq', 1, false);


--
-- Name: roles; Type: TABLE; Schema: public; Owner: gam3r; Tablespace:
--

CREATE TABLE roles (
    id bigint NOT NULL,
    name character varying(100) NOT NULL,
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    deleted_at timestamp without time zone,
    deleted_by bigint
);


ALTER TABLE public.roles OWNER TO gam3r;

--
-- Name: roles_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE roles_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.roles_seq OWNER TO postgres;

--
-- Name: roles_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('roles_seq', 1, false);



--
-- Name: user_roles; Type: TABLE; Schema: public; Owner: gam3r; Tablespace:
--

CREATE TABLE user_roles (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    deleted_at timestamp without time zone,
    deleted_by bigint
);


ALTER TABLE public.user_roles OWNER TO gam3r;

--
-- Name: user_roles_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_roles_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.user_roles_seq OWNER TO postgres;

--
-- Name: user_roles_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_roles_seq', 1, false);

--
-- Name: user_stores; Type: TABLE; Schema: public; Owner: gam3r; Tablespace:
--

CREATE TABLE user_stores (
    id bigint NOT NULL,
    user_id bigint NOT NULL,
    store_id bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    deleted_at timestamp without time zone,
    deleted_by bigint
);


ALTER TABLE public.user_stores OWNER TO gam3r;

--
-- Name: user_stores_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE user_stores_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.user_stores_seq OWNER TO postgres;

--
-- Name: user_stores_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('user_stores_seq', 1, false);


---
--- Name: contacts; Type: TABLE; Schema: public; Owner: gam3r; Tablespace:
---

CREATE TABLE contacts (
  id bigint NOT NULL,
  label character varying(255),
  details text NOT NULL,
  created_at timestamp without time zone NOT NULL,
  created_by bigint NOT NULL,
  deleted_at timestamp without time zone,
  deleted_by bigint
);

ALTER TABLE public.contacts OWNER TO gam3r;

CREATE SEQUENCE contacts_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.contacts_seq OWNER TO postgres;

SELECT pg_catalog.setval('contacts_seq', 1, false);

---
--- Name: system_logs; Type: TABLE; Schema: public; Owner: gam3r; Tablespace:
---

CREATE TABLE system_logs (
  id bigint NOT NULL,
  label character varying(100) NOT NULL,
  content text NOT NULL,
  type character varying(100) NOT NULL,
  created_at timestamp without time zone NOT NULL,
  created_by bigint NOT NULL,
  deleted_at timestamp without time zone,
  deleted_by bigint
);

ALTER TABLE public.system_logs OWNER TO gam3r;

CREATE SEQUENCE system_logs_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;

ALTER TABLE public.system_logs_seq OWNER TO postgres;

SELECT pg_catalog.setval('system_logs_seq', 1, false);

--
-- Name: users; Type: TABLE; Schema: public; Owner: gam3r; Tablespace: 
--

CREATE TABLE users (
    id bigint NOT NULL,
    email character varying(100) NOT NULL,
    password character varying(32),
    created_at timestamp without time zone NOT NULL,
    created_by bigint NOT NULL,
    deleted_at timestamp without time zone,
    deleted_by bigint
);


ALTER TABLE public.users OWNER TO gam3r;

--
-- Name: users_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE users_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


ALTER TABLE public.users_seq OWNER TO postgres;

--
-- Name: users_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('users_seq', 1, false);

--
-- Data for Name: stores; Type: TABLE DATA; Schema: public; Owner: gam3r
--

--COPY stores (id, version, label, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by) FROM stdin;
--37	0	Tomek	2012-02-23 00:00:00	32	\N	\N	\N	\N
--38	0	Darek	2012-02-23 00:00:00	32	\N	\N	\N	\N
--39	0	Sklep	2012-02-23 00:00:00	32	\N	\N	\N	\N
--40	0	Przemek	2012-02-23 00:00:00	32	\N	\N	\N	\N
--\.



--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: gam3r
--

--COPY users (id, version, email, password, created_at, created_by, edited_at, edited_by, deleted_at, deleted_by) FROM stdin;
--32	0	Maslana	asdasdQWasdasdQW	2012-02-23 00:00:00	32	\N	\N	\N	\N
--33	0	Anusiak	wertyQWEwertyQW	2012-02-23 00:00:00	32	\N	\N	\N	\N
--34	0	Konieczko	yuio1234yuio1234	2012-02-23 00:00:00	32	\N	\N	\N	\N
--35	0	Czesio	rtyuio24rtyuio24	2012-02-23 00:00:00	32	\N	\N	\N	\N
--36	0	Angela	TYUIOP23TYUIOP23	2012-02-23 00:00:00	32	\N	\N	\N	\N
--\.

--
-- Name: contacts_pkey; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace:
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT contacts_pkey PRIMARY KEY (id);

--
-- Name: system_logs_pkey; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace:
--

ALTER TABLE ONLY system_logs
    ADD CONSTRAINT system_logs_pkey PRIMARY KEY (id);

--
-- Name: deliveries_pkey; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY deliveries
    ADD CONSTRAINT deliveries_pkey PRIMARY KEY (id);


--
-- Name: user_stores; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace:
--

ALTER TABLE ONLY user_stores
    ADD CONSTRAINT user_stores_pkey PRIMARY KEY (id);


--
-- Name: products_imei_key; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_imei_key UNIQUE (imei);


--
-- Name: products_pkey; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


--
-- Name: sales_pkey; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY sales
    ADD CONSTRAINT sales_pkey PRIMARY KEY (id);


--
-- Name: stores_label_key; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY stores
    ADD CONSTRAINT stores_label_key UNIQUE (label);


--
-- Name: stores_pkey; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY stores
    ADD CONSTRAINT stores_pkey PRIMARY KEY (id);


--
-- Name: user_roles_pkey; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (id);

--
-- Name: roles_pkey; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace:
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (id);


--
-- Name: user_roles_role_name_key; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY roles
    ADD CONSTRAINT roles_name_key UNIQUE (name);

--
-- Name: contacts_label_key; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace:
--

ALTER TABLE ONLY contacts
    ADD CONSTRAINT contacts_label_key UNIQUE (label);


--
-- Name: users_email_key; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users_pkey; Type: CONSTRAINT; Schema: public; Owner: gam3r; Tablespace: 
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: deliveries_store_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY deliveries
    ADD CONSTRAINT deliveries_store_id_fkey FOREIGN KEY (store_id) REFERENCES stores(id);

--
-- Name: deliveries_contact_id_fkey ; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY deliveries
    ADD CONSTRAINT deliveries_contact_id_fkey FOREIGN KEY (contact_id) REFERENCES contacts(id);


--
-- Name: products_delivery_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_delivery_id_fkey FOREIGN KEY (delivery_id) REFERENCES deliveries(id);


--
-- Name: products_sale_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_sale_id_fkey FOREIGN KEY (sale_id) REFERENCES sales(id);


--
-- Name: products_store_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY products
    ADD CONSTRAINT products_store_id_fkey FOREIGN KEY (store_id) REFERENCES stores(id);


--
-- Name: user_roles_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: user_roles_user_role_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_role_id_fkey FOREIGN KEY (role_id) REFERENCES roles(id);


--
-- Name: sales_store_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY sales
    ADD CONSTRAINT sales_store_id_fkey FOREIGN KEY (store_id) REFERENCES stores(id);

--
-- Name: sales_contact_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY sales
    ADD CONSTRAINT sales_contact_id_fkey FOREIGN KEY (contact_id) REFERENCES contacts(id);


--
-- Name: user_stores_store_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY user_stores
    ADD CONSTRAINT user_stores_store_id_fkey FOREIGN KEY (store_id) REFERENCES stores(id);


--
-- Name: user_stores_user_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: gam3r
--

ALTER TABLE ONLY user_stores
    ADD CONSTRAINT user_stores_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--
