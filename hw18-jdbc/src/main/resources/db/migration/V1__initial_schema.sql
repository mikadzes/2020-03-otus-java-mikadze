create table client
(
    id   bigserial not null primary key,
    name varchar(50),
    age int
);
create table account
(
    no varchar(50),
    type varchar(50),
    rest real
);

