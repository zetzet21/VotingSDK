CREATE TABLE IF NOT EXISTS vote_options
(
    id uuid NOT NULL,
    text character varying(255) NOT NULL,
    vote_id uuid,
    CONSTRAINT vote_options_pkey PRIMARY KEY (id),
    CONSTRAINT fkl2akx1lll3m5b004q80729oui FOREIGN KEY (vote_id)
        REFERENCES votes (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)