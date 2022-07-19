INSERT INTO representante_legal (id, tipo_documento, documento, nombre, apellidos, celular, email) VALUES (1, 'CC','1007285862', 'santiago', 'ocampo jimenez', '3105332562', 'santiagocampo163@gmail.com');
INSERT INTO representante_legal (id, tipo_documento, documento, nombre, apellidos, celular, email) VALUES (2, 'CC','15389649', 'yojan', 'ocampo jimenez', '3136199907', 'yojan@gmail.com');

INSERT INTO categorias (id, tipo_categoria) VALUES (9,'muebles');

/*MEMBRESIAS*/
INSERT INTO membresias (id, suscripcion, dias_vigencia) VALUES (3, 'Membresia express', 60);
INSERT INTO membresias (id, suscripcion, dias_vigencia) VALUES (2, 'Membresia premium', 36);
INSERT INTO membresias (id, suscripcion, dias_vigencia) VALUES (1, 'Membresia inicial', 30);

/*VIGENCIA MEMBRESIAS*/
INSERT INTO vigencias (id, membresia) VALUES (1, 1);

/*REGISTROS*/
INSERT INTO registros (id, actividad_principal, celular, codigo_ciu,  email, nit, nombre_empresa, razon_social,  tipo_persona) VALUES (1, 'comercio','3105332562', '1256', 'santiagocampo163@gmail.com', '1525','IES','IES', 'Natural');

INSERT INTO roles (nombre) VALUES ('ROLE_EMPRESA_INICIAL');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMINISTRADOR');
INSERT INTO roles (nombre) VALUES ('ROLE_EMPRESA');

/*USUARIO*/
INSERT INTO usuarios (id, user_name, password, enabled) VALUES (1, 'IES', '$2a$10$Iq0fiDc78iDUv9d96J3Tle9cTxw.YXkt6f2KB1L1Cv/l6m/ybGMti', true);

/*EMPRESA*/
INSERT INTO empresas (usuario_id, nombre, nit, razon_social, codigo_ciu, actividad_principal, tipo_persona, correo, celular, representante_legal, membresia, vigencia) VALUES (1, 'IES', '12345', 'Tecnología', '12345', 'Tecnología', 'jurídica', 'santiagocampo163@gmail.com', '3105332562', 1, 1, 1);

/*ADMINISTRADOR*/
INSERT INTO usuarios (id, user_name, password, enabled) VALUES (2, 'admin', '$2a$10$MHh1i23IgWnnsLD5HB/a3e69GOmMCDRGb/xWn7DnIhDJ06yJMeE8q', true);

/*ASOCIACION USUARIOS ROLES */
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO usuarios_roles (usuario_id, rol_id) VALUES (2, 2);

