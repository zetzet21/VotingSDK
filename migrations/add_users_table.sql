CREATE TABLE IF NOT EXISTS users
(
    id uuid NOT NULL,
    email character varying(255),
    first_name character varying(255),
    gender character varying(255),
    password character varying(255),
    surname character varying(255),
    username character varying(255),
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT uk6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email),
    CONSTRAINT ukr43af9ap4edm43mmtq01oddj6 UNIQUE (username)
)