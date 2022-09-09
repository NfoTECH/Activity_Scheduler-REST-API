## Testing the API Endpoints

### Registering a user
* Send a POST request to the endpoint `http://localhost:8080/api/v1/user/register` with the following payload:
```json
{
    "name": "Vincent",
    "email": "obedient.2023@gmail.com",
    "password": "obi"
}
```
* You should get a response similar to this:
```json
{
    "status": "success",
    "message": "User created successfully",
    "id": 6,
    "name": "Vincent",
    "email": "obedient.2023@gmail.com",
    "password": "obi",
    "tasks": null
}
```
* If you try to register a user with an email that already exists, you should get a response similar to this:
```json
{
    "status": "error",
    "message": "User with that email already exists"
}
```
### Logging in a user
* Send a GET request to the endpoint `http://localhost:8080/api/v1/user/login` with the following payload:
```json
{
    "email" : "fortunenwachukwu@gmail.com",
    "password" : "1234"
}
```
* You should get a response similar to this:
```json
{
    "status": "success",
    "message": "User logged in successfully",
    "id": 1,
    "name": "Fortunate",
    "email": "fortunenwachukwu@gmail.com",
    "password": "1234",
    "tasks": [
        {
            "id": 5,
            "title": "Bug Fixed",
            "description": "Internal server error fixed",
            "status": "DONE",
            "createdAt": "2022-09-04T02:01:39.775365",
            "updatedAt": "2022-09-04T03:39:49.336647",
            "completedAt": "2022-09-04T03:39:49.334947"
        },
        {
            "id": 1,
            "title": "REST API",
            "description": "Advanced Restful api",
            "status": "PENDING",
            "createdAt": "2022-09-03T22:34:38.804659",
            "updatedAt": "2022-09-04T03:44:48.679015",
            "completedAt": null
        }
    ]
}
```
* If you try to login a user with an email that does not exist, you should get a response similar to this:
```json
{
    "status": "error",
    "message": "User with that email does not exist"
}
```
* If you try to login a user with an incorrect password, you should get a response similar to this:
```json
{
    "status": "error",
    "message": "Incorrect password"
}
```
### Creating a task
* Send a POST request to the endpoint `http://localhost:8080/api/v1/user/createTask/6` with the following payload:
```json
{
  "title" : "Tax Created",
  "description" : "Bug fixed! Task mapped to a specific user"
}
```
* You should get a response similar to this:
```json
{
    "status": "success",
    "message": "Task created successfully",
    "id": 6,
    "title": "Tax Created",
    "description": "Bug fixed! Task mapped to a specific user",
    "status": "PENDING",
    "createdAt": "2022-09-04T03:44:48.679015",
    "updatedAt": "2022-09-04T03:44:48.679015",
    "completedAt": null
}
```
### Getting all tasks
* Send a GET request to the endpoint `http://localhost:8080/api/v1/user/viewTasks` with the following payload`
* You should get a response similar to this:
```json

[
    {
        "id": 5,
        "title": "Bug Fixed",
        "description": "Internal server error fixed",
        "status": "DONE",
        "createdAt": "2022-09-04T02:01:39.775365",
        "updatedAt": "2022-09-04T03:39:49.336647",
        "completedAt": "2022-09-04T03:39:49.334947"
    },
    {
        "id": 2,
        "title": "PostgreSQL",
        "description": "Checkout PGAdmin latest version",
        "status": "IN_PROGRESS",
        "createdAt": "2022-09-03T22:36:26.580349",
        "updatedAt": "2022-09-04T03:41:40.360972",
        "completedAt": null
    },
    {
        "id": 1,
        "title": "REST API",
        "description": "Advanced Restful api",
        "status": "PENDING",
        "createdAt": "2022-09-03T22:34:38.804659",
        "updatedAt": "2022-09-04T03:44:48.679015",
        "completedAt": null
    },
    {
        "id": 6,
        "title": "Tax Created",
        "description": "Bug fixed! Task mapped to a specific user",
        "status": "PENDING",
        "createdAt": "2022-09-09T02:41:51.130583",
        "updatedAt": "2022-09-09T02:41:51.13065",
        "completedAt": null
    }
]
```
### Getting a task by id
* Send a GET request to the endpoint `http://localhost:8080/api/v1/user/getTask/1`
* You should get a response similar to this:
```json
{
    "id": 1,
    "title": "REST API",
    "description": "Advanced Restful api",
    "status": "IN_PROGRESS",
    "createdAt": "2022-09-03T22:34:38.804659",
    "updatedAt": "2022-09-04T03:44:48.679015",
    "completedAt": null
}
```
### Updating a task
* Send a PUT request to the endpoint `http://localhost:8080/api/v1/user/updateTask/1` with the following payload:
```json
 {
  "id": 1,
  "title": "REST API",
  "description": "Advanced Restful Api in progress"
}
```
* You should get a response similar to this:
```json
{
    "status": "success",
    "message": "Task updated successfully",
    "id": 1,
    "title": "REST API",
    "description": "Advanced Restful Api in progress",
    "status": "PENDING",
    "createdAt": "2022-09-03T22:34:38.804659",
    "updatedAt": "2022-09-04T03:44:48.679015",
    "completedAt": null
}
```
### Moving a task forward to the done state
* Send a GET request to the endpoint `http://localhost:8080/api/v1/user/moveForward/2`
* You should get a response similar to this:
```json
{
    "status": "success",
    "message": "Task status moved forward to DONE",
    "id": 2,
    "title": "PostgreSQL",
    "description": "Checkout PGAdmin latest version",
    "status": "DONE",
    "createdAt": "2022-09-03T22:36:26.580349",
    "updatedAt": "2022-09-04T03:41:40.360972",
    "completedAt": "2022-09-04T03:41:40.359272"
}
```
### Moving a task backward to the pending state
* Send a GET request to the endpoint `http://localhost:8080/api/v1/user/moveBackward/1`
* You should get a response similar to this:
```json
{
    "status": "success",
    "message": "Task status moved backward to PENDING",
    "id": 1,
    "title": "REST API",
    "description": "Advanced Restful Api in progress",
    "status": "PENDING",
    "createdAt": "2022-09-03T22:34:38.804659",
    "updatedAt": "2022-09-04T03:44:48.679015",
    "completedAt": null
}
```
### Deleting a task
* Send a DELETE request to the endpoint `http://localhost:8080/api/v1/user/deleteTask/1`
* You should get a response similar to this:
```json
{
    "status": "success",
    "message": "Task deleted successfully"
}
```
