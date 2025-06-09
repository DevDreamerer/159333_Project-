-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: nzhub
-- ------------------------------------------------------
-- Server version	8.0.41

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

CREATE DATABASE IF NOT EXISTS `nzhub`;
USE `nzhub`;
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
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` (`id`, `product_id`, `quantity`, `cost`, `user_id`, `create_time`, `update_time`) VALUES (337,2,1,4.5,60,'2025-04-29 11:52:55','2025-04-29 11:52:55');
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
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` (`id`, `order_id`, `product_id`, `quantity`, `cost`) VALUES (178,178,1,2,17.98),(179,178,1,1,8.99),(180,179,2,1,4.5),(181,180,10,1,79.99),(182,181,2,1,4.5),(183,182,8,1,5.99),(184,183,8,1,5.99),(185,183,2,2,9),(186,183,2,1,4.5),(187,183,10,2,159.98),(188,184,17,1,4.49);
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
) ENGINE=InnoDB AUTO_INCREMENT=185 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `user_id`, `login_name`, `user_address`, `cost`, `serialnumber`, `create_time`, `update_time`) VALUES (178,60,'MaeveMe','11',26.97,'0E30D1C276840F544CA249549956EDCB','2025-04-28 12:03:59','2025-04-28 12:03:59'),(179,60,'MaeveMe','11',4.5,'C30543A2DA64AFAA908DE646BB919F2D','2025-04-28 14:44:44','2025-04-28 14:44:44'),(180,60,'MaeveMe','11',79.99,'700D69B032C0812162D648EEB4100353','2025-04-28 14:47:12','2025-04-28 14:47:12'),(181,60,'MaeveMe','11',4.5,'9F767CA2007822024E1FF9EB8ACF5B29','2025-04-29 10:32:01','2025-04-29 10:32:01'),(182,60,'MaeveMe','11',5.99,'169F256C831DFFAA79E3C928A0B8D79A','2025-04-29 10:57:20','2025-04-29 10:57:20'),(183,60,'MaeveMe','12',179.47,'E897F7B4BF7161A498E5E6A54FC2545C','2025-04-29 11:24:57','2025-04-29 11:24:57'),(184,60,'MaeveMe','12',4.49,'596813A64C456E0F056AD788D53DC355','2025-04-29 11:49:51','2025-04-29 11:49:51');
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
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`, `name`, `description`, `price`, `stock`, `categorylevelone_id`, `categoryleveltwo_id`, `categorylevelthree_id`, `file_name`, `origin`, `shelf_time`, `brand`, `rating`) VALUES (1,'Zespri SunGold Kiwifruit','Premium Golden Kiwifruit, Rich in Vitamin C',8.99,497,100,101,103,'kiwi.jpg','Bay of Plenty','2023-08-01 00:00:00','Zespri',4.8),(2,'Rockit Apples','Crisp and Sweet Mini Apples',4.5,294,100,101,104,'apple.jpg','Hawke\'s Bay','2023-08-05 00:00:00','Rockit',4.6),(3,'Lewis Road Organic Milk','Creamy Organic Full Cream Milk',6.99,200,200,201,203,'milk.jpg','Waikato','2023-07-20 00:00:00','Lewis Road',4.7),(4,'Cloudy Bay Sauvignon Blanc','Award-Winning Marlborough White Wine',29.99,100,300,301,303,'wine.jpg','Marlborough','2023-06-15 00:00:00','Cloudy Bay',4.9),(5,'Comvita Manuka Honey MGO 250+','Medical-Grade Manuka Honey',89.99,150,500,501,503,'manuka.jpg','Northland','2023-08-10 00:00:00','Comvita',4.8),(6,'Silver Fern Farms Lamb Leg','Grass-Fed Premium Lamb Leg',24.99,80,400,401,403,'lamb_leg.jpg','Canterbury','2023-08-12 00:00:00','Silver Fern',4.5),(7,'First Light Wagyu Ribeye Steak','Marbled Wagyu Beef Steak',39.99,50,400,401,404,'wagyu.jpg','Waikato','2023-08-15 00:00:00','First Light',4.7),(8,'Baby Spinach','Tender Organic Baby Spinach Leaves',5.99,198,100,102,107,'spinach.jpg','Pukekohe','2023-08-18 00:00:00','FreshCo',4.6),(9,'Free-Range Chicken Breast','Skinless Free-Range Chicken Breast',12.99,150,400,402,405,'chicken_breast.jpg','Waikato','2023-08-20 00:00:00','Bostock',4.7),(10,'Felton Road Pinot Noir','Central Otago Premium Pinot Noir',79.99,57,300,302,305,'pinot.jpg','Central Otago','2023-07-25 00:00:00','Felton Road',4.9),(11,'Mainland Tasty Cheddar','Aged Cheddar Cheese Block',8.5,120,200,202,205,'cheddar.jpg','Taranaki','2023-08-22 00:00:00','Mainland',4.5),(12,'Happy Hens Eggs','6-Pack Free-Range Eggs',6.99,300,400,402,406,'eggs.jpg','Canterbury','2023-08-15 00:00:00','Happy Hens',4.8),(13,'Esk Valley Cabernet Sauvignon','Hawke\'s Bay Bold Red Wine',45.99,70,300,302,306,'cabernet.jpg','Hawke\'s Bay','2023-08-15 00:00:00','Esk Valley',4.7),(14,'Whitestone Blue Cheese','Creamy Blue Vein Cheese',12.99,80,200,202,206,'blue_cheese.jpg','Oamaru','2023-08-25 00:00:00','Whitestone',4.6),(15,'Fresh Nelson Raspberries','Plump Juicy Raspberries',9.99,90,100,101,105,'raspberries.jpg','Nelson','2023-08-20 00:00:00','BerryFresh',4.5),(16,'Fresh Strawberries','Juicy Organic Strawberries',9.99,180,100,101,105,'strawberries.jpg','Nelson','2023-09-05 00:00:00','BerryHarvest',4.7),(17,'Organic Carrots','Sweet Crisp Root Vegetables',4.49,349,100,102,507,'carrots.jpg','Pukekohe','2023-09-01 00:00:00','EarthGoodness',4.6),(18,'Greek Style Yogurt','High-Protein Greek Yogurt',6.49,120,200,505,506,'greek_yogurt.jpg','Waikato','2023-09-10 00:00:00','PureDairy',4.8),(19,'Craft Pale Ale','Hoppy Nelson Sauvin IPA',14.99,80,300,508,509,'craft_beer.jpg','Nelson','2023-09-12 00:00:00','HopHaven',4.6),(20,'Free-Range Chicken Thighs','Skin-On Chicken Thighs',9.99,200,400,402,405,'chicken_thighs.jpg','Waikato','2023-09-15 00:00:00','Bostock',4.7),(21,'Lewis Road A2 Milk','Premium A2 Protein Whole Milk',7.99,150,200,201,204,'a2_milk.jpg','Waikato','2023-09-05 00:00:00','Lewis Road',4.7),(22,'Puhoi Valley Greek Yogurt','Creamy Traditional Greek Yogurt',5.99,200,200,505,506,'greek_puhoi.jpg','Northland','2023-09-10 00:00:00','Puhoi Valley',4.6),(23,'Mainland Vintage Cheddar','24-Month Aged Sharp Cheddar',10.99,80,200,202,205,'vintage_cheddar.jpg','Taranaki','2023-09-12 00:00:00','Mainland',4.8),(24,'Villa Maria Chardonnay','Rich Oak-Aged Marlborough Chardonnay',24.99,60,300,301,304,'chardonnay.jpg','Marlborough','2023-09-15 00:00:00','Villa Maria',4.7),(25,'Kumeu River Pinot Noir','Elegant Red Wine with Berry Notes',65.99,40,300,302,305,'kumeu_pinot.jpg','Auckland','2023-09-18 00:00:00','Kumeu River',4.9),(26,'Tuatara India Pale Ale','Hoppy Craft Beer with Citrus Finish',8.99,120,300,508,509,'tuatara_ipa.jpg','Wellington','2023-09-20 00:00:00','Tuatara',4.5),(27,'Airborne Clover Honey','Raw Unprocessed Clover Honey',12.99,180,500,501,504,'clover_honey.jpg','Canterbury','2023-09-22 00:00:00','Airborne',4.6),(28,'PureNZ Lavender Essential Oil','100% Pure Therapeutic Grade Oil',19.99,90,500,502,510,'lavender_oil.jpg','Nelson','2023-09-28 00:00:00','PureNZ',4.7),(29,'Nature\'s Way Tea Tree Oil','Antiseptic Skin Care Essential Oil',15.99,110,500,502,511,'teatree_oil.jpg','Bay of Plenty','2023-09-30 00:00:00','Nature\'s Way',4.5),(30,'Cricket Protein Powder','Sustainable Protein from Crickets (65% protein)',39.99,85,500,512,517,'cricket_protein.jpg','Auckland','2023-10-15 00:00:00','Sens',4.6),(31,'New Zealand Hemp Hearts','Raw Shelled Hemp Seeds (Rich in Omega-3)',22.99,120,500,512,513,'hemp_hearts.jpg','Canterbury','2023-10-12 00:00:00','The Brothers Green',4.8),(32,'Plant-Based Venison Patties','Wild Game Flavor from Pea Protein (0% Meat)',14.99,95,400,515,516,'plant_venison.jpg','Wellington','2023-10-20 00:00:00','Wild & Pure',4.7);
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
) ENGINE=InnoDB AUTO_INCREMENT=518 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` (`id`, `name`, `parent_id`, `type`) VALUES (100,'Veg&Fruits',0,1),(101,'Fruits',100,2),(102,'Vegetables',100,2),(103,'Kiwifruit',101,3),(104,'Apples',101,3),(105,'Berries',101,3),(107,'Leafy Greens',102,3),(200,'Dairy',0,1),(201,'Milk',200,2),(202,'Cheese',200,2),(203,'Organic Milk',201,3),(204,'A2 Milk',201,3),(205,'Cheddar',202,3),(206,'Blue Cheese',202,3),(300,'spirits',0,1),(301,'White Wine',300,2),(302,'Red Wine',300,2),(303,'Sauvignon Blanc',301,3),(304,'Chardonnay',301,3),(305,'Pinot Noir',302,3),(306,'Cabernet Sauvignon',302,3),(400,'Meat',0,1),(401,'Beef & Lamb',400,2),(402,'Poultry',400,2),(403,'Lamb Cuts',401,3),(404,'Beef Steaks',401,3),(405,'Chicken',402,3),(406,'Free-Range Eggs',402,3),(500,'Naturals',0,1),(501,'Honey',500,2),(502,'Essential Oils',500,2),(503,'Manuka Honey',501,3),(504,'Clover Honey',501,3),(505,'Yogurt',200,2),(506,'Greek Yogurt',505,3),(507,'Root Vegetables',102,3),(508,'Beer',300,2),(509,'Craft Beer',508,3),(510,'Lavender Oil',502,3),(511,'Tea Tree Oil',502,3),(512,'Superfoods',500,2),(513,'Plant-Based Proteins',512,3),(514,'Ancient Grains',512,3),(515,'Alternative Proteins',400,2),(516,'Plant-Based Meat',515,3),(517,'Insect Protein',515,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `login_name`, `user_name`, `password`, `gender`, `email`, `mobile`, `file_name`, `create_time`, `update_time`) VALUES (60,'MaeveMe','aa aa','d9df2800db97732108f13736271a0913087ba5b781b37385',1,'215378@ss.com','15011108848',NULL,'2025-04-07 07:27:20','2025-04-07 07:27:20'),(61,'te','aa aa','f5cf8423d70ba03d8d76a481b8e655e99e9d15b60a63c050',1,'1353023196@qq.com','15011108848',NULL,'2025-04-28 07:27:07','2025-04-28 07:27:07'),(62,'tttt','aa aa','b48e57a0439b72b904d07f39728d28f8ec7ce30f7060d91a',1,'000487@binich.com','15011108848',NULL,'2025-04-28 07:57:30','2025-04-28 07:57:30');
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
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_address`
--

LOCK TABLES `user_address` WRITE;
/*!40000 ALTER TABLE `user_address` DISABLE KEYS */;
INSERT INTO `user_address` (`id`, `user_id`, `address`, `remark`, `isdefault`, `create_time`, `update_time`) VALUES (60,60,'11','11',0,'2025-04-28 12:03:59','2025-04-28 12:03:59'),(61,60,'12','12',1,'2025-04-29 11:24:57','2025-04-29 11:24:57');
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

-- Dump completed on 2025-06-09 11:12:03
