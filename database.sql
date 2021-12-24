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
-- Table structure for table `bill`
--

DROP TABLE IF EXISTS `bill`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bill` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` int DEFAULT NULL,
  `reduced_fee` decimal(13,2) DEFAULT NULL,
  `additional_fee` decimal(13,2) DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `payment_method` tinyint(1) DEFAULT NULL,
  `money_paid` decimal(13,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bill`
--

LOCK TABLES `bill` WRITE;
/*!40000 ALTER TABLE `bill` DISABLE KEYS */;
INSERT INTO `bill` VALUES (1,1,0.00,0.00,'',0,500000.00),(2,1,0.00,0.00,NULL,0,1000000.00),(3,1,0.00,0.00,NULL,0,1000000.00);
/*!40000 ALTER TABLE `bill` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diary`
--

LOCK TABLES `diary` WRITE;
/*!40000 ALTER TABLE `diary` DISABLE KEYS */;
INSERT INTO `diary` VALUES (1,1,1,'Thêm mới bản ghi type_price',1638045227989,1),(2,1,2,'Thêm mới bản ghi room_price',1638072571679,1),(3,1,3,'Thêm mới bản ghi type_room',1638072571711,1),(4,1,4,'Thêm mới bản ghi type_room',1639123110532,1),(5,1,5,'Thêm mới bản ghi room_price',1639123110574,1),(6,1,6,'Thêm mới bản ghi room',1639124653576,1),(7,1,7,'Thêm mới bản ghi room',1639124661920,1),(8,1,8,'Thêm mới bản ghi guest',1639126378004,1),(9,1,9,'Thêm mới bản ghi guest',1639126405698,1),(10,1,10,'Thêm mới bản ghi guest',1639126435771,1),(11,1,11,'Thêm mới bản ghi guest',1639126461796,1),(12,1,12,'Thêm mới bản ghi bill',1639131434984,1),(13,1,13,'Thêm mới bản ghi occupied_room',1639131435025,1),(14,1,14,'Thêm mới bản ghi hosted_at',1639131435050,1),(15,1,15,'Thêm mới bản ghi hosted_at',1639131435073,1),(16,1,16,'Thêm mới bản ghi service',1639131546955,1),(17,1,17,'Thêm mới bản ghi service',1639131597737,1),(18,1,18,'Thêm mới bản ghi occupied_room',1639133829026,1),(19,1,19,'Thêm mới bản ghi service_used',1639133829072,1),(20,1,20,'Thêm mới bản ghi hosted_at',1639133829096,1),(21,1,21,'Thêm mới bản ghi service_used',1639134046959,1),(22,1,22,'Thêm mới bản ghi service_used',1639134046981,1),(23,2,21,'Cập nhật bản ghi service_used',1639134072796,1),(24,2,21,'Cập nhật bản ghi service_used',1639134072810,1),(25,1,23,'Thêm mới bản ghi service_used',1639134086321,1),(26,2,23,'Cập nhật bản ghi service_used',1639134086336,1);
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
  `birthday` bigint DEFAULT NULL,
  `nationality` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone_number` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `id_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guest`
--

LOCK TABLES `guest` WRITE;
/*!40000 ALTER TABLE `guest` DISABLE KEYS */;
INSERT INTO `guest` VALUES (1,'nguyen','nguyen',1638206091445,'Việt Nam','Hà Nội','0989888999','nguyenluongnguyen@gmail.com','222222222222',1),(2,'cao','le',1638206091445,'Việt Nam','Hà Nội','0989888998','letiencao@gmail.com','111111111111',1),(3,'tung','nguyen',1638206091445,'Việt Nam','Hải Dương','0989888997','nguyenbatung@gmail.com','333333333333',1),(4,'khoa','vu',1638206091445,'Việt Nam','Hải Phòng','0989888996','vuthekhoa@gmail.com','444444444444',1);
/*!40000 ALTER TABLE `guest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hosted_at`
--

DROP TABLE IF EXISTS `hosted_at`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hosted_at` (
  `id` int NOT NULL AUTO_INCREMENT,
  `occupied_room_id` int NOT NULL,
  `guest_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hosted_at_occupied_room_id` (`occupied_room_id`),
  KEY `fk_hosted_at_guest_id` (`guest_id`),
  CONSTRAINT `fk_hosted_at_guest_id` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`id`),
  CONSTRAINT `fk_hosted_at_occupied_room_id` FOREIGN KEY (`occupied_room_id`) REFERENCES `occupied_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hosted_at`
--

LOCK TABLES `hosted_at` WRITE;
/*!40000 ALTER TABLE `hosted_at` DISABLE KEYS */;
INSERT INTO `hosted_at` VALUES (1,4,1),(2,4,2),(3,5,3);
/*!40000 ALTER TABLE `hosted_at` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `occupied_room`
--

DROP TABLE IF EXISTS `occupied_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `occupied_room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `check_in_time` bigint DEFAULT NULL,
  `check_out_time` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  `deposit` decimal(13,2) DEFAULT NULL,
  `room_id` int NOT NULL,
  `bill_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_occupied_room_room_id` (`room_id`),
  KEY `fk_occupied_room_bill_id` (`bill_id`),
  CONSTRAINT `fk_occupied_room_bill_id` FOREIGN KEY (`bill_id`) REFERENCES `bill` (`id`),
  CONSTRAINT `fk_occupied_room_room_id` FOREIGN KEY (`room_id`) REFERENCES `room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `occupied_room`
--

LOCK TABLES `occupied_room` WRITE;
/*!40000 ALTER TABLE `occupied_room` DISABLE KEYS */;
INSERT INTO `occupied_room` VALUES (4,1639131435006,1639304186613,1,500000.00,1,1),(5,1639133828996,1639304186613,1,500000.00,2,1);
/*!40000 ALTER TABLE `occupied_room` ENABLE KEYS */;
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
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation` (
  `id` int NOT NULL AUTO_INCREMENT,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `date_from` bigint DEFAULT NULL,
  `date_to` bigint DEFAULT NULL,
  `status` int DEFAULT NULL,
  `number_room` int DEFAULT NULL,
  `guest_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservation_guest_id` (`guest_id`),
  CONSTRAINT `fk_reservation_guest_id` FOREIGN KEY (`guest_id`) REFERENCES `guest` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'Đã đặt cọc 50% đến muộn 15p',1639455231901,1639465331901,1,2,1),(2,'Đã cọc 30% đến muộn 1 tiếng yêu cầu dịch vụ xe đón đưa',1639455231901,1639465331901,1,3,2);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation_room`
--

DROP TABLE IF EXISTS `reservation_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reservation_room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `room_id` int DEFAULT NULL,
  `reservation_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation_room`
--

LOCK TABLES `reservation_room` WRITE;
/*!40000 ALTER TABLE `reservation_room` DISABLE KEYS */;
INSERT INTO `reservation_room` VALUES (1,1,1,1),(2,2,1,1),(3,3,2,1),(4,4,3,1);
/*!40000 ALTER TABLE `reservation_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN',NULL),(2,'ROLE_STAFF',NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type_room_id` int NOT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_type_room_id` (`type_room_id`),
  CONSTRAINT `fk_room_type_room_id` FOREIGN KEY (`type_room_id`) REFERENCES `type_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'101',1,1),(2,'102',1,1),(3,'103',1,1),(4,'104',1,1),(5,'201',1,1),(6,'202',1,1),(7,'203',1,1),(8,'204',1,1),(9,'301',1,1),(10,'302',1,1),(11,'304',1,1),(12,'401',1,1),(13,'402',1,1),(14,'403',1,1),(15,'404',1,1),(16,'501',2,1),(17,'502',2,1),(18,'503',2,1),(19,'504',2,1),(20,'601',2,1),(21,'602',2,1),(22,'603',2,1),(23,'604',2,1);
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
  `price` decimal(13,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_room_price_type_price_id` (`type_price_id`),
  KEY `fk_room_price_type_room_id` (`type_room_id`),
  CONSTRAINT `fk_room_price_type_price_id` FOREIGN KEY (`type_price_id`) REFERENCES `type_price` (`id`),
  CONSTRAINT `fk_room_price_type_room_id` FOREIGN KEY (`type_room_id`) REFERENCES `type_room` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_price`
--

LOCK TABLES `room_price` WRITE;
/*!40000 ALTER TABLE `room_price` DISABLE KEYS */;
INSERT INTO `room_price` VALUES (1,1,1,1000000.00),(2,1,2,500000.00),(3,2,1,100000.00),(4,2,2,200000.00);
/*!40000 ALTER TABLE `room_price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  `price` decimal(13,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Giat la','',1,50000.00),(2,'Xe Đưa Đón','',1,200000.00),(3,'Mát xa',NULL,1,200000.00);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_used`
--

DROP TABLE IF EXISTS `service_used`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_used` (
  `id` int NOT NULL AUTO_INCREMENT,
  `occupied_room_id` int NOT NULL,
  `service_id` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `paid` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_service_used_occupied_room_id` (`occupied_room_id`),
  KEY `fk_service_used_service_id` (`service_id`),
  CONSTRAINT `fk_service_used_occupied_room_id` FOREIGN KEY (`occupied_room_id`) REFERENCES `occupied_room` (`id`),
  CONSTRAINT `fk_service_used_service_id` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_used`
--

LOCK TABLES `service_used` WRITE;
/*!40000 ALTER TABLE `service_used` DISABLE KEYS */;
INSERT INTO `service_used` VALUES (1,5,1,1,0),(2,4,1,3,0),(3,4,2,1,0),(4,4,1,2,1);
/*!40000 ALTER TABLE `service_used` ENABLE KEYS */;
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
  `id` int NOT NULL AUTO_INCREMENT,
  `row_id` int DEFAULT NULL,
  `table_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `table_diary`
--

LOCK TABLES `table_diary` WRITE;
/*!40000 ALTER TABLE `table_diary` DISABLE KEYS */;
INSERT INTO `table_diary` VALUES (1,1,'type_price'),(2,1,'room_price'),(3,1,'type_room'),(4,2,'type_room'),(5,2,'room_price'),(6,1,'room'),(7,2,'room'),(8,1,'guest'),(9,2,'guest'),(10,3,'guest'),(11,4,'guest'),(12,1,'bill'),(13,4,'occupied_room'),(14,1,'hosted_at'),(15,2,'hosted_at'),(16,1,'service'),(17,2,'service'),(18,5,'occupied_room'),(19,1,'service_used'),(20,3,'hosted_at'),(21,2,'service_used'),(22,3,'service_used'),(23,4,'service_used');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_action`
--

LOCK TABLES `type_action` WRITE;
/*!40000 ALTER TABLE `type_action` DISABLE KEYS */;
INSERT INTO `type_action` VALUES (1,'Thêm mới bản ghi','',1),(2,'Cập nhật bản ghi','',1);
/*!40000 ALTER TABLE `type_action` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_price`
--

DROP TABLE IF EXISTS `type_price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_price` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_price`
--

LOCK TABLES `type_price` WRITE;
/*!40000 ALTER TABLE `type_price` DISABLE KEYS */;
INSERT INTO `type_price` VALUES (1,'Một đêm',1),(2,'Ngày Lễ',1),(3,'Ngày thường',1);
/*!40000 ALTER TABLE `type_price` ENABLE KEYS */;
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
  `max_adult` int DEFAULT NULL,
  `max_child` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_room`
--

LOCK TABLES `type_room` WRITE;
/*!40000 ALTER TABLE `type_room` DISABLE KEYS */;
INSERT INTO `type_room` VALUES (1,'VIP',2,1,1),(2,'Normal',2,2,1);
/*!40000 ALTER TABLE `type_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `phone` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `salary_day` double DEFAULT NULL,
  `id_card` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin','$2a$12$Qy..CGiliVmp8bnnKqGfSO1UuDBEgWPgrSd0zp2L3d8uaVNWUADAW','Quản trị viên','Hải Phòng','admin1@gmail.com','0919323324',1,0,'8178321313',1),(2,'nguyenvana','$2a$10$HN0ypHdaqaknm5RSSgZEMe9hN1gOwYuQtml7FnbaF0uKIOCbBPsyC','Nguyễn văn a','Hà nội','nguyenvana@gmail.com','091828313',1,1000000,'8178321313',1),(3,'nguyenvanb','$2a$10$HN0ypHdaqaknm5RSSgZEMe9hN1gOwYuQtml7FnbaF0uKIOCbBPsyC','Nguyễn văn B','Hà nội','nguyevnanb','091283713',1,1000000,'2838131333',1),(4,'nguyenthic','$2a$10$tINer8Ll7vQBHPxY3b5q.eEj51Mv5usBBF53yi6EMWHRne.xon/9i','Nguyễn Thị C','Hà nội','nguyenthic@gmail.com','0987712222',1,100000,'0128182122',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
INSERT INTO `user_role` VALUES (1,1),(2,2),(2,3),(2,4);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
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

-- Dump completed on 2021-12-24 10:58:33
