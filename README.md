# Getting Started

### Reference Documentation
Test project for Lunit Assignment
* Maven project
* Openjdk 18
* H2 In-memory DB

### APIs
/swagger-ui/index.html - Swagger Documentation

#### Home
* /v1/register
* /v1/login
  
#### User related
* /v1/users
* /v1/users/{id}

#### Slide related
* /v1/users/{userid}/slides/getAnalysis
* /v1/users/{userId}/slides/upload
* /v1/users/{userId}/slides
* /v1/users/{userId}/slides/requestHistory

#### Grid related
* /v1/users/{userId}/grids
* /v1/users/{userId}/grids/{gridId}

### Docker image
* docker push bgalbadrakh4/lunit-service:tagname

