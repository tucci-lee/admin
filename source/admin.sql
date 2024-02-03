/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : admin

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 03/02/2024 12:25:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for crontab
-- ----------------------------
DROP TABLE IF EXISTS `crontab`;
CREATE TABLE `crontab` (
  `id` bigint NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '任务名称',
  `class_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '类名',
  `cron` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'cron表达式',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '任务状态\n0-未启动\n1-启动',
  `remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  `update_time` bigint DEFAULT NULL COMMENT '修改时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_delete` (`is_deleted`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='定时任务';

-- ----------------------------
-- Records of crontab
-- ----------------------------
BEGIN;
INSERT INTO `crontab` VALUES (106763734457778177, '测试任务', 'cc.tucci.admin.app.crontab.job.TestJob', '*/5 * * * * ?', b'0', '测试使用', 1666420857861, 1706720361534, b'0');
COMMIT;

-- ----------------------------
-- Table structure for crontab_log
-- ----------------------------
DROP TABLE IF EXISTS `crontab_log`;
CREATE TABLE `crontab_log` (
  `id` bigint NOT NULL,
  `crontab_id` bigint NOT NULL COMMENT '定时任务id',
  `status` int NOT NULL DEFAULT '0' COMMENT '运行状态\n0-运行中\n1-成功\n2-失败',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '运行信息',
  `start_time` bigint NOT NULL COMMENT '开始时间',
  `run_time` bigint DEFAULT NULL COMMENT '运行时间',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_crontab` (`crontab_id`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of crontab_log
-- ----------------------------
BEGIN;
INSERT INTO `crontab_log` VALUES (1752738343303868418, 106763734457778177, 1, NULL, 1706720360001, 73, 1706720360002);
COMMIT;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部门名称',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '父级id，0是顶级部门',
  `seq` int NOT NULL DEFAULT '0' COMMENT '排序',
  `manager` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '部门管理人',
  `manager_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '管理人手机',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  `update_time` bigint DEFAULT NULL COMMENT '修改时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_delete` (`is_deleted`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门表，一个用户只能绑定一个部门';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
BEGIN;
INSERT INTO `sys_dept` VALUES (24169813928574976, '总部', 0, 0, 'Tucci', '13333333333', 1644560698135, NULL, b'0');
COMMIT;

-- ----------------------------
-- Table structure for sys_operate_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_operate_log`;
CREATE TABLE `sys_operate_log` (
  `id` bigint NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '操作人账号',
  `ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户ip',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '请求URL',
  `method` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '执行方法',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '参数',
  `result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '执行成功后的返回信息',
  `description` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '描述',
  `error_message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '执行失败后的异常信息',
  `status` bit(1) NOT NULL COMMENT '执行状态\n0-失败\n1-成功',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_ip` (`ip`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of sys_operate_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_res`;
CREATE TABLE `sys_res` (
  `id` bigint NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '资源名称',
  `type` int NOT NULL COMMENT '类型\n1-菜单\n2-权限',
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'url',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '父级id，0是顶级目录',
  `res_char` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '资源字符',
  `seq` int NOT NULL DEFAULT '0' COMMENT '排序',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图标',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  `update_time` bigint DEFAULT NULL COMMENT '修改时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_delete` (`is_deleted`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='资源';

-- ----------------------------
-- Records of sys_res
-- ----------------------------
BEGIN;
INSERT INTO `sys_res` VALUES (28174904473944064, '定时任务', 1, '/monitor/crontab', 113329647516647424, NULL, 0, NULL, 1645515586131, NULL, b'0');
INSERT INTO `sys_res` VALUES (28175098825408512, '定时任务查询', 2, NULL, 28174904473944064, 'crontab:list', 0, NULL, 1645515632468, NULL, b'0');
INSERT INTO `sys_res` VALUES (28516794918502401, '定时任务添加', 2, NULL, 28174904473944064, 'crontab:create', 0, NULL, 1645597099165, NULL, b'0');
INSERT INTO `sys_res` VALUES (28516877999276032, '定时任务修改', 2, NULL, 28174904473944064, 'crontab:update', 0, NULL, 1645597118973, NULL, b'0');
INSERT INTO `sys_res` VALUES (28516935251525632, '定时任务删除', 2, NULL, 28174904473944064, 'crontab:delete', 0, NULL, 1645597132623, NULL, b'0');
INSERT INTO `sys_res` VALUES (28517012728709120, '定时任务状态', 2, NULL, 28174904473944064, 'crontab:update:status', 0, NULL, 1645597151095, NULL, b'0');
INSERT INTO `sys_res` VALUES (28517103883517953, '定时任务执行', 2, NULL, 28174904473944064, 'crontab:start', 0, NULL, 1645597172828, NULL, b'0');
INSERT INTO `sys_res` VALUES (112063924660076544, '系统管理', 1, NULL, 0, NULL, 98, 'el-icon-setting', 1610292596000, NULL, b'0');
INSERT INTO `sys_res` VALUES (112064419969630208, '资源管理', 1, '/system/res', 112063924660076544, NULL, 2, NULL, 1610292714000, NULL, b'0');
INSERT INTO `sys_res` VALUES (112064548923506688, '角色管理', 1, '/system/role', 112063924660076544, NULL, 1, NULL, 1610292745000, NULL, b'0');
INSERT INTO `sys_res` VALUES (112065408583860224, '用户管理', 1, '/system/user', 112063924660076544, NULL, 0, NULL, 1610292949000, NULL, b'0');
INSERT INTO `sys_res` VALUES (112304735788204032, '资源查询', 2, NULL, 112064419969630208, 'sys:res:tree', 0, NULL, 1610350010000, NULL, b'0');
INSERT INTO `sys_res` VALUES (112304837034508288, '资源添加', 2, NULL, 112064419969630208, 'sys:res:create', 0, NULL, 1610350034000, NULL, b'0');
INSERT INTO `sys_res` VALUES (112304921109331968, '资源修改', 2, NULL, 112064419969630208, 'sys:res:update', 0, NULL, 1610350054000, NULL, b'0');
INSERT INTO `sys_res` VALUES (112305002013261824, '资源删除', 2, NULL, 112064419969630208, 'sys:res:delete', 0, NULL, 1610350073000, NULL, b'0');
INSERT INTO `sys_res` VALUES (112766904753455104, '部门管理', 1, '/system/dept', 112063924660076544, NULL, 3, NULL, 1610460199683, NULL, b'0');
INSERT INTO `sys_res` VALUES (113296483343663104, '用户查询', 2, NULL, 112065408583860224, 'sys:user:list', 0, NULL, 1610586461058, NULL, b'0');
INSERT INTO `sys_res` VALUES (113296609537687552, '用户添加', 2, NULL, 112065408583860224, 'sys:user:create', 0, NULL, 1610586491144, NULL, b'0');
INSERT INTO `sys_res` VALUES (113296742077693952, '用户修改', 2, NULL, 112065408583860224, 'sys:user:update', 0, NULL, 1610586522744, NULL, b'0');
INSERT INTO `sys_res` VALUES (113296815033417728, '用户删除', 2, NULL, 112065408583860224, 'sys:user:delete', 0, NULL, 1610586540138, NULL, b'0');
INSERT INTO `sys_res` VALUES (113297095770767360, '密码修改', 2, NULL, 112065408583860224, 'sys:user:update:password', 0, NULL, 1610586607071, NULL, b'0');
INSERT INTO `sys_res` VALUES (113297279363842048, '角色查询', 2, NULL, 112064548923506688, 'sys:role:list', 0, NULL, 1610586650843, NULL, b'0');
INSERT INTO `sys_res` VALUES (113297348314005504, '角色添加', 2, NULL, 112064548923506688, 'sys:role:create', 0, NULL, 1610586667282, NULL, b'0');
INSERT INTO `sys_res` VALUES (113297416005877760, '角色修改', 2, NULL, 112064548923506688, 'sys:role:update', 0, NULL, 1610586683421, NULL, b'0');
INSERT INTO `sys_res` VALUES (113297479004323840, '角色删除', 2, NULL, 112064548923506688, 'sys:role:delete', 0, NULL, 1610586698441, NULL, b'0');
INSERT INTO `sys_res` VALUES (113297703277953024, '部门查询', 2, NULL, 112766904753455104, 'sys:dept:tree', 0, NULL, 1610586751912, NULL, b'0');
INSERT INTO `sys_res` VALUES (113297768126087168, '部门添加', 2, NULL, 112766904753455104, 'sys:dept:create', 0, NULL, 1610586767373, NULL, b'0');
INSERT INTO `sys_res` VALUES (113297841698373632, '部门修改', 2, NULL, 112766904753455104, 'sys:dept:update', 0, NULL, 1610586784914, NULL, b'0');
INSERT INTO `sys_res` VALUES (113297895578402816, '部门删除', 2, NULL, 112766904753455104, 'sys:dept:delete', 0, NULL, 1610586797761, NULL, b'0');
INSERT INTO `sys_res` VALUES (113326873903104000, '日志管理', 1, NULL, 0, NULL, 99, 'el-icon-tickets', 1610593706732, NULL, b'0');
INSERT INTO `sys_res` VALUES (113327217995415552, '登陆日志', 1, '/system/log/signin', 113326873903104000, NULL, 0, NULL, 1610593788770, NULL, b'0');
INSERT INTO `sys_res` VALUES (113328033569439744, '操作日志', 1, '/system/log/operate', 113326873903104000, NULL, 0, NULL, 1610593983218, NULL, b'0');
INSERT INTO `sys_res` VALUES (113329345681948672, '登录日志查询', 2, NULL, 113327217995415552, 'log:signin:list', 0, NULL, 1610594296049, NULL, b'0');
INSERT INTO `sys_res` VALUES (113329497700302848, '操作日志查询', 2, NULL, 113328033569439744, 'log:operate:list', 0, NULL, 1610594332293, NULL, b'0');
INSERT INTO `sys_res` VALUES (113329647516647424, '监控管理', 1, NULL, 0, NULL, 97, 'el-icon-monitor', 1610594368012, NULL, b'0');
INSERT INTO `sys_res` VALUES (113329855000477696, 'Druid监控', 1, '/monitor/druid', 113329647516647424, NULL, 0, NULL, 1610594417480, NULL, b'0');
INSERT INTO `sys_res` VALUES (113329976727568384, 'Druid监控查询', 2, NULL, 113329855000477696, 'monitor:druid:view', 0, NULL, 1610594446502, NULL, b'0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL,
  `role_char` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色字符',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名',
  `remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  `update_time` bigint DEFAULT NULL COMMENT '修改时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_name` (`name`) USING BTREE,
  KEY `idx_delete` (`is_deleted`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1633062196463607809, NULL, '系统管理员', NULL, 1610432493240, NULL, b'0');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_res
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_res`;
CREATE TABLE `sys_role_res` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL COMMENT '角色id',
  `res_id` bigint NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_res` (`res_id`) USING BTREE,
  KEY `idx_role` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色与资源关联表';

-- ----------------------------
-- Records of sys_role_res
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_res` VALUES (1, 1633062196463607809, 1714670404726280194);
INSERT INTO `sys_role_res` VALUES (2, 1633062196463607809, 1714670875486572546);
INSERT INTO `sys_role_res` VALUES (3, 1633062196463607809, 1715027371646898178);
INSERT INTO `sys_role_res` VALUES (4, 1633062196463607809, 1715048143979380737);
INSERT INTO `sys_role_res` VALUES (5, 1633062196463607809, 1715059665405313025);
INSERT INTO `sys_role_res` VALUES (6, 1633062196463607809, 1715067222475710465);
INSERT INTO `sys_role_res` VALUES (7, 1633062196463607809, 1715078164324388865);
INSERT INTO `sys_role_res` VALUES (8, 1633062196463607809, 1715656649543602177);
INSERT INTO `sys_role_res` VALUES (9, 1633062196463607809, 1715667591752962049);
INSERT INTO `sys_role_res` VALUES (10, 1633062196463607809, 1715671293284843522);
INSERT INTO `sys_role_res` VALUES (11, 1633062196463607809, 1715797571124129794);
INSERT INTO `sys_role_res` VALUES (12, 1633062196463607809, 113329647516647424);
INSERT INTO `sys_role_res` VALUES (13, 1633062196463607809, 28174904473944064);
INSERT INTO `sys_role_res` VALUES (14, 1633062196463607809, 28175098825408512);
INSERT INTO `sys_role_res` VALUES (15, 1633062196463607809, 28516794918502401);
INSERT INTO `sys_role_res` VALUES (16, 1633062196463607809, 28516877999276032);
INSERT INTO `sys_role_res` VALUES (17, 1633062196463607809, 28516935251525632);
INSERT INTO `sys_role_res` VALUES (18, 1633062196463607809, 28517012728709120);
INSERT INTO `sys_role_res` VALUES (19, 1633062196463607809, 28517103883517953);
INSERT INTO `sys_role_res` VALUES (20, 1633062196463607809, 113329855000477696);
INSERT INTO `sys_role_res` VALUES (21, 1633062196463607809, 113329976727568384);
INSERT INTO `sys_role_res` VALUES (22, 1633062196463607809, 112063924660076544);
INSERT INTO `sys_role_res` VALUES (23, 1633062196463607809, 112065408583860224);
INSERT INTO `sys_role_res` VALUES (24, 1633062196463607809, 113296483343663104);
INSERT INTO `sys_role_res` VALUES (25, 1633062196463607809, 113296609537687552);
INSERT INTO `sys_role_res` VALUES (26, 1633062196463607809, 113296742077693952);
INSERT INTO `sys_role_res` VALUES (27, 1633062196463607809, 113296815033417728);
INSERT INTO `sys_role_res` VALUES (28, 1633062196463607809, 113297095770767360);
INSERT INTO `sys_role_res` VALUES (29, 1633062196463607809, 112064548923506688);
INSERT INTO `sys_role_res` VALUES (30, 1633062196463607809, 113297279363842048);
INSERT INTO `sys_role_res` VALUES (31, 1633062196463607809, 113297348314005504);
INSERT INTO `sys_role_res` VALUES (32, 1633062196463607809, 113297416005877760);
INSERT INTO `sys_role_res` VALUES (33, 1633062196463607809, 113297479004323840);
INSERT INTO `sys_role_res` VALUES (34, 1633062196463607809, 112064419969630208);
INSERT INTO `sys_role_res` VALUES (35, 1633062196463607809, 112304735788204032);
INSERT INTO `sys_role_res` VALUES (36, 1633062196463607809, 112304837034508288);
INSERT INTO `sys_role_res` VALUES (37, 1633062196463607809, 112304921109331968);
INSERT INTO `sys_role_res` VALUES (38, 1633062196463607809, 112305002013261824);
INSERT INTO `sys_role_res` VALUES (39, 1633062196463607809, 112766904753455104);
INSERT INTO `sys_role_res` VALUES (40, 1633062196463607809, 113297703277953024);
INSERT INTO `sys_role_res` VALUES (41, 1633062196463607809, 113297768126087168);
INSERT INTO `sys_role_res` VALUES (42, 1633062196463607809, 113297841698373632);
INSERT INTO `sys_role_res` VALUES (43, 1633062196463607809, 113297895578402816);
INSERT INTO `sys_role_res` VALUES (44, 1633062196463607809, 113326873903104000);
INSERT INTO `sys_role_res` VALUES (45, 1633062196463607809, 113327217995415552);
INSERT INTO `sys_role_res` VALUES (46, 1633062196463607809, 113329345681948672);
INSERT INTO `sys_role_res` VALUES (47, 1633062196463607809, 113328033569439744);
INSERT INTO `sys_role_res` VALUES (48, 1633062196463607809, 113329497700302848);
COMMIT;

-- ----------------------------
-- Table structure for sys_signin_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_signin_log`;
CREATE TABLE `sys_signin_log` (
  `id` bigint NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '登录账号',
  `os` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '操作系统',
  `browser` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '浏览器',
  `ip` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'ip地址',
  `status` bit(1) NOT NULL DEFAULT b'0' COMMENT '登录状态\n0-失败\n1-成功',
  `message` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin COMMENT '信息',
  `create_time` bigint NOT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_ip` (`ip`) USING BTREE,
  KEY `idx_status` (`status`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='登录日志';

-- ----------------------------
-- Records of sys_signin_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `uid` bigint NOT NULL,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `password` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `remarks` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `dept_id` bigint DEFAULT NULL COMMENT '所属部门id',
  `is_lock` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否锁定\n0-未锁定\n1-锁定',
  `create_time` bigint NOT NULL COMMENT '创建时间',
  `update_time` bigint DEFAULT NULL COMMENT '修改时间',
  `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除\n0-未删除\n1-删除',
  PRIMARY KEY (`uid`) USING BTREE,
  KEY `idx_dept` (`dept_id`) USING BTREE,
  KEY `idx_lock` (`is_lock`) USING BTREE,
  KEY `idx_delete` (`is_deleted`) USING BTREE,
  KEY `idx_username` (`username`) USING BTREE,
  KEY `idx_phone` (`phone`) USING BTREE,
  KEY `idx_create` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户信息';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', '$2a$10$EIhOLrg3DzWDc9LmjSccsu1pE4T3fEiZrZqZkHFWtwAThAEpUqLZW', '13333333333', NULL, '管理员', NULL, 24169813928574976, b'0', 1672502400000, NULL, b'0');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `uid` bigint NOT NULL COMMENT '用户id',
  `role_id` bigint NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_uid` (`uid`) USING BTREE,
  KEY `idx_role` (`role_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户关联的角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
