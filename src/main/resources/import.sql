INSERT INTO categorias (id, tipo_categoria) VALUES (9,'muebles');

/*MEMBRESIAS*/
INSERT INTO membresias (id, suscripcion, dias_vigencia) VALUES (3, 'Membresia express', 60);
INSERT INTO membresias (id, suscripcion, dias_vigencia) VALUES (2, 'Membresia premium', 36);
INSERT INTO membresias (id, suscripcion, dias_vigencia) VALUES (1, 'Membresia inicial', 30);

/*VIGENCIA MEMBRESIAS*/
INSERT INTO vigencias (id, membresia) VALUES (1, 1);


INSERT INTO roles (nombre) VALUES ('ROLE_EMPRESA_INICIAL');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMINISTRADOR');
INSERT INTO roles (nombre) VALUES ('ROLE_EMPRESA');


/*ADMINISTRADOR*/
INSERT INTO usuarios (id, user_name, password, enabled) VALUES (1, 'administrador', '$2a$10$Iq0fiDc78iDUv9d96J3Tle9cTxw.YXkt6f2KB1L1Cv/l6m/ybGMti', true);
INSERT INTO usuarios (id, user_name, password, enabled) VALUES (2, 'santiago', '$2a$10$7J3V0SafXcOeRtwSYqfOE.5rL76qttDJspXcp0HJ7iajUJ.irzgt2', true);
INSERT INTO usuarios (id, user_name, password, enabled) VALUES (3, 'camilo', '$2a$10$7J3V0SafXcOeRtwSYqfOE.5rL76qttDJspXcp0HJ7iajUJ.irzgt2', true);

/*ASOCIACION USUARIOS ROLES */
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 2);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 2);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (3, 2);

