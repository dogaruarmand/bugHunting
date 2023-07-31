CREATE TABLE users
(
    user_id                bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name             varchar(30)    NOT NULL,
    last_name              varchar(30)    NOT NULL,
    email                  varchar(30)    NOT NULL,
    password               varchar(30)    NOT NULL,
    created_at             timestamp with time zone      NOT NULL DEFAULT (now() at time zone 'utc'),
    updated_at             timestamp with time zone      NOT NULL DEFAULT (now() at time zone 'utc')
);

CREATE TABLE roles
(
    role_id                bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    role                   varchar(30)    NOT NULL,
    created_at             timestamp with time zone      NOT NULL DEFAULT (now() at time zone 'utc'),
    updated_at             timestamp with time zone      NOT NULL DEFAULT (now() at time zone 'utc')
);

CREATE TABLE users_roles
(
    id                      bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    user_id                 bigint,
    role_id                 bigint
);

ALTER TABLE users_roles
    ADD FOREIGN KEY (role_id)
        REFERENCES roles (role_id)
        DEFERRABLE INITIALLY DEFERRED;

ALTER TABLE users_roles
    ADD FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        DEFERRABLE INITIALLY DEFERRED;

insert into roles (role, created_at, updated_at)
values ('ADMIN', now(), now());

insert into roles (role, created_at, updated_at)
values ('USER', now(), now());