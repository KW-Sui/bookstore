/*
 Navicat Premium Data Transfer

 Source Server         : sui
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : localhost:3306
 Source Schema         : bookstore

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 06/06/2020 16:52:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`  (
  `n_id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `details` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `n_time` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`n_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES (1, '公共告test', '11三生三世1sd', '2020-06-06 11:53:54');
INSERT INTO `notice` VALUES (2, '公告2', '本周图书销售量再创新高', '2018-04-14 15:40:34');
INSERT INTO `notice` VALUES (3, '公告3', '你好吗', '2018-04-14 15:42:13');
INSERT INTO `notice` VALUES (5, '新公告', '沐心小书屋开业啦', '2020-05-08 09:38:09');
INSERT INTO `notice` VALUES (7, 'muxin', 'muxin 第三个', '2020-06-06 14:42:43');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `order_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `product_id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `buynum` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`, `product_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES ('124f0dbb-a36c-4aa2-8aff-ff82f9dafa12', '1', 2);
INSERT INTO `orderitem` VALUES ('124f0dbb-a36c-4aa2-8aff-ff82f9dafa12', '4', 4);
INSERT INTO `orderitem` VALUES ('126f9618-30b4-4a41-97e3-3f61792f7dd9', '6', 2);
INSERT INTO `orderitem` VALUES ('609e0f3b-09de-46ef-8ffe-d19ded9f46e1', '4', 3);
INSERT INTO `orderitem` VALUES ('609e0f3b-09de-46ef-8ffe-d19ded9f46e1', '5936203b-5675-4494-9430-e92551914cc2', 1);
INSERT INTO `orderitem` VALUES ('8c1b981a-2cc3-4777-bce6-06c40afda268', 'cd3c4d90-b1cc-431f-ae98-0a45e2bcf0a2', 3);
INSERT INTO `orderitem` VALUES ('a9b677fd-5c33-4abf-a5f9-5337abe2d7d5', '6', 3);
INSERT INTO `orderitem` VALUES ('b549d17b-46a1-43d7-b175-1fa309967074', '5', 2);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` double NULL DEFAULT NULL,
  `receiverAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiverName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `receiverPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paystate` int(0) NULL DEFAULT 0,
  `ordertime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `user_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('124f0dbb-a36c-4aa2-8aff-ff82f9dafa12', 216, '212', 'test1', '1445182155', 1, '2020-06-02 22:38:12', 25);
INSERT INTO `orders` VALUES ('126f9618-30b4-4a41-97e3-3f61792f7dd9', 47.6, '一股hijo可怕冷静', 'test2', '1445182155', 0, '2020-06-06 10:49:57', 25);
INSERT INTO `orders` VALUES ('609e0f3b-09de-46ef-8ffe-d19ded9f46e1', 336, 'ada', 'test2', '1445182155', 0, '2020-06-03 11:49:15', 25);
INSERT INTO `orders` VALUES ('8c1b981a-2cc3-4777-bce6-06c40afda268', 300, 'sfsf', 'test32', '1445182155', 0, '2020-06-06 10:53:41', 25);
INSERT INTO `orders` VALUES ('a9b677fd-5c33-4abf-a5f9-5337abe2d7d5', 71.4, 'sdadda', 'test3', '1445182155', 1, '2020-06-02 22:38:19', 25);
INSERT INTO `orders` VALUES ('b549d17b-46a1-43d7-b175-1fa309967074', 39, '2', 'test4', '1445182155', 1, '2020-06-02 22:38:21', 25);

-- ----------------------------
-- Table structure for products
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double NULL DEFAULT NULL,
  `category` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pnum` int(0) NULL DEFAULT NULL,
  `imgurl` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('1', 'java web', 32, '计算机', 0, '/productImg/10/5/36ee63bc-c251-49ce-9b9a-b5e1e2e75ec0.jpg', '11111111111111111002313');
INSERT INTO `products` VALUES ('2', '时空穿行', 34, '科技', 99996, '/productImg/11/4/d79dc124-de69-4b77-847e-bc461bfdb857.jpg', '222222222222222222222222222222222222222222222222222222');
INSERT INTO `products` VALUES ('3', '大勇和小花的欧洲日记', 27.5, '少儿', 89993, '/productImg/12/1/986b5e98-ee73-4717-89fd-b6ac26a8dc2c.jpg', '大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记大勇和小花的欧洲日记');
INSERT INTO `products` VALUES ('4', 'Java基础入门', 38, '计算机', 0, '/productImg/12/14/a1ace169-b53a-41c6-bdea-000e5946b2a5.png', 'Java基础入门Java基础入门Java基础入门Java基础入门Java基础入门Java基础入门');
INSERT INTO `products` VALUES ('5', '别做正常的傻瓜', 19.5, '励志', 99987, '/productImg/14/1/792116e7-6d83-4be4-b3e5-4dd11b0b4565.jpg', '别做正常的傻瓜别做正常的傻瓜别做正常的傻瓜别做正常的傻瓜');
INSERT INTO `products` VALUES ('6', '中国国家地理', 23.8, '社科', 99986, '/productImg/2/0/2105fbe5-400f-4193-a7db-d7ebac389550.jpg', '中国国家地理中国国家地理中国国家地理中国国家地理中国国家地理');
INSERT INTO `products` VALUES ('7', '学会宽容', 28, '励志', 99992, '/productImg/6/5/a2da626c-c72d-4972-83de-cf48405c5563.jpg', '学会宽容学会宽容学会宽容');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PASSWORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `activeCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `state` int(0) NULL DEFAULT 0,
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '普通用户',
  `registTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (25, 'test', '123456', '男', '1445182155@qq.com', '1445182155', 'test', 'a4939c76-378b-4a86-abb7-10a4a721f548', 1, '超级管理员', '2020-06-06 10:48:56');
INSERT INTO `user` VALUES (27, 'wt', '123456', '女', '1627869181@qq.com', '123456', '123456', 'dd979a6a-3f78-415d-910c-322d80a57b28', 1, '普通用户', '2020-06-06 11:42:19');

SET FOREIGN_KEY_CHECKS = 1;
