-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- 主机: localhost
-- 生成日期: 2016 年 08 月 14 日 02:13
-- 服务器版本: 5.1.53
-- PHP 版本: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `food`
--

-- --------------------------------------------------------

--
-- 表的结构 `food`
--

CREATE TABLE IF NOT EXISTS `food` (
  `food_id` int(11) NOT NULL AUTO_INCREMENT,
  `foodname` varchar(100) NOT NULL,
  `price` double(5,0) NOT NULL,
  `intro` varchar(100) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `shop_id` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `recommand` int(11) DEFAULT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=15 ;

--
-- 转存表中的数据 `food`
--

INSERT INTO `food` (`food_id`, `foodname`, `price`, `intro`, `pic`, `shop_id`, `type_id`, `recommand`) VALUES
(1, '酸菜鱼', 23, '地道的川菜', 'http://i3.meishichina.com/attachment/recipe/201203/p320_201203302229311333492101.JPG', 1, 1, 1),
(2, '口水鸡', 20, '用健康的鸡肉做成', 'http://images.meishij.net/p/20100714/3ad48d2a0d31630f800c40c4e53266b7.jpg', 1, 1, 1),
(3, 'fish', 22, '外国菜', '', 2, 2, 1),
(14, '白灼虾', 35, '好吃', 'https://ss1.baidu.com/6ONXsjip0QIZ8tyhnq/it/u=239127283,1099924372&fm=58', 2, 2, 1),
(4, '毛血旺', 21, '辣的很爽', 'http://img2.imgtn.bdimg.com/it/u=1335413898,1555320128&fm=21&gp=0.jpg', 1, 1, 1),
(5, '乱炖', 18, '香香浓浓的东北味道！', 'http://images.meishij.net/p/20100714/3ad48d2a0d31630f800c40c4e53266b7.jpg', 2, 2, 1),
(6, '五花肉', 25, '不肥不腻', 'http://www.eat4.com/upimg/120308/1_005427_1.jpg', 2, 2, NULL),
(7, '麻辣豆腐', 12, '麻辣豆腐', '', 1, 0, 1),
(8, 'test', 22, '11', '', 5, 0, 1),
(10, '酸菜丸子', 23, '东北口味的丸子', '', 3, 0, 1),
(11, '糖醋丸子', 18, '糖醋口味', 'http://site.meishij.net/r/121/108/3214621/s3214621_142918748255966.jpg', 6, 0, 1),
(12, '烤鱼', 40, '两人份，改善生活吧', '', 1, 1, 1);

-- --------------------------------------------------------

--
-- 表的结构 `foodtype`
--

CREATE TABLE IF NOT EXISTS `foodtype` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(100) NOT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- 转存表中的数据 `foodtype`
--

INSERT INTO `foodtype` (`type_id`, `typename`) VALUES
(1, '麻辣'),
(2, '海鲜'),
(3, '面食'),
(4, '东北炖菜'),
(5, '川菜'),
(6, '炒菜'),
(7, '糕点'),
(8, '湘菜');

-- --------------------------------------------------------

--
-- 表的结构 `shop`
--

CREATE TABLE IF NOT EXISTS `shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `shopname` varchar(100) NOT NULL,
  `address` varchar(100) NOT NULL,
  `phonenum` varchar(100) NOT NULL,
  `intro` varchar(100) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `level` int(5) DEFAULT NULL,
  PRIMARY KEY (`shop_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `shop`
--

INSERT INTO `shop` (`shop_id`, `shopname`, `address`, `phonenum`, `intro`, `pic`, `comment`, `level`) VALUES
(1, '满口香川菜2', '东软食堂三期', '12345678901', '地道的川菜', 'http://img3.redocn.com/tupian/20141126/xiangxiwaipocai_3613936.jpg', NULL, 5),
(2, '东北一家人', '东软食堂三期', '12345678902', '相亲相爱的一家人，快来尝尝咱东北菜。', 'http://picapi.ooopic.com/01/43/03/11b1OOOPIC53.jpg', '', 2),
(3, '姐妹麻辣汤', '东软食堂二期', '12345678903', '麻辣烫', 'http://img3.imgtn.bdimg.com/it/u=1220109938,2935718899&fm=21&gp=0.jpg', NULL, 4),
(5, '西湖印象', '东软食堂二期', '12345678905', '杭州口味', 'http://cpic2.edushi.com/cn/hz/zh-chs/LocalInfo/Companys/170265/Windows/Photo20081113162305.jpg', NULL, 5),
(6, '丸子铺', '东软三期食堂', '12345678906', '很好吃的丸子', 'http://img1.imgtn.bdimg.com/it/u=303217830,3993092591&fm=21&gp=0.jpg', NULL, 4);

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL,
  `userpass` varchar(100) NOT NULL,
  `mobilenum` varchar(100) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`user_id`, `username`, `userpass`, `mobilenum`, `address`, `comment`) VALUES
