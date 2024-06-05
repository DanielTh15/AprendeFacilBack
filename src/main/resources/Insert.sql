INSERT INTO ASIGNATURA (nombre) VALUES
('Matemáticas'),
('Física'),
('Literatura');

-- Insertar datos en la tabla CURSO
INSERT INTO CURSO (nombre, descripcion, id_asignatura, calificacion) VALUES
('Álgebra Básica', 'Curso introductorio sobre álgebra.', 1, 85.50),
('Mecánica Clásica', 'Fundamentos de la mecánica clásica.', 2, 90.00),
('Poesía Contemporánea', 'Análisis de poesía contemporánea.', 3, 88.75);

-- Insertar datos en la tabla USUARIO
INSERT INTO USUARIO (id, nombre, apellido, bloqueado, deshabilitado, contraseña, correo, telefono) VALUES
('user01', 'Ana', 'García', false, false, 'password1', 'ana.garcia@example.com', '123456789'),
('user02', 'Luis', 'Martínez', false, true, 'password2', 'luis.martinez@example.com', '987654321'),
('user03', 'María', 'López', true, false, 'password3', 'maria.lopez@example.com', '456123789');

-- Insertar datos en la tabla INSCRIPCION
INSERT INTO INSCRIPCION (id_usuario, id_curso) VALUES
('user01', 1,'2024-06-02'),
('user02', 2, '2024-06-02'),
('user03', 3,'2024-06-02');

-- Insertar datos en la tabla TEMA
INSERT INTO TEMA (id_curso, nombre, contenido, recursos) VALUES
(1, 'Introducción al Álgebra', 'Conceptos básicos de álgebra.', 'libro1.pdf, video1.mp4'),
(2, 'Cinemática', 'Estudio del movimiento.', 'libro2.pdf, video2.mp4'),
(3, 'Poetas Modernos', 'Análisis de poetas contemporáneos.', 'libro3.pdf, video3.mp4');

-- Insertar datos en la tabla PREGUNTA
INSERT INTO PREGUNTA (tipo_pregunta, enunciado, id_tema) VALUES
('Cerrada', '¿Cuál es el valor de x en la ecuación 2x + 3 = 7?', 1),
('Abierta', 'Describa el movimiento de un objeto en caída libre.', 2),
('Cerrada', '¿Quién es el autor del poema "A un olmo seco"?', 3);

-- Insertar datos en la tabla PRE_CERRADA
INSERT INTO PRE_CERRADA (id_pregunta, opcion, es_correcta) VALUES
(1, 'x = 2', true),
(1, 'x = 3', false),
(1, 'x = 4', false),
(3, 'Antonio Machado', true),
(3, 'Federico García Lorca', false),
(3, 'Pablo Neruda', false);

-- Insertar datos en la tabla PRE_ABIERTA
INSERT INTO PRE_ABIERTA (id_pregunta, respuesta_correcta) VALUES
(2, 'El objeto acelera hacia abajo debido a la gravedad.');

-- Insertar datos en la tabla ROL
INSERT INTO ROL (rol, id_usuario) VALUES
('Estudiante', 'user01'),
('Profesor', 'user02'),
('Administrador', 'user03');