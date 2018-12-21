/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.1.37-community 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `course` (
	`id` bigint (20),
	`pid` bigint (20),
	`is_parent` tinyint (1),
	`text` varchar (96),
	`create_time` datetime ,
	`recent_time` datetime 
); 
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('1','0','1','深圳信息-课程学科',NULL,NULL);
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('2','1','1','软件学院',NULL,NULL);
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('3','2','1','软件技术',NULL,NULL);
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('4','3','1','java方向',NULL,NULL);
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('5','4','1','java编程基础',NULL,NULL);
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('6','2','1','嵌入技术',NULL,'2018-12-12 22:24:25');
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('7','6','1','软件方向',NULL,'2018-12-12 22:23:15');
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('8','7','0','linux基本命令',NULL,NULL);
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('13','11','1','asd','2018-12-12 10:19:16','2018-12-12 10:19:16');
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('14','13','0','asd','2018-12-12 10:19:23','2018-12-12 10:19:23');
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('87','1','1','应用外语学院','2018-12-13 10:49:33','2018-12-13 10:49:33');
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('88','87','0','商务英语','2018-12-13 10:49:43','2018-12-13 10:49:43');
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('89','1','1','体育学院','2018-12-13 10:50:04','2018-12-13 10:50:04');
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('90','89','0','武器学','2018-12-13 10:50:32','2018-12-13 10:50:32');
insert into `course` (`id`, `pid`, `is_parent`, `text`, `create_time`, `recent_time`) values('92','90','0','123123','2018-12-13 10:51:01','2018-12-13 10:51:10');
