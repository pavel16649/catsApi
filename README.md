#Стэк#
Java, Spring (Core, Data JPA, Security, MVC), Postgres. Сборка - maven, контейнеризация - docker.

#Api-gateway#
Отвечает за безопасность - аутентификацию пользователей и пропускает дальше в сервисы.

#Cat-friendships#
Информация о дружбах котиков.

#Cats#
Информация о котиках - кличка, порода, дата рождения, хозяин и тд.

#Owners#
Информация о хозяевах - имя, дата рождения.

#Common#
Общие зависимости maven.

#Общение микросервисов#
Выбран брокер RabbitMQ.

#API#
RESTful API

GET http://localhost:8080/catsapi/cats/ - получение всех котиков (только ROLE_ADMIN)
GET http://localhost:8080/catsapi/cats/{id} - получение котика с id {id}
DELETE http://localhost:8080/catsapi/cats/{id} - удаление котика с id {id}
POST http://localhost:8080/catsapi/cats/ - добавление котика (необходимо, чтобы хозяин уже существовал в базе)
GET http://localhost:8080/catsapi/owners/{id} - получение всех хозяев (только ROLE_ADMIN)
GET http://localhost:8080/catsapi/owners/{id} - получение хозяина {id}
POST http://localhost:8080/catsapi/owners/ - добавление хозяина
DELETE http://localhost:8080/catsapi/owners/{id} - удаление хозяина с id {id}
GET http://localhost:8080/catsapi/friendships/{id} - получение всех друзей котика {id}
POST http://localhost:8080/catsapi/friendships/{id}/{id1} - добавление дружбы котиков {id} и {id1}
DELETE http://localhost:8080/catsapi/friendships/{id}/{id1} - удаление дружбы котиков {id} и {id1}
