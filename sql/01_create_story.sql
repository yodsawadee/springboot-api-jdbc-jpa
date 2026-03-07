CREATE TABLE story (
    id bigserial NOT NULL,
    body varchar(255) NULL,
    created_at timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    title varchar(255) NULL,
    author varchar(255) NULL,
    img varchar(255) NULL,
    published_date timestamp(6) NULL,
    updated_at timestamptz(6) NOT NULL,
    CONSTRAINT story_pkey PRIMARY KEY (id)
);

CREATE TABLE "comment" (
    id bigserial NOT NULL,
    body varchar(255) NULL,
    email varchar(255) NULL,
    "name" varchar(255) NULL,
    post_id int8 NULL,
    story_id int8 NULL,
    CONSTRAINT comment_pkey PRIMARY KEY (id),
    CONSTRAINT fkdj8brghihjo4r7eokvpb33s05 FOREIGN KEY (story_id) REFERENCES story(id),
    CONSTRAINT fks1slvnkuemjsq2kj4h3vhx7i1 FOREIGN KEY (post_id) REFERENCES post(id)
);

CREATE TABLE post (
    id bigserial NOT NULL,
    body varchar(255) NULL,
    title varchar(255) NULL,
    CONSTRAINT post_pkey PRIMARY KEY (id)
);