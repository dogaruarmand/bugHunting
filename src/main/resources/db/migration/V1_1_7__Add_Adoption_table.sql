create TABLE adoptions
(
    id           bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pet_id       bigint NULL,
    user_id      bigint NULL
);

create TABLE adoptions2
(
    id           bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    pet_id       bigint NULL,
    user_id      bigint NULL
);