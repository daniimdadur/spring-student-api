# Department Api

## Get List Department Api

Endpoint : GET /api/v1/department

Headers:
- Authorization : --

Response Body Success :

```json
{
  "statusCode": 200,
  "message": "success",
  "data": [
    {
      "id": "3686bca2809b4c328526fdfa559b8cf7",
      "name": "Department Techno",
      "students": [
        {
          "id": "115286a6a53f4dca9ae980d69e84374f",
          "name": "bruno",
          "email": "bruno@gentara.com",
          "departmentId": "3686bca2809b4c328526fdfa559b8cf7",
          "departmentName": "Department Techno",
          "enrollments": null
        }
      ],
      "lecturers": [
        {
          "id": "0354767bf7c646b6ad734e910f4e4043",
          "name": "Michael",
          "email": "michael@gentara.com",
          "title": "S.T",
          "departmentId": "3686bca2809b4c328526fdfa559b8cf7",
          "departmentName": "Department Techno",
          "lecturerCourses": null
        }
      ]
    }
  ]
}
```

Response Body Error :

```json
{
    "timestamp": "2025-08-21T11:48:35.0584851",
    "statusCode": 500,
    "message": "INTERNAL_SERVER_ERROR",
    "error": "No static resource api/v1/departments."
}
```

## Get Department Api

Endpoint : GET /api/v1/department/{id}

Headers:
- Authorization : --

Response Body Success :

```json
{
  "statusCode": 200,
  "message": "success",
  "data": [
    {
      "id": "3686bca2809b4c328526fdfa559b8cf7",
      "name": "Department Techno",
      "students": [
        {
          "id": "115286a6a53f4dca9ae980d69e84374f",
          "name": "bruno",
          "email": "bruno@gentara.com",
          "departmentId": "3686bca2809b4c328526fdfa559b8cf7",
          "departmentName": "Department Techno",
          "enrollments": null
        }
      ],
      "lecturers": [
        {
          "id": "0354767bf7c646b6ad734e910f4e4043",
          "name": "Michael",
          "email": "michael@gentara.com",
          "title": "S.T",
          "departmentId": "3686bca2809b4c328526fdfa559b8cf7",
          "departmentName": "Department Techno",
          "lecturerCourses": null
        }
      ]
    }
  ]
}
```

Response Body Error :

```json
{
    "timestamp": "2025-08-21T11:48:35.0584851",
    "statusCode": 500,
    "message": "INTERNAL_SERVER_ERROR",
    "error": "No static resource api/v1/departments."
}
```

## Create Department Api

Endpoint : POST /api/v1/department

Headers:
- Authorization : --

Request Body :

```json
{
    "name": "farmer department"
}
```

Response Body Success :

```json
{
  "statusCode": 200,
  "message": "success",
  "data": {
    "id": "4dbb8c81ad4a4850bd4760fefab610f6",
    "name": "farmer department",
    "students": [],
    "lecturers": []
  }
}
```

Response Body Error :

```json
{
    "timestamp": "2025-08-21T11:48:35.0584851",
    "statusCode": 500,
    "message": "INTERNAL_SERVER_ERROR",
    "error": "No static resource api/v1/departments."
}
```

## Update Department Api

Endpoint : PATCH /api/v1/department/{id}

Headers:
- Authorization : --

Request Body :

```json
{
    "name": "farmer department 2"
}
```

Response Body Success :

```json
{
  "statusCode": 200,
  "message": "success",
  "data": {
    "id": "4dbb8c81ad4a4850bd4760fefab610f6",
    "name": "farmer department 2",
    "students": [],
    "lecturers": []
  }
}
```

Response Body Error :

```json
{
    "timestamp": "2025-08-21T11:48:35.0584851",
    "statusCode": 500,
    "message": "INTERNAL_SERVER_ERROR",
    "error": "No static resource api/v1/departments."
}
```

## Delete Department Api

Endpoint : DELETE /api/v1/department/{id}

Headers:
- Authorization : --

Response Body Success :

```json
{
  "statusCode": 200,
  "message": "success",
  "data": {
    "id": "4dbb8c81ad4a4850bd4760fefab610f6",
    "name": "farmer department 2",
    "students": [],
    "lecturers": []
  }
}
```

Response Body Error :

```json
{
    "timestamp": "2025-08-21T11:48:35.0584851",
    "statusCode": 500,
    "message": "INTERNAL_SERVER_ERROR",
    "error": "No static resource api/v1/departments."
}
```