create table usuario(
correo varchar(100),
nombre varchar(100),
contraseña varchar(200),
primary key (correo)
);


create table notificacion(
notificacionID int,
usuario varchar(100),
fecha date,
informacion varchar(200),
primary key (notificacionID, usuario),
foreign key (usuario) references usuario(correo)
);

create table proyecto(
proyectoID int,
nombre varchar(50),
creador varchar(100),
descripcion varchar(200),
fechaDeCreacion date,
primary key (proyectoID),
foreign key (creador) references usuario(correo)
);

create table participante(
usuario varchar(100),
proyecto int,
primary key (usuario, proyecto),
foreign key (usuario) references usuario(correo)
/*foreign key (proyecto) references proyecto(proyectoID)*/
);

create table mensaje(
usuario varchar(100),
proyecto int,
fecha date,
contenido varchar(200),
primary key (usuario, proyecto),
foreign key (usuario) references usuario(correo)
/*foreign key (proyecto) references proyecto(proyectoID)*/
);

create table invitacion(
remitente varchar(100),
receptor varchar(100),
proyecto int,
primary key (remitente, receptor, proyecto),
foreign key (remitente) references usuario(correo),
foreign key (receptor) references usuario(correo)
/*foreign key (proyecto) references proyecto(proyectoID)*/
);

create table rama(
ramaID int,
nombre varchar(50),
proyecto int,
ramaPadre int,
fechaDeCreacion date,
creador varchar(100),
primary key (ramaID),
/*foreign key (proyecto) references proyecto(proyectoID),*/
foreign key (ramaPadre) references rama(ramaID),
foreign key (creador) references usuario(correo)
);

create table archivo(
archivoID int, 
rama int,
primary key (archivoID),
foreign key (rama) references rama(ramaID)
);

create table modificacion(
archivo int, 
modificadoPor varchar(100),
ultimaModificacion date, 
contenido bytea,
primary key (archivo, modificadoPor, ultimaModificacion),
foreign key (modificadoPor) references usuario(correo),
foreign key (archivo) references archivo(archivoID)
);



CREATE OR REPLACE FUNCTION proyecto_fecha()
RETURNS TRIGGER AS $$
BEGIN
  NEW.fechadecreacion = NOW();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;



CREATE TRIGGER proyecto_fecha
BEFORE INSERT ON proyecto
FOR EACH ROW
EXECUTE PROCEDURE proyecto_fecha();

CREATE TRIGGER rama_fecha
BEFORE INSERT ON rama
FOR EACH ROW
EXECUTE PROCEDURE proyecto_fecha();

CREATE TRIGGER modificacion_fecha
BEFORE INSERT ON modificacion
FOR EACH ROW
EXECUTE PROCEDURE proyecto_fecha();

CREATE TRIGGER modificacion_fecha
BEFORE INSERT ON mensaje
FOR EACH ROW
EXECUTE PROCEDURE proyecto_fecha();

CREATE OR REPLACE FUNCTION proyecto_participante() RETURNS trigger LANGUAGE plpgsql AS $function$
declare
begin 
  insert into rama 
  insert into participante (usuario, proyecto) values (new.creador, new.proyectoid);
  return null;
END;
$function$
;

create trigger autogenerar after
insert
    on
    public.proyecto for each row execute function proyecto_participante();


CREATE OR REPLACE FUNCTION delete_rama() RETURNS trigger LANGUAGE plpgsql AS $function$
declare
 id INTEGER; 
begin 
  select ramapadre into id from rama where ramaid = old.ramaid; 
  if id is null then
  	 RAISE EXCEPTION 'no se puede eliminar una rama padre';
  else 
  	 update public.rama set ramapadre = id where ramapadre = old.ramaid;
  end if;
  return old;
END;
$function$
;

create trigger eliminarRama before
delete
    on
    public.rama for each row execute function delete_rama();

/*
  Índices
*/

CREATE UNIQUE INDEX index_nombre_proy ON rama (nombre, proyecto);


/*
  Foreign keys
*/
ALTER TABLE rama
ADD CONSTRAINT fk_rama_proyecto
FOREIGN KEY (proyecto)
REFERENCES proyecto(proyectoID)
ON DELETE CASCADE;

ALTER TABLE participante 
ADD CONSTRAINT fk_part_proyecto
FOREIGN KEY (proyecto)
REFERENCES proyecto(proyectoID)
ON DELETE CASCADE;

ALTER TABLE mensaje
ADD CONSTRAINT fk_mensaje_proyecto
FOREIGN KEY (proyecto)
REFERENCES proyecto(proyectoID)
ON DELETE CASCADE;


ALTER TABLE invitacion
ADD CONSTRAINT fk_invitacion_proyecto
FOREIGN KEY (proyecto)
REFERENCES proyecto(proyectoID)
ON DELETE CASCADE;

