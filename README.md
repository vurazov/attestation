# Service of keeping of the scores 

Compile project: mvn clean install

API and examples of using:

1. Authorize existing rsUser or add new rsUser to base
PUT: http://localhost:8080/scores/service/rsUser?login=user2&password=passw2
Returns rsUser.
API set cookie 'uuid' for authorize rsUser in the following requests. Set this cookie for the following requests.
