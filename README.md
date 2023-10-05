# Project with API autotests for the Stellar Burgers service

---
**Autotests for endpoints have been added to the project:**
- Create user
- Login user
- Change user data
- Create order
- Get orders

## Technologies

| <img height="50" src="https://proxys.io/files/blog/Java/javalogo.png" width="50"/>  | Java 11|
|-------------------------------------------------------------------------------------|-----------------|
|<img height="50" src="https://cdn.fs.teachablecdn.com/L2rtxPaRxa4am1VtNegg" width="50"/>| Maven 4.0.0|
|<img height="50" src="https://avatars.githubusercontent.com/u/874086?s=200&amp;v=4" width="50"/>| JUnit 4.13.2|
|<img height="50" src="https://avatars.githubusercontent.com/u/19369327?s=280&v=4" width="50"/>| REST-Assured 5.3.0 |
|<img height="50" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS9HMmuigtfRA2I1XvPSNlRVjl3A4Za7GWZbQ&amp;usqp=CAU" width="50"/>| Allure 2.15.0|
---
## Running tests and viewing the Allure report

1. To run the tests, run the command: `mvn clean test`
2. After completing the run to view the report, run: `mvn allure:serve`

---
[Link to the Stellar Burgers service](https://stellarburgers.nomoreparties.site/)