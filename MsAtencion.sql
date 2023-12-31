drop database if exists proy_MsAtencion;
create database proy_MsAtencion;
use proy_MsAtencion;

create table mesas(
    id int primary key auto_increment,
    codigo int not null
);

create table pedidos(
    id int primary key auto_increment,
    id_mesa int not null,
    created_at timestamp default CURRENT_TIMESTAMP,
    foreign key(id_mesa) references mesas(id)
);