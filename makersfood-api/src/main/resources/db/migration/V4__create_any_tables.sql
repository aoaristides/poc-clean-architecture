CREATE TABLE tb_payments
(
    id              VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY     (id)
);

CREATE TABLE tb_groups
(
    id              VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY     (id)
);

CREATE TABLE tb_group_permission
(
    group_id        VARCHAR(255) NOT NULL,
    permission_id   VARCHAR(255) NOT NULL,
    PRIMARY KEY     (group_id, permission_id)
);

CREATE TABLE tb_permissions
(
    id              VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY     (id)
);

CREATE TABLE tb_products
(
    id              VARCHAR(255) NOT NULL,
    restaurant_id   VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NOT NULL,
    price           DECIMAL(10,2) NOT NULL,
    active          BOOLEAN NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    deleted_at      TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY     (id)
);

CREATE TABLE tb_restaurants
(
    id                      VARCHAR(255) NOT NULL,
    kitchen_id              VARCHAR(255) NOT NULL,
    name                    VARCHAR(255) NOT NULL,
    freight_rate            DECIMAL(10,2) NOT NULL,
    active                  BOOLEAN NOT NULL,
    open                    BOOLEAN NOT NULL,
    address_city_id         VARCHAR(255),
    address_zip_code        VARCHAR(255),
    address_street          VARCHAR(255),
    address_street_number   VARCHAR(255),
    address_complement      VARCHAR(255),
    address_district        VARCHAR(255),
    created_at              TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at              TIMESTAMP WITH TIME ZONE NOT NULL,
    PRIMARY KEY             (id)
);

CREATE TABLE tb_restaurant_payment
(
    restaurant_id   VARCHAR(255) NOT NULL,
    payment_id      VARCHAR(255) NOT NULL,
    PRIMARY KEY     (restaurant_id, payment_id)
);

CREATE TABLE tb_users
(
    id              VARCHAR(255) NOT NULL,
    name            VARCHAR(255) NOT NULL,
    mail            VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    active          BOOLEAN NOT NULL,
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at      TIMESTAMP WITH TIME ZONE NOT NULL,
    deleted_at      TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY     (id)
);

CREATE TABLE tb_user_group
(
    user_id         VARCHAR(255) NOT NULL,
    group_id        VARCHAR(255) NOT NULL,
    PRIMARY KEY     (user_id, group_id)
);

CREATE TABLE tb_restaurant_user_responsible
(
    restaurant_id   VARCHAR(255) NOT NULL,
    user_id         VARCHAR(255) NOT NULL,
    PRIMARY KEY     (restaurant_id, user_id)
);


ALTER TABLE tb_group_permission ADD CONSTRAINT fk_group_permission_permission FOREIGN KEY (permission_id) REFERENCES tb_permissions (id);

ALTER TABLE tb_group_permission ADD CONSTRAINT fk_group_permission_group FOREIGN KEY (group_id) REFERENCES tb_groups (id);

ALTER TABLE tb_products ADD CONSTRAINT fk_product_restaurant FOREIGN KEY (restaurant_id) REFERENCES tb_restaurants (id);

ALTER TABLE tb_restaurants ADD CONSTRAINT fk_restaurant_kitchen FOREIGN KEY (kitchen_id) REFERENCES tb_kitchens (id);

ALTER TABLE tb_restaurants ADD CONSTRAINT fk_restaurant_city FOREIGN KEY (address_city_id) REFERENCES tb_cities (id);

ALTER TABLE tb_restaurant_payment ADD CONSTRAINT fk_restaurant_payment_payment FOREIGN KEY (payment_id) REFERENCES tb_payments (id);

ALTER TABLE tb_restaurant_payment ADD CONSTRAINT fk_restaurant_payment_restaurant FOREIGN KEY (restaurant_id) REFERENCES tb_restaurants (id);

ALTER TABLE tb_user_group ADD CONSTRAINT fk_user_group_group FOREIGN KEY (group_id) REFERENCES tb_groups (id);

ALTER TABLE tb_user_group ADD CONSTRAINT fk_user_group_user FOREIGN KEY (user_id) REFERENCES tb_users (id);

ALTER TABLE tb_restaurant_user_responsible ADD CONSTRAINT fk_restaurant_user_restaurant FOREIGN KEY (restaurant_id) REFERENCES tb_restaurants (id);

ALTER TABLE tb_restaurant_user_responsible ADD CONSTRAINT fk_restaurant_user_user FOREIGN KEY (user_id) REFERENCES tb_users (id);