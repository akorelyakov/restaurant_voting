DELETE FROM user_role;
DELETE FROM dish;
DELETE FROM menu;
DELETE FROM vote;
DELETE FROM restaurant;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_role (role, user_id)
VALUES ('ADMIN', 100001),
--        ('USER', 100001),
       ('USER', 100000);

INSERT INTO restaurant (name)
VALUES ('Кафе'),
       ('Бар'),
       ('Ресторан');

INSERT INTO menu (restaurant_id)
VALUES (100002),
       (100003),
       (100004);

INSERT INTO dish (name, price, menu_id)
VALUES ('Стейк', 1000, 100005),
       ('Мимоза', 300, 100005),
       ('Оливье', 400, 100006),
       ('Яичница', 180, 100006),
       ('Тост', 150, 100006),
       ('Суп', 800, 100007),
       ('Паста', 550, 100007),
       ('Пицца', 700, 100007),
       ('Закуска', 450, 100007);
