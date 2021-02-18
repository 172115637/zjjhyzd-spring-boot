/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50732
 Source Host           : localhost:3306
 Source Schema         : rbac

 Target Server Type    : MySQL
 Target Server Version : 50732
 File Encoding         : 65001

 Date: 18/02/2021 15:11:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_configure
-- ----------------------------
DROP TABLE IF EXISTS `t_configure`;
CREATE TABLE `t_configure`  (
  `configure_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' /*主键*/',
  `keywords` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' /*配置key*/',
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' /*配置内容*/',
  `content_sub` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '/*分裂积分最大值*/',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' /*描述*/',
  `createAt` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' /*创建时间,YYYY-MM-DDHH:MM:SS*/',
  `createBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' /*创建人*/',
  `updateAt` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' /*最近修改时间,YYYY-MM-DDHH:MM:SS*/',
  `updateBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' /*修改人*/',
  `deleteAt` char(19) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' /*删除时间,YYYY-MM-DDHH:MM:SS*/',
  `deleteBy` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT ' /*删除人*/',
  `dr` smallint(2) NOT NULL DEFAULT 0 COMMENT ' /*逻辑删除标志0:未删除;1:已删除*/',
  `data_version` int(11) NOT NULL DEFAULT 1 COMMENT ' /*版本号,从0开始*/',
  PRIMARY KEY (`configure_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '《配置表》' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父权限',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限英文名称',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权路径',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `delete_at` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除者',
  `deleted` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('237793219987', '699831254004', '系统权限', 'System', NULL, NULL, '2021-01-18 13:21:30', 'anonymousUser', '2021-01-18 13:24:05', 'anonymousUser', NULL, NULL, 0, 2);
INSERT INTO `t_permission` VALUES ('699831254004', NULL, '用户权限', 'User', NULL, NULL, '2021-01-18 19:20:47', 'anonymousUser', '2021-01-18 19:20:47', 'anonymousUser', NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父角色',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `enname` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色英文名称',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `delete_at` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除者',
  `deleted` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('38770074618', '972326068834', '管理员组', 'ROLE_ADMIN', NULL, '2021-01-18 19:20:12', 'anonymousUser', '2021-01-18 19:20:12', 'anonymousUser', NULL, NULL, 0, 0);
INSERT INTO `t_role` VALUES ('972326068834', NULL, '用户组', 'ROLE_USER', NULL, '2021-01-18 19:20:24', 'anonymousUser', '2021-01-18 19:20:24', 'anonymousUser', NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色 ID',
  `permission_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限 ID',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `delete_at` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除者',
  `deleted` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------
INSERT INTO `t_role_permission` VALUES ('265129179694', '38770074618', '237793219987', '2021-01-18 19:23:19', 'anonymousUser', '2021-01-18 19:23:19', 'anonymousUser', NULL, NULL, 0, 0);
INSERT INTO `t_role_permission` VALUES ('549225762314', '972326068834', '699831254004', '2021-01-18 19:23:37', 'anonymousUser', '2021-01-18 19:23:37', 'anonymousUser', NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码，加密存储',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册邮箱',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `delete_at` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除者',
  `deleted` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE,
  UNIQUE INDEX `phone`(`phone`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('130250465504', 'admin', '$2a$10$z5FzYyihmCQCTK.oS9RXGOydcpm24tCQ1wx84rlEiADsQbqOrME3C', '123123', 'admin@zjjhyzd.com', '2021-01-18 19:18:52', 'anonymousUser', '2021-01-18 19:18:52', 'anonymousUser', NULL, NULL, 0, 0);
INSERT INTO `t_user` VALUES ('99891612037', '18574481219', '$2a$10$J3kOyBO4J1i3k8qMEQ2AMukci1Gqu5z2cjHEyyPFsf3p/NGDYFHqe', '18574481219', NULL, '2021-02-12 08:03:46', 'anonymousUser', '2021-02-12 08:03:46', 'anonymousUser', NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户 ID',
  `role_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色 ID',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `delete_at` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除者',
  `deleted` tinyint(2) NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('320553331705', '99891612037', '972326068834', '2021-02-12 08:03:48', 'anonymousUser', '2021-02-12 08:03:48', 'anonymousUser', NULL, NULL, 0, 0);
INSERT INTO `t_user_role` VALUES ('393384468349', '130250465504', '38770074618', '2021-01-18 19:24:19', 'anonymousUser', '2021-01-18 19:24:19', 'anonymousUser', NULL, NULL, 0, 0);

-- ----------------------------
-- Table structure for t_wechat_miniapp_account
-- ----------------------------
DROP TABLE IF EXISTS `t_wechat_miniapp_account`;
CREATE TABLE `t_wechat_miniapp_account`  (
  `id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `unionid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'unionid',
  `openid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信的openid',
  `session_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '微信的session_key',
  `user_id` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '绑定的小程序账号id',
  `create_at` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `update_at` datetime(0) NULL DEFAULT NULL COMMENT '最近修改时间S',
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `delete_at` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `delete_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '删除人',
  `deleted` smallint(2) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT 1 COMMENT '版本号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '微信小程序用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wechat_miniapp_account
-- ----------------------------
INSERT INTO `t_wechat_miniapp_account` VALUES ('448258322576', NULL, 'oMWV15xNnmWHdVkaPZ0XqKISig8w', '11K31WebV8KxnjYlDNhjrg==', '99891612037', '2021-02-12 08:03:48', 'anonymousUser', '2021-02-12 08:03:48', 'anonymousUser', NULL, NULL, 0, 3);

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `context` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime(0) NOT NULL,
  `log_modified` datetime(0) NOT NULL,
  `ext` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `ux_undo_log`(`xid`, `branch_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undo_log
-- ----------------------------
INSERT INTO `undo_log` VALUES (6, 2066780348, '192.168.31.127:8091:2066780347', 'serializer=jackson', 0x7B7D, 1, '2021-02-11 22:07:35', '2021-02-11 22:07:35', NULL);

SET FOREIGN_KEY_CHECKS = 1;
