CREATE TABLE IF NOT EXISTS user_vote
(
    id uuid NOT NULL,
    negative_response character varying(255),
    positive_response character varying(255),
    user_id uuid,
    vote_options_id uuid,
    CONSTRAINT user_vote_pkey PRIMARY KEY (id),
    CONSTRAINT fkgkpdh3q12uvf1xswyy23c59is FOREIGN KEY (vote_options_id)
        REFERENCES vote_options (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkr9gox540u7ifjh1r2f269snmq FOREIGN KEY (user_id)
        REFERENCES users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)