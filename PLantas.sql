CREATE DATABASE Plantas;

CREATE TABLE Planta
(
  ID_planta INT NOT NULL,
  Altura INT NOT NULL,
  Diametro INT NOT NULL,
  Descripcion VARCHAR(300) NOT NULL,
  Favorito BIT NOT NULL,
  URL_imagen VARCHAR(150) NOT NULL,
  PRIMARY KEY (ID_planta)
);

CREATE TABLE Taxonomia
(
  Reino VARCHAR(20) NOT NULL,
  Division VARCHAR(30) NOT NULL,
  Clase VARCHAR(30) NOT NULL,
  Orden VARCHAR(30) NOT NULL,
  Familia VARCHAR(20) NOT NULL,
  Genero VARCHAR(20) NOT NULL,
  Especie VARCHAR(30) NOT NULL,
  ID_planta INT NOT NULL,
  FOREIGN KEY (ID_planta) REFERENCES Planta(ID_planta)
);

CREATE TABLE Recomendaciones
(
  Ciclo INT NOT NULL,
  Unidad_ciclo VARCHAR(10) NOT NULL,
  Maceta BIT NOT NULL,
  Luminosidad INT NOT NULL,
  Otras_ VARCHAR(300) NOT NULL,
  ID_planta INT NOT NULL,
  FOREIGN KEY (ID_planta) REFERENCES Planta(ID_planta)
);
