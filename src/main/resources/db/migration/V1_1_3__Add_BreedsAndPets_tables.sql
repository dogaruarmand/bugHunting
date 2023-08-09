create TABLE breeds
(
    id          bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_category bigint      NOT NULL,
    breed       varchar(30) not null
);

ALTER TABLE breeds
    ADD CONSTRAINT fk_breeds_categories FOREIGN KEY (id_category) REFERENCES categories (id);

create table pets
(
    ID             bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name           varchar(50)  not null,
    age            smallint     not null,
    owners_name    varchar(50)  not null,
    owners_phone   varchar(20)  not null,
    owners_email   varchar(50)  not null,
    gender         varchar(6)   not null,
    addopted       varchar(3)   not null,
    foster_user_id bigint,
    category_id    bigint       not null,
    breed_id       bigint       not null,
    location_id    bigint       not null,
    description    varchar(500) not null,
    user_id        bigint       not null,
);

ALTER TABLE pets
    ADD CONSTRAINT fk_pet_foster_user FOREIGN KEY (foster_user_id) REFERENCES users (user_id);

ALTER TABLE pets
    ADD CONSTRAINT fk_pet_user FOREIGN KEY (user_id) REFERENCES users (user_id);

ALTER TABLE pets
    ADD CONSTRAINT fk_pet_category FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE pets
    ADD CONSTRAINT fk_pet_breed FOREIGN KEY (breed_id) REFERENCES breeds (id);

ALTER TABLE pets
    ADD CONSTRAINT fk_pet_location FOREIGN KEY (location_id) REFERENCES locations (id);

ALTER TABLE pets
    ADD CONSTRAINT pet_gender_check
        CHECK (
            gender in ('MALE', 'FEMALE')
            );

ALTER TABLE pets
    ADD CONSTRAINT pet_addopted_check
        CHECK (
            addopted in ('YES', 'NO')
            );