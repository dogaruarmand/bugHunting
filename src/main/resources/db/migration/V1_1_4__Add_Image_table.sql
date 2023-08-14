create TABLE tbl_product_image
(
    id          bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name        varchar(50)      NOT NULL,
    type        varchar(50) not null,
    image_path  varchar(50) not null
);