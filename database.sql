-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: hotel_management
-- ------------------------------------------------------
-- Server version	5.7.31-log

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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL,
  `reduced_fee` decimal(13,2) NOT NULL,
  `additional_fee` decimal(13,2) NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 NOT NULL,
  `payment_method` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diary`
--

DROP TABLE IF EXISTS `diary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `diary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_action_id` int(11) NOT NULL,
  `table_diary_id` int(11) NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 NOT NULL,
  `action_date` bigint(20) NOT NULL,
  `action_by` varchar(50) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_diary_type_action_id` (`type_action_id`),
  KEY `fk_diary_table_diary_id` (`table_diary_id`),
  CONSTRAINT `fk_diary_table_diary_id` FOREIGN KEY (`table_diary_id`) REFERENCES `table_diary` (`id`),
  CONSTRAINT `fk_diary_type_action_id` FOREIGN KEY (`type_action_id`) REFERENCES `type_action` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary`
--

LOCK TABLES `diary` WRITE;
/*!40000 ALTER TABLE `diary` DISABLE KEYS */;
/*!40000 ALTER TABLE `diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `guest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `birthday` bigint(20) NOT NULL,
  `nationality` varchar(30) CHARACTER SET utf8 NOT NULL,
  `address` varchar(150) CHARACTER SET utf8 NOT NULL,
  `phone_number` varchar(10) CHARACTER SET utf8 NOT NULL,
  `email` varchar(100) CHARACTER SET utf8 NOT NULL,
  `id_card` varchar(50) CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
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
-- Table structure for table `hosted_at`
--

DROP TABLE IF EXISTS `hosted_at`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
<<<<<<< HEAD
CREATE TABLE `hosted_at` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `occupied_room_id` int(11) NOT NULL,
  `guest_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hosted_at_occupied_room_id` (`occupied_room_id`),
  KEY `fk_hosted_at_guest_id` (`guest_id`),
  CONSTRAINT `fk_hosted_at_guest_id` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`id`),
  CONSTRAINT `fk_hosted_at_occupied_room_id` FOREIGN KEY (`occupied_room_id`) REFERENCES `occupied_room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
=======
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
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosted_at`
--

<<<<<<< HEAD
LOCK TABLES `hosted_at` WRITE;
/*!40000 ALTER TABLE `hosted_at` DISABLE KEYS */;
/*!40000 ALTER TABLE `hosted_at` ENABLE KEYS */;
=======
LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'coca','Nước coca',1,20,8000.00,20000.00,2,1),(2,'7up','Nước 7up',1,20,8000.00,20000.00,2,1),(3,'Nước suối','Nước lọc nguyên chất',1,20,3000.00,15000.00,3,1),(4,'Oscar','Oscar',1,20,5000.00,12000.00,4,1),(5,'Cafe','Cà phê đá',1,20,5000.00,10000.00,4,1),(6,'Mì tôm','Mì tôm',1,1,10000.00,20000.00,6,2),(7,'Bít tết','Bít tết',1,1,200000.00,400000.00,5,2),(8,'Giặt là','Dịch vụ giặt khô',1,1,0.00,50000.00,7,3);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
UNLOCK TABLES;

--
-- Table structure for table `occupied_room`
--

DROP TABLE IF EXISTS `occupied_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `occupied_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `check_in_time` bigint(20) NOT NULL,
  `check_out_time` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `deposit` decimal(13,2) NOT NULL,
  `room_id` int(11) NOT NULL,
  `bill_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_occupied_room_room_id` (`room_id`),
  KEY `fk_occupied_room_bill_id` (`bill_id`),
  CONSTRAINT `fk_occupied_room_bill_id` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `fk_occupied_room_room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occupied_room`
--

