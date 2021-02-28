/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50710
 Source Host           : localhost:3306
 Source Schema         : student

 Target Server Type    : MySQL
 Target Server Version : 50710
 File Encoding         : 65001

 Date: 28/02/2021 16:42:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for college_info
-- ----------------------------
DROP TABLE IF EXISTS `college_info`;
CREATE TABLE `college_info`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学院名称',
  `year` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '入学年份',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人id',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `last_update_by` int(11) NULL DEFAULT NULL COMMENT '最后一次修改人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for major_info
-- ----------------------------
DROP TABLE IF EXISTS `major_info`;
CREATE TABLE `major_info`  (
  `id` int(11) NOT NULL,
  `college_id` int(11) NOT NULL COMMENT '学院id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '专业名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人id',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `last_update_by` int(11) NULL DEFAULT NULL COMMENT '最后一次修改人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for student_info
-- ----------------------------
DROP TABLE IF EXISTS `student_info`;
CREATE TABLE `student_info`  (
  `id` int(11) NOT NULL,
  `major_id` int(11) NOT NULL COMMENT '专业id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学生姓名',
  `sex` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '性别',
  `school_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '学号',
  `birthday` date NOT NULL COMMENT '出生日期',
  `identity` varchar(18) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '身份证号码',
  `place` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '籍贯',
  `nation` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '民族',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人id',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `last_update_by` int(11) NULL DEFAULT NULL COMMENT '最后一次修改人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '头像',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `enable_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '是否启用（Y:启用N:禁用）',
  `delete_flag` char(1) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '是否删除（Y:删除N:未删除）',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `create_by` int(11) NOT NULL COMMENT '创建人id',
  `last_update_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次修改时间',
  `last_update_by` int(11) NULL DEFAULT NULL COMMENT '最后一次修改人id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, NULL, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '1', '0', '2021-02-27 17:52:42', 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
