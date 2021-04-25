-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bugtracker
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `admin_ID` varchar(20) NOT NULL,
  `admin_pass` varchar(255) NOT NULL,
  `admin_firstname` varchar(255) DEFAULT NULL,
  `admin_middlename` varchar(255) DEFAULT NULL,
  `admin_lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`admin_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('admin_01','1234','Luan',NULL,''),('admin_02','4321','Nam',NULL,NULL);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_view`
--

DROP TABLE IF EXISTS `admin_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_view` (
  `project_ID` varchar(20) NOT NULL,
  `user_ID` varchar(20) NOT NULL,
  `staff_ID` varchar(20) NOT NULL,
  `bug_ID` varchar(20) NOT NULL,
  `ticket_ID` varchar(20) NOT NULL,
  KEY `project_IDforeignkey_idx` (`project_ID`),
  KEY `user_IDforeignkey_idx` (`user_ID`),
  KEY `staff_IDforeignkey_idx` (`staff_ID`),
  KEY `bug_idforeignkey_idx` (`bug_ID`),
  KEY `ticket_IDforeignkey_idx` (`ticket_ID`),
  CONSTRAINT `bug_idforeignkey` FOREIGN KEY (`bug_ID`) REFERENCES `bug` (`bug_ID`),
  CONSTRAINT `project_IDforeignkey` FOREIGN KEY (`project_ID`) REFERENCES `project` (`project_ID`),
  CONSTRAINT `staff_IDforeignkey` FOREIGN KEY (`staff_ID`) REFERENCES `staff` (`staff_ID`),
  CONSTRAINT `ticket_IDforeignkey` FOREIGN KEY (`ticket_ID`) REFERENCES `ticket` (`ticket_ID`),
  CONSTRAINT `user_IDforeignkey` FOREIGN KEY (`user_ID`) REFERENCES `user` (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_view`
--

