create TABLE location
(
    id           bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    address      varchar(100) NOT NULL,
    name         varchar(50) NOT NULL,
    PHONE_NUMBER varchar(20) NOT NULL
);