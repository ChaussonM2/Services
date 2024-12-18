--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0 (Debian 17.0-1.pgdg120+1)
-- Dumped by pg_dump version 17.0 (Debian 17.0-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres-isi
--

CREATE TABLE public.client (
    id integer NOT NULL,
    nom character varying(255) NOT NULL
);


ALTER TABLE public.client OWNER TO "postgres-isi";

--
-- Name: client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres-isi
--

CREATE SEQUENCE public.client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.client_id_seq OWNER TO "postgres-isi";

--
-- Name: client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres-isi
--

ALTER SEQUENCE public.client_id_seq OWNED BY public.client.id;


--
-- Name: commande; Type: TABLE; Schema: public; Owner: postgres-isi
--

CREATE TABLE public.commande (
    id_commande integer NOT NULL,
    id_produit integer NOT NULL,
    id_client integer NOT NULL,
    qte double precision NOT NULL
);


ALTER TABLE public.commande OWNER TO "postgres-isi";

--
-- Name: itemcommande; Type: TABLE; Schema: public; Owner: postgres-isi
--

CREATE TABLE public.itemcommande (
    iditemcommande integer NOT NULL,
    id_commande integer NOT NULL,
    id_produit integer NOT NULL,
    qte integer NOT NULL
);


ALTER TABLE public.itemcommande OWNER TO "postgres-isi";

--
-- Name: produit; Type: TABLE; Schema: public; Owner: postgres-isi
--

CREATE TABLE public.produit (
    id bigint NOT NULL,
    nom character varying(255) NOT NULL,
    prix double precision NOT NULL,
    type character varying(255) NOT NULL
);


ALTER TABLE public.produit OWNER TO "postgres-isi";

--
-- Name: produit_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres-isi
--

CREATE SEQUENCE public.produit_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.produit_id_seq OWNER TO "postgres-isi";

--
-- Name: produit_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres-isi
--

ALTER SEQUENCE public.produit_id_seq OWNED BY public.produit.id;


--
-- Name: client id; Type: DEFAULT; Schema: public; Owner: postgres-isi
--

ALTER TABLE ONLY public.client ALTER COLUMN id SET DEFAULT nextval('public.client_id_seq'::regclass);


--
-- Name: produit id; Type: DEFAULT; Schema: public; Owner: postgres-isi
--

ALTER TABLE ONLY public.produit ALTER COLUMN id SET DEFAULT nextval('public.produit_id_seq'::regclass);


--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres-isi
--

COPY public.client (id, nom) FROM stdin;
1	John Doe
\.


--
-- Data for Name: commande; Type: TABLE DATA; Schema: public; Owner: postgres-isi
--

COPY public.commande (id_commande, id_produit, id_client, qte) FROM stdin;
\.


--
-- Data for Name: itemcommande; Type: TABLE DATA; Schema: public; Owner: postgres-isi
--

COPY public.itemcommande (iditemcommande, id_commande, id_produit, qte) FROM stdin;
\.


--
-- Data for Name: produit; Type: TABLE DATA; Schema: public; Owner: postgres-isi
--

COPY public.produit (id, nom, prix, type) FROM stdin;
\.


--
-- Name: client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres-isi
--

SELECT pg_catalog.setval('public.client_id_seq', 13, true);


--
-- Name: produit_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres-isi
--

SELECT pg_catalog.setval('public.produit_id_seq', 10, true);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres-isi
--

ALTER TABLE ONLY public.client
    ADD CONSTRAINT client_pkey PRIMARY KEY (id);


--
-- Name: produit produit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres-isi
--

ALTER TABLE ONLY public.produit
    ADD CONSTRAINT produit_pkey PRIMARY KEY (id);


--
-- PostgreSQL database dump complete
--