(1, 'lnn', '6512bd43d9caa6e02c990b0a82652dca', '12345678900', '中国大连', '测试'),
(2, 'test', 'e10adc3949ba59abbe56e057f20f883e', '12345678900', 'dalian', 'ok'),
(3, 'lnn2', 'e10adc3949ba59abbe56e057f20f883e', '12345678900', 'dalian', 'test');

-- --------------------------------------------------------

--
-- 表的结构 `user_collect`
--

CREATE TABLE IF NOT EXISTS `user_collect` (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `shop_id` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `collect_date` datetime DEFAULT NULL,
  `flag` int(11) NOT NULL,
  PRIMARY KEY (`collect_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=41 ;

--
-- 转存表中的数据 `user_collect`
--

INSERT INTO `user_collect` (`collect_id`, `user_id`, `shop_id`, `food_id`, `collect_date`, `flag`) VALUES
(35, 1, 2, NULL, '2016-04-10 00:00:00', 0),
(24, 2, 2, NULL, '2016-04-08 00:00:00', 0),
(33, 1, NULL, 5, '2016-04-10 00:00:00', 1),
(37, 1, 6, NULL, '2016-04-13 20:51:12', 0),
(38, 1, NULL, 6, '2016-04-17 12:25:13', 1),
(40, 3, NULL, 5, '2016-05-02 18:40:19', 1);

-- --------------------------------------------------------

--
-- 表的结构 `user_order`
--

CREATE TABLE IF NOT EXISTS `user_order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `food_id` int(11) NOT NULL,
  `num` int(11) NOT NULL,
  `sum` double NOT NULL,
  `ordertime` datetime NOT NULL,
  `suggesttime` varchar(50) DEFAULT NULL,
  `content` varchar(200) DEFAULT NULL,
  `comment_time` date DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- 转存表中的数据 `user_order`
--

INSERT INTO `user_order` (`order_id`, `user_id`, `food_id`, `num`, `sum`, `ordertime`, `suggesttime`, `content`, `comment_time`) VALUES
(1, 1, 1, 1, 23, '2016-04-09 00:00:00', NULL, 'sichuang', '2016-04-10'),
(2, 1, 3, 1, 15, '2016-04-10 00:00:00', NULL, 'fish', '2016-04-10'),
(3, 1, 1, 1, 23, '2016-04-10 00:00:00', NULL, NULL, NULL),
(4, 1, 5, 2, 36, '2016-04-10 00:00:00', NULL, NULL, NULL),
(5, 2, 3, 1, 15, '2016-04-10 00:00:00', NULL, 'hahaha', '2016-04-10'),
(6, 1, 11, 1, 18, '2016-04-13 20:54:05', NULL, 'test-test', '2016-05-02'),
(7, 1, 1, 1, 23, '2016-04-16 10:11:03', '2011-00-13 00:00:00', NULL, NULL),
(8, 1, 1, 1, 23, '2016-04-16 10:14:43', '11:00-13:00', NULL, NULL),
(9, 3, 6, 1, 25, '2016-05-02 18:49:55', '10:30-11:00', 'delicious', '2016-05-02');
