<h1 align="center">Get and Set of SQL</h1>
---

I'll help you create a simple but effective MySQL database structure for your inventory management system.



```sql
-- Create the database
CREATE DATABASE inventory_management;
USE inventory_management;

-- Create products table
CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    quantity INT NOT NULL DEFAULT 0,
    price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Create transactions table to track stock movements
CREATE TABLE transactions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    product_id INT NOT NULL,
    transaction_type ENUM('IN', 'OUT') NOT NULL,
    quantity INT NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    notes TEXT,
    FOREIGN KEY (product_id) REFERENCES products(id)
);

-- Create users table for authentication
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Insert sample data
INSERT INTO products (name, description, quantity, price) VALUES 
('Laptop', 'High-performance laptop', 10, 999.99),
('Mouse', 'Wireless mouse', 20, 29.99),
('Keyboard', 'Mechanical keyboard', 15, 89.99);

-- Insert a test user (password should be hashed in real application)
INSERT INTO users (username, password, role) VALUES
('admin', 'admin123', 'ADMIN');

-- Create view for product statistics
CREATE VIEW product_statistics AS
SELECT 
    p.id,
    p.name,
    p.quantity as current_stock,
    COUNT(t.id) as total_transactions,
    SUM(CASE WHEN t.transaction_type = 'IN' THEN t.quantity ELSE 0 END) as total_in,
    SUM(CASE WHEN t.transaction_type = 'OUT' THEN t.quantity ELSE 0 END) as total_out
FROM products p
LEFT JOIN transactions t ON p.id = t.product_id
GROUP BY p.id, p.name, p.quantity;

```

Voici les étapes pour mettre en place votre base de données :

1. **Installation et Configuration**
   - Installez MySQL (si ce n'est pas déjà fait)
   - Utilisez MySQL Workbench ou phpMyAdmin pour l'interface

2. **Création de la Base de Données**
   - Copiez et exécutez le script SQL fourni ci-dessus
   - Cela créera :
     - La base de données
     - Les tables nécessaires
     - Des données d'exemple

3. **Structure des Tables**
   ```
   inventory_management/
   ├── products (Produits)
   ├── transactions (Mouvements de stock)
   └── users (Utilisateurs)
   ```

4. **Configuration Java**
   Ajoutez ces dépendances à votre projet :
   ```xml
   <!-- MySQL Connector -->
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.27</version>
   </dependency>
   ```

5. **Connection String**
   ```java
   String url = "jdbc:mysql://localhost:3306/inventory_management";
   String user = "votre_username";
   String password = "votre_password";
   ```

Pour interagir avec la base de données, voici les requêtes SQL principales dont vous aurez besoin :

1. **Produits**
   ```sql
   -- Ajouter un produit
   INSERT INTO products (name, description, quantity, price) 
   VALUES (?, ?, ?, ?);

   -- Obtenir tous les produits
   SELECT * FROM products;

   -- Mettre à jour un produit
   UPDATE products 
   SET name=?, description=?, quantity=?, price=? 
   WHERE id=?;
   ```

2. **Transactions**
   ```sql
   -- Enregistrer une transaction
   INSERT INTO transactions (product_id, transaction_type, quantity, notes)
   VALUES (?, ?, ?, ?);

   -- Voir l'historique d'un produit
   SELECT * FROM transactions 
   WHERE product_id = ?
   ORDER BY transaction_date DESC;
   ```

3. **Statistiques**
   ```sql
   -- Voir les statistiques d'un produit
   SELECT * FROM product_statistics 
   WHERE id = ?;
   ```

Conseils pour la sécurité :

1. Utilisez des PreparedStatements pour éviter les injections SQL
2. Ne stockez jamais les mots de passe en clair
3. Limitez les privilèges de l'utilisateur MySQL
4. Faites des sauvegardes régulières

Voulez-vous que je vous montre comment :
1. Implémenter une classe de connexion à la base de données ?
2. Créer des méthodes CRUD pour gérer les produits ?
3. Gérer les transactions de stock ?
4. Ajouter une fonctionnalité spécifique ?
