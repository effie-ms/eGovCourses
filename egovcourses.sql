-- Database: egovcourses

--
-- Table structure for table `authority`
--
CREATE TABLE `authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
);

--
-- Table structure for table `course`
--
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `course_title` varchar(255) NOT NULL,
  `course_description` varchar(255) DEFAULT NULL,
  `course_price` bigint(20) DEFAULT NULL,
  `course_level` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `lesson`
--
CREATE TABLE `lesson` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lesson_title` varchar(255) NOT NULL,
  `lesson_description` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lesson_course_id` (`course_id`),
  CONSTRAINT `fk_lesson_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`) ON DELETE CASCADE
);

--
-- Table structure for table `resource`
--
CREATE TABLE `resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(255) DEFAULT NULL,
  `resource_description` varchar(255) DEFAULT NULL,
  `resource_url` varchar(255) DEFAULT NULL,
  `resource_type` varchar(255) DEFAULT NULL,
  `lesson_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_resource_lesson_id` (`lesson_id`),
  CONSTRAINT `fk_resource_lesson_id` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE CASCADE
);

--
-- Table structure for table `task`
--
CREATE TABLE `task` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_title` varchar(255) DEFAULT NULL,
  `task_description` varchar(255) DEFAULT NULL,
  `lesson_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_task_lesson_id` (`lesson_id`),
  CONSTRAINT `fk_task_lesson_id` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`) ON DELETE CASCADE
);

--
-- Table structure for table `user`
--
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(254) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(6) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `created_date` timestamp NOT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`)
);

--
-- Table structure for table `user_authority`
--
CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

INSERT INTO `authority` VALUES ('ROLE_ADMIN'),('ROLE_USER');
INSERT INTO `course` VALUES (5,'Information Society Principles: towards e-Governance','ITE4110',0,'NOVICE'),(6,'Legal Framework of e-Governance','HOE7210',0,'NOVICE'),(7,'Introduction to IT and e-Governance','ITE4120',0,'NOVICE'),(8,'Digital Transformation of Government','MNA5410',0,'BEGINNER'),(9,'Impact and Measurement of e-Governance','ITE4240',0,'BEGINNER'),(10,'Creating Innovative Capacities in Government','MNA5420',0,'BEGINNER'),(11,'e-Governance and e-Democracy','MNE5210',0,'INTERMEDIATE');
INSERT INTO `lesson` VALUES (8,'Introductory lecture','','ENGLISH',6),(9,'Digital Identity',NULL,'ENGLISH',6),(10,'Artificial Intelligence and legislation',NULL,'ENGLISH',6),(11,'Privacy and Data Protection Online',NULL,'ENGLISH',6),(12,'European free flow of data initiative: the fifth freedom',NULL,'ENGLISH',6);
INSERT INTO `resource` VALUES (4,'X-Road','Official information regarding Estonian Data Exchange Layer from Estonian Information System Authority','https://www.ria.ee/en/state-information-system/x-tee.html','PAGE',8);
INSERT INTO `task` VALUES (6,'Task 1',NULL,8),(7,'Task 2',NULL,8),(8,'Task 3',NULL,8);
INSERT INTO `user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost','',_binary '','en',NULL,NULL,'system','2018-11-16 20:28:01',NULL,'admin','2018-11-22 11:12:48'),(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost','',_binary '','en',NULL,NULL,'system','2018-11-16 20:28:01',NULL,'system',NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost','',_binary '','en',NULL,NULL,'system','2018-11-16 20:28:01',NULL,'system',NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost','',_binary '','en',NULL,NULL,'system','2018-11-16 20:28:01',NULL,'system',NULL));
INSERT INTO `user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(10,'ROLE_USER'),(11,'ROLE_USER');

