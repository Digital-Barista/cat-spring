CREATE TABLE client (
client_id bigint(20) NOT NULL AUTO_INCREMENT,
name varchar(64) NOT NULL,
contact_name varchar(128) DEFAULT NULL,
contact_email varchar(128) DEFAULT NULL,
contact_phone varchar(32) DEFAULT NULL,
active tinyint(1) NOT NULL DEFAULT '1',
PRIMARY KEY (client_id))
ENGINE=InnoDB;