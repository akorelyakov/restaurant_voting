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
VALUES ('Стейк', 1000, 100002, CURRENT_DATE),
       ('Мимоза', 300, 100002, CURRENT_DATE),
       ('Оливье', 400, 100003, CURRENT_DATE),
       ('Яичница', 180, 100003, CURRENT_DATE),
       ('Тост', 150, 100003, CURRENT_DATE),
       ('Суп', 800, 100004, CURRENT_DATE),
       ('Паста', 550, 100004, CURRENT_DATE),
       ('Пицца', 700, 100004, CURRENT_DATE),
       ('Закуска', 450, 100004, CURRENT_DATE),
       ('Вчерашний стейк', 500, 100002, CURRENT_DATE - 1 DAY),
       ('Вчерашняя мимоза', 150, 100002, CURRENT_DATE - 1 DAY);

INSERT INTO vote (restaurant_id, user_id)
VALUES (100003, 100001);
