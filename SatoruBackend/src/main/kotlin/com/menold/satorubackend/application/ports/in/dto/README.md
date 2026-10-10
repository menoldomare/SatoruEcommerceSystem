com.company.project/
│
├── adapter/
│   ├── in/
│   │   └── web/
│   │       ├── UserController.kt
│   │       └── dto/                         <-- 1. Web / Request-Response DTOs
│   │           ├── RegisterUserRequest.kt
│   │           └── UserResponse.kt
│   │
│   └── out/
│       └── persistence/
│           ├── UserRepositoryImpl.kt
│           ├── UserEntity.kt                <-- 2. Database Entities / JPA DTOs
│           └── SpringDataUserRepository.kt
│
├── application/
│   ├── port/
│   │   ├── in/
│   │   │   ├── RegisterUserUseCase.kt
│   │   │   └── dto/                         <-- 3. Command / Query DTOs (Input Ports)
│   │   │       └── RegisterUserCommand.kt
│   │   │
│   │   └── out/                             <-- Output Ports (Domain Repository interfaces)
│   │       └── LoadUserPort.kt
│   │
│   └── service/                             <-- Use Case implementations
│       └── RegisterUserService.kt
│
└── domain/                                  <-- Pure Domain Aggregates & Value Objects
├── User.kt                              <-- Core Domain Model (NOT a DTO)
└── valueobject/
├── Email.kt
└── PasswordHash.kt