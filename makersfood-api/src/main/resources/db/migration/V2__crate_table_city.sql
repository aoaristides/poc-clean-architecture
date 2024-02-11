CREATE TABLE tb_cities
(
    id              VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    state_id        VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY     (id)
);