create table if not exists meetings
(
    id   serial primary key not null,
    name varchar(100)
);

create table if not exists status
(
    id   serial primary key not null,
    name varchar(30)
);

create table if not exists users
(
    id   serial primary key not null,
    name varchar(30)
);

create table if not exists user_meeting_status
(
    id         serial primary key not null,
    user_id    int references users (id),
    meeting_id int references meetings (id),
    status_id  int references status (id)
);

insert into meetings (name)
values ('alumni meeting'),
       ('business meeting'),
       ('party'),
       ('protests'),
       ('a round table meeting');

insert into status (name)
values ('participate'),
       ('refused');

insert into users (name)
values ('Коля'),
       ('Маша'),
       ('Даша');

insert into user_meeting_status (user_id, meeting_id, status_id)
values (1, 1, 1),
       (1, 2, 1),
       (1, 3, 1),
       (1, 4, 2),
       (1, 5, 2),
       (2, 1, 1),
       (2, 2, 2),
       (2, 3, 1),
       (2, 4, 2),
       (2, 5, 2),
       (3, 1, 1),
       (3, 2, 2),
       (3, 3, 1),
       (3, 4, 2),
       (3, 5, 2);

-- вывести имя события и количество пользователей на этой конференции.
select m.name, count(*) filter (where status_id = 1)
from meetings m
         left join user_meeting_status ums on m.id = ums.meeting_id
group by m.name
order by 2 desc;

-- вывести имя события, где все пользователи отказались.
select m.name
from meetings m
         join user_meeting_status ums on m.id = ums.meeting_id
group by m.name
having count(*) - (count(*) filter (where status_id = 2)) = 0;
