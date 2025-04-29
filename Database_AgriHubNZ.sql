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
-- Current Database: `nzHub`
--
DROP DATABASE IF EXISTS `nzHub`;
CREATE DATABASE /*!32312 IF NOT EXISTS*/ `nzHub` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `nzHub`;

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
) ENGINE=InnoDB AUTO_INCREMENT=326 DEFAULT CHARSET=utf8mb3;
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
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8mb3;
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
) ENGINE=InnoDB AUTO_INCREMENT=178 DEFAULT CHARSET=utf8mb3;
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

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` varchar(1024) DEFAULT NULL,
  `price` float NOT NULL,
  `stock` int NOT NULL,
  `categorylevelone_id` int NOT NULL,
  `categoryleveltwo_id` int NOT NULL,
  `categorylevelthree_id` int NOT NULL,
  `file_name` varchar(200) DEFAULT NULL,
  `origin` varchar(100) DEFAULT NULL,
  `shelf_time` datetime DEFAULT NULL,
  `brand` varchar(50) DEFAULT NULL,
  `rating` decimal(3,1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `name`, `description`, `price`, `stock`, `categorylevelone_id`, `categoryleveltwo_id`, `categorylevelthree_id`, `file_name`, `origin`, `shelf_time`, `brand`, `rating`) VALUES (1,'Zespri SunGold Kiwifruit','Premium Golden Kiwifruit, Rich in Vitamin C',8.99,500,100,101,103,'kiwi.jpg','Bay of Plenty','2023-08-01 00:00:00','Zespri',4.8),(2,'Rockit Apples','Crisp and Sweet Mini Apples',4.5,300,100,101,104,'apple.jpg','Hawke\'s Bay','2023-08-05 00:00:00','Rockit',4.6),(3,'Lewis Road Organic Milk','Creamy Organic Full Cream Milk',6.99,200,200,201,203,'milk.jpg','Waikato','2023-07-20 00:00:00','Lewis Road',4.7),(4,'Cloudy Bay Sauvignon Blanc','Award-Winning Marlborough White Wine',29.99,100,300,301,303,'wine.jpg','Marlborough','2023-06-15 00:00:00','Cloudy Bay',4.9),(5,'Comvita Manuka Honey MGO 250+','Medical-Grade Manuka Honey',89.99,150,500,501,503,'manuka.jpg','Northland','2023-08-10 00:00:00','Comvita',4.8),(6,'Silver Fern Farms Lamb Leg','Grass-Fed Premium Lamb Leg',24.99,80,400,401,403,'lamb.jpg','Canterbury','2023-08-12 00:00:00','Silver Fern',4.5),(7,'First Light Wagyu Ribeye Steak','Marbled Wagyu Beef Steak',39.99,50,400,401,404,'wagyu.jpg','Waikato','2023-08-15 00:00:00','First Light',4.7),(8,'Baby Spinach','Tender Organic Baby Spinach Leaves',5.99,200,100,102,107,'spinach.jpg','Pukekohe','2023-08-18 00:00:00','FreshCo',4.6),(9,'Free-Range Chicken Breast','Skinless Free-Range Chicken Breast',12.99,150,400,402,405,'chicken_breast.jpg','Waikato','2023-08-20 00:00:00','Bostock',4.7),(10,'Felton Road Pinot Noir','Central Otago Premium Pinot Noir',79.99,60,300,302,305,'pinot.jpg','Central Otago','2023-07-25 00:00:00','Felton Road',4.9),(11,'Mainland Tasty Cheddar','Aged Cheddar Cheese Block',8.5,120,200,202,205,'cheddar.jpg','Taranaki','2023-08-22 00:00:00','Mainland',4.5),(12,'Happy Hens Free-Range Eggs','6-Pack Free-Range Eggs',6.99,300,400,402,406,'eggs.jpg','Canterbury','2023-08-15 00:00:00','Happy Hens',4.8),(13,'Esk Valley Cabernet Sauvignon','Hawke\'s Bay Bold Red Wine',45.99,70,300,302,306,'cabernet.jpg','Hawke\'s Bay','2023-08-15 00:00:00','Esk Valley',4.7),(14,'Whitestone Blue Cheese','Creamy Blue Vein Cheese',12.99,80,200,202,206,'blue_cheese.jpg','Oamaru','2023-08-25 00:00:00','Whitestone',4.6),(15,'Fresh Nelson Raspberries','Plump Juicy Raspberries',9.99,90,100,101,105,'raspberries.jpg','Nelson','2023-08-20 00:00:00','BerryFresh',4.5);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `parent_id` int NOT NULL COMMENT '父分类ID',
  `type` int DEFAULT NULL COMMENT '层级 (1:一级 2:二级 3:三级)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=505 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` (`id`, `name`, `parent_id`, `type`) VALUES (100,'Fresh Produce',0,1),(101,'Fruits',100,2),(102,'Vegetables',100,2),(103,'Kiwifruit',101,3),(104,'Apples',101,3),(105,'Berries',101,3),(107,'Leafy Greens',102,3),(200,'Dairy Products',0,1),(201,'Milk',200,2),(202,'Cheese',200,2),(203,'Organic Milk',201,3),(204,'A2 Milk',201,3),(205,'Cheddar',202,3),(206,'Blue Cheese',202,3),(300,'Wine & Beverages',0,1),(301,'White Wine',300,2),(302,'Red Wine',300,2),(303,'Sauvignon Blanc',301,3),(304,'Chardonnay',301,3),(305,'Pinot Noir',302,3),(306,'Cabernet Sauvignon',302,3),(400,'Meat & Poultry',0,1),(401,'Beef & Lamb',400,2),(402,'Poultry',400,2),(403,'Lamb Cuts',401,3),(404,'Beef Steaks',401,3),(405,'Chicken',402,3),(406,'Free-Range Eggs',402,3),(500,'Natural Products',0,1),(501,'Honey',500,2),(502,'Essential Oils',500,2),(503,'Manuka Honey',501,3),(504,'Clover Honey',501,3);
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `login_name`, `user_name`, `password`, `gender`, `email`, `mobile`, `file_name`, `create_time`, `update_time`) VALUES (58,'test','张三','b3472e734711122a5c892c3a93705ed11d1513d59b107d57',1,'123@test.com','13312345678',NULL,'2021-11-18 12:55:37','2021-11-18 12:55:37'),(59,'test2','张三','f1bc53d58d67b98a3d41a247a94f5b11de5aa2bc61596723',1,'123@test.com','13312345678',NULL,'2021-11-18 13:00:07','2021-11-18 13:00:07'),(60,'MaeveMe','aa aa','d9df2800db97732108f13736271a0913087ba5b781b37385',1,'215378@ss.com','15011108848',NULL,'2025-04-07 07:27:20','2025-04-07 07:27:20');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
INSERT INTO `user_address` (`id`, `user_id`, `address`, `remark`, `isdefault`, `create_time`, `update_time`) VALUES (49,56,'IT园','公司',0,'2020-05-21 15:55:24','2020-07-29 11:22:13'),(50,56,'软件园','公司',0,'2020-05-22 15:11:07','2020-07-25 09:14:19'),(57,56,'测试','测试',0,'2021-11-17 18:04:49','2021-11-17 18:04:49'),(59,59,'软件园','公司',1,'2021-11-18 13:01:19','2021-11-18 13:01:19');
/*!40000 ALTER TABLE `user_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'nzhub'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-26 14:50:30
