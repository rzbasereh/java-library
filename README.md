# Java Library App

## Setup

You need to first run `docker-compose` that place in root of repository to set up mysql db.

Create `java_db` by this query:

```mysql
CREATE DATABASE java_library
```

And use this database by:
```mysql
USE java_library
```

Then create tables in this database for domain models by these queries:

```mysql
CREATE TABLE `Author` (
    `id` int(11) unsigned NOT NULL,
    `firstname` varchar(20) DEFAULT NULL,
    `lastname` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
```

```mysql
CREATE TABLE `Publisher` (
    `id` int(11) unsigned NOT NULL,
    `name` varchar(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
```

```mysql
CREATE TABLE `Book` (
    `id` int(11) unsigned NOT NULL,
    `publisherId` int(11) unsigned NOT NULL,
    `authorId` int(11) unsigned NOT NULL,
    PRIMARY KEY (`id`)
);
```
