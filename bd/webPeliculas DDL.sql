DROP TABLE PERSONA CASCADE CONSTRAINTS;
DROP TABLE USUARIO CASCADE CONSTRAINTS;
DROP TABLE ADMINISTRADOR CASCADE CONSTRAINTS;

CREATE TABLE PERSONA (
    id_persona NUMBER PRIMARY KEY,
    nombre VARCHAR(100),
    contrasenia VARCHAR(100),
    tipo VARCHAR(1) CHECK (tipo IN ('A', 'U')),
    baneado NUMBER(1,0)
);


INSERT INTO persona (id_persona, nombre, contrasenia,tipo,baneado) VALUES (1, 'pepe', '1234','U',0);
INSERT INTO persona (id_persona, nombre, contrasenia,tipo,baneado) VALUES (2, 'manolo', '2345','U',1);
INSERT INTO persona (id_persona, nombre, contrasenia,tipo,baneado) VALUES (3, 'jose', '7892','U',0);

INSERT INTO persona (id_persona, nombre, contrasenia,tipo,baneado) VALUES (4, 'Admin1', '7892','A',0);


SELECT id_persona, nombre, contrasenia, tipo, baneado FROM PERSONA;

//SELECT DNI, NOMBRE, SALARIO FROM Empleados;