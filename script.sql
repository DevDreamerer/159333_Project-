-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: nzHub
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- 创建新西兰农产品数据库
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `nzHub` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `nzHub`;

-- ----------------------------
-- 农产品分类表 (Product Categories) - 结构调整
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `parent_id` int NOT NULL COMMENT '父分类ID',
  `type` int DEFAULT NULL COMMENT '层级 (1:一级 2:二级 3:三级)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=500 DEFAULT CHARSET=utf8;

-- 插入完整的农业分类数据
LOCK TABLES `product_category` WRITE;
INSERT INTO `product_category` VALUES

(100, 'Fresh Produce', 0, 1),        -- 一级分类：生鲜产品
(200, 'Dairy Products', 0, 1),       -- 一级分类：乳制品
(300, 'Wine & Beverages', 0, 1),     -- 一级分类：酒类饮品
(400, 'Meat & Poultry', 0, 1),       -- 一级分类：肉类禽类
(500, 'Natural Products', 0, 1),     -- 一级分类：天然产品

-- Level 2 分类 (Fresh Produce) --
(101, 'Fruits', 100, 2),             -- 二级分类：水果
(102, 'Vegetables', 100, 2),         -- 二级分类：蔬菜

-- Level 3 分类 (Fruits) --
(103, 'Kiwifruit', 101, 3),          -- 三级分类：奇异果
(104, 'Apples', 101, 3),             -- 三级分类：苹果
(105, 'Berries', 101, 3),            -- 三级分类：浆果

-- Level 2 分类 (Dairy Products) --
(201, 'Milk', 200, 2),               -- 二级分类：牛奶
(202, 'Cheese', 200, 2),             -- 二级分类：奶酪

-- Level 3 分类 (Milk) --
(203, 'Organic Milk', 201, 3),       -- 三级分类：有机牛奶
(204, 'A2 Milk', 201, 3),            -- 三级分类：A2牛奶

-- Level 2 分类 (Wine & Beverages) --
(301, 'White Wine', 300, 2),         -- 二级分类：白葡萄酒
(302, 'Red Wine', 300, 2),           -- 二级分类：红葡萄酒

-- Level 3 分类 (White Wine) --
(303, 'Sauvignon Blanc', 301, 3),    -- 三级分类：长相思
(304, 'Chardonnay', 301, 3),         -- 三级分类：霞多丽

-- Level 2 分类 (Meat & Poultry) --
(401, 'Beef & Lamb', 400, 2),        -- 二级分类：牛羊肉
(402, 'Poultry', 400, 2),            -- 二级分类：禽类

-- Level 3 分类 (Beef & Lamb) --
(403, 'Lamb Cuts', 401, 3),          -- 三级分类：羊肉分割
(404, 'Beef Steaks', 401, 3),        -- 三级分类：牛排

-- Level 2 分类 (Natural Products) --
(501, 'Honey', 500, 2),              -- 二级分类：蜂蜜
(502, 'Essential Oils', 500, 2),     -- 二级分类：精油

-- Level 3 分类 (Honey) --
(503, 'Manuka Honey', 501, 3),       -- 三级分类：麦卢卡蜂蜜
(504, 'Clover Honey', 501, 3);       -- 三级分类：三叶草蜂蜜
UNLOCK TABLES;

-- ----------------------------
-- 商品表 (Products) - 修复分类ID
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `price` float NOT NULL,
  `stock` int NOT NULL,
  -- 确保所有分类ID非空 --
  `categorylevelone_id` int NOT NULL,
  `categoryleveltwo_id` int NOT NULL,
  `categorylevelthree_id` int NOT NULL,
  `file_name` varchar(200) DEFAULT NULL,
  `origin` varchar(100) DEFAULT NULL,
  `shelf_time` datetime DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `rating` decimal(3,1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8;

-- 插入商品数据（每个商品严格匹配三级分类）
LOCK TABLES `product` WRITE;
INSERT INTO `product` VALUES
-- 奇异果 (100 → 101 → 103) --
(1, 'Zespri SunGold Kiwifruit', 'Premium Golden Kiwifruit, Rich in Vitamin C', 8.99, 500,
 100, 101, 103, 'kiwi.jpg', 'Bay of Plenty', '2023-08-01', 'Zespri', 4.8),

-- 苹果 (100 → 101 → 104) --
(2, 'Rockit Apples', 'Crisp and Sweet Mini Apples', 4.50, 300,
 100, 101, 104, 'apple.jpg', 'Hawke''s Bay', '2023-08-05', 'Rockit', 4.6),

-- 有机牛奶 (200 → 201 → 203) --
(3, 'Lewis Road Organic Milk', 'Creamy Organic Full Cream Milk', 6.99, 200,
 200, 201, 203, 'milk.jpg', 'Waikato', '2023-07-20', 'Lewis Road', 4.7),

-- 长相思葡萄酒 (300 → 301 → 303) --
(4, 'Cloudy Bay Sauvignon Blanc', 'Award-Winning Marlborough White Wine', 29.99, 100,
 300, 301, 303, 'wine.jpg', 'Marlborough', '2023-06-15', 'Cloudy Bay', 4.9),

-- 麦卢卡蜂蜜 (500 → 501 → 503) --
(5, 'Comvita Manuka Honey MGO 250+', 'Medical-Grade Manuka Honey', 89.99, 150,
 500, 501, 503, 'manuka.jpg', 'Northland', '2023-08-10', 'Comvita', 4.8),

-- 羊腿肉 (400 → 401 → 403) --
(6, 'Silver Fern Farms Lamb Leg', 'Grass-Fed Premium Lamb Leg', 24.99, 80,
 400, 401, 403, 'lamb.jpg', 'Canterbury', '2023-08-12', 'Silver Fern', 4.5),

-- 牛排 (400 → 401 → 404) --
(7, 'First Light Wagyu Ribeye Steak', 'Marbled Wagyu Beef Steak', 39.99, 50,
 400, 401, 404, 'wagyu.jpg', 'Waikato', '2023-08-15', 'First Light', 4.7);
UNLOCK TABLES;
-- --------------------------------------------------------
-- 其他表结构保持不变 (用户、购物车、订单等)
-- --------------------------------------------------------
-- ... (此处保留原有用户表、购物车表结构，数据可清空或调整) ...

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `cost` float DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `productId` (`product_id`),
  KEY `userId` (`user_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` int NOT NULL COMMENT '订单主键',
  `product_id` int NOT NULL COMMENT '商品主键',
  `quantity` int NOT NULL COMMENT '数量',
  `cost` float NOT NULL COMMENT '消费',
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___66E1BD8E2F10007B` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int DEFAULT NULL COMMENT '用户主键',
  `login_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `user_address` varchar(255) DEFAULT NULL COMMENT '用户地址',
  `cost` float DEFAULT NULL COMMENT '总金额',
  `serialnumber` varchar(255) DEFAULT NULL COMMENT '订单号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--


--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_name` varchar(255) NOT NULL COMMENT '登录名',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `gender` int NOT NULL DEFAULT '1' COMMENT '性别(1:男 0：女)',
  `email` varchar(80) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `file_name` varchar(255) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PK__EASYBUY___C96109CC3A81B327` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- Table structure for table `user_address`
--

DROP TABLE IF EXISTS `user_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_address` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int DEFAULT NULL COMMENT '用户主键',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `isdefault` int DEFAULT '0' COMMENT '是否是默认地址（1:是 0否）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


