drop database if exists proy_MsDetallePedido;
create database proy_MsDetallePedido;
use proy_MsDetallePedido;

create table detalle_pedido(
    id int primary key auto_increment,
    id_pedido int not null,
    id_plato int not null,
    cantidad int not null,
    precio_unitario decimal(8,2) not null
);