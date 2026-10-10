When you receive a request, think:

What is the user trying to do?

For:

POST /api/products
-------------------------
think:

HTTP request
↓
Controller
"Someone wants to create a product."
↓
DTO
"Is this request shaped correctly?"
↓
Service
"What needs to happen to create this product?"
↓
Domain/business rules
"Is this product valid according to our business?"
↓
Repository
"Save it."
↓
JPA/Hibernate
"Turn this object into SQL."
↓
PostgreSQL
"Persist it."
-----------------------------------------
Then back up:

PostgreSQL
↓
Hibernate
↓
Repository
↓
Service
↓
DTO
↓
Controller
↓
JSON response

If you understand that flow, you understand the core of Spring Boot application development.\


--------------------------
1. Domain Model
   ↓
2. JPA Entity
   ↓
3. Repository
   ↓
4. Service
   ↓
5. DTO
   ↓
6. Controller
