DROP DATABASE IF EXISTS photo;

CREATE DATABASE photo;

use photo;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `nickname` VARCHAR(60) NOT NULL ,
  `sex` VARCHAR(10) not null,
  `real_name` VARCHAR(20) ,
  `addr` VARCHAR(255) ,
  `email` VARCHAR(255),
  `phone` VARCHAR(30),
  `headimgurl` VARCHAR(255) ,
  `openid` VARCHAR(60),
  `province` VARCHAR(20)
)ENGINE =InnoDB DEFAULT CHARSET =utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `order_day`;
CREATE TABLE `order_day`(
  `order_id` int not NULL PRIMARY KEY AUTO_INCREMENT,
  `store_id` INT NOT NULL ,
  `is_expire` INT not null DEFAULT 0
)ENGINE =InnoDB DEFAULT CHARSET =utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `order_point`;
CREATE TABLE `order_point`(
  `point_id` int not NULL PRIMARY KEY AUTO_INCREMENT,
  `order_id` int NOT NULL ,
  `ordered` int NOT NULL DEFAULT 0,
  `is_expire` int not null DEFAULT 0
)ENGINE =InnoDB DEFAULT CHARSET =utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `order_type`;
CREATE TABLE `order_type`(
  `type_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
  `name` VARCHAR(60) NOT NULL ,
  `money` FLOAT NOT NULL
)ENGINE =InnoDB DEFAULT CHARSET =utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`(
  `store_id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` VARCHAR(60) NOT NULL ,
  `addr` VARCHAR(255) NOT NULL ,
  `open_hours` VARCHAR(60) NOT NULL,
  `phone` VARCHAR(30) not null,
  `closed` INT NOT NULL DEFAULT 0 COMMENT '是否开业'
)ENGINE =InnoDB DEFAULT CHARSET =utf8 AUTO_INCREMENT=1;

DROP TABLE IF EXISTS `detail`;
CREATE TABLE `detail`(
  `id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `user_id` int NOT NULL ,
  `order_id` INT NOT NULL ,
  `point_id` INT NOT NULL ,
  `type_id` INT NOT NULL ,
  `store_id` INT NOT NULL ,
  `status` INT NOT NULL DEFAULT 0 COMMENT '0:未完成 1:已完成 -1:已取消 -2:已过期',
  `order_time` VARCHAR(100) NOT NULL ,
  `create_time` VARCHAR(100) not null,
  `remark` TEXT
)ENGINE =InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET =utf8;

DROP table  IF EXISTS `token`;
create table `token`(
  `rec_id` int(11) not null PRIMARY KEY AUTO_INCREMENT,
  `user_id` int not null,
  `token` varchar(255) not null,
  `create_time` varchar(255) not null,
  `expire_time` varchar(255) not null

)ENGINE =InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET =utf8;