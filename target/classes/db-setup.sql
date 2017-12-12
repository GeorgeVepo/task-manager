CREATE DATABASE IF NOT EXISTS task_manager;

USE task_manager;

CREATE TABLE `TaskEntity` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`titulo` varchar(255) NOT NULL,
`status` varchar(255) NOT NULL,
`descricao` varchar(800) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;