CREATE TABLE tb_states
(
    id              VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY     (id)
);

ALTER TABLE tb_cities ADD CONSTRAINT fk_city_state FOREIGN KEY (state_id) REFERENCES tb_states (id);