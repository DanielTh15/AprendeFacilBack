CREATE TABLE ASIGNATURA (
    id serial PRIMARY KEY,
    nombre varchar(100)
);

CREATE TABLE CURSO (
    id serial PRIMARY KEY,
    nombre varchar(100) NULL,
    descripcion text NULL,
    id_asignatura int NOT NULL,
    calificacion decimal(5,2) NOT NULL,
    FOREIGN KEY (id_asignatura) REFERENCES ASIGNATURA(id)
);

CREATE TABLE USUARIO (
    id varchar(20) NOT NULL PRIMARY KEY,
    nombre varchar(100) NULL,
    apellido varchar(100) NULL,
    bloqueado boolean NULL,
    deshabilitado boolean NULL,
    contraseña varchar(500) NULL,
    correo varchar(50) NULL,
    telefono varchar(20) NULL
);

CREATE TABLE INSCRIPCION (
    id_usuario varchar(20) NOT NULL,
    id_curso int NOT NULL,
    fecha date not null,
    PRIMARY KEY (id_usuario, id_curso),
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id),
    FOREIGN KEY (id_curso) REFERENCES CURSO(id)
);

CREATE TABLE TEMA (
    id serial PRIMARY KEY,
    id_curso int NOT NULL,
    nombre varchar(100) NULL,
    contenido text NULL,
    recursos varchar(500) NULL,
    FOREIGN KEY (id_curso) REFERENCES CURSO(id)
);

CREATE TABLE PREGUNTA (
    id serial PRIMARY KEY,
    tipo_pregunta varchar(100),
    enunciado text NULL,
    id_tema int NOT NULL,
    FOREIGN KEY (id_tema) REFERENCES TEMA(id)
);

CREATE TABLE PRE_CERRADA (
    id serial PRIMARY KEY,
    id_pregunta int NOT NULL,
    opcion varchar(100),
    es_correcta boolean,
    FOREIGN KEY (id_pregunta) REFERENCES PREGUNTA(id)
);

CREATE TABLE PRE_ABIERTA (
    id serial PRIMARY KEY,
    id_pregunta int NOT NULL,
    respuesta_correcta varchar(100),
    FOREIGN KEY (id_pregunta) REFERENCES PREGUNTA(id)
);

CREATE TABLE ROL (
    id serial NOT NULL PRIMARY KEY,
    rol varchar(100) NULL,
    id_usuario varchar(20) NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES USUARIO(id)
);
