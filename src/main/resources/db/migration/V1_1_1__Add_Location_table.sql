create TABLE locations
(
    id           bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    address      varchar(100) NOT NULL,
    name         varchar(50) NOT NULL,
    phone_number varchar(20) NOT NULL
);