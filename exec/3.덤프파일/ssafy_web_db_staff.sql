-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: i6a503.p.ssafy.io    Database: ssafy_web_db
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

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
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` varchar(255) DEFAULT NULL,
  `updated_at` varchar(255) DEFAULT NULL,
  `admin_yn` varchar(255) DEFAULT NULL,
  `approve_yn` varchar(255) DEFAULT NULL,
  `delete_yn` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fcm_token` varchar(255) DEFAULT NULL,
  `match_yn` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `staff_id` varchar(255) DEFAULT NULL,
  `areas_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc9noua2aejcw6mcfas9qukq9n` (`areas_id`),
  CONSTRAINT `FKc9noua2aejcw6mcfas9qukq9n` FOREIGN KEY (`areas_id`) REFERENCES `area` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'2022-02-16 02:27','2022-02-17 11:36','N','Y','Y','asd','0','N','asd','asd','asd','asd',14),(3,'2022-02-16 02:56','2022-02-17 18:21','N','Y','N','hyunkyu0808@gmail.com','0','N','김현구','$2a$10$loNyRnoBpEnx2dRl0EOUfufSp4p.GuXEk0zoOMW9m09VwHGYx0qfy','010-0000-0011','hyunkyu001',5),(4,'2022-02-17 01:08','2022-02-17 01:08','Y','N','Y','ssafy@ssafy.com','0','N','어드민','$2a$10$JLtsurP8vHYvktIO4owdx.gpqYhQn4vMq4CBxv3PZbOh/NrVxZ7La','010-0000-0000','admin',NULL),(5,'2022-02-17 11:35','2022-02-17 18:12','N','Y','N','godbs98@naver.com','0','N','정해윤','$2a$10$8ap1lkpnuIC/tiv0RorPBOtPgGKCgUBgtCDhLd5r98Q/BPuSB9/c6','01097744553','godbs',5),(6,'2022-02-17 11:36','2022-02-17 13:55','N','Y','N','ssafy@ssafy.com','0','N','임재현','$2a$10$ivFwlXiDdl.Qx0ViBWUvC.xcauTCOi1vQyqcIbSkSTnvmbuk7eLmG','010-0000-0000','jaehyun',5),(7,'2022-02-17 11:36','2022-02-17 15:01','N','Y','N','tree@ssafy.com','0','N','채예은','$2a$10$xUaGiPu/zrVay5I1lol/8.dn.pMas7fHlrjAvMGeL3yakoyu0m4ye','010-1111-1111','codpdms',14),(8,'2022-02-17 11:38','2022-02-17 15:29','N','Y','N','limyt@ssafy.com','0','N','임영택','$2a$10$9aXyFH554/ikmneio8LwD.JyEDbwTIs5U3yiOtm5dU5.44RqR7Bv2','010-1234-1234','limyt',5),(10,'2022-02-17 11:41','2022-02-17 18:06','N','N','Y','test@ssafy.com','0','N','test','$2a$10$dY8ge5pU2GH0F58svEkQcOitVi1DrsSU2byAD9Oe4gzNczVVSGNnK','010-010-010','test',NULL),(14,'2022-02-17 11:42','2022-02-17 11:43','N','N','Y','1234','0','N','1234','$2a$10$0ITq0VkmiAIUTiT9mSf3aOW3hOhOCcOuaYBvajekHxdVT4Z.u8UPi','1234','test111',NULL),(16,'2022-02-17 16:38','2022-02-17 17:10','N','Y','N','ssafy1@ssafy.com','0','N','김싸피','$2a$10$w.IjxtrWX.SfH.JxmhY0CelQX14O2MXPMiN7G5LsE/OiUywPllkTO','010-0000-0000','staff1',13),(18,'2022-02-17 16:51','2022-02-17 17:11','N','Y','N','ssafy2@ssafy.com','0','N','이싸피','$2a$10$2BN47jS5MR71ApGBrQH36.caj0RIs9qSWlv1/GMady0QnQmy5/a4.','010-0000-0000','staff2',24),(19,'2022-02-17 17:00','2022-02-17 17:00','Y','N','Y','ssafy@ssafy.com','0','N','관리자','$2a$10$/2/VHJECdDAMOc735YaMKuRZrCtzo8Vnri74O752HH9T9fspuZM7C','010-0000-0000','admin2',NULL),(20,'2022-02-17 18:08','2022-02-17 18:11','N','Y','N','tree@ssafy.com','0','N','채트리','$2a$10$lxpkGBe18r46NO0vLWt/2efnQAOPDsPw3m1DPjUyoBhFYmB2yIxfe','010-1234-5678','tree',NULL),(21,'2022-02-17 19:19','2022-02-17 19:19','N','N','N','baba@naver.com','0','N','바바','$2a$10$x964Dn4BKVtlFBjZMfkw8uzGN9Vu.XqiZcb29HOKOktg3K7whr1RK','01088452745','baba',NULL),(22,'2022-02-17 19:30','2022-02-17 19:30','N','N','N','bana96@daum.net','0','N','baba','$2a$10$hQeGz.o/a49rVZTsWn.xr.578KmeO3OpAC7lsPGJrUhMh8dZ/d0sC','01011112222','baba123',NULL);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 11:00:48
