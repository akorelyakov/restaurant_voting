DELETE
FROM user_role;
DELETE
FROM dish;
DELETE
FROM vote;
DELETE
FROM restaurant;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_role (role, user_id)
VALUES ('ADMIN', 100001),
       ('USER', 100000);

INSERT INTO restaurant (name)
VALUES ('Кафе'),
       ('Бар'),
       ('Ресторан');

INSERT INTO dish (name, price, restaurant_id, added)
VALUES ('Стейк', 1000, 100002, current_date),
       ('Мимоза', 300, 100002, current_date),
       ('Оливье', 400, 100003, current_date),
       ('Яичница', 180, 100003, current_date),
       ('Тост', 150, 100003, current_date),
       ('Суп', 800, 100004, current_date),
       ('Паста', 550, 100004, current_date),
       ('Пицца', 700, 100004, current_date),
       ('Закуска', 450, 100004, current_date),
       ('Вчерашний стейк', 500, 100002, current_date - integer '1'),
       ('Вчерашняя мимоза', 150, 100002, current_date - integer '1'),
       ('Вчерашний оливье', 200, 100003, current_date - integer '1'),
       ('Вчерашняя яичница', 90, 100003, current_date - integer '1'),
       ('Вчерашний тост', 150, 100003, current_date - integer '1');

INSERT INTO vote (restaurant_id, user_id)
VALUES (100002, 100000),
       (100003, 100001);
