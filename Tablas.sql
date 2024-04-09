USE b7myktzllsfzpoxl0vp8;

CREATE TABLE empresa (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    sector varchar(255) NOT NULL,
    ubicacion varchar(255) NOT NULL,
    contacto varchar(255) NOT NULL
);

CREATE TABLE vacante (
	id INT AUTO_INCREMENT PRIMARY KEY,
    empresa_id INT,
    titulo varchar(255) NOT NULL,
	tecnologia varchar(255),
    descripcion TEXT NOT NULL,
    duracion varchar(255),
    estado varchar(255) NOT NULL,
    FOREIGN KEY (empresa_id) REFERENCES empresa(id) ON DELETE CASCADE
);

CREATE TABLE coder (
	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre varchar(255) NOT NULL,
    apellidos varchar(255) NOT NULL,
    documento varchar(255) NOT NULL UNIQUE,
    cohorte INT NOT NULL,
	clan varchar(255),
    cv TEXT
);

DROP TABLE empresa;

CREATE TABLE contratacion (
	id INT AUTO_INCREMENT PRIMARY KEY,
    vacante_id INT,
    coder_id INT,
    fecha_aplicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado varchar(255),
    salario DECIMAL(10,2),
    FOREIGN KEY (vacante_id) REFERENCES vacante(id) ON DELETE CASCADE,
    FOREIGN KEY (coder_id) REFERENCES coder(id) ON DELETE CASCADE
);

/* Empresa */
INSERT INTO empresa (nombre, sector, ubicacion, contacto) VALUES ("Zenu", "Embutidos", "Acevedo", "32457");
INSERT INTO empresa (nombre, sector, ubicacion, contacto) VALUES ("DELL", "Tecnologia", "EEUU", "dell@gmail.com");

UPDATE empresa SET nombre = "Choriceria Pepito", sector = "Carnicos", 
ubicacion = "Por donde hay un arbol", contacto = "carniceriapepe@gmail.com" WHERE id = 3;

SELECT * FROM empresa;
DELETE FROM empresa WHERE id = 1;

/* Vacante */
INSERT INTO vacante (empresa_id, titulo, descripcion, duracion, estado) 
VALUES (1, "Empacador", "Empacar los terneros en bolsasa", "1 año", "Activo");

INSERT INTO vacante (empresa_id, titulo, tecnologia,descripcion, duracion, estado) 
VALUES (1, "dd", "","dd", "1 año", "Activo");

INSERT INTO vacante (empresa_id, titulo, descripcion, duracion, estado) 
VALUES (2, "Desarrollador", "Ser el dueño de la empresa", "Toda la vida", "Activo");
SELECT * FROM vacante;

SELECT empresa.nombre, vacante.titulo, vacante.descripcion FROM vacante 
INNER JOIN empresa ON empresa_id = vacante.empresa_id;

SELECT * FROM vacante 
INNER JOIN empresa ON empresa.id = vacante.empresa_id;

SELECT * FROM vacante;


UPDATE vacante SET empresa_id = 3, titulo = "Empacador", tecnologia = "Empacar", 
descripcion= "Empacar los terneros en bolsasa", duracion = "Nose", estado = "Activo"
WHERE id = 1;





/* Coder*/
INSERT INTO coder (nombre, apellidos, documento, cohorte, cv) 
VALUES ("Brujilda", "Perez", "1234556", 1, "Hola soy Brujilda");

SELECT * FROM coder;

UPDATE coder SET nombre = "Armando", apellidos = "Casas", documento = "1245368", 
cohorte = 1, clan = ":2)", cv = "HOLA SOY PEPITO" WHERE id = 3;

INSERT INTO contratacion (vacante_id, coder_id, estado, salario) 
VALUES (1, 1, "Activo", 10,0);

SELECT *
FROM vacante
WHERE vacante.titulo LIKE "%e%";


SELECT *
FROM coder
WHERE coder.cohorte LIKE "%1%";

SELECT *
FROM coder
WHERE coder.clan LIKE "%sdsfsf%";



UPDATE coder SET nombre = "Pepito", apellidos = "Perez", documento = "12457800",
cohorte = 2, clan = "sdsfsf", cv = "dsdfsfsdfdsf" WHERE id = 4;  


SELECT * FROM contratacion; 

INSERT INTO contratacion (vacante_id, coder_id, estado, salario) 
VALUES (2, 1, "hola", 10.0);

