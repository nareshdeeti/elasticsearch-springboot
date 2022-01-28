CREATE TABLE sugea.Laptop (
	laptop_id BIGINT auto_increment NOT NULL,
	laptop_model varchar(100) NULL,
	brand varchar(100) NULL,
	CONSTRAINT Laptop_pk PRIMARY KEY (laptop_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;


INSERT INTO sugea.laptop (laptop_model, brand) VALUES(12, 'r542u', 'asus');
