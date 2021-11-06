-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: hotel_management
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `diary`
--

DROP TABLE IF EXISTS `diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_action_id` int NOT NULL,
  `table_diary_id` int NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `action_date` bigint DEFAULT NULL,
  `action_by` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_diary_type_action_id` (`type_action_id`),
  KEY `fk_diary_table_diary_id` (`table_diary_id`),
  CONSTRAINT `fk_diary_table_diary_id` FOREIGN KEY (`table_diary_id`) REFERENCES `table_diary` (`id`),
  CONSTRAINT `fk_diary_type_action_id` FOREIGN KEY (`type_action_id`) REFERENCES `type_action` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary`
--

LOCK TABLES `diary` WRITE;
/*!40000 ALTER TABLE `diary` DISABLE KEYS */;
INSERT INTO `diary` VALUES (1,1,1,'Thêm mới giá phòng',NULL,1),(2,1,2,'Thêm mới giá phòng',NULL,1),(3,1,3,'Thêm mới giá phòng',NULL,1);
/*!40000 ALTER TABLE `diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `last_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `date_of_birth` bigint DEFAULT NULL,
  `nationality` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone_number` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `expiry_date` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (1,'Nguyễn','Văn An',NULL,'Việt Nam','số 5 Phạm Hùng-Hà Nội','0688877449','Annv@gmail.com','030199008894',NULL),(2,'Nguyễn','Thị Sen',NULL,'Việt Nam','Đồ Sơn-Hải hòng','0564979999','Sennt@gmail.com','030099015646',NULL),(3,'Trần','Thị Hà',NULL,'Việt Nam','Sao Đỏ-CHí Linh-Hải Dương','0687994666','Hatt@gmail.com','030099008561',NULL),(4,'Đõ','Thị Quyên',NULL,'Việt Nam','Mê Linh-Hà Nội','0568799944','Quyendt@gmail.com','065997899999',NULL),(5,'Trần','Văn Sơn',NULL,NULL,'Mê Linh-Hà Nội','0568566666','Sontv@gmail.com','056977646666',NULL),(6,'Nguyễn','Thị Hà',NULL,NULL,'Từ Sơn-Bắc Ninh','0126649999','Hant@gmail.com','030099007856',NULL),(7,'Đỗ','Văn Hùng',NULL,NULL,'Đống Đa- Hà Nộ','0846979999','Hungdv@gmail.com','030099004566',NULL);
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hotel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hotel`
--

LOCK TABLES `hotel` WRITE;
/*!40000 ALTER TABLE `hotel` DISABLE KEYS */;
INSERT INTO `hotel` VALUES (1,'Daisy','Số 5, Phạm Hùng, HÀ Nội',NULL,'0966888888',NULL),(2,'Venus','270 Võ Nguyên Giáp, Bắc Mỹ Phú, Ngũ Hành Sơn, Đà Nẵng',NULL,'0868688888',NULL);
/*!40000 ALTER TABLE `hotel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `price` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` decimal(13,2) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price`
--

LOCK TABLES `price` WRITE;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
INSERT INTO `price` VALUES (1,'VIP1',1000000.00,1),(2,'NORMAL',300000.00,1),(3,'King',10000000.00,1),(4,'VIP2',1000000.00,1);
/*!40000 ALTER TABLE `price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `selling_price` decimal(13,2) DEFAULT NULL,
  `import_price` decimal(13,2) DEFAULT NULL,
  `unit_id` int NOT NULL,
  `type_product_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_unit_id` (`unit_id`),
  KEY `fk_product_type_product_id` (`type_product_id`),
  CONSTRAINT `fk_product_type_product_id` FOREIGN KEY (`type_product_id`) REFERENCES `type_product` (`id`),
  CONSTRAINT `fk_product_unit_id` FOREIGN KEY (`unit_id`) REFERENCES `unit` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'coca','Nước coca',1,20,8000.00,20000.00,2,1),(2,'7up','Nước 7up',1,20,8000.00,20000.00,2,1),(3,'Nước suối','Nước lọc nguyên chất',1,20,3000.00,15000.00,3,1),(4,'Oscar','Oscar',1,20,5000.00,12000.00,4,1),(5,'Cafe','Cà phê đá',1,20,5000.00,10000.00,4,1),(6,'Mì tôm','Mì tôm',1,1,10000.00,20000.00,6,2),(7,'Bít tết','Bít tết',1,1,200000.00,400000.00,5,2),(8,'Giặt là','Dịch vụ giặt khô',1,1,0.00,50000.00,7,3);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_reserved`
--

DROP TABLE IF EXISTS `product_reserved`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_reserved` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `reservation_id` int NOT NULL,
  `product_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_reserved_reservation_id` (`reservation_id`),
  KEY `fk_product_reserved_product_id` (`product_id`),
  CONSTRAINT `fk_product_reserved_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `fk_product_reserved_reservation_id` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_reserved`
--

LOCK TABLES `product_reserved` WRITE;
/*!40000 ALTER TABLE `product_reserved` DISABLE KEYS */;
/*!40000 ALTER TABLE `product_reserved` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `total` decimal(13,2) DEFAULT NULL,
  `deposit` decimal(13,2) DEFAULT NULL,
  `payment_method` tinyint DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `reduce_fee` decimal(13,2) DEFAULT NULL,
  `additional_fee` decimal(13,2) DEFAULT NULL,
  `date_from` bigint DEFAULT NULL,
  `date_to` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_guest`
--

DROP TABLE IF EXISTS `reservation_guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_guest` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `guest_id` int NOT NULL,
  `reservation_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservation_guest_guest_id` (`guest_id`),
  KEY `fk_reservation_guest_reservation_id` (`reservation_id`),
  CONSTRAINT `fk_reservation_guest_guest_id` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`id`),
  CONSTRAINT `fk_reservation_guest_reservation_id` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_guest`
--

LOCK TABLES `reservation_guest` WRITE;
/*!40000 ALTER TABLE `reservation_guest` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation_guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `description` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN',NULL),(2,'ROLE_STAFF',NULL);
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hotel_id` int NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type_room_id` int DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_type_id_idx` (`type_room_id`),
  KEY `fk_room_hotel_id_idx` (`hotel_id`),
  CONSTRAINT `fk_room_hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `fk_room_type_id` FOREIGN KEY (`type_room_id`) REFERENCES `type_room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,1,'101',1,NULL,1),(2,1,'102',1,NULL,1),(3,1,'103',1,NULL,1),(4,1,'104',1,NULL,1),(5,1,'201',2,NULL,1),(6,1,'202',2,NULL,1),(7,1,'203',2,NULL,1),(8,1,'204',2,NULL,1),(9,1,'301',3,NULL,1),(10,1,'302',3,NULL,1),(11,2,'101',1,NULL,1),(12,2,'102',1,NULL,1),(13,2,'201',1,NULL,1),(14,2,'202',1,NULL,1),(15,2,'203',2,NULL,1),(16,2,'204',2,NULL,1),(17,2,'301',3,NULL,1),(18,2,'302',3,NULL,1);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_price`
--

DROP TABLE IF EXISTS `room_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_price` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_price_id` int NOT NULL,
  `type_room_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_price_type_price_id` (`type_price_id`),
  KEY `fk_room_price_type_room_id` (`type_room_id`),
  CONSTRAINT `fk_room_price_type_price_id` FOREIGN KEY (`type_price_id`) REFERENCES `price` (`id`),
  CONSTRAINT `fk_room_price_type_room_id` FOREIGN KEY (`type_room_id`) REFERENCES `type_room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_price`
--

LOCK TABLES `room_price` WRITE;
/*!40000 ALTER TABLE `room_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_reserved`
--

DROP TABLE IF EXISTS `room_reserved`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_reserved` (
  `id` int NOT NULL AUTO_INCREMENT,
  `reservation_id` int NOT NULL,
  `room_id` int NOT NULL,
  `status` int DEFAULT NULL,
  `cleaner_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_reserved_reservation_id` (`reservation_id`),
  KEY `fk_room_reserved_room_id` (`room_id`),
  CONSTRAINT `fk_room_reserved_reservation_id` FOREIGN KEY (`reservation_id`) REFERENCES `reservation` (`id`),
  CONSTRAINT `fk_room_reserved_room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_reserved`
--

LOCK TABLES `room_reserved` WRITE;
/*!40000 ALTER TABLE `room_reserved` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_reserved` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `table_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `table_diary`
--

DROP TABLE IF EXISTS `table_diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `table_diary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `row_id` int DEFAULT NULL,
  `table_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_diary`
--

LOCK TABLES `table_diary` WRITE;
/*!40000 ALTER TABLE `table_diary` DISABLE KEYS */;
INSERT INTO `table_diary` VALUES (1,1,'price'),(2,2,'price'),(3,3,'price');
/*!40000 ALTER TABLE `table_diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_action`
--

DROP TABLE IF EXISTS `type_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_action` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_action`
--

LOCK TABLES `type_action` WRITE;
/*!40000 ALTER TABLE `type_action` DISABLE KEYS */;
INSERT INTO `type_action` VALUES (1,'Thêm mới',NULL,1),(2,'Cập nhật',NULL,1),(3,'Xóa',NULL,1),(4,'Đăng nhập',NULL,1);
/*!40000 ALTER TABLE `type_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_product`
--

DROP TABLE IF EXISTS `type_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_product`
--

LOCK TABLES `type_product` WRITE;
/*!40000 ALTER TABLE `type_product` DISABLE KEYS */;
INSERT INTO `type_product` VALUES (1,'Đồ uống','Đồ uống',1),(2,'Đồ ăn','Đồ ăn',1),(3,'Dịch vụ','Dịch vụ',1);
/*!40000 ALTER TABLE `type_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_room`
--

DROP TABLE IF EXISTS `type_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number_children` int DEFAULT NULL,
  `number_adult` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_room`
--

LOCK TABLES `type_room` WRITE;
/*!40000 ALTER TABLE `type_room` DISABLE KEYS */;
INSERT INTO `type_room` VALUES (1,'Thường',NULL,2,4,1),(2,'VIP1',NULL,2,2,1),(3,'VIP2',NULL,2,2,1),(4,'King',NULL,1,2,1);
/*!40000 ALTER TABLE `type_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `unit`
--

DROP TABLE IF EXISTS `unit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `unit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `unit`
--

LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'chiếc',1),(2,'lon',1),(3,'chai',1),(4,'gói',1),(5,'đĩa',1),(6,'bát',1),(7,'lần',1);
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(2,1),(2,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `salary_day` double DEFAULT NULL,
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','$2a$10$vRT4McPsDuHNTNbGl.Qq5OX8nNjK29rLidPXKRI34JDglNGCJGbhm','Quản trị viên',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'nguyenvana','$2a$10$HN0ypHdaqaknm5RSSgZEMe9hN1gOwYuQtml7FnbaF0uKIOCbBPsyC','Nguyễn văn a',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hotel_management'
--

--
-- Dumping routines for database 'hotel_management'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-05 21:38:54
