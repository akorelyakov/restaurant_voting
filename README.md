## REST API application of voting system for deciding where to have lunch using Hibernate/Spring/SpringMVC

> - 2 types of users: admin and regular users
> - Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
> - Menu changes each day (admins do the updates)
> - Users can vote on which restaurant they want to have lunch at 
> - Only one vote counted per user
> - If user votes again the same day:
>     - If it is before 11:00 we assume that he changed his mind.
>     - If it is after 11:00 then it is too late, vote can't be changed
    
**Build: mvn clean package -DskipTests=true cargo:run**

**curl for testing:**
- Get all menus of current day (no auth)
> curl --location --request GET 'http://localhost:8080/restaurant_voting/rest/restaurants/menus'

- Get all users
> curl --location --request GET 'http://localhost:8080/restaurant_voting/rest/admin/users' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'

- Get user
> curl --location --request GET 'http://localhost:8080/restaurant_voting/rest/admin/users/100000' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'

- Get user from his profile
> curl --location --request GET 'http://localhost:8080/restaurant_voting/rest/profile' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'

- Сreate new user
> curl --location --request POST 'http://localhost:8080/restaurant_voting/rest/admin/users' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu' \
--data-raw '{
"name": "New User",
"email": "newUser@yandex.ru",
"password": "123456"
}'

- Delete user
> curl --location --request DELETE 'http://localhost:8080/restaurant_voting/rest/admin/users/100001' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'

- Get all dishes for restaurant
> curl --location --request GET 'http://localhost:8080/restaurant_voting/rest/admin/restaurants/100002/dishes' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'

- Vote for restaurant
> curl --location --request POST 'http://localhost:8080/restaurant_voting/rest/restaurants/100002/votes' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
--data-raw '{}'

- Get votes for restaurant
> curl --location --request GET 'http://localhost:8080/restaurant_voting/rest/restaurants/100002/votes/' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dXNlckB5YW5kZXgucnU6cGFzc3dvcmQ=' \
--data-raw ''

- Get all restaurants
> curl --location --request GET 'http://localhost:8080/restaurant_voting/rest/admin/restaurants' \
--header 'Authorization: Basic YWRtaW5AZ21haWwuY29tOmFkbWlu'

- Update restaurant
> curl --location --request PUT 'http://localhost:8080/restaurant_voting/rest/admin/restaurants/100002' \
--header 'Content-Type: application/json' \
--data-raw '{
"id": 100002,
"name": "Updated restaurant"
}'



