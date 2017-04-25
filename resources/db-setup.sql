CREATE DATABASE IF NOT EXISTS task_manager;

CREATE TABLE `taskentity` (
`id` bigint(20) NOT NULL AUTO_INCREMENT,
`titulo` varchar(255) DEFAULT NULL,
`status` varchar(255) DEFAULT NULL,
`descricao` varchar(800) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;