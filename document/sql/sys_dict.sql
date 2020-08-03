/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-02-26 16:26:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `value` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '性别', '男', '男');
INSERT INTO `sys_dict` VALUES ('2', '性别', '女', '女');
INSERT INTO `sys_dict` VALUES ('3', '季度', '第一季度', '1');
INSERT INTO `sys_dict` VALUES ('4', '季度', '第二季度', '2');
INSERT INTO `sys_dict` VALUES ('5', '季度', '第三季度', '3');
INSERT INTO `sys_dict` VALUES ('6', '季度', '第四季度', '4');
INSERT INTO `sys_dict` VALUES ('25', '你好', '你好', '你好');
