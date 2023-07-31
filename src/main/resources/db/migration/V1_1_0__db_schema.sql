CREATE TABLE users
(
    user_id                bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    first_name             varchar(30)    NOT NULL,
    last_name              varchar(30)    NOT NULL,
    email                  varchar(30)    NOT NULL,
    password               varchar(30)    NOT NULL,
    role                   varchar(30)    CHECK (role IN ('ADMIN', 'USER')),
    created_at             timestamp with time zone      NOT NULL DEFAULT (now() at time zone 'utc'),
    updated_at             timestamp with time zone      NOT NULL DEFAULT (now() at time zone 'utc')
);