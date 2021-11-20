CREATE TABLE IF NOT EXISTS users
(
    id           VARCHAR(255) NOT NULL,
    name     VARCHAR(255),
    email        VARCHAR(255),
    password VARCHAR(255),
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP,
    CONSTRAINT pk_users PRIMARY KEY (id)
);