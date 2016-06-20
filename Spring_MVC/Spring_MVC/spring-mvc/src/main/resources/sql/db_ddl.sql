-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.13 - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
-- Дамп данных таблицы fast_food.cart_item: ~0 rows (приблизительно)
DELETE FROM `cart_item`;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;

-- Дамп данных таблицы fast_food.meal: ~6 rows (приблизительно)
DELETE FROM `meal`;
/*!40000 ALTER TABLE `meal` DISABLE KEYS */;
INSERT INTO `meal` (`ID`, `NAME`, `VEGETARIAN`, `DIABETIC`, `PRICE`, `IMG_PATH`) VALUES
	(2, 'Vegeterian Hamburger', 1, 1, 6.99, '/resources/img/vegi-burger.jpg'),
	(3, 'Cheesburger', 0, 0, 7.25, 'http://localhost:8080/fastfood/images/cheesburger.jpg'),
	(4, 'Coke', NULL, 0, 2.50, '/resources/img/coke.jpg'),
	(5, 'Diabetic Coke', NULL, 1, 2.99, '/resources/img/diet_coke.png'),
	(6, 'Mineral Water', NULL, NULL, 3.01, '/resources/img/mineral_water.png'),
	(16, 'awe', 1, NULL, 2.00, 'http://localhost:8080/fastfood/images/diet_coke.png');
/*!40000 ALTER TABLE `meal` ENABLE KEYS */;

-- Дамп данных таблицы fast_food.role: ~0 rows (приблизительно)
DELETE FROM `role`;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`ID`, `NAME`) VALUES
	(1, 'admin'),
	(2, 'user');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;

-- Дамп данных таблицы fast_food.user: ~3 rows (приблизительно)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`ID`, `LOGIN`, `PASSWORD`, `F_NAME`, `L_NAME`) VALUES
	(3, 'BobT', 'spring', 'Bill', 'Tornton'),
	(4, 'AdamS', 'spring', 'Adam', 'Sandler'),
	(5, 'JonnyD', 'spring', 'John', 'Depp'),
	(6, 'Admin', 'spring', 'Admin', 'Admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Дамп данных таблицы fast_food.user_role: ~0 rows (приблизительно)
DELETE FROM `user_role`;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
	(6, 1),
	(4, 2),
	(3, 2),
	(5, 2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
