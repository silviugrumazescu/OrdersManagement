-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: ordersmag
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) NOT NULL,
  `prenume` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `adresa` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (1,'Miahi','Aurel','aurelmihai@gmail.com','str. Primavara nr. 12'),(2,'Cristian ','Turcea','cristi9@yahoo.com','Botosani, str. Magurele nr.12'),(3,'Elena','Vlasov','elenaxx12@gmail.com','Falticeni, str. Republicii nr. 10'),(4,'Matei','Corvin','mateiush420@yahoo.com','Cluj-Napoca, str. Augusting Presecan nr. 6 bl. T2'),(5,'Maria ','Ioana','mariaioana5020@yahoo.com','Iasi, str. Mihai Eminescu nr. 19'),(6,'Costel','Vamesu','costikka.vamesu@gmail.com','Radaseni, str. Viilor nr.2'),(7,'Marcela','Andries','marcela.andries@gmail.com','Falticeni, str. Motilor nr.3 bl T4 ap 32'),(8,'Mircea','Diaconu','mircea.diaconu@protonmail.com','Bucuresti, str. Victoriei nr. 8'),(9,'Laura','Matei','laura.matei@yahoo.com','Constanta, Costinesti, str. Delfinului nr.2');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comanda`
--

DROP TABLE IF EXISTS `comanda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comanda` (
  `id` int NOT NULL AUTO_INCREMENT,
  `clientID` int NOT NULL,
  `produsID` int NOT NULL,
  `cantitate` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `clientID_idx` (`clientID`),
  KEY `produsID_idx` (`produsID`),
  CONSTRAINT `clientID` FOREIGN KEY (`clientID`) REFERENCES `client` (`id`),
  CONSTRAINT `produsID` FOREIGN KEY (`produsID`) REFERENCES `produs` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comanda`
--

LOCK TABLES `comanda` WRITE;
/*!40000 ALTER TABLE `comanda` DISABLE KEYS */;
INSERT INTO `comanda` VALUES (1,1,2,3),(2,2,5,3),(3,3,1,130),(4,4,6,2),(5,5,3,5),(6,6,7,1),(7,7,1,20),(8,8,2,2),(9,9,4,7);
/*!40000 ALTER TABLE `comanda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produs`
--

DROP TABLE IF EXISTS `produs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nume` varchar(45) NOT NULL,
  `pret` int NOT NULL,
  `descriere` longtext,
  `cantitate` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produs`
--

LOCK TABLES `produs` WRITE;
/*!40000 ALTER TABLE `produs` DISABLE KEYS */;
INSERT INTO `produs` VALUES (1,'Cuie',1,'1.8 x 19mm',200),(2,'Mortar',30,'25kg, alb, interior, exterior',20),(3,'Tabla zincata',34,'0.25 x 910 x 1500mm, maro',30),(4,'Betoniera',750,'120L, 550W',5),(5,'Roaba',185,'85L',10),(6,'Chinga de ancoraj',100,'12m, 50mm',50),(7,'Ciocan ',20,'1200TT',123),(8,'Stivuitor electric',21000,'1930 x 1710 x 800 mm, 1.2 tone',3);
/*!40000 ALTER TABLE `produs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-09 10:55:19
