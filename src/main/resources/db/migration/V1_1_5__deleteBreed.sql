-- drop table BREEDS2 cascade;
-- drop table breeds cascade;

ALTER TABLE pets
    DROP CONSTRAINT fk_pet_breed;

ALTER TABLE pets2
    DROP CONSTRAINT fk_pet2_breed;

alter table pets alter column breed_id type varchar(30);
alter table pets2 alter column breed_id type varchar(30);

-- drop table pets cascade;
-- drop table pets2 cascade;
--
-- create table pets
-- (
--     ID             bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
--     name           varchar(50)  not null,
--     age            smallint     not null,
--     owners_name    varchar(50)  not null,
--     owners_phone   varchar(20)  not null,
--     owners_email   varchar(50)  not null,
--     gender         varchar(6)   not null,
--     addopted       varchar(3)   not null,
--     foster_user_id bigint,
--     category_id    bigint       not null,
--     location_id    bigint       not null,
--     description    varchar(500) not null,
--     user_id        bigint       not null
-- );
--
-- ALTER TABLE pets
--     ADD CONSTRAINT fk_pet_foster_user FOREIGN KEY (foster_user_id) REFERENCES users (user_id);
--
-- ALTER TABLE pets
--     ADD CONSTRAINT fk_pet_user FOREIGN KEY (user_id) REFERENCES users (user_id);
--
-- ALTER TABLE pets
--     ADD CONSTRAINT fk_pet_category FOREIGN KEY (category_id) REFERENCES categories (id);
--
-- ALTER TABLE pets
--     ADD CONSTRAINT fk_pet_location FOREIGN KEY (location_id) REFERENCES locations (id);
--
-- ALTER TABLE pets
--     ADD CONSTRAINT pet_gender_check
--         CHECK (
--                 gender in ('MALE', 'FEMALE')
--             );
--
-- ALTER TABLE pets
--     ADD CONSTRAINT pet_addopted_check
--         CHECK (
--                 addopted in ('YES', 'NO')
--             );
--
-- create table pets2
-- (
--     ID             bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
--     name           varchar(50)  not null,
--     age            smallint     not null,
--     owners_name    varchar(50)  not null,
--     owners_phone   varchar(20)  not null,
--     owners_email   varchar(50)  not null,
--     gender         varchar(6)   not null,
--     addopted       varchar(3)   not null,
--     foster_user_id bigint,
--     category_id    bigint       not null,
--     location_id    bigint       not null,
--     description    varchar(500) not null,
--     user_id        bigint       not null
-- );
--
-- ALTER TABLE pets2
--     ADD CONSTRAINT fk_pet2_foster_user FOREIGN KEY (foster_user_id) REFERENCES users2 (user_id);
--
-- ALTER TABLE pets2
--     ADD CONSTRAINT fk_pet2_user FOREIGN KEY (user_id) REFERENCES users2 (user_id);
--
-- ALTER TABLE pets2
--     ADD CONSTRAINT fk_pet2_category FOREIGN KEY (category_id) REFERENCES categories2 (id);
--
-- ALTER TABLE pets2
--     ADD CONSTRAINT fk_pet2_location FOREIGN KEY (location_id) REFERENCES locations2 (id);
--
-- ALTER TABLE pets2
--     ADD CONSTRAINT pet2_gender_check
--         CHECK (
--                 gender in ('MALE', 'FEMALE')
--             );
--
-- ALTER TABLE pets2
--     ADD CONSTRAINT pet2_addopted_check
--         CHECK (
--                 addopted in ('YES', 'NO')
--             );