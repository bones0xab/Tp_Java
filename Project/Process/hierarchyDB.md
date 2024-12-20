# hierarchy of DB
---

erDiagram
    PRODUCTS ||--o{ TRANSACTIONS : has
    USERS ||--o{ TRANSACTIONS : makes
    CATEGORIES ||--o{ PRODUCTS : contains

    PRODUCTS {
        int id PK
        varchar name
        text description
        int quantity
        decimal price
        int category_id FK
        timestamp created_at
        timestamp updated_at
    }

    TRANSACTIONS {
        int id PK
        int product_id FK
        int user_id FK
        enum transaction_type
        int quantity
        text notes
        timestamp transaction_date
    }

    USERS {
        int id PK
        varchar username
        varchar password
        enum role
        timestamp created_at
    }

    CATEGORIES {
        int id PK
        varchar name
        text description
        timestamp created_at
    }

