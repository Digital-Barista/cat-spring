create table network_account (
    account_id bigint unsigned not null auto_increment primary key,
    type varchar(16) not null,
    name varchar(16),
    credentials varchar(128),
    description varchar(128),
    client_id bigint,
    unique index net_account_unq (name, type)
) engine = InnoDb;