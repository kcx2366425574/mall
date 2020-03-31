/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 5.5.36 : Database - mall
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mall` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mall`;

/*Table structure for table `cus_ex` */

DROP TABLE IF EXISTS `cus_ex`;

CREATE TABLE `cus_ex` (
  `ce_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ce_cus_id` int(11) NOT NULL COMMENT '用户id',
  `ce_cus_name` varchar(20) NOT NULL COMMENT '用户姓名',
  `ce_cus_address` varchar(50) NOT NULL COMMENT '用户地址',
  `ce_cus_idcard` varchar(18) NOT NULL COMMENT '用户身份证',
  PRIMARY KEY (`ce_id`),
  UNIQUE KEY `ce_cus_id` (`ce_cus_id`),
  CONSTRAINT `cus_ex_ibfk_1` FOREIGN KEY (`ce_cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `cus_ex` */

insert  into `cus_ex`(`ce_id`,`ce_cus_id`,`ce_cus_name`,`ce_cus_address`,`ce_cus_idcard`) values (1,1,'张三丰','辽宁省盛阳师','123456789009876543');

/*Table structure for table `cus_record` */

DROP TABLE IF EXISTS `cus_record`;

CREATE TABLE `cus_record` (
  `cr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cr_cus_id` int(11) NOT NULL COMMENT '用户id',
  `cr_cus_recharge` float NOT NULL COMMENT '用户充值金额',
  `cr_cus_rest` float NOT NULL COMMENT '用户充值后余额',
  `cr_time` datetime NOT NULL COMMENT '用户充值时间',
  PRIMARY KEY (`cr_id`),
  KEY `cr_cus_id` (`cr_cus_id`),
  CONSTRAINT `cus_record_ibfk_1` FOREIGN KEY (`cr_cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cus_record` */

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `cus_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `cus_login_name` varchar(20) NOT NULL COMMENT '用户登录名',
  `cus_password` varchar(20) NOT NULL COMMENT '用户密码',
  `cus_account` float NOT NULL COMMENT '用户余额',
  `cus_contanct_info` varchar(1000) DEFAULT NULL COMMENT '用户基本信息，包含联系方式',
  `cus_ison` tinyint(1) NOT NULL COMMENT '用户是否在线',
  PRIMARY KEY (`cus_id`),
  UNIQUE KEY `cus_login_name` (`cus_login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `customer` */

insert  into `customer`(`cus_id`,`cus_login_name`,`cus_password`,`cus_account`,`cus_contanct_info`,`cus_ison`) values (1,'zhangsan','123456',13,'没有联系方式',0),(2,'lisi','123456',99,'怎么可能没有联系方式呢，13112345678',0);

/*Table structure for table `mana_role` */

DROP TABLE IF EXISTS `mana_role`;

CREATE TABLE `mana_role` (
  `mr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mr_mana_id` int(11) NOT NULL COMMENT '管理员id',
  `mr_role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`mr_id`),
  KEY `mr_mana_id` (`mr_mana_id`),
  KEY `mr_role_id` (`mr_role_id`),
  CONSTRAINT `mana_role_ibfk_1` FOREIGN KEY (`mr_mana_id`) REFERENCES `manager` (`mana_id`) ON UPDATE CASCADE,
  CONSTRAINT `mana_role_ibfk_2` FOREIGN KEY (`mr_role_id`) REFERENCES `role` (`role_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `mana_role` */

/*Table structure for table `manager` */

DROP TABLE IF EXISTS `manager`;

CREATE TABLE `manager` (
  `mana_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mana_name` varchar(20) NOT NULL COMMENT '管理员登录名',
  `mana_password` varchar(20) NOT NULL COMMENT '管理员登录密码',
  `mana_ison` tinyint(1) NOT NULL COMMENT '管理员是否在线',
  PRIMARY KEY (`mana_id`),
  UNIQUE KEY `mana_name` (`mana_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `manager` */

insert  into `manager`(`mana_id`,`mana_name`,`mana_password`,`mana_ison`) values (1,'super','12345678',0),(2,'ceshi','123456',0);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `perm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `perm_name` varchar(20) NOT NULL COMMENT '权限名',
  `perm_info` varchar(50) NOT NULL COMMENT '权限介绍',
  PRIMARY KEY (`perm_id`),
  UNIQUE KEY `perm_name` (`perm_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

/*Table structure for table `pic` */

DROP TABLE IF EXISTS `pic`;

CREATE TABLE `pic` (
  `pic_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pic_pro_id` int(11) NOT NULL COMMENT '商品id',
  `pic_info` longtext NOT NULL COMMENT '商品附加图片',
  PRIMARY KEY (`pic_id`),
  KEY `pic_pro_id` (`pic_pro_id`),
  CONSTRAINT `pic_ibfk_1` FOREIGN KEY (`pic_pro_id`) REFERENCES `product` (`pro_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pic` */

/*Table structure for table `pro_record` */

DROP TABLE IF EXISTS `pro_record`;

CREATE TABLE `pro_record` (
  `pr_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pr_pro_id` int(11) NOT NULL COMMENT '商品id',
  `pr_cus_id` int(11) DEFAULT NULL COMMENT '商品购买人id',
  `pr_shop_id` int(11) DEFAULT NULL COMMENT '商品购买商家id',
  `pr_time` datetime NOT NULL COMMENT '商品交易时间',
  PRIMARY KEY (`pr_id`),
  KEY `pr_pro_id` (`pr_pro_id`),
  KEY `pr_cus_id` (`pr_cus_id`),
  KEY `pr_shop_id` (`pr_shop_id`),
  CONSTRAINT `pro_record_ibfk_1` FOREIGN KEY (`pr_pro_id`) REFERENCES `product` (`pro_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pro_record_ibfk_2` FOREIGN KEY (`pr_cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pro_record_ibfk_3` FOREIGN KEY (`pr_shop_id`) REFERENCES `shop` (`shop_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pro_record` */

/*Table structure for table `pro_type` */

DROP TABLE IF EXISTS `pro_type`;

CREATE TABLE `pro_type` (
  `pt_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pt_name` varchar(20) NOT NULL COMMENT '商品类型名',
  `pt_info` varchar(1000) DEFAULT NULL COMMENT '商品类型描述',
  PRIMARY KEY (`pt_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `pro_type` */

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `pro_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pro_name` varchar(20) NOT NULL COMMENT '商品名',
  `pro_price` float NOT NULL COMMENT '商品价格',
  `pro_info` varchar(1000) DEFAULT NULL COMMENT '商品信息',
  `pro_cus_id` int(11) DEFAULT NULL COMMENT '商品发布者',
  `pro_shop_id` int(11) DEFAULT NULL COMMENT '商品发布商家',
  `pro_pt_id` int(11) NOT NULL COMMENT '商品类别',
  `pro_state` varchar(5) NOT NULL COMMENT '商品状态',
  `pro_pic` longtext NOT NULL COMMENT '商品主图片',
  `pro_mana_id` int(11) DEFAULT NULL COMMENT '审核人',
  PRIMARY KEY (`pro_id`),
  KEY `pro_cus_id` (`pro_cus_id`),
  KEY `pro_shop_id` (`pro_shop_id`),
  KEY `pro_mana_id` (`pro_mana_id`),
  KEY `product_ibfk_3` (`pro_pt_id`),
  CONSTRAINT `product_ibfk_3` FOREIGN KEY (`pro_pt_id`) REFERENCES `pro_type` (`pt_id`) ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`pro_cus_id`) REFERENCES `customer` (`cus_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_2` FOREIGN KEY (`pro_shop_id`) REFERENCES `shop` (`shop_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `product_ibfk_4` FOREIGN KEY (`pro_mana_id`) REFERENCES `manager` (`mana_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_name` varchar(20) NOT NULL COMMENT '角色名',
  `role_info` varchar(100) NOT NULL COMMENT '角色信息',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

/*Table structure for table `role_perm` */

DROP TABLE IF EXISTS `role_perm`;

CREATE TABLE `role_perm` (
  `rp_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rp_role_id` int(11) NOT NULL COMMENT '角色id',
  `rp_perm_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`rp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_perm` */

/*Table structure for table `shop` */

DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shop_name` varchar(20) NOT NULL COMMENT '商家名',
  `shop_minname` varchar(10) NOT NULL COMMENT '商家简称，用于登录名',
  `shop_password` varchar(20) NOT NULL COMMENT '商家登录密码',
  `shop_info` varchar(1000) DEFAULT NULL COMMENT '商家信息，经营范围',
  `shop_address` varchar(50) NOT NULL COMMENT '商家地址',
  `shop_owner` varchar(10) NOT NULL COMMENT '经营者姓名',
  `shop_owner_idcard` varchar(18) NOT NULL COMMENT '经营者身份证',
  `shop_account` float NOT NULL COMMENT '商家余额',
  `shop_state` varchar(5) NOT NULL COMMENT '商家审核状态',
  `shop_mana` int(11) DEFAULT NULL COMMENT '商家审核人',
  PRIMARY KEY (`shop_id`),
  UNIQUE KEY `shop_minname` (`shop_minname`),
  KEY `shop_mana` (`shop_mana`),
  CONSTRAINT `shop_ibfk_1` FOREIGN KEY (`shop_mana`) REFERENCES `manager` (`mana_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `shop` */

insert  into `shop`(`shop_id`,`shop_name`,`shop_minname`,`shop_password`,`shop_info`,`shop_address`,`shop_owner`,`shop_owner_idcard`,`shop_account`,`shop_state`,`shop_mana`) values (1,'淘宝','taobaowang','123456','收各种二手商品，尤其是电子产品','杭州','马云','123456788909874257',1092.7,'审核中',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
