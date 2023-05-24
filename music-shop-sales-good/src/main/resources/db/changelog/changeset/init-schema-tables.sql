create schema good;

create table good.shop_sale
(
    shop_sale_id serial primary key,
    sale_id      uuid         not null,
    inst_nm      varchar(100) not null,
    inst_cd      varchar(32)  not null,
    manufct_num  varchar(32)  not null,
    employee_nm  varchar(100) not null,
    cust_nm      varchar(100) not null,
    sale_dt      timestamp    not null,
    sale_amt     decimal      not null,
    tax          decimal      not null,
    promo_cd     varchar(32)  not null,
    dscnt_amt    decimal      not null,
    grnd_ttl     decimal      not null,
    msg_id       uuid         not null,
    evnt_ts      timestamp    not null,
    msg_version  varchar(12)  not null,
    shop_id      uuid         not null,
    shop_ctry    varchar(100) not null,
    shop_st      varchar(100) not null,
    shop_nm      varchar(100) not null
);

create table good.shop_lesson
(
    shop_lesson_id serial primary key,
    lesson_id      uuid         not null,
    inst_nm        varchar(100) not null,
    tchr_nm        varchar(100)    not null,
    cust_nm        varchar(100) not null,
    lesson_dt      timestamp    not null,
    sale_amt       decimal      not null,
    tax            decimal      not null,
    promo_cd       varchar(32)  not null,
    dscnt_amt      decimal      not null,
    grnd_ttl       decimal      not null,
    msg_id         uuid         not null,
    evnt_ts        timestamp    not null,
    msg_version    varchar(12)  not null,
    shop_id        uuid         not null,
    shop_ctry      varchar(100) not null,
    shop_st        varchar(100) not null,
    shop_nm        varchar(100) not null
);

create table good.shop_return
(
    shop_return_id serial primary key,
    shop_sale_id   integer     not null references good.shop_sale,
    rfnd_amt       decimal     not null,
    rtrn_dt        timestamp   not null,
    msg_id         uuid        not null,
    evnt_ts        timestamp   not null,
    msg_version    varchar(12) not null
);

create table good.lesson_cancel
(
    lesson_cncl_id serial primary key,
    shop_lesson_id integer     not null references good.shop_lesson,
    rfnd_amt       decimal     not null,
    msg_id         uuid        not null,
    evnt_ts        timestamp   not null,
    msg_version    varchar(12) not null
);

create table good.sale_warranty
(
    sale_warranty_id serial primary key,
    shop_sale_id     integer      not null references good.shop_sale,
    wrnty_strt_dt    timestamp    not null,
    wrnty_end_dt     timestamp    not null,
    wrnty_nm         varchar(100) not null,
    wrnty_cd         varchar(100) not null,
    msg_id           uuid         not null,
    evnt_ts          timestamp    not null,
    msg_version      varchar(12)  not null
);