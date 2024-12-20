<h1>DATABASE Commands</h1>



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
