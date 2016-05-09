-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.20 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных fast_food
DROP DATABASE IF EXISTS `fast_food`;
CREATE DATABASE IF NOT EXISTS `fast_food` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `fast_food`;


-- Дамп структуры для таблица fast_food.cart_item
DROP TABLE IF EXISTS `cart_item`;
CREATE TABLE IF NOT EXISTS `cart_item` (
  `USER_ID` int(10) NOT NULL,
  `MEAL_ID` int(10) NOT NULL,
  `QTY` int(3) NOT NULL,
  `DATE_ADDED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`ID`),
  KEY `FK_USERID` (`USER_ID`),
  KEY `FK_MEALID` (`MEAL_ID`),
  CONSTRAINT `FK_MEALID` FOREIGN KEY (`MEAL_ID`) REFERENCES `meal` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_USERID` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы fast_food.cart_item: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;


-- Дамп структуры для таблица fast_food.meal
DROP TABLE IF EXISTS `meal`;
CREATE TABLE IF NOT EXISTS `meal` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `VEGETARIAN` tinyint(1) DEFAULT '0',
  `DIABETIC` tinyint(1) DEFAULT '0',
  `PRICE` decimal(10,2) NOT NULL,
  `IMG_PATH` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы fast_food.meal: ~7 rows (приблизительно)
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` (`ID`, `NAME`, `VEGETARIAN`, `DIABETIC`, `PRICE`, `IMG_PATH`) VALUES
	(2, 'Vegeterian Hamburger', 1, 1, 6.99, '/resources/img/vegi-burger.jpg'),
	(3, 'Cheesburger', 0, 0, 7.25, 'http://localhost:8080/fastfood/images/cheesburger.jpg'),
	(4, 'Coke', NULL, 0, 2.50, '/resources/img/coke.jpg'),
	(5, 'Diabetic Coke', NULL, 1, 2.99, '/resources/img/diet_coke.png'),
	(6, 'Mineral Water', NULL, NULL, 3.01, '/resources/img/mineral_water.png'),
	(16, 'awe', 1, NULL, 2.00, 'http://localhost:8080/fastfood/images/diet_coke.png');
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;


-- Дамп структуры для таблица fast_food.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `LOGIN` varchar(25) NOT NULL,
  `PASSWORD` varchar(60) NOT NULL,
  `F_NAME` varchar(60) NOT NULL,
  `L_NAME` varchar(60) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы fast_food.user: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`ID`, `LOGIN`, `PASSWORD`, `F_NAME`, `L_NAME`) VALUES
	(3, 'BobT', 'spring', 'Bill', 'Tornton'),
	(4, 'AdamS', 'spring', 'Adam', 'Sandler'),
	(5, 'JonnyD', 'spring', 'John', 'Depp');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
