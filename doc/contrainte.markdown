# Contrainte

# Table de parametrage

La table existe déjà, elle est utilisé par tout le projet.

  CREATE TABLE `jasmin`.`sys_param` ( `id` INT NOT NULL AUTO_INCREMENT , 
	  
`function_key` INT NOT NULL , 
	  
`int_value` INT NULL , 
	  
`varchar_value_256` VARCHAR(256) NULL , 
	  
`varchar_value_4096` VARCHAR(4096) NOT NULL , 
	  
`blob_value` BLOB NOT NULL , 
	  
`date_time_value` DATETIME NOT NULL , 
	  PRIMARY KEY (`id`), 
	  UNIQUE `key_code` (`key`)) ENGINE = InnoDB; 

