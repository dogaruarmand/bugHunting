create TABLE users2
(
    user_id    bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name varchar(30) NOT NULL,
    last_name  varchar(30) NOT NULL,
    phone      varchar(30) NOT NULL,
    email      varchar(30) NOT NULL UNIQUE,
    password   varchar(30) NOT NULL,
    role       varchar(30) CHECK (role IN ('ADMIN', 'USER'))
);

create TABLE locations2
(
    id           bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    address      varchar(100)  NULL,
    name         varchar(50)   NULL,
    phone_number varchar(20)   NULL
);

create TABLE categories2
(
    id       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    category varchar(30) NOT NULL
);

create TABLE breeds2
(
    id          bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    id_category bigint      NOT NULL,
    breed       varchar(30) not null
);

ALTER TABLE breeds2
    ADD CONSTRAINT fk_breeds2_categories2 FOREIGN KEY (id_category) REFERENCES categories2 (id);

create table pets2
(
    ID             bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name           varchar(50)  not null,
    age            smallint     not null,
    owners_name    varchar(50)  null,
    owners_phone   varchar(20)  null,
    owners_email   varchar(50)  null,
    gender         varchar(6)   not null,
    addopted       varchar(3)   not null,
    foster_user_id bigint       null,
    category_id    bigint       not null,
    breed_id       bigint       null,
    location_id    bigint       not null,
    description    varchar(500) not null,
    user_id        bigint       not null
);

ALTER TABLE pets2
    ADD CONSTRAINT fk_pet2_foster_user FOREIGN KEY (foster_user_id) REFERENCES users2 (user_id);

ALTER TABLE pets2
    ADD CONSTRAINT fk_pet2_user FOREIGN KEY (user_id) REFERENCES users2 (user_id);

ALTER TABLE pets2
    ADD CONSTRAINT fk_pet2_category FOREIGN KEY (category_id) REFERENCES categories2 (id);

ALTER TABLE pets2
    ADD CONSTRAINT fk_pet2_breed FOREIGN KEY (breed_id) REFERENCES breeds2 (id);

ALTER TABLE pets2
    ADD CONSTRAINT fk_pet2_location FOREIGN KEY (location_id) REFERENCES locations2 (id);

--ALTER TABLE pets2
--    ADD CONSTRAINT pet2_gender_check
--        CHECK (
--            gender in ('Male', 'Female')
--            );

ALTER TABLE pets2
    ADD CONSTRAINT pet2_addopted_check
        CHECK (
            addopted in ('YES', 'NO')
            );