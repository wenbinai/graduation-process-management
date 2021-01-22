
create table if not exists test
    (
    id bigint(19) not null
    primary key,
    name varchar(45) not null,
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    version int default 0,
    address varchar(45) null
    );
