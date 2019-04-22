/*
Navicat MySQL Data Transfer

Source Server         : server
Source Server Version : 50725
Source Host           : localhost:3306
Source Database       : Demo

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-04-22 23:39:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(255) NOT NULL,
  `phoneNum` char(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('297ac948-ca0b-4025-9120-94987c341f62', '12345678912', 'qiluren', '崎路人');
INSERT INTO `t_user` VALUES ('496fe26a-e263-4581-8744-54afea8abcf9', '12345678910', 'nicholas', 'Nicholas.Hzf');
INSERT INTO `t_user` VALUES ('62fd2835-0176-4f69-b5fa-4287cee61cc3', '14785236910', 'qiluren', '崎路人2');
INSERT INTO `t_user` VALUES ('e9afe1b7-920f-4491-90db-7cdc1d23ff22', '10987654321', 'qingxiangbailian', '素还真');
