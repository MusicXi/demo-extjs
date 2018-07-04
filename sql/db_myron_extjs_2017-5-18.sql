/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50627
Source Host           : 127.0.0.1:3306
Source Database       : db_myron_extjs

Target Server Type    : MYSQL
Target Server Version : 50627
File Encoding         : 65001

Date: 2017-05-18 11:14:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cms_article`
-- ----------------------------
DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article` (
  `article_id` varchar(32) NOT NULL COMMENT '编号',
  `category_id` varchar(32) NOT NULL COMMENT '栏目编号',
  `content` varchar(2000) DEFAULT NULL COMMENT '文章内容',
  `link` varchar(255) DEFAULT NULL COMMENT '文章链接',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `color` varchar(50) DEFAULT NULL COMMENT '标题颜色',
  `image` varchar(255) DEFAULT NULL COMMENT '文章图片',
  `keywords` varchar(255) DEFAULT NULL COMMENT '关键字',
  `description` varchar(255) DEFAULT NULL COMMENT '摘要',
  `weight` int(11) DEFAULT '0' COMMENT '权重',
  `weight_date` datetime DEFAULT NULL COMMENT '权重期限',
  `hits` int(11) DEFAULT '0' COMMENT '点击数',
  `posid` varchar(10) DEFAULT NULL COMMENT '推荐位',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`article_id`),
  KEY `cms_article_create_by` (`create_by`),
  KEY `cms_article_title` (`title`),
  KEY `cms_article_keywords` (`keywords`),
  KEY `cms_article_del_flag` (`del_flag`),
  KEY `cms_article_weight` (`weight`),
  KEY `cms_article_update_date` (`update_date`),
  KEY `cms_article_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章表';

-- ----------------------------
-- Records of cms_article
-- ----------------------------
INSERT INTO `cms_article` VALUES ('11', '', '地发呆发呆', '/news/11.html', '呜呜呜', '', '', '', '', '0', '1970-01-01 08:00:00', '0', '', '', '1970-01-01 08:00:00', '', '1970-01-01 08:00:00', '', '');
INSERT INTO `cms_article` VALUES ('123', '', '地方大幅度', '/news/123.html', '123', '', '', '', '', '0', '1970-01-01 08:00:00', '0', '', '', '1970-01-01 08:00:00', '', '1970-01-01 08:00:00', '', '');
INSERT INTO `cms_article` VALUES ('1d0bd0df1d9f4330aedb60a30cefa51f', '', '而据美国战略司令部证实，其实这是中国长征七号火箭重新进入大气层，刚好经过加利福利亚附近。', '/news/1d0bd0df1d9f4330aedb60a30cefa51f.html', '法国巴黎圣母院为教堂恐袭案遇害神父举行大弥撒', '', '', '', '', '0', '2016-12-29 00:00:00', '0', '', '', '2016-12-29 00:00:00', '', '2016-12-29 00:00:00', '', '');
INSERT INTO `cms_article` VALUES ('2222', '', '据美国《洛杉矶时报》报道，当地时间周三晚(北京时间周四)，在美国中西部的犹他州、内华达州、加利福利亚州，数千人被划过夜空的神秘火球吓到', '/news/2222.html', '不明真相的美国人被UFO惊呆了 其实是长征7号', '', '', '', '', '0', '1970-01-01 08:00:00', '0', '', '', '1970-01-01 08:00:00', '', '1970-01-01 08:00:00', '', '');
INSERT INTO `cms_article` VALUES ('5a905039dfbf4331ad5106b57a5840d3', '', '中国长征七号火箭6月25日在海南文昌航天发射中心首次发射，并成功升空进入轨道。有学者指出长征七号第二级火箭一直在地球低轨运行，一个月后重新进入大气层', '/news/5a905039dfbf4331ad5106b57a5840d3.html', '中日关系正处十字路口日应寻求减少与华冲突', '', '', '', '', '0', '2016-12-29 00:00:00', '0', '', '', '2016-12-29 00:00:00', '', '2016-12-29 00:00:00', '', '');
INSERT INTO `cms_article` VALUES ('addfdfd', '', '方法反反复复', '/news/addfdfd.html', '顶顶顶顶顶', '', '', '', '', '0', '1970-01-01 08:00:00', '0', '', '', '1970-01-01 08:00:00', '', '1970-01-01 08:00:00', '', '');

-- ----------------------------
-- Table structure for `c_goods`
-- ----------------------------
DROP TABLE IF EXISTS `c_goods`;
CREATE TABLE `c_goods` (
  `goods_id` varchar(32) NOT NULL COMMENT '商品ID',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `sale_start_date` datetime NOT NULL COMMENT '销售开始时间',
  `sale_end_date` datetime NOT NULL COMMENT '销售截止时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `status` varchar(3) DEFAULT '' COMMENT '状态',
  `comment` text COMMENT '商品说明',
  `data` int(12) DEFAULT NULL COMMENT '流量',
  `flow_type` varchar(3) DEFAULT NULL COMMENT '流量类型',
  `number` int(9) DEFAULT NULL COMMENT '商品数量',
  `effective_month` int(3) DEFAULT NULL COMMENT '有效月',
  `price` decimal(9,2) DEFAULT NULL COMMENT '单价',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售模块商品表';

-- ----------------------------
-- Records of c_goods
-- ----------------------------
INSERT INTO `c_goods` VALUES ('0dac88a23c894fc2b6f60ce41e3e5090', '商品2', '2017-04-07 09:36:52', '2017-04-07 09:36:52', '2017-04-07 09:36:52', null, 'd', null, null, null, null, null, null, null, null);
INSERT INTO `c_goods` VALUES ('201702201640194004404', '2017-0303-1423', '2017-02-01 16:39:51', '2017-05-20 16:39:51', '2017-02-20 16:40:19', 'cly123', '00A', 'dddfdfd', '222', '20', '44324', '44', '99.00', '2017-03-24 14:15:55', 'admin');
INSERT INTO `c_goods` VALUES ('201702201640429143167', '2017-0306-1116', '2017-02-09 16:40:21', '2017-05-31 16:40:22', '2017-02-20 16:40:43', 'cly123', '00B', '商品说明', '1', '20', '1', '1', '1.00', '2017-03-24 18:28:54', 'admin');
INSERT INTO `c_goods` VALUES ('201702221422497740455', '测试终端001', '2017-02-10 14:21:10', '2017-04-21 14:21:10', '2017-02-22 14:22:50', 'cly123', '00B', '落霞与孤鹜齐飞秋水共长天一色1', '42', '10', '666', '599', '2.00', '2017-03-24 18:30:32', 'admin');
INSERT INTO `c_goods` VALUES ('201702231923114646882', '商品20170223', '2017-02-02 19:20:56', '2017-07-28 19:20:56', '2017-02-23 19:23:11', 'cly123', '00D', '勿以善小而不为,勿以恶小而为之。', '999999697', '20', '5', '5', '16.00', '1970-01-01 08:00:00', '');
INSERT INTO `c_goods` VALUES ('201703031530139539874', 'goods_2017-0303-1525', '2017-03-03 15:24:54', '2017-03-31 15:24:54', '2017-03-03 15:30:14', 'cly123', '00D', '十年生死两茫茫。不思量，自难忘。千里孤坟、无处话凄凉。纵使相逢应不识、尘满面，鬓如霜。 夜来幽梦忽还乡。小轩窗，正梳妆。相顾无言，惟有泪千行，料得年年断肠处，明月夜，短松冈。', '38', '10', '9', '13', '7.00', '2017-03-25 17:03:58', 'admin');
INSERT INTO `c_goods` VALUES ('201703221643460347462', '999999999999999999999999999999999999999999999999999999', '2017-03-01 16:40:22', '2017-03-02 16:40:22', '2017-03-22 16:43:46', 'admin', '00A', 'ddddd', '999999999', '20', '7777777', '999', '9999999.00', '2017-04-11 00:00:00', 'ddddd');
INSERT INTO `c_goods` VALUES ('201703231431544377017', '秦时明月流量', '2017-03-23 14:31:15', '2019-01-01 14:30:21', '2017-03-23 14:31:54', 'admin', '00D', '秦时明月流量的说明', '8', '10', '1000000', '10', '8.00', '1970-01-01 08:00:00', 'dd');
INSERT INTO `c_goods` VALUES ('201703231604551351238', '123', '2017-03-23 16:02:39', '2017-03-31 16:02:39', '2017-03-23 16:04:55', 'hb1', '00D', '3333', '3', '20', '123', '3', '3.00', null, null);
INSERT INTO `c_goods` VALUES ('fc29d9a15a504222bf3e2a35ecc7da19', '商品3', '2017-04-07 09:36:52', '2017-04-07 09:36:52', '2017-04-07 09:36:52', null, 'd', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `m_manager`
-- ----------------------------
DROP TABLE IF EXISTS `m_manager`;
CREATE TABLE `m_manager` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `salary` float DEFAULT NULL COMMENT '工资',
  `in_duty_date` datetime DEFAULT NULL COMMENT '入职日期',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_by` varchar(32) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_manager
-- ----------------------------
INSERT INTO `m_manager` VALUES ('01892fcfb06a49589e2619b22ad9fb6a', 'W@qq.com', '张学友xxx', '222', '800', '2015-09-13 00:00:00', '1970-01-01 08:00:00', '1970-01-01 08:00:00', '', '');
INSERT INTO `m_manager` VALUES ('044e00eb0e5f4d2db02ba4ff8096c3dd', '4466664@qq.com', '33333', 'dddd666', '44450000', '2017-03-10 00:00:00', '2017-03-15 00:00:00', '2017-03-22 00:00:00', '33333', '3333');
INSERT INTO `m_manager` VALUES ('073c1191c75d43b4a4d1354c43ec5158', 'linrx@139.com', 'linrx', '123', '1', '2015-09-03 08:00:00', '1970-01-01 08:00:00', '1970-01-01 08:00:00', '', '');
INSERT INTO `m_manager` VALUES ('189b0fe7081f4eafab5e88f83bb935be', '4466664@qq.com', 'ddddd', 'dddd666', '44450000', '2017-03-10 00:00:00', '2017-03-15 00:00:00', '2017-03-22 00:00:00', '5555', '55555');
INSERT INTO `m_manager` VALUES ('18ceba9507a64144a1fbcfde5b7b315d', 'zhagns@dd.com', '张三', '2', '4444', '2015-09-03 00:00:00', '1970-01-01 08:00:00', '1970-01-01 08:00:00', '', '');
INSERT INTO `m_manager` VALUES ('2417a8d7-0915-4c82-af8f-608067c2fa05', 'linrx@qq.com', 'heihei444', '123', '1', '2015-11-20 08:00:00', '2017-03-17 00:00:00', '2017-03-23 00:00:00', '', '');
INSERT INTO `m_manager` VALUES ('2f6512044bed4214a5e8f79ee29c9606', '4466664@qq.com', 'ddddd', 'dddd666', '44450000', '2017-03-10 00:00:00', '2017-03-15 00:00:00', '2017-03-22 00:00:00', '5555', '55555');
INSERT INTO `m_manager` VALUES ('2f7e16bc901744e196396ce6d7ad0f78', '66@qq.com', '哈哈哈,', '999', '66666', '2015-09-09 08:00:00', '1970-01-01 08:00:00', '1970-01-01 08:00:00', '', '');
INSERT INTO `m_manager` VALUES ('315b8e0d3db14fe38b5bf6705a6f1970', '66@qq.com', '成功啦', '999', '66666', '2016-12-22 00:00:00', null, null, null, null);
INSERT INTO `m_manager` VALUES ('326e853ad4564070b339a938b1ce1706', '4466664@qq.com', 'ddddd', 'dddd666', '44450000', '2017-03-10 00:00:00', '1970-01-01 08:00:00', '1970-01-01 08:00:00', '', '');
INSERT INTO `m_manager` VALUES ('34afb0f2a54f4fd5afc56b1459a8697b', 'lala@qq.com', 'lalall', '111', '345', '2015-11-01 08:00:00', '1970-01-01 08:00:00', '1970-01-01 08:00:00', '', '');
INSERT INTO `m_manager` VALUES ('402881f050a43d8e0150a44080f20001', 'linrx@139.com', 'linrx', '123', '12345', '2016-03-15 00:00:00', '2017-03-17 00:00:00', '2017-03-01 00:00:00', '', '');
INSERT INTO `m_manager` VALUES ('402881f050a43d8e0150a44081370002', '66@qq.com', '功啦2222', '999', '66666', '2015-09-14 08:00:00', null, null, null, null);
INSERT INTO `m_manager` VALUES ('402881f050a43d8e0150a440821f0004', 'test@qq.com', 'test12', '122.0', '10001', '2016-09-30 08:00:00', null, null, null, null);
INSERT INTO `m_manager` VALUES ('402881f050a43d8e0150a440824b0005', 'test@qq.com', 'test11', '122.0', '9993', '2014-12-11 08:00:00', null, null, null, null);
INSERT INTO `m_manager` VALUES ('402881f050a43d8e0150a440827c0006', 'lis@ee.com', '李四2', '12', '2.55556e15', '2015-10-13 00:00:00', '2017-03-16 00:00:00', '2017-03-23 00:00:00', '', '');
INSERT INTO `m_manager` VALUES ('a2d93e65d996407ba6aab3ae9483cfab', 'lis@ee.com', '李四2', '12', '2.55556e15', '2015-10-13 00:00:00', '2017-03-16 00:00:00', '2017-03-23 00:00:00', '', '');
INSERT INTO `m_manager` VALUES ('a6ad494a9dcb462da2b6c737cbea5a42', 'lis@ee.com', '李四2', '12', '2.55556e15', '2015-10-13 00:00:00', '2017-03-16 00:00:00', '2017-03-23 00:00:00', '', '');

