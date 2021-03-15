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
--        ('USER', 100001),
       ('USER', 100000);

INSERT INTO restaurant (name, user_id)
VALUES ('Кафе', 100001),
       ('Бар', 100001),
       ('Ресторан', 100001);

INSERT INTO dish (name, price, restaurant_id)
VALUES ('Стейк', 1000, 100002),
       ('Мимоза', 300, 100002),
       ('Оливье', 400, 100003),
       ('Яичница', 180, 100003),
       ('Тост', 150, 100003),
       ('Суп', 800, 100004),
       ('Паста', 550, 100004),
       ('Пицца', 700, 100004),
       ('Закуска', 450, 100004);