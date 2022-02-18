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
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` varchar(255) DEFAULT NULL,
  `updated_at` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `desks_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2d8gt6ju1lf5lwiij9qu2n9oo` (`desks_id`),
  CONSTRAINT `FK2d8gt6ju1lf5lwiij9qu2n9oo` FOREIGN KEY (`desks_id`) REFERENCES `desk` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'2022-02-16 03:03','2022-02-16 03:03','다들 코로나 조심!!','$2a$10$Mje8zUQQHx38copWYfxeFOtDOIL0SAJoEfhyzE5vBWc9dfIhmonCy','홍대는 사람이 많네요',2),(3,'2022-02-16 03:08','2022-02-16 03:08','야호','$2a$10$G1jDaT81cGmFlz39eXe1IeP2zWoCTlBNQX9R1855ANeLpC2Yq8Lby','야호',2),(5,'2022-02-16 03:08','2022-02-16 03:08','잠을 푹 잘거야!!','$2a$10$XcDwOX6JztHd5xrh5IgIsuj33ssZk82VX1.eOUTtfuv1wX2q.RLJe','이번주 주말엔',2),(9,'2022-02-16 19:59','2022-02-16 19:59','ㅠㅠ','$2a$10$uieqdjO7K8L.UzbeLMQKSOX9N3aKwoR1KSregojHkir9PDiysxbOu','다들 너무나도 고생하셨어요 ㅠㅠ',2),(10,'2022-02-17 10:27','2022-02-17 10:27','으아아아','$2a$10$CvcKD.NqWg.KmxVjQFMnguZRh3UvI6DYQGUu3DrwSA6WvpvDhC53G','고생 정말 많으셨어요 ㅠㅠㅠ',2),(11,'2022-02-17 10:45','2022-02-17 10:45','얼른 금요일!!!','$2a$10$yBUf.lMeIwvPFZ2HnkqDeOne9BGJX8E/9dojuf9egpCBhWQX1O0sm','헤헤',2),(12,'2022-02-17 10:52','2022-02-17 10:52','글 잘써지나요?','$2a$10$57w3towm3i9dpQSqPLM00OMGEmac78b3.9EnCn6yQPsyna.5ViDBC','글 잘써지나?',67),(14,'2022-02-17 11:03','2022-02-17 11:03','야호','$2a$10$MxSupRsGfIgAnXJMH0PpRuY/8jnweLs1yYmNMByd72z4mhuqjgI5K','대한독립만세!',43),(15,'2022-02-17 13:10','2022-02-17 13:10','같이 여행해요!','$2a$10$KDYbP0xIt.MiEalJOdS1KOIx095Y/Rf7rLfG5i817T4MexdRQ0lVa','안녕하세요! 같이 여행할 친구 구해요!',28),(16,'2022-02-17 15:05','2022-02-17 15:05','와아','$2a$10$zg5jxAZa8FZtYqhWhhCJ3OV.yRiX.FZZHtsrsnsxUIW/rgwMjxW2G','건대엔 맛집이 많아요',64),(17,'2022-02-17 15:30','2022-02-17 15:30','Hongik Univ는\n홍익 유니버시티 일까요\n혼긱 유니버시티 일까요\n미스테리 합니다....','$2a$10$VTU0VU64MQDUG54YamOpM.bwWOfe1wjlnCh7iCGlWghbpd3GABp.S','홍대는 영어로 뭐라고 부르나요',2),(18,'2022-02-17 15:31','2022-02-17 15:31','미안하다 이거 보여주려고 어그로 끌었다','$2a$10$LaiwSYw930f3bcuR6H/4iebbQJYf8U3bNpjk0GyYkBOLol01FKQsW','홍대 최고 맛집 소개합니다.',2),(19,'2022-02-17 15:31','2022-02-17 15:31','홍대 맛집 맛있는 곳 많아요.','$2a$10$mJ8XJFjOLMuVJIwglyWW9ONJyjd.65jEm478CJKMLo4DCaO.JHWZC','홍대 맛집 굳굳',2),(20,'2022-02-17 15:32','2022-02-17 15:32','2022년 2월 15일 홍대 댄스 버스킹 다시 돌아옵니다. 많은 관심부탁드려요~','$2a$10$OKIFJAm8iQ1h653vyAXIW.6.Kfr5A7p3UCq4nGLuYvwf5Z6b1ZaZS','홍대 거리 버스킹 예정 2022',2),(22,'2022-02-17 15:34','2022-02-17 15:34','이번에 여행왔는데 재밌게 놀러 다닐 친구 구해요\n\n함께 맛집 탐방 하실분!!\n\n인스타 디엠주세요\n\nsteve_ssafy_kim','$2a$10$hY36Typ84dVsff2EAVYs7OZ0Z8jc/B17oH3PGaj0un2l3nEn8vFyq','홍대 함께 여행하실 동행 찾습니다.',2),(23,'2022-02-17 15:35','2022-02-17 15:35','한강도 이쁘고 이태원 해방촌도 야경 예뻐요','$2a$10$m4Hn9h1Tma26NqlewxqMk.q/5MnWj0U/IR0gAgCsKLRkGhRi.CbzK','서울 야경 추천해요!!',2),(24,'2022-02-17 15:36','2022-02-17 15:36','서울시 자전거 따릉이 타세요 한시간에 천원 밖에 안해요! 자전거 퀄리티도 꽤 좋아요','$2a$10$.ESoNuMpI6bkE3IYrLLrFeQcbXoxMDS0NdN9ItPUL4nMq7kQ87Wam','서울 여행 꿀팁',2),(25,'2022-02-17 15:39','2022-02-17 15:39','야호','$2a$10$jG2HB2Q0hSAhqBJRDRV74u8wYLKgwMBWOfDgDxJplurzmXcyyK1Au','나도 건대생 하고싶다~~~~',64),(26,'2022-02-17 15:40','2022-02-17 15:40','건대에서 한강까지 걸어가는 것도 가능!','$2a$10$IMZX9hNkpn/Nckxjbpvtyevilk9paJltPahvExVjCAcQGZhTq/KFm','한강도 갈 수 있어요',64),(27,'2022-02-17 15:40','2022-02-17 15:40','아아아아앙','$2a$10$JiAZduKz291ecf3qja1e2e1z8FIa8cbaGIhE2yCOk0VlhrHB1gR36','와아아아앙',64),(28,'2022-02-17 15:44','2022-02-17 15:44','서울에는 이런거도 다있네 ㅋㅋ\n이래가  서울에서 살아야된다 카는구나\n마~이 배워갑니데이','$2a$10$8vtdMX/uOPjabJ.6bScCHe.PUMTHd.ApjuUYL9OFRrcp9NU5NNE7y','와 이거 쥑이네예',28),(29,'2022-02-17 15:45','2022-02-17 15:45','어른이 공원도 만들어 주세요 응애','$2a$10$Mxf.oPTLXAU6KZQl6mIX2eVTwjqgCmDIAampQJMGMscYJjnMP8yZO','어린이 공원 너무 좋아요',28),(30,'2022-02-17 15:46','2022-02-17 15:46','건대 많이들 오세요','$2a$10$v9QnD7eAxoqH56y0loTlFOTRnv5rR47y9fsK9MONqmU9AYexIRyxq','건대 많이 오세요',64),(31,'2022-02-17 15:47','2022-02-17 15:47','히히','$2a$10$x4gGc.l/kYWoxyNo.Tl6Ju//nhLFTuTKIftf61CI2sDOgOKlvbEZS','어린이대공원 가면 나도 어린이인가',28),(32,'2022-02-17 15:47','2022-02-17 15:47','건대 포장마차집 맛있습니다.','$2a$10$kx.sKC92Rjx9ODzgF6xvwOY5ztuMhqXOFFK4tC8g3nl1SHgh/UT3y','건대 포장마차집 맛있음',64),(33,'2022-02-17 15:48','2022-02-17 15:48','가족들이랑 주말에 와서 놀고갑니다 ㅎㅎ','$2a$10$uJWpdeFAYSUpkUeLE3I/Quv.GB/sOGkqAnj0fUknJiEePC3vCfN6.','서울 도심 안에 있어서 좋네요',28),(34,'2022-02-17 15:49','2022-02-17 15:49','계속 같이 가자~~~','$2a$10$xhaGVhNyxcJuAdiQukvlGeB9RVnhCI5sv5dgCcJegvT12zQMN1psq','어린이대공원 없어지면 안돼요',28),(35,'2022-02-17 15:49','2022-02-17 15:50','나는 경기도 안양의 이준영이다','$2a$10$BYoI4rViowFGHFCmDWmh9up7bip7CbJU140mDl0KEdJ0BK67/YU4y','나는 경기도 안양의 이준영이다',28),(36,'2022-02-17 15:50','2022-02-17 15:50','해외여행도 갈 수 있으면 좋겠어요!','$2a$10$APQMxFShd3ByCHSSm4NNceTm07Akf2a8EtvQHND6nvvNm1Je18sQi','코로나 얼른 끝나서',2),(37,'2022-02-17 16:22','2022-02-17 16:22','다훔에도 올커해효!','$2a$10$fqXbUmlUQ7d7sK/DFlfjXuHSiazeLK8oMqsg/ZFSYF4wAMpcH5dwu','한쿡 좋아요! ',2),(39,'2022-02-17 16:44','2022-02-17 16:44','서울의 매력에 빠져보세요!!','$2a$10$4fP/XIQ8smB8XqmiHPK7j.zZAon24691WVqku3qld79PnfDAkU8Y2','아름다운 서울',2),(40,'2022-02-17 16:47','2022-02-17 17:11','Welcome to Jamsil!','$2a$10$/9VHXH2t.GHFCOfmAt7yyuI4L0W/2LvXkKT8xxTvGmNGGuzl/lHzG','잠실 게시판에 오신걸 환영합니다.',22),(41,'2022-02-17 16:53','2022-02-17 16:53','Welcome to Sinchon!','$2a$10$yhKpTMhMJoBEqeP.zzwIu.dr5EDBuHn8aUg.vGZjSD2z8K60OXEAK','신촌 게시판에 오신 것을 환영합니다.',24),(43,'2022-02-17 18:22','2022-02-17 18:22','다시 또 올게요~','$2a$10$aszMCHKYeIYAyyGrKVj/2e0gM5h/Y8g4SQSrJRAbZjkzkVKhlSq5S','건대 처음 왔는데 좋네요!!',64);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-18 11:00:49