LOCK TABLES `occupied_room` WRITE;
/*!40000 ALTER TABLE `occupied_room` DISABLE KEYS */;
/*!40000 ALTER TABLE `occupied_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `note` varchar(255) CHARACTER SET utf8 NOT NULL,
  `date_from` bigint(20) NOT NULL,
  `date_to` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `number_room` int(11) NOT NULL,
  `guest_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservation_guest_id` (`guest_id`),
  CONSTRAINT `fk_reservation_guest_id` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `description` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `type_room_id` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
<<<<<<< HEAD
  KEY `fk_room_type_room_id` (`type_room_id`),
  CONSTRAINT `fk_room_type_room_id` FOREIGN KEY (`type_room_id`) REFERENCES `type_room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
=======
  KEY `fk_room_type_id_idx` (`type_room_id`),
  KEY `fk_room_hotel_id_idx` (`hotel_id`),
  CONSTRAINT `fk_room_hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `hotel` (`id`),
  CONSTRAINT `fk_room_type_id` FOREIGN KEY (`type_room_id`) REFERENCES `type_room` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_price_id` int(11) NOT NULL,
  `type_room_id` int(11) NOT NULL,
  `price` decimal(13,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_price_type_price_id` (`type_price_id`),
  KEY `fk_room_price_type_room_id` (`type_room_id`),
  CONSTRAINT `fk_room_price_type_price_id` FOREIGN KEY (`type_price_id`) REFERENCES `type_price` (`id`),
  CONSTRAINT `fk_room_price_type_room_id` FOREIGN KEY (`type_room_id`) REFERENCES `type_room` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_price`
--

LOCK TABLES `room_price` WRITE;
/*!40000 ALTER TABLE `room_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL,
  `price` decimal(13,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_used`
--

DROP TABLE IF EXISTS `service_used`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_used` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `occupied_room_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `paid` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_service_used_occupied_room_id` (`occupied_room_id`),
  KEY `fk_service_used_service_id` (`service_id`),
  CONSTRAINT `fk_service_used_occupied_room_id` FOREIGN KEY (`occupied_room_id`) REFERENCES `occupied_room` (`id`),
  CONSTRAINT `fk_service_used_service_id` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_used`
--

LOCK TABLES `service_used` WRITE;
/*!40000 ALTER TABLE `service_used` DISABLE KEYS */;
/*!40000 ALTER TABLE `service_used` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `table_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
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
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `row_id` int(11) NOT NULL,
  `table_name` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_diary`
--

LOCK TABLES `table_diary` WRITE;
/*!40000 ALTER TABLE `table_diary` DISABLE KEYS */;
/*!40000 ALTER TABLE `table_diary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_action`
--

DROP TABLE IF EXISTS `type_action`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 NOT NULL,
  `note` varchar(255) CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_action`
--

LOCK TABLES `type_action` WRITE;
/*!40000 ALTER TABLE `type_action` DISABLE KEYS */;
<<<<<<< HEAD
=======
INSERT INTO `type_action` VALUES (1,'Thêm mới',NULL,1),(2,'Cập nhật',NULL,1),(3,'Xóa',NULL,1),(4,'Đăng nhập',NULL,1);
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
/*!40000 ALTER TABLE `type_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_price`
--

DROP TABLE IF EXISTS `type_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_price`
--

<<<<<<< HEAD
LOCK TABLES `type_price` WRITE;
/*!40000 ALTER TABLE `type_price` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_price` ENABLE KEYS */;
=======
LOCK TABLES `type_product` WRITE;
/*!40000 ALTER TABLE `type_product` DISABLE KEYS */;
INSERT INTO `type_product` VALUES (1,'Đồ uống','Đồ uống',1),(2,'Đồ ăn','Đồ ăn',1),(3,'Dịch vụ','Dịch vụ',1);
/*!40000 ALTER TABLE `type_product` ENABLE KEYS */;
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
UNLOCK TABLES;

--
-- Table structure for table `type_room`
--

DROP TABLE IF EXISTS `type_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_room` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 NOT NULL,
  `max_adult` int(11) NOT NULL,
  `max_child` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `password` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `address` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `email` varchar(100) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `phone` varchar(15) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `salary_day` double DEFAULT NULL,
  `id_card` varchar(20) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
<<<<<<< HEAD
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
=======
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

<<<<<<< HEAD
LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
=======
LOCK TABLES `unit` WRITE;
/*!40000 ALTER TABLE `unit` DISABLE KEYS */;
INSERT INTO `unit` VALUES (1,'chiếc',1),(2,'lon',1),(3,'chai',1),(4,'gói',1),(5,'đĩa',1),(6,'bát',1),(7,'lần',1);
/*!40000 ALTER TABLE `unit` ENABLE KEYS */;
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `role_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
<<<<<<< HEAD
=======

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
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

<<<<<<< HEAD
-- Dump completed on 2021-11-23 22:51:09
=======
-- Dump completed on 2021-11-05 21:38:54
>>>>>>> 23ab904a62626d28546e6f4b952302dd60b3de95
