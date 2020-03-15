create table company
(
    id   serial primary key not null,
    name varchar(15)
);

create table person
(
    id         serial primary key not null,
    name       varchar(15),
    company_id int references company (id)
);

insert into company (name)
values ('Apple'),
       ('Microsoft'),
       ('Google'),
       ('Intel'),
       ('Asus');

insert into person (name, company_id)
values ('Стив', 1),
       ('Тим', 1),
       ('Эдди', 1),
       ('Джонатан', 1),
       ('Филип', 1),
       ('Брюс', 2),
       ('Джеф', 2),
       ('Анджела', 2),
       ('Ден', 3),
       ('Лука', 3),
       ('Тома', 3),
       ('Маша', 4),
       ('Коля', 4),
       ('Саша', 4),
       ('Сергей', 5),
       ('Даша', 5),
       ('Диана', 5);


-- 1) Retrieve in a single query:
-- - names of all persons that are NOT in the company with id = 5
-- - company name for each person

select p.name, c.name, company_id
from person p
         join company c on c.id = p.company_id
where c.id <> 5;

-- 2) Select the name of the company with the maximum number of persons + number of persons in this company.

select c.name, count(*) as counter
from person p
         join company c on c.id = p.company_id
group by c.name
order by counter desc
limit 1;
