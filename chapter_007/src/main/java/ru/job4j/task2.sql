create table if not exists cities
(
    id   serial primary key not null,
    name varchar (15)
);

insert into cities (name)
values ('Москва'),
       ('Москва'),
       ('СПб'),
       ('Казань'),
       ('Москва');

delete from cities
where id not in (select max(id)
                 from cities
                 group by name);