# Java Library App

## Setup

You need to first run `docker-compose` that place in root of repository to set up mysql db.

Create `java_db` by this query:

```mysql
CREATE DATABASE library
```

And use this database by:
```mysql
USE library
```

Then create tables in this database for domain models by these queries:

```mysql
CREATE TABLE `author` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `first_name` varchar(20) DEFAULT NULL,
    `last_name` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
```

```mysql
CREATE TABLE `publisher` (
    `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
```

```mysql
CREATE TABLE `book` (
                        `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
                        `name` varchar(20) DEFAULT NULL,
                        `publisher_id` int(11) unsigned NOT NULL,
                        `author_id` int(11) unsigned NOT NULL,
                        PRIMARY KEY (`id`),
                        FOREIGN KEY (`publisher_id`) REFERENCES `publisher`(`id`),
                        FOREIGN KEY (`author_id`) REFERENCES `author`(`id`)
);
```
