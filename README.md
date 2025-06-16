# 🌱 AgriNZHub

**AgriNZHub** is a e-commerce platform for New Zealand agricultural market. It supports both customer and administrator functionalities, users can browse and purchase products while allowing admins could manage users and inventory.


## 🗂 Branch Structure

The project code is divided into two branches, each corresponding to one role of the system:

- [`customer_branch`]: Customer-facing site and functionality
- [`master`]: Admin portal for backend management

> ✅ Please switch to the appropriate branch depending on which part of the system you'd like to explore.


## 📌 System Overview

The system includes two main roles: customer and administrator.

### 👤 Customer-Side Features
- User registration and login
- Multi-level product category browsing
- View detailed product information (price, origin, brand, stock, etc.)
- Add products to the cart and complete checkout
- Manage personal profile and shipping addresses
- View order history

### 🛠 Administrator Features
- Secure admin login with input validation （At present, there is only one administrator account with the username 'admin' and password 'admin666'）
- User management: create, update, search, and delete inactive users
- Product management: add, update, delete products; modify stock and pricing
- Changes are reflected in real time on the customer interface

All data is stored in a **MySQL database**, you can access the sql script through **master** or **customer_branch**, the file name is **script.sql**.

⚠️Note: Everyone's database path, username, and password may be different, so in order to successfully run the code, you need to change the relevant information of the database in the **application.yml** file in the java code, and change the path, username, and password to match your database.

---

## 🧰 Tech Stack

| Layer         | Technologies Used                              |
|---------------|-------------------------------------------------|
| Backend       | Spring Boot, MyBatis Plus                       |
| Frontend      | Thymeleaf, HTML, CSS, JavaScript                |
| Database      | MySQL                                           |
| Architecture  | MVC                                             |

---

