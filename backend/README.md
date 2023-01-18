# ICONSDK-EXPLORER backend

## ICONSDK-EXPLORER Web
Base URLs:
* <a href="http://localhost:8080">http://localhost:8080
    -  server.port in application.properties [[Config Guide](../doc/config.md)]
    
---
## Swagger URLs
* <a href="http://localhost:8080/swagger-ui.html">http://localhost:8080/swagger-ui.html</a>

---

## Backend Package configuration
```bash
├── main
│   ├── java
│   │   └── com
│   │       ├── dfg
│   │       │   └── icon
│   │       │       ├── config
│   │       │       ├── core
│   │       │       │   ├── common
│   │       │       │   │   └── service
│   │       │       │   │       └── impl
│   │       │       │   ├── dao
│   │       │       │   │   └── icon
│   │       │       │   ├── exception
│   │       │       │   ├── mappers
│   │       │       │   │   └── icon
│   │       │       │   ├── v0
│   │       │       │   │   └── service
│   │       │       │   │       └── impl
│   │       │       │   ├── v2
│   │       │       │   │   ├── adapter
│   │       │       │   │   │   └── impl
│   │       │       │   │   ├── service
│   │       │       │   │   │   └── impl
│   │       │       │   │   └── vo
│   │       │       │   └── v3
│   │       │       │       ├── adapter
│   │       │       │       │   └── impl
│   │       │       │       ├── scheduler
│   │       │       │       ├── service
│   │       │       │       │   ├── database
│   │       │       │       │   │   └── tenant
│   │       │       │       │   └── impl
│   │       │       │       └── vo
│   │       │       ├── thread
│   │       │       ├── util
│   │       │       └── web
│   │       │           ├── v0
│   │       │           │   ├── controller
│   │       │           │   └── dto
│   │       │           │       ├── block
│   │       │           │       └── main
│   │       │           └── v3
│   │       │               ├── controller
│   │       │               └── dto
│   │       └── theromus
│   │           └── sha
│   └── resources
│       ├── core
│       │   └── mappers
│       │       └── icon
│       └── static
│           └── test