LOCK TABLES `admin_view` WRITE;
/*!40000 ALTER TABLE `admin_view` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assign`
--

DROP TABLE IF EXISTS `assign`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assign` (
  `admin_ID` varchar(20) NOT NULL,
  `staff_ID` varchar(20) NOT NULL,
  `project_ID` varchar(20) NOT NULL,
  KEY `getadminID_idx` (`admin_ID`),
  KEY `getstaff_ID_idx` (`staff_ID`),
  KEY `getproject_ID_idx` (`project_ID`),
  CONSTRAINT `getadminID` FOREIGN KEY (`admin_ID`) REFERENCES `admin` (`admin_ID`),
  CONSTRAINT `getproject_ID` FOREIGN KEY (`project_ID`) REFERENCES `project` (`project_ID`),
  CONSTRAINT `getstaff_ID` FOREIGN KEY (`staff_ID`) REFERENCES `staff` (`staff_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assign`
--

LOCK TABLES `assign` WRITE;
/*!40000 ALTER TABLE `assign` DISABLE KEYS */;
/*!40000 ALTER TABLE `assign` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bug`
--

DROP TABLE IF EXISTS `bug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bug` (
  `bug_ID` varchar(20) NOT NULL,
  `project_ID` varchar(20) NOT NULL,
  `bug_name` varchar(255) DEFAULT NULL,
  `bug_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bug_ID`,`project_ID`),
  KEY `project_connect_idx` (`project_ID`),
  CONSTRAINT `project_connect` FOREIGN KEY (`project_ID`) REFERENCES `project` (`project_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bug`
--

LOCK TABLES `bug` WRITE;
/*!40000 ALTER TABLE `bug` DISABLE KEYS */;
INSERT INTO `bug` VALUES ('bug_1','1234','loss name','not fix'),('bug_2','4321','overload','fixed');
/*!40000 ALTER TABLE `bug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `debug`
--

DROP TABLE IF EXISTS `debug`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `debug` (
  `staff_ID` varchar(20) NOT NULL,
  `bug_ID` varchar(20) NOT NULL,
  `project_ID` varchar(20) NOT NULL,
  `ticket_ID` varchar(20) NOT NULL,
  KEY `getdebug_staff_ID_idx` (`staff_ID`),
  KEY `getdebug_bug_ID_idx` (`bug_ID`),
  KEY `getdebug_project_ID_idx` (`project_ID`),
  KEY `getdebug_ticket_ID_idx` (`ticket_ID`),
  CONSTRAINT `getdebug_bug_ID` FOREIGN KEY (`bug_ID`) REFERENCES `bug` (`bug_ID`),
  CONSTRAINT `getdebug_project_ID` FOREIGN KEY (`project_ID`) REFERENCES `project` (`project_ID`),
  CONSTRAINT `getdebug_staff_ID` FOREIGN KEY (`staff_ID`) REFERENCES `staff` (`staff_ID`),
  CONSTRAINT `getdebug_ticket_ID` FOREIGN KEY (`ticket_ID`) REFERENCES `ticket` (`ticket_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `debug`
--

LOCK TABLES `debug` WRITE;
/*!40000 ALTER TABLE `debug` DISABLE KEYS */;
/*!40000 ALTER TABLE `debug` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manage`
--

DROP TABLE IF EXISTS `manage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manage` (
  `staff_ID` varchar(20) NOT NULL,
  `user_ID` varchar(20) NOT NULL,
  `project_ID` varchar(20) NOT NULL,
  KEY `getManageStaff_ID_idx` (`staff_ID`),
  KEY `getManageUser_ID_idx` (`user_ID`),
  KEY `getManageProject_ID_idx` (`project_ID`),
  CONSTRAINT `getManageProject_ID` FOREIGN KEY (`project_ID`) REFERENCES `project` (`project_ID`),
  CONSTRAINT `getManageStaff_ID` FOREIGN KEY (`staff_ID`) REFERENCES `staff` (`staff_ID`),
  CONSTRAINT `getManageUser_ID` FOREIGN KEY (`user_ID`) REFERENCES `user` (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manage`
--

LOCK TABLES `manage` WRITE;
/*!40000 ALTER TABLE `manage` DISABLE KEYS */;
/*!40000 ALTER TABLE `manage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `project_ID` varchar(20) NOT NULL,
  `project_name` varchar(255) DEFAULT NULL,
  `project_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`project_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('1234','bugtracker','bugtracker'),('4321','planB',NULL);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `user_ID` varchar(20) NOT NULL,
  `ticket_ID` varchar(20) NOT NULL,
  `bug_ID` varchar(20) NOT NULL,
  `project_ID` varchar(20) NOT NULL,
  KEY `get_report_user_ID_idx` (`user_ID`),
  KEY `get_report_ticket_ID_idx` (`ticket_ID`),
  KEY `get_report_bug_ID_idx` (`bug_ID`),
  KEY `get_report_project_ID_idx` (`project_ID`),
  CONSTRAINT `get_report_bug_ID` FOREIGN KEY (`bug_ID`) REFERENCES `bug` (`bug_ID`),
  CONSTRAINT `get_report_project_ID` FOREIGN KEY (`project_ID`) REFERENCES `project` (`project_ID`),
  CONSTRAINT `get_report_ticket_ID` FOREIGN KEY (`ticket_ID`) REFERENCES `ticket` (`ticket_ID`),
  CONSTRAINT `get_report_user_ID` FOREIGN KEY (`user_ID`) REFERENCES `user` (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_ID` varchar(20) NOT NULL,
  `staff_pass` varchar(255) DEFAULT NULL,
  `staff_firstname` varchar(255) DEFAULT NULL,
  `staff_middlename` varchar(255) DEFAULT NULL,
  `staff_lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`staff_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES ('staff_01','123123','Duy',NULL,NULL),('staff_02','312312','Lap',NULL,NULL);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_view`
--

DROP TABLE IF EXISTS `staff_view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_view` (
  `project_ID` varchar(20) NOT NULL,
  `user_ID` varchar(20) NOT NULL,
  `bug_ID` varchar(20) NOT NULL,
  `ticket_ID` varchar(20) NOT NULL,
  KEY `get_staffview_project_ID_idx` (`project_ID`),
  KEY `get_staffview_user_ID_idx` (`user_ID`),
  KEY `get_staffview_bug_ID_idx` (`bug_ID`),
  KEY `get_staffview_ticket_ID_idx` (`ticket_ID`),
  CONSTRAINT `get_staffview_bug_ID` FOREIGN KEY (`bug_ID`) REFERENCES `bug` (`bug_ID`),
  CONSTRAINT `get_staffview_project_ID` FOREIGN KEY (`project_ID`) REFERENCES `project` (`project_ID`),
  CONSTRAINT `get_staffview_ticket_ID` FOREIGN KEY (`ticket_ID`) REFERENCES `ticket` (`ticket_ID`),
  CONSTRAINT `get_staffview_user_ID` FOREIGN KEY (`user_ID`) REFERENCES `user` (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_view`
--

LOCK TABLES `staff_view` WRITE;
/*!40000 ALTER TABLE `staff_view` DISABLE KEYS */;
/*!40000 ALTER TABLE `staff_view` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ticket` (
  `ticket_ID` varchar(20) NOT NULL,
  `bug_ID` varchar(20) NOT NULL,
  `ticket_name` varchar(255) DEFAULT NULL,
  `ticket_date` date DEFAULT NULL,
  `ticket_desc` varchar(255) DEFAULT NULL,
  `ticket_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ticket_ID`,`bug_ID`),
  KEY `get_ticket_bug_ID_idx` (`bug_ID`),
  CONSTRAINT `get_ticket_bug_ID` FOREIGN KEY (`bug_ID`) REFERENCES `bug` (`bug_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ticket`
--

LOCK TABLES `ticket` WRITE;
/*!40000 ALTER TABLE `ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `ticket` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_ID` varchar(20) NOT NULL,
  `user_pass` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_firstName` varchar(255) DEFAULT NULL,
  `user_middleName` varchar(255) DEFAULT NULL,
  `user_lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('user_01',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user-view`
--

DROP TABLE IF EXISTS `user-view`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user-view` (
  `ticket_id` varchar(20) NOT NULL,
  `bug_ID` varchar(20) NOT NULL,
  KEY `get_userview_ticket_ID_idx` (`ticket_id`),
  KEY `get_userview_bug_ID_idx` (`bug_ID`),
  CONSTRAINT `get_userview_bug_ID` FOREIGN KEY (`bug_ID`) REFERENCES `bug` (`bug_ID`),
  CONSTRAINT `get_userview_ticket_ID` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user-view`
--

LOCK TABLES `user-view` WRITE;
/*!40000 ALTER TABLE `user-view` DISABLE KEYS */;
/*!40000 ALTER TABLE `user-view` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-25 13:49:23
