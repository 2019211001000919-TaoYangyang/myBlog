/*
 Navicat Premium Data Transfer

 Source Server         : tyy
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 28/03/2021 13:33:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blog
-- ----------------------------
DROP TABLE IF EXISTS `t_blog`;
CREATE TABLE `t_blog`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `appreciation` bit(1) NULL DEFAULT NULL,
  `commentabled` bit(1) NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `first_picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `published` bit(1) NULL DEFAULT NULL,
  `recommend` bit(1) NULL DEFAULT NULL,
  `share_statement` bit(1) NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `views` int(0) NULL DEFAULT NULL,
  `type_id` bigint(0) NULL DEFAULT NULL,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `tag_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK8ky5rrsxh01nkhctmo7d48p82`(`user_id`) USING BTREE,
  INDEX `FK292449gwg5yf7ocdlmswv9w4j`(`type_id`) USING BTREE,
  CONSTRAINT `FK292449gwg5yf7ocdlmswv9w4j` FOREIGN KEY (`type_id`) REFERENCES `t_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK8ky5rrsxh01nkhctmo7d48p82` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog
-- ----------------------------


-- ----------------------------
-- Table structure for t_blog_tags
-- ----------------------------
DROP TABLE IF EXISTS `t_blog_tags`;
CREATE TABLE `t_blog_tags`  (
  `blogs_id` bigint(0) NOT NULL,
  `tags_id` bigint(0) NOT NULL,
  INDEX `FK5feau0gb4lq47fdb03uboswm8`(`tags_id`) USING BTREE,
  INDEX `FKh4pacwjwofrugxa9hpwaxg6mr`(`blogs_id`) USING BTREE,
  CONSTRAINT `FK5feau0gb4lq47fdb03uboswm8` FOREIGN KEY (`tags_id`) REFERENCES `t_tag` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FKh4pacwjwofrugxa9hpwaxg6mr` FOREIGN KEY (`blogs_id`) REFERENCES `t_blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_blog_tags
-- ----------------------------
INSERT INTO `t_blog_tags` VALUES (18, 8);
INSERT INTO `t_blog_tags` VALUES (19, 8);
INSERT INTO `t_blog_tags` VALUES (19, 14);
INSERT INTO `t_blog_tags` VALUES (19, 26);

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blog_id` bigint(0) NULL DEFAULT NULL,
  `parent_comment_id` bigint(0) NULL DEFAULT NULL,
  `admin_comment` bit(1) NULL DEFAULT NULL,
  `parent_nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FKke3uogd04j4jx316m1p51e05u`(`blog_id`) USING BTREE,
  INDEX `FK4jj284r3pb7japogvo6h72q95`(`parent_comment_id`) USING BTREE,
  CONSTRAINT `FKke3uogd04j4jx316m1p51e05u` FOREIGN KEY (`blog_id`) REFERENCES `t_blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, '/images/wo.jpg', '测试', '2021-03-27 13:33:54.000000', '南渡', 19, -1, b'1', NULL, '1732190917@qq.com');
INSERT INTO `t_comment` VALUES (2, '/images/wo.jpg', '太强了', '2021-03-27 07:27:19.141000', '顾知遥', 19, -1, NULL, NULL, '1826466478@qq.com');
INSERT INTO `t_comment` VALUES (3, '/images/wo.jpg', '嘿黑', '2021-03-27 08:28:36.072000', '南渡', 19, 2, b'1', '顾知遥', '1732190917@qq.com');
INSERT INTO `t_comment` VALUES (4, '/images/wo.jpg', '测你个鬼', '2021-03-27 10:05:59.756000', '甜皮', 19, 1, NULL, '南渡', '2673882615@qq.com');
INSERT INTO `t_comment` VALUES (13, '/images/wo.jpg', '你也可以', '2021-03-28 01:58:45.195000', '南渡', 19, 2, b'1', '顾知遥', '1732190917@qq.com');
INSERT INTO `t_comment` VALUES (14, '/images/wo.jpg', '咋了', '2021-03-28 02:13:53.025000', '南渡', 19, 4, b'1', '甜皮', '1732190917@qq.com');
INSERT INTO `t_comment` VALUES (16, '/images/wo.jpg', '说你好帅', '2021-03-28 02:35:05.775000', '甜皮', 19, 14, NULL, '南渡', '1826466478@qq.com');
INSERT INTO `t_comment` VALUES (17, '/images/moren.jpg', '测试异步任务', '2021-03-28 03:26:07.229000', '南渡', 18, -1, b'1', NULL, '1732190917@qq.com');
INSERT INTO `t_comment` VALUES (18, '/images/moren.jpg', '测试', '2021-03-28 03:26:13.079000', '南渡', 18, -1, b'1', NULL, '1732190917@qq.com');
INSERT INTO `t_comment` VALUES (19, '/images/moren.jpg', '测试邮件任务', '2021-03-28 03:28:55.985000', '南渡', 19, -1, b'1', NULL, '1732190917@qq.com');
INSERT INTO `t_comment` VALUES (20, '/images/moren.jpg', '去去去', '2021-03-28 03:32:06.792000', '南渡', 19, 16, b'1', '甜皮', '1732190917@qq.com');
INSERT INTO `t_comment` VALUES (21, '/images/wo.jpg', '测尼玛呢', '2021-03-28 04:40:16.025000', '南渡', 19, 19, b'1', '南渡', '1732190917@qq.com');

-- ----------------------------
-- Table structure for t_tag
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES (8, 'java');
INSERT INTO `t_tag` VALUES (9, 'mysql');
INSERT INTO `t_tag` VALUES (10, '中间件');
INSERT INTO `t_tag` VALUES (11, 'Linux');
INSERT INTO `t_tag` VALUES (12, 'spring');
INSERT INTO `t_tag` VALUES (13, 'springboot');
INSERT INTO `t_tag` VALUES (14, 'ssm');
INSERT INTO `t_tag` VALUES (15, 'mybatis');
INSERT INTO `t_tag` VALUES (16, 'js');
INSERT INTO `t_tag` VALUES (17, 'jQuery');
INSERT INTO `t_tag` VALUES (18, 'vue');
INSERT INTO `t_tag` VALUES (19, '数据结构');
INSERT INTO `t_tag` VALUES (20, '微服务');
INSERT INTO `t_tag` VALUES (21, '多线程');
INSERT INTO `t_tag` VALUES (22, '注解');
INSERT INTO `t_tag` VALUES (23, '反射');
INSERT INTO `t_tag` VALUES (24, '网络编程');
INSERT INTO `t_tag` VALUES (25, '组件');
INSERT INTO `t_tag` VALUES (26, '架构');

-- ----------------------------
-- Table structure for t_type
-- ----------------------------
DROP TABLE IF EXISTS `t_type`;
CREATE TABLE `t_type`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_type
-- ----------------------------
INSERT INTO `t_type` VALUES (3, '数据库');
INSERT INTO `t_type` VALUES (8, '算法');
INSERT INTO `t_type` VALUES (11, '后端');
INSERT INTO `t_type` VALUES (12, '前端');
INSERT INTO `t_type` VALUES (13, '网络安全');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int(0) NULL DEFAULT NULL,
  `update_time` datetime(6) NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '/images/wo.jpg', '2021-03-01 16:11:18.000000', '1732190917@qq.com', '南渡', 'c852890b852d3412fa117fa979971469', 1, '2021-03-01 16:11:38.000000', 'nandu');

SET FOREIGN_KEY_CHECKS = 1;
