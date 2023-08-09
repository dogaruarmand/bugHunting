create TABLE categories
(
    id       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    category varchar(30) NOT NULL
);

