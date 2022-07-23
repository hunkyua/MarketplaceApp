-- auto-generated definition
create table if not exists products
(
    id    bigint auto_increment
        primary key,
    name  varchar(255) null,
    price double       null
);
-- auto-generated definition
create table if not exists users
(
    id              bigint auto_increment
        primary key,
    amount_of_money double       null,
    first_name      varchar(255) null,
    last_name       varchar(255) null
);
-- auto-generated definition
create table if not exists user_product
(
    user_id    bigint not null,
    product_id bigint not null,
    constraint FKctnnp6lvp5tkjb6fm0k0gmhns
        foreign key (user_id) references users (id),
    constraint FKsu8beo945wpgaux2kwu677srs
        foreign key (product_id) references products (id)
);


