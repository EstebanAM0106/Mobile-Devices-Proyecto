drop table if exists plantas cascade;
drop table if exists favoritos cascade;
drop table if exists usuarios cascade;
CREATE TABLE usuarios
(
	usuarioId SERIAL PRIMARY KEY NOT NULL,
	nombre VARCHAR(30) NOT NULL
);

CREATE TABLE plantas
(
	plantaId SERIAL PRIMARY KEY NOT NULL,
	nombre VARCHAR(50) NOT NULL,
	nombreAlt VARCHAR(50),
	imagenURL VARCHAR(500) NOT NULL,
	
	reino VARCHAR(20) NOT NULL,
	division VARCHAR(30) NOT NULL,
	clase VARCHAR(30) NOT NULL,
	orden VARCHAR(30) NOT NULL,
	familia VARCHAR(30) NOT NULL,
	genero VARCHAR(30) NOT NULL,
	especie VARCHAR(30) NOT NULL,
	
	altura INT NOT NULL,
	alturaUnidad VARCHAR(10) NULL,
	diametro INT NOT NULL,
	diametroUnidad VARCHAR(10) NULL,
	ciclo INT NOT NULL,
	cicloUnidad VARCHAR(10) NULL,
	maceta boolean NOT NULL,
	luminosidad INT NOT NULL,
	otrasRecomendaciones VARCHAR(1500),
	
	descripcion VARCHAR(2000),
	ultimoUsuario INT NOT NULL,
	
	FOREIGN KEY (ultimoUsuario) references usuarios(usuarioId) on delete cascade on update cascade
);

CREATE TABLE favoritos
(
	usuarioId int NOT NULL,
	plantaId int NOT NULL,
	FOREIGN KEY (usuarioId) references usuarios(usuarioId) on delete cascade on update cascade,
	FOREIGN KEY (plantaId) references plantas(plantaId) on delete cascade on update cascade
);

INSERT INTO usuarios(nombre) VALUES
('Marco Lagunes Garcia'),
('Esteban Avila Medina');

INSERT INTO plantas(nombre,nombreAlt,imagenURL,reino,division,clase,orden,familia,genero,especie,altura,alturaUnidad,diametro,diametroUnidad,ciclo,cicloUnidad,maceta,luminosidad,otrasRecomendaciones,descripcion,ultimoUsuario) VALUES
('Planta Carnivora','Venus Atrapamoscas','https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Venus_Flytrap_showing_trigger_hairs.jpg/439px-Venus_Flytrap_showing_trigger_hairs.jpg','reino','divison','clase','orden','familia','genero','especie','10','cm','7','cm','5','dias',true,'1','Alimentar cada semana con insectos pequeños.','Dionaea muscipula es la única especie del género monotípico Dionaea.',1),
('Planta Carnivora','Venus Atrapamoscas','https://upload.wikimedia.org/wikipedia/commons/thumb/3/37/Venus_Flytrap_showing_trigger_hairs.jpg/439px-Venus_Flytrap_showing_trigger_hairs.jpg','reino','divison','clase','orden','familia','genero','especie','10','cm','7','cm','5','dias',true,'1','Alimentar cada semana con insectos pequeños.','Dionaea muscipula es la única especie del género monotípico Dionaea.',1);

INSERT INTO favoritos(usuarioId,plantaId) VALUES
(1,1),
(1,2),
(2,2);