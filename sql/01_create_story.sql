CREATE TABLE story (
    id           	BIGSERIAL PRIMARY key,
    title			TEXT DEFAULT NULL,
    body 			TEXT DEFAULT NULL,
    author 		    TEXT DEFAULT NULL,
    img 			TEXT DEFAULT NULL,
    published_date	TIMESTAMP DEFAULT NULL,
    create_at 	    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    update_at 	    TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);