CREATE TABLE IF NOT EXISTS `team`
(
    `id`        INT          NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(100) NOT NULL,
    `team_abbr` VARCHAR(10),
    `location`  VARCHAR(100),
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `coach`
(
    `id`       INT NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(100),
    `age`      INT,
    `team_id`  INT NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_coach_team` FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE

);

CREATE TABLE IF NOT EXISTS `player`
(
    `id`        INT NOT NULL AUTO_INCREMENT,
    `name`      VARCHAR(100),
    `pos`       ENUM ('PG','SG','PF','SF','C'),
    `age`       INT,
    `team_id`   INT  NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_player_team`  FOREIGN KEY (`team_id`) REFERENCES `team` (`id`) ON DELETE CASCADE
);