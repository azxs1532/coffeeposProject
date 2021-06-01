-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema jpg
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema jpg
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jpg` DEFAULT CHARACTER SET utf8 ;
USE `jpg` ;

-- -----------------------------------------------------
-- Table `jpg`.`coffee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpg`.`coffee` (
  `menu` CHAR(30) NULL,
  `won` INT(30) NULL,
  `ea` INT(30) NULL,
  `daily` INT(30) NULL,
  `money` INT(30) NULL)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `jpg`.`jpglist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpg`.`jpglist` (
  `no` INT(10) NOT NULL AUTO_INCREMENT,
  `menulist` VARCHAR(50) NULL,
  `money` INT(20) NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `jpg`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `jpg`.`review` (
  `idx` INT(11) NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(45) NULL,
  `han` VARCHAR(500) NULL,
  `password` VARCHAR(45) NULL,
  `review` VARCHAR(45) NULL,
  PRIMARY KEY (`idx`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
