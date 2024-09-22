CREATE TABLE IF NOT EXISTS votes
(
    id uuid NOT NULL,
    status character varying(255),
    title character varying(255),
    CONSTRAINT votes_pkey PRIMARY KEY (id),
    CONSTRAINT votes_status_check CHECK (status::text = ANY (ARRAY['ACTIVE'::character varying, 'CLOSED'::character varying]::text[]))
)