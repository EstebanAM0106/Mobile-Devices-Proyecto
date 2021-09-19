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
	imagenURL TEXT NOT NULL,
	
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
	otrasRecomendaciones TEXT,
	
	descripcion TEXT,
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
('Esteban Avila Medina'),
('Leonardo Axel Ortiz Zaragoza');

INSERT INTO plantas(nombre,nombreAlt,imagenURL,reino,division,clase,orden,familia,genero,especie,altura,alturaUnidad,diametro,diametroUnidad,ciclo,cicloUnidad,maceta,luminosidad,otrasRecomendaciones,descripcion,ultimoUsuario) VALUES
	('Cempasúchil',
	'Tagetes erecta',
	'https://upload.wikimedia.org/wikipedia/commons/b/b0/Tagetes_erecta_chendumalli_chedi.jpg',
	'Plantae',
	'Magnoliophyta',
	'Magnoliopsida',
	'Asterales',
	'Asteraceae, Asteroideae',
	'Tagetes',
	'Tagetes erecta',
	'20',
	'cm',
	'15',
	'cm',
	'1',
	'semana',
	false,
	'2',
	'Abono: Época de floración',
	'El tagete es una planta de flor muy llamativa que florece durante todo el verano y desprende un olor intenso que se puede reproducir por semillas. Comprende una variedad de 60 especies y pertenece a la familia de las margaritas (asteraceae).',
	1),
	('Ágave',
	'',
	'https://s28461.pcdn.co/wp-content/uploads/2015/04/Agave-usos-medicinales.jpg',
	'Plantae',
	'Magnoliophyta',
	'Liliopsida, Liliidae',
	'Asparagales',
	'Asparagaceae, Agavoideae',
	'Agave',
	'Agave tequilana',
	'90','cm',
	'1','m',
	'20','dias',
	false,
	'2',
	'Temperatura: 10-30 ºC. Se suele cultivar en exterior, donde alcanza los 2 metros, pero también puede cultivarse en maceta. En ese caso, el tamaño es mucho más reducido.',
	'La pita es una planta crasa que resiste muy bien la sequía y los terrenos áridos. Sus hojas, largas, grandes, duras y carnosas salen desde el suelo formando una roseta. En los bordes, tienen púas agudas de unos 2 cm. de longitud. y, en el extremo, una espina gruesa y dura. Como todos los ágaves, florece una sola vez en su vida y muere tras esa floración (monocarpismo).',
	2),
	('Dalia',
	'Dahlia',
	'https://t1.ev.ltmcdn.com/es/posts/0/6/4/cuidados_de_las_dalias_2460_orig.jpg',
	'Plantae',
	'Magnoliophyta',
	'Magnoliopsida, Magnoliopsida',
	'Asterales',
	'Asteraceae, Asteroideae',
	'Coreopsideae',
	'Dahlia',
	'40','cm',
	'20','cm',
	'4','dias',
	true,
	'2',
	'Abono: para plantas de flor. Conviene realizar la tarea del abonado antes de su cultivo o preferiblemente durante el invierno cuando pierde las hojas.',
	'Sus llamativas flores emergen de entre su decorativo follaje sujetas por tallos delgados de mediados de verano hasta las primeras heladas otoñales.',
	3);
/*
	('nombre',
	'nombrealt',
	'url',
	'reino',
	'divison',
	'clase',
	'orden',
	'familia',
	'genero',
	'especie',
	'10','cm',
	'7','cm',
	'5','dias',
	false,
	'0',
	'otrasRecomendaciones',
	'descripcion',
	1),
	*/
INSERT INTO favoritos(usuarioId,plantaId) VALUES
(1,1),
(1,3),
(2,2);
--hey