# Todo Tracker
Todo Tracker is a task tracking application that allows you to manage and track your tasks based on their status and importance.

# How to Run the Project

1. Clone the repository to your local machine: `git clone https://github.com/weronika0000/todo-tracker.git`
2. Navigate to the project directory: `cd todo-tracker`
3. Build the project using Maven: `mvn clean install`
4. Run the application: `mvn spring-boot:run`

# Usage

The application will start running on http://localhost:8080

You can access and interact with the Todo Tracker application using your preferred HTTP client tool, such as **Postman.**
The database is set up with H2, and its file is stored within the project structure, allowing you to browse the existing data and perform operations.

The following features are available:

* Create new tasks
* Update tasks
* Delete tasks
* Retrieve tasks
* Filter tasks

# Endpoints
The following endpoints are available for managing tasks:


* `POST /api/tasks`: Create a new task.
* `GET /api/tasks`: Retrieve all tasks.
* `GET /api/tasks/{id}`: Retrieve a specific task by ID.
* `PUT /api/tasks/{id}`: Update a specific task by ID.
* `DELETE /api/tasks/{id}`: Delete a specific task by ID.
* `GET /api/tasks/filtered`: Retrieve filtered tasks based on parameters.



    Query Parameters:

    * status (optional): Filter tasks by status (TODO, DOING, DONE).
    * importance (optional): Filter tasks by importance level.(LOW, AVERAGE", HIGH)
    * sortBy (optional): Sort tasks by a specific field (e.g., "deadline", "importance").
    * sortDirection (optional, default: "ASC"): Sort direction for the sorting field ("ASC" or "DESC").


Please note that the /api/tasks/filtered endpoint allows you to retrieve tasks based on various filters and sorting options.

* `GET /actuator/metrics/http.server.requests`: Count the number of executed requests.
# Task format

A task object has the following properties:

* `taskId`: The unique identifier of the task.
* `title`: The title of the task. (Required, cannot be empty)
* `details`: The description of the task. (Not Required, can be empty,must not exceed 1800 characters)
* `status`: The status of the task (TODO, DOING, DONE). (Required)
* `importance`: The importance of the task (LOW, AVERAGE", HIGH). (Required)
* `deadline`: The deadline of the task in ISO 8601 date format (e.g., "2023-06-30"). (Required, must be a future or present date)
* `createdAt` (string): The timestamp when the task was created.
* `modifiedAt` (string): The timestamp when the task was last modified.

# Create Task Request

To create a new task, send a POST request to /api/tasks with the following example request body (_json_):



~~~~
{
"title": "Task Title",
"details": "Task Details",
"status": "TODO",
"importance": "AVERAGE",
"deadline": "2023-06-30"
}
~~~~

# Structure

The Todo Tracker application follows a layered architecture design pattern, separating concerns into different layers:

**1. boundary:**
* controller: Contains classes responsible for handling HTTP requests and defining the API endpoints.
* dto: Includes Data Transfer Objects (DTOs) used for transferring data between the client and the server.
* mapper: Provides classes for mapping between entities and DTOs.

**2. control:**
* repository: Contains class responsible for interacting with the database and performing CRUD operations.
* service: Includes classes implementing the business logic of the application, handling operations on tasks.

**3. entity:**

* Contains classes representing the domain entities, such as the Task entity, which defines the structure and properties of a task.