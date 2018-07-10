-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: im
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisos` (
  `rolId` bigint(20) DEFAULT NULL,
  `rol_permisos` varchar(255) DEFAULT NULL,
  KEY `FK_permisos_rolId` (`rolId`),
  CONSTRAINT `FK_permisos_rolId` FOREIGN KEY (`rolId`) REFERENCES `rol` (`rolId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `rolId` bigint(20) NOT NULL AUTO_INCREMENT,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  `NOMBRE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rolId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,NULL,'Super Administrador');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `APELLIDOMATERNO` varchar(255) DEFAULT NULL,
  `APELLIDOPATERNO` varchar(255) DEFAULT NULL,
  `BANCO` varchar(255) DEFAULT NULL,
  `CONTRASENA` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `estadoUsuario` int(11) NOT NULL,
  `MALE` tinyint(1) DEFAULT '0',
  `NOMBRES` varchar(255) DEFAULT NULL,
  `NROCUENTABANCARIA` varchar(255) DEFAULT NULL,
  `TASADOR` tinyint(1) DEFAULT '0',
  `TELEFONOFIJO` varchar(255) DEFAULT NULL,
  `TELEFONOMOVIL` varchar(255) DEFAULT NULL,
  `rolId` bigint(20) DEFAULT NULL,
  `RUT` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_USUARIO_rolId` (`rolId`),
  CONSTRAINT `FK_USUARIO_rolId` FOREIGN KEY (`rolId`) REFERENCES `rol` (`rolId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'','O\'Shea',NULL,'$2a$10$07HS2atSipXGoGweh5.9geMBl4Nq8JXIdu/8/hUbsaAzoD6g2alb6','admin@admin.com',1,0,'Joseph',NULL,0,NULL,NULL,1,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-06 21:59:11
