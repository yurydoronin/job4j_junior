create table if not exists log4j2
(
    id         serial primary key not null,
    event_date timestamp not null default now(),
    level      varchar(10),
    logger     varchar(30),
    message    varchar(30),
    exception  varchar(30)
);