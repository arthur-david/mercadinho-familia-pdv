-- create_tables_category_and_product

create table if not exists category (
    id bigserial primary key,
    name varchar(255) unique not null,
    created_at timestamp default now(),
    updated_at timestamp
);

create table if not exists product (
    id bigserial primary key,
    name varchar(255) unique not null,
    barcode varchar unique not null,
    price numeric,
    status varchar(50),
    created_at timestamp default now(),
    updated_at timestamp
);