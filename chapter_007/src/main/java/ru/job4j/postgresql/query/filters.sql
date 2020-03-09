create table if not exists type
(
    id   serial primary key not null,
    name varchar(15)
);

create table if not exists product
(
    id           serial primary key not null,
    name         varchar(30)        not null,
    type_id      int references type (id),
    expired_date date,
    price        decimal(5, 2)
);

insert into type (name)
values ('мороженное'),
       ('сыр'),
       ('шоколад'),
       ('молоко'),
       ('фрукты');

insert into product (name, type_id, expired_date, price)
values ('шоколадное мороженное', 1, '11/05/2019', 10.50),
       ('ванильное мороженное', 1, '11/10/2019', 9.50),
       ('фисташковое мороженное', 1, '11/08/2019', 9.50),
       ('клубничное мороженное', 1, '11/08/2019', 11.50),
       ('банановое мороженное', 1, '11/17/2019', 10.50),
       ('швейцарский сыр', 2, '12/10/2019', 77.50),
       ('сыр чеддер', 2, '12/10/2019', 90.50),
       ('сыр камамбер', 2, '11/29/2019', 100.50),
       ('шоколад швейцарский', 3, '04/15/2020', 40.50),
       ('шоколад бельгийский', 3, '05/25/2020', 40.50),
       ('шоколад французский', 3, '03/27/2020', 51.50),
       ('творог жирный', 4, '11/01/2019', 51.50),
       ('творог нежирный', 4, '11/01/2019', 31.50),
       ('шоколадное молоко', 5, '11/01/2019', 16.50),
       ('молоко жирное', 5, '11/01/2019', 21.50),
       ('молоко нежирное', 5, '11/01/2019', 18.50),
       ('виноград', 6, '11/05/2019', 28.50),
       ('бананы', 6, '11/03/2019', 3.50),
       ('клубника', 6, '11/01/2019', 13.50);

-- 1. Написать запрос получение всех продуктов с типом "СЫР".
SELECT p.name
FROM product p
         JOIN type t ON type_id = t.id
WHERE t.name = 'сыр';

-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное".
SELECT name
FROM product
WHERE name LIKE '%мороженное%';

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT name
FROM product
WHERE (SELECT date_part('month', expired_date)) = (SELECT date_part('month', current_date + INTERVAL '1 month'));

-- 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце (ver. 2).
SELECT name
FROM product
WHERE (SELECT extract(month FROM expired_date)) = (SELECT extract(month FROM current_date + INTERVAL '1 month'));

-- 4. Написать запрос, который выводит самый дорогой продукт.
SELECT name
FROM product
WHERE price = (SELECT max(price) FROM product);

-- 4. Написать запрос, который выводит самый дорогой продукт (ver. 2).
SELECT name
FROM product
GROUP BY name
ORDER BY max(price) DESC
LIMIT 1;

-- 5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT count(p.name)
FROM product p
         JOIN type t ON type_id = t.id
WHERE t.name = 'мороженное';

-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
SELECT p.name
FROM product p
         JOIN type t ON p.type_id = t.id
WHERE t.name IN ('сыр', 'молоко');

-- 7. Вывести все продукты и их тип.
SELECT p.name, t.name
FROM product p
         JOIN type t ON p.type_id = t.id
ORDER BY t.name;

-- 8. Написать запрос, который выводит тип продуктов, которых осталось меньше 5 штук.
SELECT t.name
FROM type t
         JOIN product p ON t.id = p.type_id
GROUP BY t.name
HAVING count(p.name) < 5;



