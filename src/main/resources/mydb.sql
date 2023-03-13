CREATE SEQUENCE people_id_seq
INCREMENT 1
START 1;

CREATE SEQUENCE books_id_seq
INCREMENT 1
START 1;

CREATE SEQUENCE authors_id_seq
INCREMENT 1
START 1;

CREATE SEQUENCE country_id_seq
INCREMENT 1
START 1;

--this is the country table
CREATE TABLE IF NOT EXISTS country
(
    id bigint NOT NULL DEFAULT nextval('country_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
	code character varying(25) NOT NULL,
    CONSTRAINT country_pkey PRIMARY KEY (id)
)



-- this is the author and book multi to multi relationship table
CREATE TABLE IF NOT EXISTS author_books
(
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    author_id integer NOT NULL,
    book_id integer NOT NULL,
    CONSTRAINT author_books_pkey PRIMARY KEY (author_id, book_id),
    CONSTRAINT author_books_author_id_fkey FOREIGN KEY (author_id)
        REFERENCES public.authors (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT author_books_book_id_fkey FOREIGN KEY (book_id)
        REFERENCES public.books (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

-- this is the author table
CREATE TABLE IF NOT EXISTS authors
(
    id integer NOT NULL DEFAULT nextval('authors_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    CONSTRAINT authors_pkey PRIMARY KEY (id)
)

--this is the book renting history table
CREATE TABLE IF NOT EXISTS book_rents
(
    person_id bigint NOT NULL,
    book_id bigint NOT NULL,
    "createdAt" time with time zone NOT NULL,
    "updatedAt" time with time zone NOT NULL
)

--this is the book table
CREATE TABLE IF NOT EXISTS books
(
    id integer NOT NULL DEFAULT nextval('books_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    CONSTRAINT books_pkey PRIMARY KEY (id)
)

--this is the people table
CREATE TABLE IF NOT EXISTS people
(
    id integer NOT NULL DEFAULT nextval('people_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    "createdAt" timestamp with time zone NOT NULL,
    "updatedAt" timestamp with time zone NOT NULL,
    country_id bigint,
    CONSTRAINT people_pkey PRIMARY KEY (id)
)
-- Populate table with some data entries

INSERT INTO people (id,name, "createdAt", "updatedAt", country_id)
VALUES(1,'Nyein', now(), now(), 1);
INSERT INTO people (id,name, "createdAt", "updatedAt", country_id)
VALUES(2,'Bella', now(),now(), 1);
INSERT INTO people (id,name, "createdAt", "updatedAt", country_id)
VALUES(3,'Steven', now(),now(), 3);
INSERT INTO people (id,name, "createdAt", "updatedAt", country_id)
VALUES(4,'May', now(),now(), 2);
INSERT INTO people (id,name, "createdAt", "updatedAt", country_id)
VALUES(5,'Thoon',now(),now(), 2);
INSERT INTO people (id,name, "createdAt", "updatedAt", country_id)
VALUES(6,'Phoo',now(),now(), 2);

INSERT INTO authors (id,name, "createdAt", "updatedAt")
VALUES(1,'Ludwig Aurbache', now(),now());
INSERT INTO authors (id,name, "createdAt", "updatedAt")
VALUES(2,'Charles Perrault', now(),now());
INSERT INTO authors (id,name, "createdAt", "updatedAt")
VALUES(3,'Joanne Rowling', now(),now());
INSERT INTO authors (id,name, "createdAt", "updatedAt")
VALUES(4,'Barack Obama', now(),now());

INSERT INTO books (id,name, "createdAt", "updatedAt")
VALUES(1,'Snow White', now(),now());
INSERT INTO books (id,name, "createdAt", "updatedAt")
VALUES(2,'Harry Porter', now(),now());
INSERT INTO books (id,name, "createdAt", "updatedAt")
VALUES(3,'Cinderella', now(),now());
INSERT INTO books (id,name, "createdAt", "updatedAt")
VALUES(4,'Obama', now(),now());

INSERT INTO author_books ("createdAt", "updatedAt", author_id, book_id)
VALUES(now(), now(), 1, 1);
INSERT INTO author_books ("createdAt", "updatedAt", author_id, book_id)
VALUES(now(), now(), 1, 2);
INSERT INTO author_books ("createdAt", "updatedAt", author_id, book_id)
VALUES(now(), now(), 2, 3);
INSERT INTO author_books ("createdAt", "updatedAt", author_id, book_id)
VALUES(now(), now(), 3, 4);


INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(1, 1, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(1, 2, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(1, 3, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(1, 4, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(2, 2, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(2, 3, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(2, 4,now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(3, 3, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(3, 4, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(4, 4,now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(5, 1, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(5, 2, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(5, 3, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(5, 4, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(4, 2, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(4, 3, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(4, 1, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(3, 2,now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(3, 1, now(), now());
INSERT INTO book_rents (person_id, book_id, "createdAt", "updatedAt")
VALUES(2, 1, now(), now());