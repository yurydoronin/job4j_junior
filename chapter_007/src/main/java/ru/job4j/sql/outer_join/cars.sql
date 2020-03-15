
create table if not exists car_body
(
    id   serial primary key not null,
    name varchar(30)        not null
);

create table if not exists gearbox
(
    id   serial primary key not null,
    name varchar(30)        not null
);

create table if not exists engine
(
    id   serial primary key not null,
    name varchar(30)        not null
);

create table if not exists vehicle
(
    id          serial primary key not null,
    name        varchar(30)        not null,
    car_body_id int references car_body (id),
    gearbox_id  int references gearbox (id),
    engine_id   int references engine (id)
);

insert into car_body (name)
values ('sedan'),
       ('wagon'),
       ('hatchback'),
       ('SUV');

insert into gearbox (name)
values ('manual'),
       ('automatic'),
       ('robot');

insert into engine (name)
values ('gasoline'),
       ('diesel'),
       ('hybrid'),
       ('electric');

insert into vehicle (name, car_body_id, gearbox_id, engine_id)
values ('kia', 4, 2, 1),
       ('bmw', 1, 2, 2),
       ('porsche', 2, 3, 3),
       ('audi', 4, 2, 2),
       ('volvo', 2, 2, 1),
       ('ford', 4, 2, 1);

-- 1. Вывести список всех машин и все привязанные к ним детали.
select v.name, cb.name, g.name, e.name
from vehicle v
         join car_body cb on v.car_body_id = cb.id
         join engine e on v.engine_id = e.id
         join gearbox g on v.gearbox_id = g.id;

-- 2. Вывести отдельно детали, которые не используются в машине: кузова.
select cb.name
from car_body cb
         left join vehicle v on cb.id = v.car_body_id
where v.id is null;

-- 2. Вывести отдельно детали, которые не используются в машине: двигатели.
select e.name
from engine e
         left join vehicle v on e.id = v.engine_id
where v.id is null;

-- 2. Вывести отдельно детали, которые не используются в машине: коробки передач.
select g.name
from gearbox g
         left join vehicle v on g.id = v.gearbox_id
where v.id is null;
