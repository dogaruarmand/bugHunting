create TABLE locations
(
    id           bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    address      varchar(100) NULL,
    name         varchar(50)  NULL,
    phone_number varchar(20)  NULL
);