drop database if exists proy_MsComida;
create database proy_MsComida;
use proy_MsComida;

create table categorias(
    id int primary key auto_increment,
    descripcion varchar(50) not null
);

create table platos(
    id int primary key auto_increment,
    descripcion varchar(50) not null,
    precio decimal(8,2) not null,
    id_categoria int not null,
    created_at timestamp default CURRENT_TIMESTAMP,
    foreign key(id_categoria) references categorias(id)
);