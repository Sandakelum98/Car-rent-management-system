-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.11-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for car_rental_company
CREATE DATABASE IF NOT EXISTS `car_rental_company` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `car_rental_company`;

-- Dumping structure for table car_rental_company.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `cust_id` int(11) NOT NULL AUTO_INCREMENT,
  `nic` varchar(50) NOT NULL,
  `cust_name` varchar(50) NOT NULL,
  `cust_address` varchar(50) DEFAULT NULL,
  `mobile_no` varchar(50) NOT NULL,
  PRIMARY KEY (`cust_id`) USING BTREE,
  UNIQUE KEY `mobile_no` (`mobile_no`),
  UNIQUE KEY `nic` (`nic`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.customer: ~8 rows (approximately)
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
REPLACE INTO `customer` (`cust_id`, `nic`, `cust_name`, `cust_address`, `mobile_no`) VALUES
	(16, '922710333V', 'Rasika Niroshan', 'Angoda', '0717063156'),
	(17, '980900886V', 'Sanjaya Sandakelum', 'Kahawandala', '0717760530'),
	(18, '993570710V', 'Danuka Lakshan', 'Kurunegala', '0778090357'),
	(19, '933013650V', 'Kasun Chamara', 'Siripura', '0711727579'),
	(20, '981563221V', 'Yasindi Amaya', 'Warakapola', '0714449623'),
	(21, '574563219V', 'Dase Dasanayake', '', '0777955554'),
	(22, '929632147V', 'Nimesha Sandeepani', 'Kahawandala', '0777821086'),
	(23, '980900889V', 'Jaynga Tharu', 'Kandy', '0767640530');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for procedure car_rental_company.dateList
DELIMITER //
CREATE PROCEDURE `dateList`(
	IN `from_date` DATE,
	IN `to_date` DATE
)
BEGIN
 CREATE TEMPORARY TABLE IF NOT EXISTS date_range (day DATE);

    WHILE from_date <= to_date DO
      INSERT INTO date_range VALUES (from_date);
      SET from_date = DATE_ADD(from_date, INTERVAL 1 DAY);
    END WHILE;

    SELECT * FROM date_range;
    DROP TEMPORARY TABLE IF EXISTS date_range;
END//
DELIMITER ;

-- Dumping structure for table car_rental_company.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `res_id` int(11) NOT NULL,
  `payment_date` date NOT NULL,
  `payment_type` varchar(50) NOT NULL,
  `amount` double(22,0) NOT NULL,
  PRIMARY KEY (`payment_id`),
  KEY `FK_payment_reservation` (`res_id`),
  CONSTRAINT `FK_payment_reservation` FOREIGN KEY (`res_id`) REFERENCES `reservation` (`res_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.payment: ~6 rows (approximately)
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
REPLACE INTO `payment` (`payment_id`, `res_id`, `payment_date`, `payment_type`, `amount`) VALUES
	(1, 1, '2020-10-06', 'Advance', 1000),
	(2, 2, '2020-10-06', 'Advance', 1500),
	(3, 3, '2020-10-06', 'Advance', 0),
	(4, 4, '2020-10-08', 'Advance', 1500),
	(5, 4, '2020-10-08', 'Full Payment', 4500),
	(6, 5, '2020-10-10', 'Advance', 2000);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- Dumping structure for table car_rental_company.payment_detail
CREATE TABLE IF NOT EXISTS `payment_detail` (
  `payment_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `payment_id` int(11) NOT NULL,
  `amount` double NOT NULL,
  `payment_type` varchar(50) NOT NULL,
  `card_number` int(11) DEFAULT NULL,
  PRIMARY KEY (`payment_detail_id`),
  KEY `FK_payment_detail_payment` (`payment_id`),
  CONSTRAINT `FK_payment_detail_payment` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`payment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.payment_detail: ~6 rows (approximately)
/*!40000 ALTER TABLE `payment_detail` DISABLE KEYS */;
REPLACE INTO `payment_detail` (`payment_detail_id`, `payment_id`, `amount`, `payment_type`, `card_number`) VALUES
	(1, 1, 1000, 'Cash', 0),
	(2, 2, 1500, 'Cash', 0),
	(3, 3, 0, 'Cash', 0),
	(4, 4, 1500, 'Card', 1234),
	(5, 5, 4500, 'Cash', 0),
	(6, 6, 2000, 'Cash', 0);
/*!40000 ALTER TABLE `payment_detail` ENABLE KEYS */;

-- Dumping structure for table car_rental_company.price_category
CREATE TABLE IF NOT EXISTS `price_category` (
  `price_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `price_category_name` varchar(50) DEFAULT NULL,
  `default_rate` double NOT NULL,
  `km` int(11) NOT NULL,
  `adding_rate` double NOT NULL,
  PRIMARY KEY (`price_category_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.price_category: ~4 rows (approximately)
/*!40000 ALTER TABLE `price_category` DISABLE KEYS */;
REPLACE INTO `price_category` (`price_category_id`, `price_category_name`, `default_rate`, `km`, `adding_rate`) VALUES
	(1, 'Category 1', 2000, 200, 50),
	(2, 'Category 2', 3000, 200, 60),
	(3, 'Category 3', 3200, 200, 65),
	(4, 'Category 4', 3400, 200, 70);
/*!40000 ALTER TABLE `price_category` ENABLE KEYS */;

-- Dumping structure for table car_rental_company.reservation
CREATE TABLE IF NOT EXISTS `reservation` (
  `res_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `transaction_date` date NOT NULL,
  `reservation_status` varchar(50) NOT NULL COMMENT 'booked,issueed,completed',
  PRIMARY KEY (`res_id`),
  KEY `FK_reservation_customer` (`cust_id`),
  KEY `FK_reservation_user` (`user_id`),
  CONSTRAINT `FK_reservation_customer` FOREIGN KEY (`cust_id`) REFERENCES `customer` (`cust_id`),
  CONSTRAINT `FK_reservation_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.reservation: ~5 rows (approximately)
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
REPLACE INTO `reservation` (`res_id`, `cust_id`, `user_id`, `transaction_date`, `reservation_status`) VALUES
	(1, 17, 3, '2020-10-06', 'Booked'),
	(2, 19, 3, '2020-10-06', 'Booked'),
	(3, 18, 3, '2020-10-06', 'Booked'),
	(4, 22, 3, '2020-10-08', 'Complete'),
	(5, 23, 3, '2020-10-10', 'Booked');
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;

-- Dumping structure for table car_rental_company.reservation_days
CREATE TABLE IF NOT EXISTS `reservation_days` (
  `reservation_day_id` int(11) NOT NULL AUTO_INCREMENT,
  `reservation_datail_id` int(11) NOT NULL,
  `reservation_date` date NOT NULL,
  PRIMARY KEY (`reservation_day_id`),
  KEY `FK__resevation_detail` (`reservation_datail_id`),
  CONSTRAINT `FK__resevation_detail` FOREIGN KEY (`reservation_datail_id`) REFERENCES `reservation_detail` (`res_detail_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.reservation_days: ~11 rows (approximately)
/*!40000 ALTER TABLE `reservation_days` DISABLE KEYS */;
REPLACE INTO `reservation_days` (`reservation_day_id`, `reservation_datail_id`, `reservation_date`) VALUES
	(1, 1, '2020-10-12'),
	(2, 1, '2020-10-13'),
	(3, 2, '2020-10-15'),
	(4, 2, '2020-10-16'),
	(5, 3, '2020-10-20'),
	(6, 3, '2020-10-21'),
	(9, 4, '2020-10-11'),
	(10, 4, '2020-10-12'),
	(11, 4, '2020-10-13'),
	(12, 5, '2020-10-15'),
	(13, 5, '2020-10-16');
/*!40000 ALTER TABLE `reservation_days` ENABLE KEYS */;

-- Dumping structure for table car_rental_company.reservation_detail
CREATE TABLE IF NOT EXISTS `reservation_detail` (
  `res_detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `res_id` int(11) NOT NULL,
  `vehicle_id` int(11) NOT NULL,
  `from_date` date NOT NULL,
  `to_date` date NOT NULL,
  `default_rate` double(22,0) NOT NULL,
  `km` int(11) NOT NULL,
  `adding_rate` double NOT NULL,
  `start_mileage` int(11) DEFAULT NULL,
  `end_mileage` int(11) DEFAULT NULL,
  `total_amount` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`res_detail_id`),
  KEY `FK_resevation_detail_reservation` (`res_id`),
  KEY `FK_resevation_detail_vehicle` (`vehicle_id`),
  CONSTRAINT `FK_resevation_detail_reservation` FOREIGN KEY (`res_id`) REFERENCES `reservation` (`res_id`),
  CONSTRAINT `FK_resevation_detail_vehicle` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicle` (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.reservation_detail: ~5 rows (approximately)
/*!40000 ALTER TABLE `reservation_detail` DISABLE KEYS */;
REPLACE INTO `reservation_detail` (`res_detail_id`, `res_id`, `vehicle_id`, `from_date`, `to_date`, `default_rate`, `km`, `adding_rate`, `start_mileage`, `end_mileage`, `total_amount`) VALUES
	(1, 1, 2, '2020-10-12', '2020-10-13', 3000, 200, 60, NULL, NULL, 0),
	(2, 2, 11, '2020-10-15', '2020-10-16', 3000, 200, 60, 6500, 6800, 0),
	(3, 3, 11, '2020-10-20', '2020-10-21', 3000, 200, 60, NULL, NULL, 0),
	(4, 4, 1, '2020-10-11', '2020-10-13', 2000, 200, 50, 6600, 7100, 6000),
	(5, 5, 9, '2020-10-15', '2020-10-16', 3000, 200, 60, NULL, NULL, 0);
/*!40000 ALTER TABLE `reservation_detail` ENABLE KEYS */;

-- Dumping structure for table car_rental_company.user
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_type` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name` (`user_name`),
  UNIQUE KEY `user_password` (`user_password`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.user: ~2 rows (approximately)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
REPLACE INTO `user` (`user_id`, `user_name`, `user_password`, `user_type`) VALUES
	(2, 'sanjaya', '1998', 'admin'),
	(3, 'kamal', '1111', 'employee');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

-- Dumping structure for table car_rental_company.vehicle
CREATE TABLE IF NOT EXISTS `vehicle` (
  `vehicle_id` int(11) NOT NULL AUTO_INCREMENT,
  `price_category_id` int(11) NOT NULL,
  `make_id` int(11) NOT NULL,
  `model_id` int(11) NOT NULL,
  `vehicle_no` varchar(50) NOT NULL,
  `year` int(11) NOT NULL,
  `vehicle_colour` varchar(50) NOT NULL,
  `is_active` int(10) NOT NULL DEFAULT '1',
  PRIMARY KEY (`vehicle_id`),
  UNIQUE KEY `vehicle_no` (`vehicle_no`),
  KEY `FK_vehicle_vehicle_make` (`make_id`),
  KEY `FK_vehicle_vehicle_model` (`model_id`),
  KEY `FK_vehicle_price_category` (`price_category_id`),
  CONSTRAINT `FK_vehicle_price_category` FOREIGN KEY (`price_category_id`) REFERENCES `price_category` (`price_category_id`),
  CONSTRAINT `FK_vehicle_vehicle_make` FOREIGN KEY (`make_id`) REFERENCES `vehicle_make` (`make_id`),
  CONSTRAINT `FK_vehicle_vehicle_model` FOREIGN KEY (`model_id`) REFERENCES `vehicle_model` (`model_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.vehicle: ~9 rows (approximately)
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
REPLACE INTO `vehicle` (`vehicle_id`, `price_category_id`, `make_id`, `model_id`, `vehicle_no`, `year`, `vehicle_colour`, `is_active`) VALUES
	(1, 1, 3, 5, 'KV-4613', 2005, 'Red', 1),
	(2, 2, 2, 4, 'KH-9421', 2007, 'Blue', 1),
	(3, 1, 3, 6, 'KA-1335', 2002, 'Black', 1),
	(4, 1, 3, 5, 'CAA-7896', 2011, 'Grey', 1),
	(5, 1, 3, 5, 'CAA-7789', 2008, 'red', 1),
	(6, 1, 1, 3, 'CAB-1236', 2015, 'White', 1),
	(7, 2, 3, 7, 'KW-7874', 2012, 'Black', 1),
	(8, 2, 1, 1, 'HC-4652', 2000, 'Light Blue', 1),
	(9, 2, 1, 2, 'KT-4563', 2013, 'Red', 1),
	(10, 2, 3, 5, 'CBA-7861', 2017, 'Green', 1),
	(11, 2, 1, 15, 'HI-2115', 2010, 'White', 1);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;

-- Dumping structure for table car_rental_company.vehicle_make
CREATE TABLE IF NOT EXISTS `vehicle_make` (
  `make_id` int(11) NOT NULL AUTO_INCREMENT,
  `make_name` varchar(50) NOT NULL,
  PRIMARY KEY (`make_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.vehicle_make: ~4 rows (approximately)
/*!40000 ALTER TABLE `vehicle_make` DISABLE KEYS */;
REPLACE INTO `vehicle_make` (`make_id`, `make_name`) VALUES
	(1, 'Toyota'),
	(2, 'Nissan'),
	(3, 'Suzuki'),
	(4, 'Micro'),
	(5, 'Mitsubishi');
/*!40000 ALTER TABLE `vehicle_make` ENABLE KEYS */;

-- Dumping structure for table car_rental_company.vehicle_model
CREATE TABLE IF NOT EXISTS `vehicle_model` (
  `model_id` int(11) NOT NULL AUTO_INCREMENT,
  `make_id` int(11) NOT NULL,
  `model_name` varchar(50) NOT NULL,
  PRIMARY KEY (`model_id`),
  KEY `FK_vehicle_model_vehicle_make` (`make_id`),
  CONSTRAINT `FK_vehicle_model_vehicle_make` FOREIGN KEY (`make_id`) REFERENCES `vehicle_make` (`make_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Dumping data for table car_rental_company.vehicle_model: ~17 rows (approximately)
/*!40000 ALTER TABLE `vehicle_model` DISABLE KEYS */;
REPLACE INTO `vehicle_model` (`model_id`, `make_id`, `model_name`) VALUES
	(1, 1, 'Vitz'),
	(2, 1, 'Prius'),
	(3, 1, 'Axio'),
	(4, 2, 'March'),
	(5, 3, 'Alto'),
	(6, 3, 'Maruti'),
	(7, 3, 'Wagon-R'),
	(8, 4, 'Panda'),
	(9, 2, 'Sunny'),
	(10, 2, 'BlueBird'),
	(11, 1, 'Carina'),
	(12, 2, 'X-Trail'),
	(13, 2, 'Caravan'),
	(14, 1, 'KDH'),
	(15, 1, 'Dolphin'),
	(16, 1, 'Lite Ace'),
	(17, 3, 'Every'),
	(18, 5, 'Lancer Box');
/*!40000 ALTER TABLE `vehicle_model` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
