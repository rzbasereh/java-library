CREATE DATABASE library;
USE library;

CREATE TABLE `author` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `first_name` varchar(20) DEFAULT NULL,
    `last_name` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `publisher` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `book` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT NULL,
    `publisher_id` int(11) unsigned NOT NULL,
    `author_id` int(11) unsigned NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`publisher_id`) REFERENCES `publisher`(`id`) ON DELETE SET NULL,
    FOREIGN KEY (`author_id`) REFERENCES `author`(`id`) ON DELETE SET NULL
);

