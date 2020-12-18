drop database if exists ejercicio_5;
create database ejercicio_5;

use ejercicio_5;

create table Cat_TipoUsuario(
	idTipoUsuario int not null auto_increment primary key,
    descripcion varchar(64) not null
);

insert into Cat_TipoUsuario(descripcion) values('Administrador');
insert into Cat_TipoUsuario(descripcion) values('Limitado');

create table Usuarios(
	idUsuario int not null auto_increment primary key,
    nombre varchar(64) not null,
    paterno varchar(64) not null,
    materno varchar(64) not null,
    email varchar(128) not null,
    idTipoUsuario int not null,
    foreign key (idTipoUsuario) references Cat_TipoUsuario(idTipoUsuario)
);

insert into Usuarios(nombre, paterno, materno, email, idTipoUsuario) values 
	('Ricardo', 'Almazan', 'Trejo', 'ricardo@gmail.com', 1),
    ('Frida', 'Calderon', 'Vazquez', 'frida@gmail.com', 1),
    ('Alan', 'Torres', 'Farias', 'alan@gmail.com', 2),
    ('Adriana', 'Cano', 'Rodriguez', 'adriana@gmail.com', 2),
    ('Joshua', 'Villalobos', 'Chirino', 'joshua@gmail.com', 2);

delimiter //
drop procedure if exists spMostrarUsuarios//
create procedure spMostrarUsuarios()
  begin
    select * from Usuarios;
  end;//
  delimiter ;