-- ----------------------------
-- Table structure for `m_menu`
-- ----------------------------
DROP TABLE IF EXISTS `m_menu`;
CREATE TABLE `m_menu` (
  `id` int(11) NOT NULL,
  `app` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_menu
-- ----------------------------
INSERT INTO `m_menu` VALUES ('1', null, null, '系统管理', '0', 'hello.jsp');
INSERT INTO `m_menu` VALUES ('2', null, null, '用户管理', '1', 'product/system/userManager/index.jsp');
INSERT INTO `m_menu` VALUES ('3', null, null, '资源管理', '1', 'product/system/resourceManager/index.jsp');
INSERT INTO `m_menu` VALUES ('4', null, null, '3', '2', 'product/manager/index.jsp');
INSERT INTO `m_menu` VALUES ('5', null, null, '报表管理', '0', null);
INSERT INTO `m_menu` VALUES ('6', null, null, 'ExtJS功能测试', '0', null);
INSERT INTO `m_menu` VALUES ('13', null, null, 'jQuery测试', '0', 'jsp/home/system.jsp');
INSERT INTO `m_menu` VALUES ('14', null, null, '测试_增删改查', '0', 'product/manager/index.jsp');
INSERT INTO `m_menu` VALUES ('15', null, null, '机构管理', '1', null);
INSERT INTO `m_menu` VALUES ('16', null, null, '字典管理', '1', null);
INSERT INTO `m_menu` VALUES ('17', null, null, '角色管理', '1', null);
INSERT INTO `m_menu` VALUES ('18', null, null, '日志管理', '1', 'product/system/logManager/index.jsp');

-- ----------------------------
-- Table structure for `m_user`
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_user
-- ----------------------------
INSERT INTO `m_user` VALUES ('402881e550d64f880150d650d10e0000', 'tom', '123');
INSERT INTO `m_user` VALUES ('402881e5510e969c01510ea5749f0000', 'mary', '111');

-- ----------------------------
-- Table structure for `sys_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary` (
  `dictionary_id` varchar(50) NOT NULL COMMENT '�ֵ�����',
  `dictionary_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`dictionary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='�ֵ�� ';

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------
INSERT INTO `sys_dictionary` VALUES ('color', '颜色');
INSERT INTO `sys_dictionary` VALUES ('organization', '机构');
INSERT INTO `sys_dictionary` VALUES ('公司', '机构');
INSERT INTO `sys_dictionary` VALUES ('部门', '机构');

-- ----------------------------
-- Table structure for `sys_dictionary_item`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary_item`;
CREATE TABLE `sys_dictionary_item` (
  `dictionary_item_id` varchar(32) NOT NULL COMMENT '字典项ID',
  `dictionary_id` varchar(50) DEFAULT NULL COMMENT '字典类型ID',
  `value` varchar(100) DEFAULT NULL COMMENT '字典值',
  `text` varchar(100) DEFAULT NULL COMMENT '显示名称',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父级ID',
  `grade` int(10) DEFAULT NULL COMMENT '层级',
  `sort` int(10) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`dictionary_item_id`),
  KEY `FK_Relationship_5` (`dictionary_id`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`dictionary_id`) REFERENCES `sys_dictionary` (`dictionary_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dictionary_item
-- ----------------------------
INSERT INTO `sys_dictionary_item` VALUES ('001', 'color', 'yellow', '黄色', null, '0', null);
INSERT INTO `sys_dictionary_item` VALUES ('0012', 'color', 'yellow', '黄色ddd', '', '0', null);
INSERT INTO `sys_dictionary_item` VALUES ('002', 'color', 'red', '红色', '', '33', null);
INSERT INTO `sys_dictionary_item` VALUES ('1123', 'color', 'green', '蓝色', '', null, null);
INSERT INTO `sys_dictionary_item` VALUES ('26433827f1074cf4a6efa604ef85961b', 'organization', '120000tj', '天津分公司', '411b079046894f6786e7839721ee68b9', '1', null);
INSERT INTO `sys_dictionary_item` VALUES ('33ac8a93b1ad41c6abba93284073c59f', 'organization', 'sale', '销售部2', '26433827f1074cf4a6efa604ef85961b', '2', null);
INSERT INTO `sys_dictionary_item` VALUES ('411b079046894f6786e7839721ee68b9', 'organization', '01', '中兴软创', '#', '0', null);
INSERT INTO `sys_dictionary_item` VALUES ('9ef0183787f146cdaeb84cb0a02e4db3', 'organization', 'caiwubu', '财务部', 'a86604e0f89b4d83bd084349eae51967', '2', '1');
INSERT INTO `sys_dictionary_item` VALUES ('a0957981dd9048268df184f8d6376d1f', 'organization', '110000bj', '北京分公司', '411b079046894f6786e7839721ee68b9', '1', null);
INSERT INTO `sys_dictionary_item` VALUES ('a86604e0f89b4d83bd084349eae51967', 'organization', '350000fj', '福建分公司', '411b079046894f6786e7839721ee68b9', '1', null);
INSERT INTO `sys_dictionary_item` VALUES ('ae9e6ece4df94644b2ca0a43e0ac2b5d', 'organization', '110000bj01', '信通部', 'a0957981dd9048268df184f8d6376d1f', '2', null);
INSERT INTO `sys_dictionary_item` VALUES ('c0d872125de64801915cd83b3271ecc2', 'organization', '110000bj02', '业务部', 'a0957981dd9048268df184f8d6376d1f', '2', '2');
INSERT INTO `sys_dictionary_item` VALUES ('f4acee7106594226af14d0f11588e3dc', 'color', 'blue', '蓝色', '', '1', '1');

-- ----------------------------
-- Table structure for `sys_log`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `log_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '日志主键',
  `type` varchar(20) DEFAULT NULL COMMENT '日志类型',
  `title` varchar(100) DEFAULT NULL COMMENT '日志标题',
  `remote_addr` varchar(20) DEFAULT NULL COMMENT '请求地址',
  `request_uri` varchar(300) DEFAULT NULL COMMENT 'URI',
  `method` varchar(300) DEFAULT NULL COMMENT '请求方式',
  `params` varchar(300) DEFAULT NULL COMMENT '提交参数',
  `exception` varchar(500) DEFAULT NULL COMMENT '异常',
  `operate_date` datetime DEFAULT NULL COMMENT '操作时间',
  `timeout` varchar(10) DEFAULT NULL COMMENT '请求超时',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('03f4e3d1d7d840edb858f8f17b53f974', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-spring4/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=j14r', null, '2017-02-09 14:07:26', '0:0:0.328', '1001');
INSERT INTO `sys_log` VALUES ('0647c4c1b8fc441da4489ff4166e383a', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-12 17:03:54', '0:0:0.41', '1001');
INSERT INTO `sys_log` VALUES ('0806abc9fe90411cbebf4a3ef94dc886', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478537815699&page=1&start=0&limit=15', null, '2016-11-08 00:56:56', '0:0:0.19', '1001');
INSERT INTO `sys_log` VALUES ('08255cd2269f4c009cd4fedf714c907d', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-spring4/product/system/userManager/update.do', 'POST', '_dc=1486622923620', null, '2017-02-09 14:48:44', '0:0:0.32', '1001');
INSERT INTO `sys_log` VALUES ('0946721b5a7440c480cdfe0bb2bc5619', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-01-17 15:18:35', '0:0:1.611', '10e29164211446f0864a2f904ce70d32');
INSERT INTO `sys_log` VALUES ('099ae9226e0442aaae1202a2ff6e9ca2', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-spring4/logout.do', 'GET', '', null, '2017-02-09 11:05:15', '0:0:0.31', '1001');
INSERT INTO `sys_log` VALUES ('0c49b06c1dbe48e99d4b6272cbe8fc0f', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478537821930&filter=ae9e6ece4df94644b2ca0a43e0ac2b5d&page=1&start=0&limit=15', null, '2016-11-08 00:57:02', '0:0:0.15', '1001');
INSERT INTO `sys_log` VALUES ('0dd92cb9fb7b4ac687ee7a4749ea7117', 'error', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', 'java.lang.IllegalStateException: org.apache.shiro.session.UnknownSessionException: There is no session with id [be0da9b3-4ef9-484f-a8a5-63f407f2e41e]', '2017-01-10 19:56:07', '0:0:5.679', '1001');
INSERT INTO `sys_log` VALUES ('0ed26f5462ed4007abe8f99241f6db29', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-02-13 16:09:13', '0:0:0.12', '1001');
INSERT INTO `sys_log` VALUES ('1804588162304f398cf772bb05ee903e', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=r8m1', null, '2017-03-29 16:39:46', '0:0:0.47', '1001');
INSERT INTO `sys_log` VALUES ('208e4b70679d4068a380eab0e0eb115a', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1481859611569', null, '2016-12-16 11:40:12', '0:0:0.118', '1001');
INSERT INTO `sys_log` VALUES ('2177108e067d4537be9a47ab7c842aec', 'info', '登入系统', '10.45.45.113', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-17 10:10:13', '0:0:0.266', '1001');
INSERT INTO `sys_log` VALUES ('225f033f4976415190b0d25dfd9f6add', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-16 11:24:51', '0:0:2.994', '1001');
INSERT INTO `sys_log` VALUES ('22d9498a5926487a8d1a2dc1b204494d', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=ckrh', null, '2017-02-13 15:02:36', '0:0:0.108', '1001');
INSERT INTO `sys_log` VALUES ('2e753065b91a46b5ac88ae5921db6ed6', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-12 10:03:25', '0:0:2.736', '1001');
INSERT INTO `sys_log` VALUES ('2f7a2bf133534b23bc4e1d36ed20c7b7', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=769a', null, '2017-02-13 14:13:36', '0:0:0.127', '1001');
INSERT INTO `sys_log` VALUES ('30f16722f7d74dbe8bdea59e2e463997', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1484642565852', null, '2017-01-17 16:42:46', '0:0:0.6', '1001');
INSERT INTO `sys_log` VALUES ('30f421152ae84bb498726b330215dea9', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1482408551300', null, '2016-12-22 20:09:11', '0:0:0.23', '1001');
INSERT INTO `sys_log` VALUES ('3289c22ef7864c789f80d0d359fd0c38', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-02-09 15:45:12', '0:0:0.146', '1001');
INSERT INTO `sys_log` VALUES ('34dbf4c5e8a84c14b442ffd5d7cc8003', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1482408948540', null, '2016-12-22 20:15:49', '0:0:0.4', '1001');
INSERT INTO `sys_log` VALUES ('35128d97c0d14bc7aab8a2caa82d4e22', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=wq0d', null, '2017-03-27 10:24:17', '0:0:0.500', '1001');
INSERT INTO `sys_log` VALUES ('37ab765cb0a54b478902bd36354ba254', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-17 15:10:42', '0:0:7.521', '1001');
INSERT INTO `sys_log` VALUES ('39f7e499816b4aefbb353cc34e2bd166', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=09w0', null, '2017-04-07 09:31:21', '0:0:0.158', '1001');
INSERT INTO `sys_log` VALUES ('3a9cc49943cf43bfa0b092e6009b709b', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478537839810&filter={\"name\":\"你好\",\"username\":\"\",\"email\":\"\",\"phone\":\"\",\"organizationId\":\"\"}&page=1&start=0&limit=15', null, '2016-11-08 00:57:20', '0:0:0.14', '1001');
INSERT INTO `sys_log` VALUES ('3ae3c34313d54de783c092447e2da780', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-16 11:34:03', '0:0:2.57', '1001');
INSERT INTO `sys_log` VALUES ('400934d3363e4fed8180b29e2f158102', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-10 19:54:03', '0:0:6.115', '1001');
INSERT INTO `sys_log` VALUES ('40c73a50a17144d1adc24525cf2f70ad', 'error', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', 'java.lang.IllegalStateException: org.apache.shiro.session.UnknownSessionException: There is no session with id [21b94281-0052-4594-b9f6-599a0949e217]', '2017-01-10 20:04:54', '0:0:38.741', '1001');
INSERT INTO `sys_log` VALUES ('40fd7e7f4b904cc187d6d8497a33c904', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-02-08 16:32:51', '0:0:0.23', '1001');
INSERT INTO `sys_log` VALUES ('42525ca546254178a416cd2af06900ee', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-02-08 16:37:35', '0:0:0.125', '1001');
INSERT INTO `sys_log` VALUES ('46c05a07565249b99902292b4ea24d4d', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1486714119227', null, '2017-02-10 16:08:39', '0:0:0.13', '1001');
INSERT INTO `sys_log` VALUES ('49b6ca2bdbd84defb7bf542e3ed535a3', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-12 10:05:25', '0:0:0.5', '1001');
INSERT INTO `sys_log` VALUES ('4b78b26a4d2841fe958d58a72db55a8d', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=etlk', null, '2017-03-03 16:27:49', '0:0:1.783', '1001');
INSERT INTO `sys_log` VALUES ('4bbffa9c6d6d4eba844220cf88508b62', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=2xox', null, '2017-03-29 13:53:40', '0:0:0.46', '1001');
INSERT INTO `sys_log` VALUES ('50e2585778d64544b4c1fac1ba8d6ccb', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-16 11:12:03', '0:0:5.844', '1001');
INSERT INTO `sys_log` VALUES ('55ed7244289c4bb08b22dd22c9d9650b', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1491813818140', null, '2017-04-10 16:43:38', '0:0:0.182', '1001');
INSERT INTO `sys_log` VALUES ('57d191063c164c46ac0d4e519fb3f32d', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-02-08 16:37:45', '0:0:0.73', '1001');
INSERT INTO `sys_log` VALUES ('59407d31e0ab4d20b29f271e3b08dae3', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-10 20:43:40', '0:0:0.13', '1001');
INSERT INTO `sys_log` VALUES ('5c8db52fd2c84f72bb23c6f8446af830', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1484053109735', null, '2017-01-10 20:58:30', '0:0:3.17', '1001');
INSERT INTO `sys_log` VALUES ('5daf9f8bba594a489f3e55ab9ffd04d3', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=houzi&password=&rememberMe=false&verifycode=tjzx', null, '2017-02-13 16:06:47', '0:0:0.24', '10e29164211446f0864a2f904ce70d32');
INSERT INTO `sys_log` VALUES ('6128161ddf034764a96722b2c3e8bf6a', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1482408918448', null, '2016-12-22 20:15:18', '0:0:0.7', '1001');
INSERT INTO `sys_log` VALUES ('61fd3993ac5a4a1689167718eaf4bf66', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=yiga', null, '2017-03-29 15:21:56', '0:0:0.156', '1001');
INSERT INTO `sys_log` VALUES ('63c5a8b3cd734e6ebda8be5a3ced7c94', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1484642561716', null, '2017-01-17 16:42:42', '0:0:0.127', '1001');
INSERT INTO `sys_log` VALUES ('669ac9e4f0784bdfa3b9f440ae530e91', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=bgls', null, '2017-02-13 10:17:38', '0:0:1.914', '1001');
INSERT INTO `sys_log` VALUES ('67a0c6322fd640e682d1a8aa034b9283', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1483497763391', null, '2017-01-04 10:42:43', '0:0:1.249', '1001');
INSERT INTO `sys_log` VALUES ('72d653184f4144b2a0195d6d06523691', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=bt98', null, '2017-02-13 16:08:44', '0:0:0.30', '1001');
INSERT INTO `sys_log` VALUES ('73235ce73009460fa6967f608c32cb0c', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=uw23', null, '2017-03-28 19:55:14', '0:0:0.723', '1001');
INSERT INTO `sys_log` VALUES ('74f8d28d73e147ecac697dc23bc9f6e7', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-16 11:28:19', '0:0:2.142', '1001');
INSERT INTO `sys_log` VALUES ('76de60496d5d4b64a916fb85ad7e54e6', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-01-17 15:17:03', '0:0:14.309', '1001');
INSERT INTO `sys_log` VALUES ('7b0e33f5e4e24149b08a311c5e8ff2a0', 'info', '登入系统', '10.45.45.113', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-17 10:14:58', '0:0:0.55', '1001');
INSERT INTO `sys_log` VALUES ('7dd319034e994b00b76d566a0702e9bd', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1482408540383', null, '2016-12-22 20:09:00', '0:0:0.37', '1001');
INSERT INTO `sys_log` VALUES ('824fb7b60c7942a98f662a84de28914c', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478537838861&filter={\"name\":\"你好\",\"username\":\"\",\"email\":\"\",\"phone\":\"\",\"organizationId\":\"\"}&page=1&start=0&limit=15', null, '2016-11-08 00:57:19', '0:0:0.11', '1001');
INSERT INTO `sys_log` VALUES ('85119bf8544a407495f46c031f7aaf44', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-01-17 15:17:43', '0:0:1.537', '1001');
INSERT INTO `sys_log` VALUES ('8770338656fe464390b7c244ceffdc03', 'info', '用户管理-添加用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/create.do', 'POST', '_dc=1479477590643', null, '2016-11-18 21:59:51', '0:0:8.895', '1001');
INSERT INTO `sys_log` VALUES ('87db0f44cf464b48b9df8945f093ce04', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-13 13:52:18', '0:0:7.770', '1001');
INSERT INTO `sys_log` VALUES ('895753ca0a7a4bec90b2f4edd6ef38b0', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=lkpo', null, '2017-03-27 14:00:29', '0:0:0.163', '1001');
INSERT INTO `sys_log` VALUES ('8b64f492118c481fa180a2d6dd0f35dd', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-02-13 16:07:36', '0:0:0.16', '10e29164211446f0864a2f904ce70d32');
INSERT INTO `sys_log` VALUES ('8bc05e6deb2b409899db5962a3373af6', 'info', '用户管理-添加用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/create.do', 'POST', '_dc=1482408525889', null, '2016-12-22 20:08:46', '0:0:0.57', '1001');
INSERT INTO `sys_log` VALUES ('8cf37a922dc54fb399fa63b668d3387b', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-spring4/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=jxif', null, '2017-02-09 15:36:47', '0:0:3.268', '1001');
INSERT INTO `sys_log` VALUES ('8eddb6c1e0c849f58b9c2c2823bd15f5', 'error', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1484642458286', 'org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.myron.ims.dao.UserDao.deleteByPrimaryKey', '2017-01-17 16:40:58', '0:0:3.97', '1001');
INSERT INTO `sys_log` VALUES ('9000b03bd5b345f09f0709b7e37a83c2', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-spring4/logout.do', 'GET', '', null, '2017-02-09 09:38:54', '0:0:1.40', '1001');
INSERT INTO `sys_log` VALUES ('93b39537c3814ab58fdaf20c49c625de', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1482408941776', null, '2016-12-22 20:15:42', '0:0:0.6', '1001');
INSERT INTO `sys_log` VALUES ('95b84452a2484c539e9cd7c0074d726c', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-spring4/logout.do', 'GET', '', null, '2017-02-09 11:00:32', '0:0:0.29', '1001');
INSERT INTO `sys_log` VALUES ('9775dfa63cb445ed829c435c38afa31c', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-spring4/logout.do', 'GET', '', null, '2017-02-09 14:07:33', '0:0:0.34', '1001');
INSERT INTO `sys_log` VALUES ('993dcae8c7414a789f4eaf91b8a0e22e', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=j2t6', null, '2017-02-09 17:15:31', '0:0:10.237', '1001');
INSERT INTO `sys_log` VALUES ('9ad702f10d0a425bab2e3d3617da7c2c', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-17 10:32:15', '0:0:0.6', '1001');
INSERT INTO `sys_log` VALUES ('9de52ebbbe6a4ba2b8e8ed5ae65daa28', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-17 14:12:16', '0:0:2.602', '1001');
INSERT INTO `sys_log` VALUES ('9e13ed2e75344b4d848b2446a9754e5b', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478537840624&filter={\"name\":\"你好\",\"username\":\"\",\"email\":\"\",\"phone\":\"\",\"organizationId\":\"\"}&page=1&start=0&limit=15', null, '2016-11-08 00:57:21', '0:0:0.25', '1001');
INSERT INTO `sys_log` VALUES ('9e375cf3a4cf407487c47739e0379830', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-10 20:10:24', '0:0:0.133', '1001');
INSERT INTO `sys_log` VALUES ('a269572522154bdf8dc58fe81d9b788b', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=7l0e', null, '2017-02-10 14:22:41', '0:0:0.51', '1001');
INSERT INTO `sys_log` VALUES ('a3910b126b004b72a1122ce35826b676', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1484642646632', null, '2017-01-17 16:44:07', '0:0:0.185', '1001');
INSERT INTO `sys_log` VALUES ('a44839ae6ccf4bc1b1ea9fa038750d47', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1479477599761&page=1&start=0&limit=15', null, '2016-11-18 22:00:00', '0:0:0.23', '1001');
INSERT INTO `sys_log` VALUES ('a4726b6c7c1643cba4282697a49f2ae8', 'info', '用户管理-添加用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/create.do', 'POST', '_dc=1483497769771', null, '2017-01-04 10:42:50', '0:0:0.128', '1001');
INSERT INTO `sys_log` VALUES ('a4744ce5eaed48a0871ac3d7e849421c', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-10 20:56:10', '0:0:0.228', '1001');
INSERT INTO `sys_log` VALUES ('a60aff32e1bb4c278dd0850e5bdd199c', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1484642597992', null, '2017-01-17 16:43:18', '0:0:0.23', '1001');
INSERT INTO `sys_log` VALUES ('aa84224a566d4c4b902a0e78885b64ac', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=on&verifycode=amwu', null, '2017-04-06 16:22:59', '0:0:0.505', '1001');
INSERT INTO `sys_log` VALUES ('af63f74fc60545e3b07b0a322896c443', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-16 14:25:08', '0:0:2.546', '1001');
INSERT INTO `sys_log` VALUES ('b2da972cbfb548b7b51581d0e2466a37', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-01-17 17:12:02', '0:0:0.49', '1001');
INSERT INTO `sys_log` VALUES ('b31baf1db5be4334a8a918b60d8c26bd', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-spring4/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=', null, '2017-02-09 09:46:18', '0:0:5.448', '1001');
INSERT INTO `sys_log` VALUES ('b56a4e30e019482c8e8225c0de3e78d1', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-spring4/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=znpb', null, '2017-02-09 11:04:25', '0:0:0.8', '1001');
INSERT INTO `sys_log` VALUES ('b607327a532648aeabbce6efdf3d12e1', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=zech', null, '2017-03-29 09:37:03', '0:0:0.276', '1001');
INSERT INTO `sys_log` VALUES ('b6cf18b7de00465697d6f929bcd48b38', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=9qhu', null, '2017-02-13 16:00:46', '0:0:0.221', '1001');
INSERT INTO `sys_log` VALUES ('b6fad9cd87ae452eafe3c92dff626a9c', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-12 10:06:13', '0:0:0.11', '1001');
INSERT INTO `sys_log` VALUES ('b7b7052df9f14efda27f3745e6998366', 'error', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1483688497917', 'org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.myron.ims.dao.UserDao.deleteByPrimaryKey', '2017-01-06 15:41:38', '0:0:0.5', '10e29164211446f0864a2f904ce70d32');
INSERT INTO `sys_log` VALUES ('b7dfde163b6e491c98a763ceddd52be3', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=houzi&password=&rememberMe=false&verifycode=h2ht', null, '2017-02-13 16:08:07', '0:0:0.29', '10e29164211446f0864a2f904ce70d32');
INSERT INTO `sys_log` VALUES ('ba9118909dea4fce82be3f79d28f3bd4', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-02-09 15:29:24', '0:0:0.267', '1001');
INSERT INTO `sys_log` VALUES ('bc2c9dcf398948f9b2c66c0159fbc404', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=yucn', null, '2017-04-10 16:43:00', '0:0:0.578', '1001');
INSERT INTO `sys_log` VALUES ('bcb7f08ed8144d929e3238dbc75bac06', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-02-13 16:08:35', '0:0:0.14', '10e29164211446f0864a2f904ce70d32');
INSERT INTO `sys_log` VALUES ('be3a6a63f3f449a6b48b7e75f1fa406a', 'error', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-spring4/logout.do', 'GET', '', 'java.lang.NullPointerException', '2017-02-09 11:02:08', '0:0:0.22', '1001');
INSERT INTO `sys_log` VALUES ('c17ddf5641924961a90a1983d550a389', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1486714083549', null, '2017-02-10 16:08:04', '0:0:0.8', '1001');
INSERT INTO `sys_log` VALUES ('c203617470ca4fc1900c558dcf1125d8', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-spring4/logout.do', 'GET', '', null, '2017-02-09 10:07:47', '0:0:0.948', '1001');
INSERT INTO `sys_log` VALUES ('c27fda35338f497eb73e2abb9a71f4aa', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-16 11:26:04', '0:0:2.52', '1001');
INSERT INTO `sys_log` VALUES ('c2fa819ccefc4d0eab0a0a340cf10135', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-spring4/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=222', null, '2017-02-09 11:00:02', '0:0:0.34', '1001');
INSERT INTO `sys_log` VALUES ('c4108f03510640f69d71e6c675eb830f', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1484642616989', null, '2017-01-17 16:43:37', '0:0:0.85', '1001');
INSERT INTO `sys_log` VALUES ('c7c435b5b8e04d1a89618f4ab5b9b86d', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1486714060395', null, '2017-02-10 16:07:40', '0:0:0.59', '1001');
INSERT INTO `sys_log` VALUES ('ca4e240735264df9b4ddcf5fde78851d', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478537824499&filter=c0d872125de64801915cd83b3271ecc2&page=1&start=0&limit=15', null, '2016-11-08 00:57:05', '0:0:0.10', '1001');
INSERT INTO `sys_log` VALUES ('cbc8c8fd27d74945a55ba5bee430fc73', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-10 19:51:58', '0:1:2.211', '1001');
INSERT INTO `sys_log` VALUES ('cc9562eaf000488fb33edad0a427a101', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1484642569253', null, '2017-01-17 16:42:49', '0:0:0.59', '1001');
INSERT INTO `sys_log` VALUES ('ceedbded1a4e431fa9382a5f1632ca46', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-10 20:05:44', '0:0:0.13', '1001');
INSERT INTO `sys_log` VALUES ('d90f895cc33544cd9c26ea6f94bc156d', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-10 20:03:55', '0:0:0.6', '1001');
INSERT INTO `sys_log` VALUES ('d9d7851c6a8b44d2b4df616b7ce4b71c', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-spring4/logout.do', 'GET', '', null, '2017-02-09 09:45:59', '0:0:1.111', '1001');
INSERT INTO `sys_log` VALUES ('da42fbc31fee45988309450f10279987', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-spring4/logout.do', 'GET', '', null, '2017-02-09 11:28:47', '0:0:0.814', '1001');
INSERT INTO `sys_log` VALUES ('dba498226d5f44c9ac840899bb273c7a', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=qc7c', null, '2017-03-27 11:44:09', '0:0:0.162', '1001');
INSERT INTO `sys_log` VALUES ('de53c2c1f4984363bdff97c387558afa', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-spring4/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=13pi', null, '2017-02-09 11:05:33', '0:0:0.0', '1001');
INSERT INTO `sys_log` VALUES ('def02e277e5e46998a870ffa8f5fa2b8', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1479477606639&page=2&start=15&limit=15', null, '2016-11-18 22:00:07', '0:0:0.6', '1001');
INSERT INTO `sys_log` VALUES ('df358397908147c7adc83908105b8988', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=lnr2', null, '2017-04-01 16:40:56', '0:0:0.347', '1001');
INSERT INTO `sys_log` VALUES ('e254cbdad34b489491251ca5ba212aa9', 'error', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', 'com.myron.ims.exception.WrongVerifyCodeException: 验证码不正确,请重新输入', '2017-02-13 16:04:03', '0:0:0.41', '1001');
INSERT INTO `sys_log` VALUES ('e2ae7dc2cd7a4f7cb83ea447216b312b', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-16 11:30:29', '0:0:2.846', '1001');
INSERT INTO `sys_log` VALUES ('e78c78e0413044d7b43d6b93bf7a3ca4', 'info', '安全退出系统', '0:0:0:0:0:0:0:1', '/demo-extjs/logout.do', 'GET', '', null, '2017-02-09 15:50:07', '0:0:0.68', '1001');
INSERT INTO `sys_log` VALUES ('e7b1b9552836471a8f6ea96ffbf8350a', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1479477609330&page=1&start=0&limit=15', null, '2016-11-18 22:00:09', '0:0:0.12', '1001');
INSERT INTO `sys_log` VALUES ('e89fad3a81d24e849ec91d6bc90d7139', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478537280842&page=1&start=0&limit=15', null, '2016-11-08 00:48:01', '0:0:0.31', '1001');
INSERT INTO `sys_log` VALUES ('e972d7b68b984aae8abad691be088f4f', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1484642583287', null, '2017-01-17 16:43:03', '0:0:0.41', '1001');
INSERT INTO `sys_log` VALUES ('f04cfb0fdf714a2084ac326294411593', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1479477572553&page=1&start=0&limit=15', null, '2016-11-18 21:59:33', '0:0:0.20', '1001');
INSERT INTO `sys_log` VALUES ('f0e91fbe70f34afe8df9cff89bd3e988', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-spring4/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=eere', null, '2017-02-09 11:01:14', '0:0:0.22', '1001');
INSERT INTO `sys_log` VALUES ('f4673b074c4442b793d0ca9946ba8d3e', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=21vv', null, '2017-03-29 17:54:29', '0:0:0.172', '1001');
INSERT INTO `sys_log` VALUES ('f6960ba2df444dcabb99f499f8d3f0e4', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478537841176&filter={\"name\":\"你好\",\"username\":\"\",\"email\":\"\",\"phone\":\"\",\"organizationId\":\"\"}&page=1&start=0&limit=15', null, '2016-11-08 00:57:21', '0:0:0.12', '1001');
INSERT INTO `sys_log` VALUES ('f7b4c43a9ae1410d8353f397aee9eac9', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1484642572903', null, '2017-01-17 16:42:53', '0:0:0.40', '1001');
INSERT INTO `sys_log` VALUES ('fa854c9492cf465ba3db1bdb3e86ddff', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478537445816&page=1&start=0&limit=15', null, '2016-11-08 00:50:46', '0:0:0.23', '1001');
INSERT INTO `sys_log` VALUES ('fb56a5312bd84c2f967cbbc4aa226605', 'error', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1483688508082', 'org.apache.ibatis.binding.BindingException: Invalid bound statement (not found): com.myron.ims.dao.UserDao.deleteByPrimaryKey', '2017-01-06 15:41:48', '0:0:0.1', '10e29164211446f0864a2f904ce70d32');
INSERT INTO `sys_log` VALUES ('fbb3b20203fc4655a9663592849493e7', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=4992', null, '2017-02-13 16:16:04', '0:0:0.146', '1001');
INSERT INTO `sys_log` VALUES ('fccf3f50c21a478e9b656b613538c74c', 'info', '用户管理-修改用户信息', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1482408847255', null, '2016-12-22 20:14:07', '0:0:0.7', '1001');
INSERT INTO `sys_log` VALUES ('fd463608ba6c4d6eb72d32857efe6bb2', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-12 17:03:11', '0:0:0.919', '1001');
INSERT INTO `sys_log` VALUES ('fd91f1e02aeb425b9476760a0b8d517d', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2017-01-17 14:11:57', '0:0:2.519', '1001');
INSERT INTO `sys_log` VALUES ('ff41afb8d6214c7db3e9c592dc2c6459', 'info', '登入系统', '0:0:0:0:0:0:0:1', '/demo-spring4/login.do', 'POST', 'username=ims&password=&rememberMe=false&verifycode=bveb', null, '2017-02-09 14:45:44', '0:0:0.33', '1001');

-- ----------------------------
-- Table structure for `sys_log_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_tbl`;
CREATE TABLE `sys_log_tbl` (
  `log_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '日志主键',
  `type` varchar(20) DEFAULT NULL COMMENT '日志类型',
  `title` varchar(100) DEFAULT NULL COMMENT '日志标题',
  `remote_addr` varchar(20) DEFAULT NULL COMMENT '请求地址',
  `request_uri` varchar(300) DEFAULT NULL COMMENT 'URI',
  `method` varchar(300) DEFAULT NULL COMMENT '请求方式',
  `params` varchar(300) DEFAULT NULL COMMENT '提交参数',
  `exception` varchar(500) DEFAULT NULL COMMENT '异常',
  `begin_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_log_tbl
-- ----------------------------
INSERT INTO `sys_log_tbl` VALUES ('01f9d10fbeb941a781ea259862e1f390', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1477109318183&page=1&start=0&limit=15', null, '2016-10-22 12:08:38', '2016-10-22 12:08:38', '1001');
INSERT INTO `sys_log_tbl` VALUES ('0363565b48824970878930444bbecade', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476285373865&page=1&start=0&limit=15', null, '2016-10-12 23:16:14', '2016-10-12 23:16:14', '1001');
INSERT INTO `sys_log_tbl` VALUES ('04b5c72006eb4c4d8b8a4c95855ccc19', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1477108741574&page=1&start=0&limit=15', null, '2016-10-22 11:59:02', '2016-10-22 11:59:02', '1001');
INSERT INTO `sys_log_tbl` VALUES ('10044de3bf9449bfa4d3f35fbb8a5cf3', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475312234019&page=1&start=0&limit=15', null, '2016-10-01 16:57:14', '2016-10-01 16:57:14', '1001');
INSERT INTO `sys_log_tbl` VALUES ('10ac3d3747e542678bf5b41e21fef56c', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475918921163&page=1&start=0&limit=15', null, '2016-10-08 17:28:41', '2016-10-08 17:28:41', '1001');
INSERT INTO `sys_log_tbl` VALUES ('11499059d0574f6f832d849270fbab74', 'info', '用户管理-删除用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/destroy.do', 'POST', '_dc=1476284853949', null, '2016-10-12 23:07:34', '2016-10-12 23:07:34', '1001');
INSERT INTO `sys_log_tbl` VALUES ('13629d00cffe491580e6f3024c3ae710', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476284300121&page=1&start=0&limit=15', null, '2016-10-12 22:58:20', '2016-10-12 22:58:20', '1001');
INSERT INTO `sys_log_tbl` VALUES ('1449a85ac5a0472b9ee82931282a036f', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475919381061&page=1&start=0&limit=15', null, '2016-10-08 17:36:21', '2016-10-08 17:36:21', '1001');
INSERT INTO `sys_log_tbl` VALUES ('1851c9c21ccd4152865838e830858012', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475315534467&page=1&start=0&limit=15', null, '2016-10-01 17:52:15', '2016-10-01 17:52:15', '1001');
INSERT INTO `sys_log_tbl` VALUES ('1fe2a632a9f1425ba1e0fddd78f6f906', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922816631&page=1&start=0&limit=15', null, '2016-10-08 18:33:37', '2016-10-08 18:33:37', '1001');
INSERT INTO `sys_log_tbl` VALUES ('2824f154f6f9408ea0dc0d28dfd2023d', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525359257&page=2&start=15&limit=15&sort=[{\"property\":\"sex\",\"direction\":\"ASC\"}]', null, '2016-11-07 21:29:19', '2016-11-07 21:29:19', '1001');
INSERT INTO `sys_log_tbl` VALUES ('2856126246954433b45816d0cf2ed390', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476284845518&page=1&start=0&limit=15', null, '2016-10-12 23:07:26', '2016-10-12 23:07:26', '1001');
INSERT INTO `sys_log_tbl` VALUES ('2aa2cefb75e544faa1b16d86bd1eff99', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1477113498544', null, '2016-10-22 13:18:19', '2016-10-22 13:18:19', '1001');
INSERT INTO `sys_log_tbl` VALUES ('2d27400134ef4c41b8550e8315358f35', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475208294163&page=1&start=0&limit=15', '', '2016-09-14 00:00:00', '2016-09-30 12:04:54', '1001');
INSERT INTO `sys_log_tbl` VALUES ('2f30e2890bd6426096ab2ac301754159', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475308978063&page=1&start=0&limit=15', null, '2016-10-01 16:02:58', '2016-10-01 16:02:58', '1001');
INSERT INTO `sys_log_tbl` VALUES ('2f6066d196d049b5b48ac2212aef8b2d', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', '', '2016-09-01 00:00:00', '2016-09-30 11:38:57', '1001');
INSERT INTO `sys_log_tbl` VALUES ('30848f5a7fd44a50b35acc1fdfdbbf3f', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922807865&page=1&start=0&limit=15', null, '2016-10-08 18:33:28', '2016-10-08 18:33:28', '1001');
INSERT INTO `sys_log_tbl` VALUES ('31a14cb183694707a1e9567623a3e44b', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475305542818&page=1&start=0&limit=15', null, '2016-10-01 15:05:43', '2016-10-01 15:05:43', '1001');
INSERT INTO `sys_log_tbl` VALUES ('33df4712c8ca4f8687284e4087e16acf', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1477108716991&page=1&start=0&limit=15', null, '2016-10-22 11:58:37', '2016-10-22 11:58:37', '1001');
INSERT INTO `sys_log_tbl` VALUES ('3bcbc0c26cb74921935a845eb92d8e0f', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922807386&page=1&start=0&limit=15', null, '2016-10-08 18:33:27', '2016-10-08 18:33:27', '1001');
INSERT INTO `sys_log_tbl` VALUES ('3cd240481980468a8414dc47ac9ec9a1', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475204002684&page=1&start=0&limit=15', null, '2016-09-30 10:53:23', '2016-09-30 10:53:29', '1001');
INSERT INTO `sys_log_tbl` VALUES ('3d39c4005cc049c59cb96d720d55ae55', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525399195&page=2&start=15&limit=15&sort=[{\"property\":\"sex\",\"direction\":\"ASC\"}]', null, '2016-11-07 21:29:59', '2016-11-07 21:29:59', '1001');
INSERT INTO `sys_log_tbl` VALUES ('3e7b951b8b4f44cfad90ab4a88c4219d', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1475308977870', null, '2016-10-01 16:02:58', '2016-10-01 16:02:58', '1001');
INSERT INTO `sys_log_tbl` VALUES ('3ff709a41d4e4484bd84986ee3e95dcf', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525471194&page=2&start=15&limit=15&sort=[{\"property\":\"email\",\"direction\":\"DESC\"}]', null, '2016-11-07 21:31:11', '2016-11-07 21:31:11', '1001');
INSERT INTO `sys_log_tbl` VALUES ('40e4dc08a4ff40fd928a8b589910ea68', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922999728&page=1&start=0&limit=15', null, '2016-10-08 18:36:40', '2016-10-08 18:36:40', '1001');
INSERT INTO `sys_log_tbl` VALUES ('413f0530e90047509177894ffbb04cae', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1477109315795&page=2&start=15&limit=15', null, '2016-10-22 12:08:36', '2016-10-22 12:08:36', '1001');
INSERT INTO `sys_log_tbl` VALUES ('4467733d28ee4acf9baddfe25c71b4b6', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475918596066&page=1&start=0&limit=15', null, '2016-10-08 17:23:16', '2016-10-08 17:23:16', '1001');
INSERT INTO `sys_log_tbl` VALUES ('456706fbf6e846ababcb43256cf26793', 'info', '用户管理-添加用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/create.do', 'POST', '_dc=1475922813281', null, '2016-10-08 18:33:33', '2016-10-08 18:33:34', '1001');
INSERT INTO `sys_log_tbl` VALUES ('47c0b316a89648cbbac24a78eaa5704e', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475307879424&page=1&start=0&limit=15', null, '2016-10-01 15:44:39', '2016-10-01 15:44:39', '1001');
INSERT INTO `sys_log_tbl` VALUES ('4c6fef8233454cffa1432cd2d29303be', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475211673634&page=1&start=0&limit=15', '', '2016-09-07 00:00:00', '2016-09-30 13:01:14', '1001');
INSERT INTO `sys_log_tbl` VALUES ('4c9a48c332a242fc88092f15960a5d8d', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2016-09-30 12:25:49', '2016-09-30 12:25:49', '1001');
INSERT INTO `sys_log_tbl` VALUES ('4fe139bfd93c4e85b602d188d72cebcc', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475312222023&page=1&start=0&limit=15', null, '2016-10-01 16:57:02', '2016-10-01 16:57:02', '1001');
INSERT INTO `sys_log_tbl` VALUES ('530fd6c1a98848d186ffaaa39e9aa6a4', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', '', '2016-09-30 11:24:03', '2016-09-17 00:00:01', '1001');
INSERT INTO `sys_log_tbl` VALUES ('59ac959b44c04f94a4f804367b1ff502', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525426422&page=1&start=0&limit=15&sort=[{\"property\":\"sex\",\"direction\":\"ASC\"}]', null, '2016-11-07 21:30:26', '2016-11-07 21:30:26', '1001');
INSERT INTO `sys_log_tbl` VALUES ('5a65716c32ee456484a79b65526bf247', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475918615960&page=1&start=0&limit=15', null, '2016-10-08 17:23:36', '2016-10-08 17:23:36', '1001');
INSERT INTO `sys_log_tbl` VALUES ('5d952117f7224d6097f087cccfd2209c', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475308966348&page=1&start=0&limit=15', null, '2016-10-01 16:02:46', '2016-10-01 16:02:46', '1001');
INSERT INTO `sys_log_tbl` VALUES ('605103db3a9e43aebe5a3f6fef8a9a74', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922622216&page=1&start=0&limit=15', null, '2016-10-08 18:30:22', '2016-10-08 18:30:22', '1001');
INSERT INTO `sys_log_tbl` VALUES ('651146fb14de4204aa389bbcd078c4c4', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475923015324&page=1&start=0&limit=15', null, '2016-10-08 18:36:55', '2016-10-08 18:36:55', '1001');
INSERT INTO `sys_log_tbl` VALUES ('6f9edf9a9d284a3ba4bf6187c9ffcfba', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1475312292124', null, '2016-10-01 16:58:12', '2016-10-01 16:58:12', '1001');
INSERT INTO `sys_log_tbl` VALUES ('6fbc0a33eb354a26a5b6fca125f4f4eb', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525379257&page=1&start=0&limit=15&sort=[{\"property\":\"company.name\",\"direction\":\"ASC\"}]', null, '2016-11-07 21:29:39', '2016-11-07 21:29:39', '1001');
INSERT INTO `sys_log_tbl` VALUES ('701c493ccb834dcf968aec9c6f519176', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1475308999203', null, '2016-10-01 16:03:19', '2016-10-01 16:03:19', '1001');
INSERT INTO `sys_log_tbl` VALUES ('7d111f968d9d4877b8d396457f05727b', 'info', '用户管理-添加用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/create.do', 'POST', '_dc=1477108741385', null, '2016-10-22 11:59:01', '2016-10-22 11:59:01', '1001');
INSERT INTO `sys_log_tbl` VALUES ('7d4c5f34e31d447b9977cbaafbb44c7d', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1477109314864&page=1&start=0&limit=15', null, '2016-10-22 12:08:35', '2016-10-22 12:08:35', '1001');
INSERT INTO `sys_log_tbl` VALUES ('7d630b7f10ee4f6ca4c4d8f7f6c805d6', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2016-09-30 12:42:25', '2016-09-30 12:42:25', '1001');
INSERT INTO `sys_log_tbl` VALUES ('7f95639a2f584dff99e6ca639bcaecc6', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475923024173&page=1&start=0&limit=15', null, '2016-10-08 18:37:04', '2016-10-08 18:37:04', '1001');
INSERT INTO `sys_log_tbl` VALUES ('8146d2e1fe5d4413a5e65d2b3d030974', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475312292322&page=1&start=0&limit=15', null, '2016-10-01 16:58:12', '2016-10-01 16:58:12', '1001');
INSERT INTO `sys_log_tbl` VALUES ('81f8c28dfd9146d891b4903dd1ecc9ac', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1476283444826', null, '2016-10-12 22:44:05', '2016-10-12 22:44:05', '1001');
INSERT INTO `sys_log_tbl` VALUES ('829aa8c1f8ec4bae9e361633c1ac925d', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2016-10-01 14:33:09', '2016-10-01 14:33:10', '1001');
INSERT INTO `sys_log_tbl` VALUES ('8391536f18fa4418aafd757133ff4fde', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476283473824&page=1&start=0&limit=15', null, '2016-10-12 22:44:34', '2016-10-12 22:44:34', '1001');
INSERT INTO `sys_log_tbl` VALUES ('840d5919a247473b95b47339a4a31862', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2016-10-01 14:31:46', '2016-10-01 14:31:46', '1001');
INSERT INTO `sys_log_tbl` VALUES ('843d9161ab25450db46599b252b9ae26', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475205566495&page=1&start=0&limit=15', '', '2016-09-30 11:19:27', '2016-09-21 21:00:00', '1001');
INSERT INTO `sys_log_tbl` VALUES ('875a319876354871893c7aa049e8450a', 'info', '用户管理-添加用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/create.do', 'POST', '_dc=1476284845105', null, '2016-10-12 23:07:25', '2016-10-12 23:07:25', '1001');
INSERT INTO `sys_log_tbl` VALUES ('884129e0211347f6aa9bf513278772a5', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922813668&page=1&start=0&limit=15', null, '2016-10-08 18:33:34', '2016-10-08 18:33:34', '1001');
INSERT INTO `sys_log_tbl` VALUES ('8a0545f122d346f7a225e8c3d0d390dd', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475312372948&page=1&start=0&limit=15', null, '2016-10-01 16:59:33', '2016-10-01 16:59:33', '1001');
INSERT INTO `sys_log_tbl` VALUES ('8b453b8b19984fb2818a177de6a0d0bf', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475208084165&page=1&start=0&limit=15', null, '2016-09-30 12:01:24', '2016-09-30 12:01:24', '1001');
INSERT INTO `sys_log_tbl` VALUES ('8c053c304d0b4be797b310908def3ef5', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475307883930&page=1&start=0&limit=15', null, '2016-10-01 15:44:44', '2016-10-01 15:44:44', '1001');
INSERT INTO `sys_log_tbl` VALUES ('8ea70e1983824360987dee031bdad123', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1477113498840&page=1&start=0&limit=15', null, '2016-10-22 13:18:19', '2016-10-22 13:18:19', '1001');
INSERT INTO `sys_log_tbl` VALUES ('8ee238f0e3374eb0bc781c757b3e1c84', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2016-10-01 14:30:06', '2016-10-01 14:30:06', '1001');
INSERT INTO `sys_log_tbl` VALUES ('8ffefd9c4d7c407c8776afc1031cfc6d', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476283135800&page=1&start=0&limit=15', null, '2016-10-12 22:38:56', '2016-10-12 22:38:56', '1001');
INSERT INTO `sys_log_tbl` VALUES ('953bac8d3f704e66a4e15603256afd59', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475308999398&page=1&start=0&limit=15', null, '2016-10-01 16:03:19', '2016-10-01 16:03:19', '1001');
INSERT INTO `sys_log_tbl` VALUES ('96337edb49bb4516adccbdfb3de25346', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922815397&page=2&start=15&limit=15', null, '2016-10-08 18:33:35', '2016-10-08 18:33:35', '1001');
INSERT INTO `sys_log_tbl` VALUES ('9a650a15b4e445109430c23bf2fd45ac', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922806240&page=1&start=0&limit=15', null, '2016-10-08 18:33:26', '2016-10-08 18:33:26', '1001');
INSERT INTO `sys_log_tbl` VALUES ('9b6ab7db2cf545efa6b83e428fdf813b', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1477113492231&page=1&start=0&limit=15', null, '2016-10-22 13:18:12', '2016-10-22 13:18:12', '1001');
INSERT INTO `sys_log_tbl` VALUES ('9bfd92681e2a4ec094b3c891e5be592a', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476283445263&page=1&start=0&limit=15', null, '2016-10-12 22:44:05', '2016-10-12 22:44:05', '1001');
INSERT INTO `sys_log_tbl` VALUES ('9c1a9c16d52245468fc2753a69fe58e0', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1475308965919', null, '2016-10-01 16:02:46', '2016-10-01 16:02:46', '1001');
INSERT INTO `sys_log_tbl` VALUES ('9d53c7373b0a48c2be41e63ddae2b9ba', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475919116612&page=1&start=0&limit=15', null, '2016-10-08 17:31:57', '2016-10-08 17:31:57', '1001');
INSERT INTO `sys_log_tbl` VALUES ('9f386b5908f44714a7a538e4454a3af3', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1475308982918', null, '2016-10-01 16:03:03', '2016-10-01 16:03:03', '1001');
INSERT INTO `sys_log_tbl` VALUES ('a0a8e31bd41c4a5fa6b7fa559216be95', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475919674352&page=1&start=0&limit=15', null, '2016-10-08 17:41:14', '2016-10-08 17:41:14', '1001');
INSERT INTO `sys_log_tbl` VALUES ('a87154e6dae1445191f86699a6069589', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1475312233630', null, '2016-10-01 16:57:14', '2016-10-01 16:57:14', '1001');
INSERT INTO `sys_log_tbl` VALUES ('adf694e3a37f4a80be31a4937b0e08fd', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476283685611&page=1&start=0&limit=15', null, '2016-10-12 22:48:06', '2016-10-12 22:48:06', '1001');
INSERT INTO `sys_log_tbl` VALUES ('ae27011e826f4fc092c8995cbb23b8ae', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', '', '2013-10-22 00:00:00', '2016-10-01 12:05:51', '1001');
INSERT INTO `sys_log_tbl` VALUES ('aec9b0a9fc1c43a4b5c68899ec8f90e9', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475919659413&page=1&start=0&limit=15', null, '2016-10-08 17:41:00', '2016-10-08 17:41:00', '1001');
INSERT INTO `sys_log_tbl` VALUES ('b2d685aeb77d49ecb7afd344800fb238', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2016-09-30 12:44:11', '2016-09-30 12:44:11', '1001');
INSERT INTO `sys_log_tbl` VALUES ('b647ff4855504130a026f6ea3eec2d10', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475205947813&page=1&start=0&limit=15', '', '2012-08-14 00:00:00', '2016-09-30 11:25:48', '1001');
INSERT INTO `sys_log_tbl` VALUES ('b7c79c1e05e04ee5a199684288f65e70', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1475307883639', null, '2016-10-01 15:44:44', '2016-10-01 15:44:44', '1001');
INSERT INTO `sys_log_tbl` VALUES ('bdaf90891ee14cbc9f4e7a584584f03b', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476285413300&page=1&start=0&limit=15', null, '2016-10-12 23:16:53', '2016-10-12 23:16:53', '1001');
INSERT INTO `sys_log_tbl` VALUES ('bdff263b17f74ebf8aa0410ce6cdfedd', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475919690508&page=1&start=0&limit=15', null, '2016-10-08 17:41:31', '2016-10-08 17:41:31', '1001');
INSERT INTO `sys_log_tbl` VALUES ('be8dff74a71b4ff8bca1e2a72403659f', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525377663&page=2&start=15&limit=15&sort=[{\"property\":\"company.name\",\"direction\":\"ASC\"}]', null, '2016-11-07 21:29:38', '2016-11-07 21:29:38', '1001');
INSERT INTO `sys_log_tbl` VALUES ('bea85d7e891844aba38f2b7c27ae47cc', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525370552&page=2&start=15&limit=15&sort=[{\"property\":\"sex\",\"direction\":\"ASC\"}]', null, '2016-11-07 21:29:31', '2016-11-07 21:29:31', '1001');
INSERT INTO `sys_log_tbl` VALUES ('c1b2aff961734adfbfacecd4a50982fe', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475919704282&page=1&start=0&limit=15', null, '2016-10-08 17:41:44', '2016-10-08 17:41:44', '1001');
INSERT INTO `sys_log_tbl` VALUES ('c209e0e7b88d471fbbb9b3929a3c9003', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475308983093&page=1&start=0&limit=15', null, '2016-10-01 16:03:03', '2016-10-01 16:03:03', '1001');
INSERT INTO `sys_log_tbl` VALUES ('c4c1aac8b6be4427ad1625c6dab34f15', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475309838071&page=1&start=0&limit=15', null, '2016-10-01 16:17:18', '2016-10-01 16:17:18', '1001');
INSERT INTO `sys_log_tbl` VALUES ('c6d6794580214b03b9a42c84b8f87cea', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922992943&page=1&start=0&limit=15', null, '2016-10-08 18:36:33', '2016-10-08 18:36:33', '1001');
INSERT INTO `sys_log_tbl` VALUES ('c6fdbd6cfdd24a3494afcfda2cfb7384', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475312448676&page=1&start=0&limit=15', null, '2016-10-01 17:00:49', '2016-10-01 17:00:49', '1001');
INSERT INTO `sys_log_tbl` VALUES ('cc74e8ed7ee74a1cbeae4b9d533b082a', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476283688209&page=2&start=15&limit=15', null, '2016-10-12 22:48:08', '2016-10-12 22:48:08', '1001');
INSERT INTO `sys_log_tbl` VALUES ('cdaddd932bbf41bdbfe887ac449cdb1b', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476284741225&page=1&start=0&limit=15', null, '2016-10-12 23:05:41', '2016-10-12 23:05:41', '1001');
INSERT INTO `sys_log_tbl` VALUES ('cf6b8d19725648cc95731fad844e18eb', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525352026&page=1&start=0&limit=15', null, '2016-11-07 21:29:12', '2016-11-07 21:29:12', '1001');
INSERT INTO `sys_log_tbl` VALUES ('d0263c8ca1e34d52b7e81bc7970a8626', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476283878750&page=1&start=0&limit=15', null, '2016-10-12 22:51:19', '2016-10-12 22:51:19', '1001');
INSERT INTO `sys_log_tbl` VALUES ('d092ed25bf91477bb80e22b090cb5c94', 'info', '用户管理-添加修改', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/update.do', 'POST', '_dc=1475312372748', null, '2016-10-01 16:59:33', '2016-10-01 16:59:33', '1001');
INSERT INTO `sys_log_tbl` VALUES ('d0fce0cd17394e57b220c5b180ffbe81', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476283437134&page=1&start=0&limit=15', null, '2016-10-12 22:43:57', '2016-10-12 22:43:57', '1001');
INSERT INTO `sys_log_tbl` VALUES ('d1abb5b26dff499684410f99549c3aac', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475208281654&page=1&start=0&limit=15', '', '2016-08-16 00:00:00', '2016-09-30 12:04:42', '1001');
INSERT INTO `sys_log_tbl` VALUES ('d316916a13124b1ca4b81b294a122e69', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525472795&page=1&start=0&limit=15&sort=[{\"property\":\"email\",\"direction\":\"DESC\"}]', null, '2016-11-07 21:31:13', '2016-11-07 21:31:13', '1001');
INSERT INTO `sys_log_tbl` VALUES ('d4e3ee1876a64a2498a2c1bd977a367e', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475307968604&page=1&start=0&limit=15', null, '2016-10-01 15:46:09', '2016-10-01 15:46:09', '1001');
INSERT INTO `sys_log_tbl` VALUES ('d68536508855467d83e615deac890327', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475923004902&page=1&start=0&limit=15', null, '2016-10-08 18:36:45', '2016-10-08 18:36:45', '1001');
INSERT INTO `sys_log_tbl` VALUES ('d8018a09434146a38f02c7347c8dfc57', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475315546365&filter=8a42c5e4ae8f443592b082e0c875aeab&page=1&start=0&limit=15', null, '2016-10-01 17:52:26', '2016-10-01 17:52:26', '1001');
INSERT INTO `sys_log_tbl` VALUES ('ddb71079120a49fcab56c29ff5cde9b6', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525372395&page=1&start=0&limit=15&sort=[{\"property\":\"sex\",\"direction\":\"ASC\"}]', null, '2016-11-07 21:29:32', '2016-11-07 21:29:32', '1001');
INSERT INTO `sys_log_tbl` VALUES ('df971313df8a43d8b836bb1ef3f8a7cd', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475208358011&page=1&start=0&limit=15', '', '2016-09-02 00:00:00', '2012-12-09 00:00:00', '1001');
INSERT INTO `sys_log_tbl` VALUES ('e25eb243c5424d51809aeb24aa26aa47', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1478525361288&page=1&start=0&limit=15&sort=[{\"property\":\"sex\",\"direction\":\"ASC\"}]', null, '2016-11-07 21:29:21', '2016-11-07 21:29:21', '1001');
INSERT INTO `sys_log_tbl` VALUES ('e683958bd1274a0084a9f4899f87d0ce', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475308957681&page=1&start=0&limit=15', null, '2016-10-01 16:02:38', '2016-10-01 16:02:38', '1001');
INSERT INTO `sys_log_tbl` VALUES ('eb272de65f3a44e29acd93539b0d7451', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', '', '2016-01-01 00:00:00', '2016-09-30 11:15:24', '1001');
INSERT INTO `sys_log_tbl` VALUES ('eed1f87276a044a89dafb24a40df75e8', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475312978863&page=1&start=0&limit=15', null, '2016-10-01 17:09:39', '2016-10-01 17:09:39', '1001');
INSERT INTO `sys_log_tbl` VALUES ('f03f6b01122343248a13a83aa92e4bb2', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476283690620&page=1&start=0&limit=15', null, '2016-10-12 22:48:11', '2016-10-12 22:48:11', '1001');
INSERT INTO `sys_log_tbl` VALUES ('f04058f8deb74e33a50490b0e74303d3', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922806874&page=1&start=0&limit=15', null, '2016-10-08 18:33:27', '2016-10-08 18:33:27', '1001');
INSERT INTO `sys_log_tbl` VALUES ('f58aee2e5843458cb9aab2f0661601f7', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1477109312727&page=1&start=0&limit=15', null, '2016-10-22 12:08:33', '2016-10-22 12:08:33', '1001');
INSERT INTO `sys_log_tbl` VALUES ('f6409fe342fe403b97bb852f7ffe7446', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475208349543&page=1&start=0&limit=15', null, '2016-09-30 12:05:50', '2016-09-30 12:05:50', '1001');
INSERT INTO `sys_log_tbl` VALUES ('f9c38efa6ddb442885f53a970eff5de7', 'info', '用户管理-用户登入系统', '0:0:0:0:0:0:0:1', '/demo-extjs/login.do', 'POST', 'username=ims&password=&rememberMe=false', null, '2016-09-30 11:31:44', '2016-09-30 11:31:44', '1001');
INSERT INTO `sys_log_tbl` VALUES ('fbeec46e360f4e55ac4d0275191d46d6', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1475922996319&page=1&start=0&limit=15', null, '2016-10-08 18:36:36', '2016-10-08 18:36:36', '1001');
INSERT INTO `sys_log_tbl` VALUES ('fe7814f4a79e4c2eb7da21e5030fd3b6', 'info', '用户管理-查询用户', '0:0:0:0:0:0:0:1', '/demo-extjs/product/system/userManager/read.do', 'POST', '_dc=1476283683535&page=2&start=15&limit=15', null, '2016-10-12 22:48:04', '2016-10-12 22:48:04', '1001');

-- ----------------------------
-- Table structure for `sys_manager`
-- ----------------------------
DROP TABLE IF EXISTS `sys_manager`;
CREATE TABLE `sys_manager` (
  `manager_id` varchar(255) NOT NULL COMMENT '主键',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `in_duty_date` datetime DEFAULT NULL COMMENT '入职日期',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话',
  `salary` float DEFAULT NULL COMMENT '工资',
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_manager
-- ----------------------------
INSERT INTO `sys_manager` VALUES ('2417a8d7-0915-4c82-af8f-608067c2fa05', 'linrx@qq.com', '2015-11-20 08:00:00', 'heihei', '123', '1');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a44080f20001', 'linrx@139.com', '2016-03-17 08:00:00', 'linrx', '123', '12345');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a44081370002', '66@qq.com', '2015-09-14 08:00:00', '功啦', '999', '66666');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a440816e0003', 'W@qq.com', '2015-09-10 08:00:00', '张学友', '1115555555551111111', '800000');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a440821f0004', 'test@qq.com', '2015-12-18 08:00:00', 'test12', '122.0', '10001');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a440824b0005', 'test@qq.com', '2016-10-10 00:00:00', 'test11', '122.0', '9993');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a440827c0006', 'lis@ee.com', '2016-10-06 00:44:00', '李四2', '123333333', '2.55556e15');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a44082ad0007', 'zhagns@dd.com', '2015-09-03 00:00:00', '张三', '0000', '4444');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a44082d10008', 'linrx@139.com', '2016-10-01 00:00:00', 'linrx', '123', '12345');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a44082f30009', '66@qq.com', '2015-09-09 08:00:00', '哈哈哈,', '999', '66666');
INSERT INTO `sys_manager` VALUES ('402881f050a43d8e0150a440831f000a', 'W@qq.com', '2015-09-13 00:00:00', '张学友', '1115555555551111111', '800000');
INSERT INTO `sys_manager` VALUES ('4028a081505b6e9501505b6eebd5000a', 'zhagns@dd.com', '2016-10-01 00:00:00', '张三', '0000', '4444');
INSERT INTO `sys_manager` VALUES ('4028a081505b6e9501505b6eec04000b', 'linrkkkkx@139.com', '2016-03-18 08:00:00', 'linrxdddd', '123', '12345');
INSERT INTO `sys_manager` VALUES ('4028a081505b6e9501505b6eec2c000c', '66@qq.com', '2015-09-18 00:00:00', '哈哈哈,成功啦', '999', '66666');
INSERT INTO `sys_manager` VALUES ('4028a081505b6e9501505b6eec4f000d', 'W@qq.com', '2015-09-13 00:00:00', '张学友', '1115555555551111111', '800000');
INSERT INTO `sys_manager` VALUES ('4d79472c-a6fe-43c7-a182-d8ed6e0bc9e7', 'dd@qq.com', '2015-11-20 08:00:00', 'youhaola', '1234', '123');
INSERT INTO `sys_manager` VALUES ('983939cf-eff5-47da-8060-1cb4e7e1856b', 'lala@qq.com', '2015-11-01 08:00:00', 'lalall', '111', '345');

-- ----------------------------
-- Table structure for `sys_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
  `organization_id` varchar(32) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `organization_pid` varchar(32) DEFAULT NULL,
  `code` varchar(100) DEFAULT NULL,
  `grade` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
INSERT INTO `sys_organization` VALUES ('26433827f1074cf4a6efa604ef85961b', '天津分公司', '公司', '411b079046894f6786e7839721ee68b9', '120000tj', '1');
INSERT INTO `sys_organization` VALUES ('33ac8a93b1ad41c6abba93284073c59f', '销售部', '部门', '26433827f1074cf4a6efa604ef85961b', '', '');
INSERT INTO `sys_organization` VALUES ('411b079046894f6786e7839721ee68b9', '中兴软创', '公司', '#', '01', '');
INSERT INTO `sys_organization` VALUES ('8a42c5e4ae8f443592b082e0c875aeab', '信通部', '部门', '26433827f1074cf4a6efa604ef85961b', '', '');
INSERT INTO `sys_organization` VALUES ('9ef0183787f146cdaeb84cb0a02e4db3', '财务部', '部门', 'a86604e0f89b4d83bd084349eae51967', '', '');
INSERT INTO `sys_organization` VALUES ('a0957981dd9048268df184f8d6376d1f', '北京分公司', '公司', '411b079046894f6786e7839721ee68b9', '110000bj', '');
INSERT INTO `sys_organization` VALUES ('a86604e0f89b4d83bd084349eae51967', '福建分公司', '公司', '411b079046894f6786e7839721ee68b9', '350000fj', '1');
INSERT INTO `sys_organization` VALUES ('ae9e6ece4df94644b2ca0a43e0ac2b5d', '信通部', '部门', 'a0957981dd9048268df184f8d6376d1f', '110000bj01', '');
INSERT INTO `sys_organization` VALUES ('c0d872125de64801915cd83b3271ecc2', '业务部', '部门', 'a0957981dd9048268df184f8d6376d1f', '110000bj02', '1');

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `resource_id` varchar(32) NOT NULL COMMENT '主键',
  `name` varchar(20) DEFAULT NULL COMMENT '资源名称',
  `type` varchar(20) DEFAULT NULL COMMENT '类型',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '父ID',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `order_no` decimal(8,0) DEFAULT NULL COMMENT '排序',
  `permission` varchar(200) DEFAULT NULL COMMENT '权限标识',
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '', '', null, '', '', null, '');
INSERT INTO `sys_resource` VALUES ('100', '内容管理', 'menu', '1', 'product/system/articleManager/index.jsp', '/demo-spring4/common/images/toolbar/toolbar_add.png', null, 'baidu:*afaf');
INSERT INTO `sys_resource` VALUES ('11', '部门管理', 'menu', '1', 'product/agencymonitor2/index.jsp', '', null, 'organization:*');
INSERT INTO `sys_resource` VALUES ('1100', '日志管理', 'menu', '1', 'product/system/logManager/index.jsp', null, null, null);
INSERT INTO `sys_resource` VALUES ('12', '新增', 'button', '11', '', '', null, 'organization:create');
INSERT INTO `sys_resource` VALUES ('13', '修改', 'button', '11', '', '', null, 'organization:update');
INSERT INTO `sys_resource` VALUES ('14', '删除', 'button', '11', '', '', null, 'organization:delete');
INSERT INTO `sys_resource` VALUES ('15', '查看', 'button', '11', '', '', null, 'organization:view');
INSERT INTO `sys_resource` VALUES ('21', '用户管理', 'menu', '1', 'product/system/userManager/index.jsp', null, null, 'user:*');
INSERT INTO `sys_resource` VALUES ('22', '新增', 'button', '21', '', null, null, 'user:create');
INSERT INTO `sys_resource` VALUES ('23', '修改', 'button', '21', '', null, null, 'user:update');
INSERT INTO `sys_resource` VALUES ('24', '删除', 'button', '21', '', null, null, 'user:delete');
INSERT INTO `sys_resource` VALUES ('25', '查看', 'button', '21', '', null, null, 'user:read');
INSERT INTO `sys_resource` VALUES ('31', '资源管理', 'menu', '1', 'product/system/resourceManager/index.jsp', null, null, 'resource:*');
INSERT INTO `sys_resource` VALUES ('32', '资源新增', 'button', '31', '', null, null, 'resource:create');
INSERT INTO `sys_resource` VALUES ('33', '资源修改', 'button', '31', '', null, null, 'resource:update');
INSERT INTO `sys_resource` VALUES ('34', '资源删除', 'button', '31', '', null, null, 'resource:delete');
INSERT INTO `sys_resource` VALUES ('35', '资源查看', 'button', '31', '', null, null, 'resource:view');
INSERT INTO `sys_resource` VALUES ('41', '角色管理', 'menu', '1', 'product/system/roleManager/index.jsp', null, null, 'role:*');
INSERT INTO `sys_resource` VALUES ('42', '角色新增', 'button', '41', '', null, null, 'role:create');
INSERT INTO `sys_resource` VALUES ('43', '角色修改', 'button', '41', '', null, null, 'role:update');
INSERT INTO `sys_resource` VALUES ('43707c2567484efe9a1436661c76808b', '新闻预览', 'menu', '100', 'news/index.do', '', null, 'hello:*');
INSERT INTO `sys_resource` VALUES ('44', '角色删除', 'button', '41', '', null, null, 'role:delete');
INSERT INTO `sys_resource` VALUES ('45', '角色查看', 'button', '41', '', null, null, 'role:view');
INSERT INTO `sys_resource` VALUES ('50', '数据字典', 'menu', '1', 'product/system/dictionaryItemManager/index.jsp', '', null, 'menu:*');
INSERT INTO `sys_resource` VALUES ('5511ab6ccfbb4944a3dbf319032f20fc', 'Manager代码测试', 'menu', '100', 'product/system/goodsManager/index.jsp', '', null, '');
INSERT INTO `sys_resource` VALUES ('60', '连接池管理', 'menu', '1', 'druid/index.html', null, null, 'druid:*');
INSERT INTO `sys_resource` VALUES ('6ab11b5628c44bcf9aa4b41703939178', '文章预览(thymeleaf)', 'menu', '43707c2567484efe9a1436661c76808b', 'thymeleaf/news', '', null, '');
INSERT INTO `sys_resource` VALUES ('83cb3288c67e4826a118c56234ccfd9d', '复制', 'button', '21', '', '', null, 'user:copy');
INSERT INTO `sys_resource` VALUES ('c107c87aee4c441c9c8301e47cce7c83', '3444', 'button', 'd04089a6898d418f97d9524f39c91738', '444', '/demo-extjs/common/images/toolbar/toolbar_add.png', null, '444');
INSERT INTO `sys_resource` VALUES ('d04089a6898d418f97d9524f39c91738', 'Thymeleaf表单绑定', 'menu', '43707c2567484efe9a1436661c76808b', 'thymeleaf/form', '', null, '');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` varchar(32) NOT NULL COMMENT '角色ID',
  `organization_id` varchar(32) DEFAULT NULL COMMENT '机构ID',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  `role` varchar(50) DEFAULT NULL COMMENT '权限标识',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `available` varchar(2) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`role_id`),
  KEY `FK_Relationship_4` (`organization_id`),
  CONSTRAINT `sys_role_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `sys_organization` (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('0001', 'a0957981dd9048268df184f8d6376d1f', '超级管理员', 'root', '超级管理员', '可用');
INSERT INTO `sys_role` VALUES ('0002', 'a0957981dd9048268df184f8d6376d1f', '普通用户', 'user', '普通用户d', '不可');
INSERT INTO `sys_role` VALUES ('0003', 'a0957981dd9048268df184f8d6376d1f', '普通用户001', 'user01', '测试444ddd', '');

-- ----------------------------
-- Table structure for `sys_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `role_id` varchar(32) NOT NULL,
  `resource_id` varchar(32) NOT NULL,
  PRIMARY KEY (`role_id`,`resource_id`),
  KEY `FK_sys_role_resource2` (`resource_id`),
  CONSTRAINT `sys_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`),
  CONSTRAINT `sys_role_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`resource_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('0002', '100');
INSERT INTO `sys_role_resource` VALUES ('0001', '11');
INSERT INTO `sys_role_resource` VALUES ('0002', '11');
INSERT INTO `sys_role_resource` VALUES ('0001', '12');
INSERT INTO `sys_role_resource` VALUES ('0002', '12');
INSERT INTO `sys_role_resource` VALUES ('0001', '13');
INSERT INTO `sys_role_resource` VALUES ('0002', '13');
INSERT INTO `sys_role_resource` VALUES ('0002', '14');
INSERT INTO `sys_role_resource` VALUES ('0002', '15');
INSERT INTO `sys_role_resource` VALUES ('0001', '21');
INSERT INTO `sys_role_resource` VALUES ('0002', '21');
INSERT INTO `sys_role_resource` VALUES ('0001', '22');
INSERT INTO `sys_role_resource` VALUES ('0002', '22');
INSERT INTO `sys_role_resource` VALUES ('0001', '23');
INSERT INTO `sys_role_resource` VALUES ('0002', '23');
INSERT INTO `sys_role_resource` VALUES ('0001', '24');
INSERT INTO `sys_role_resource` VALUES ('0002', '24');
INSERT INTO `sys_role_resource` VALUES ('0001', '25');
INSERT INTO `sys_role_resource` VALUES ('0002', '25');
INSERT INTO `sys_role_resource` VALUES ('0001', '31');
INSERT INTO `sys_role_resource` VALUES ('0002', '32');
INSERT INTO `sys_role_resource` VALUES ('0001', '41');
INSERT INTO `sys_role_resource` VALUES ('0002', '43707c2567484efe9a1436661c76808b');
INSERT INTO `sys_role_resource` VALUES ('0003', '43707c2567484efe9a1436661c76808b');
INSERT INTO `sys_role_resource` VALUES ('0001', '50');
INSERT INTO `sys_role_resource` VALUES ('0001', '60');
INSERT INTO `sys_role_resource` VALUES ('0002', '6ab11b5628c44bcf9aa4b41703939178');
INSERT INTO `sys_role_resource` VALUES ('0003', '6ab11b5628c44bcf9aa4b41703939178');
INSERT INTO `sys_role_resource` VALUES ('0001', '83cb3288c67e4826a118c56234ccfd9d');
INSERT INTO `sys_role_resource` VALUES ('0002', '83cb3288c67e4826a118c56234ccfd9d');
INSERT INTO `sys_role_resource` VALUES ('0002', 'c107c87aee4c441c9c8301e47cce7c83');
INSERT INTO `sys_role_resource` VALUES ('0003', 'c107c87aee4c441c9c8301e47cce7c83');
INSERT INTO `sys_role_resource` VALUES ('0002', 'd04089a6898d418f97d9524f39c91738');
INSERT INTO `sys_role_resource` VALUES ('0003', 'd04089a6898d418f97d9524f39c91738');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `organization_id` varchar(32) DEFAULT NULL COMMENT '机构ID',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机',
  `email` varchar(100) DEFAULT NULL COMMENT '邮件',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人ID',
  PRIMARY KEY (`user_id`),
  KEY `FK_Relationship_3` (`organization_id`),
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`organization_id`) REFERENCES `sys_organization` (`organization_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1001', '33ac8a93b1ad41c6abba93284073c59f', 'ims', 'ims', 'f1eaef26e3617e09c2364d4cdaa390ae', '0', '123456dd', 'ims@163.com', '2016-03-04 10:18:56', null);
INSERT INTO `sys_user` VALUES ('10e29164211446f0864a2f904ce70d32', 'a86604e0f89b4d83bd084349eae51967', 'houzi', '齐天大圣', 'fceafe5db1fd2511ee63049563be0d93', '1', '1ffff', 'dasheng@163.com', '2016-03-04 10:25:53', null);
INSERT INTO `sys_user` VALUES ('d4bbb31c44b24c4790b8a3c08ac22fce', '33ac8a93b1ad41c6abba93284073c59f', 'Name2', 'Jack', null, '', 'ddd', 'dd@qq.com', '2017-04-06 16:21:01', null);
INSERT INTO `sys_user` VALUES ('d8dafb1e65c1432086b1b1a2a0ab278d', null, 'Name1', 'Tom', null, null, null, null, '2017-04-06 16:21:01', null);

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_sys_user_role2` (`role_id`),
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1001', '0001');
INSERT INTO `sys_user_role` VALUES ('10e29164211446f0864a2f904ce70d32', '0001');
INSERT INTO `sys_user_role` VALUES ('1001', '0002');
INSERT INTO `sys_user_role` VALUES ('1001', '0003');

-- ----------------------------
-- Table structure for `sys_user_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tbl`;
CREATE TABLE `sys_user_tbl` (
  `user_id` varchar(32) NOT NULL,
  `orgnization_id` varchar(32) DEFAULT NULL,
  `role_id` varchar(32) DEFAULT NULL,
  `no` varchar(32) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `create_date` date DEFAULT NULL,
  `update_date` date DEFAULT NULL,
  `del_flag` char(1) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_tbl
-- ----------------------------
INSERT INTO `sys_user_tbl` VALUES ('1001', 'ae9e6ece4df94644b2ca0a43e0ac2b5d', '0001', '', 'ims', 'f1eaef26e3617e09c2364d4cdaa390ae', '', 'ims@167.com', '1234567', null, null, '', 'ims');
INSERT INTO `sys_user_tbl` VALUES ('10e29164211446f0864a2f904ce70d32', 'ae9e6ece4df94644b2ca0a43e0ac2b5d', '0001', '0001', '齐天大圣', 'fceafe5db1fd2511ee63049563be0d93', '', '123@qq.com', '123', null, null, '', 'houzi');
INSERT INTO `sys_user_tbl` VALUES ('1ea2efbccb254bdd9637e0ba87b04bd8', 'ae9e6ece4df94644b2ca0a43e0ac2b5d', null, '8888888', '换句话客户客户客户', '123', '', '123@qq.com', '123', null, null, '', 'lrx');
INSERT INTO `sys_user_tbl` VALUES ('2e41dfbfabeb4227bd14d4626451a318', 'ae9e6ece4df94644b2ca0a43e0ac2b5d', null, null, '测试啦', '379cd07a15c55fa7a5c194b3d2c13525', null, '123@qq.com', '123', null, null, null, null);
INSERT INTO `sys_user_tbl` VALUES ('46136e1abc454ce88d05b512de72dbd3', 'c0d872125de64801915cd83b3271ecc2', '0001', null, '123213', '39877611d83a009ee2e45131db728978', null, 'ddd@qq.com', '21321', null, null, null, null);
INSERT INTO `sys_user_tbl` VALUES ('51f075667dc44377a9d6ad5edaa57c35', '010201', '0002', null, '241', 'feed57eadd3bcf51f602cc3fa70b6f1f', null, '142', '421', null, null, null, '142');
INSERT INTO `sys_user_tbl` VALUES ('55ca0414cb544750b43d86fa9b9ac4b1', 'c0d872125de64801915cd83b3271ecc2', '240566259190595584', null, '南京分公司', '03a9e043b0d79f6341418237dec2b010', null, '123@qq.com', '123FFFFFFF', null, null, null, null);
INSERT INTO `sys_user_tbl` VALUES ('5e5eb26c04794a29b3bb45559e507223', '33ac8a93b1ad41c6abba93284073c59f', '0005', null, 'ddd', 'd4e862a3619b8d39dd3ef00501bb8b05', null, 'zhangs@qq.com', 'dddd', null, null, null, null);
INSERT INTO `sys_user_tbl` VALUES ('5fe9036e52c1461b88d7b0c19cb27fdb', 'c0d872125de64801915cd83b3271ecc2', '240566259190595584', '111', '张学友', '123', '', '123@qq.com', '123', null, null, '', null);
INSERT INTO `sys_user_tbl` VALUES ('7579067ad2774ab28bdd711a0b866811', 'ae9e6ece4df94644b2ca0a43e0ac2b5d', null, 'null', '进口哈哈怕', '123', '', '123@qq.com', '123', null, null, '', null);
INSERT INTO `sys_user_tbl` VALUES ('857597259bb143e28e6c5fb5914fca2e', '8a42c5e4ae8f443592b082e0c875aeab', null, null, 'z', 'bd8d070e66b229f9ff8f0a84d52114a9', null, 'zhangs@qq.com', 'd', null, null, null, null);
INSERT INTO `sys_user_tbl` VALUES ('91db655da75641bc889606623a891f6f', '8a42c5e4ae8f443592b082e0c875aeab', '0005', null, 'dddd', 'bd8d070e66b229f9ff8f0a84d52114a9', null, 'zhangs@qq.com', 'd', null, null, null, null);
INSERT INTO `sys_user_tbl` VALUES ('b1bd5284b4b44e938271921bc77f01e4', 'c0d872125de64801915cd83b3271ecc2', '240566259190595584', null, '覃先骁', '9837d891091961834fa4cdbcc0b39740', null, '123@qq.com', '123', null, null, null, null);
INSERT INTO `sys_user_tbl` VALUES ('bf9a2aefa9094acf863ebaf89376c07f', 'ae9e6ece4df94644b2ca0a43e0ac2b5d', '0001', null, 'test', 'cc03e747a6afbbcbf8be7668acfebee5', null, 'test01@qq.com', 'test', null, null, null, 'test');
INSERT INTO `sys_user_tbl` VALUES ('d3104504a447462b8333d74216c409bb', 'ae9e6ece4df94644b2ca0a43e0ac2b5d', null, '', 'dddf', '123', '', '123@qq.com', '123', null, null, '', null);
INSERT INTO `sys_user_tbl` VALUES ('dc4123c8e0c041d1969fe11fdb19bd41', 'c0d872125de64801915cd83b3271ecc2', '240566259190595584', null, '廖旭辉', '0150b5e3bcd99cbb8ee6713d657f4dcb', null, '123@qq.com', '123', null, null, null, null);
INSERT INTO `sys_user_tbl` VALUES ('efb9d5f96c2f4d4585cfefd0088cee91', 'ae9e6ece4df94644b2ca0a43e0ac2b5d', '0001', '110', 'mr.lin', '202cb962ac59075b964b07152d234b70', '', '123@qq.com', '123', null, null, '', 'ljm');
INSERT INTO `sys_user_tbl` VALUES ('efb9d5f96c2f4d4585cfefd0088cee93', 'ae9e6ece4df94644b2ca0a43e0ac2b5d', '0001', '11', '麦林小炮', 'f71ec401082462c5b5c8c0ca66c63ca5', '', '123@qq.com', '123', null, null, '', 'xiaopao');
INSERT INTO `sys_user_tbl` VALUES ('f6c4873f5a724890b0f9749774117a46', 'c0d872125de64801915cd83b3271ecc2', '0001', null, 'zz', 'a5c7b760d87f7254bd516124c673fc3d', null, '123@qq.com', '123ljljlj', null, null, null, null);
INSERT INTO `sys_user_tbl` VALUES ('fd92d726877d4dd791872a474cd4aca6', '9ef0183787f146cdaeb84cb0a02e4db3', '24398910982193152', null, 'zhangxy', 'bd8d070e66b229f9ff8f0a84d52114a9', null, 'ddd@qq.com', 'dd', null, null, null, 'zhangxy');

-- ----------------------------
-- Function structure for `hhh`
-- ----------------------------
DROP FUNCTION IF EXISTS `hhh`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `hhh`(`kkk` int) RETURNS int(11)
BEGIN
	#Routine body goes here...

	RETURN 0;
END
;;
DELIMITER ;
