create database filmesdb;

use filmesdb;

create table filmes(
    id int not null auto_increment,
    titulo char(100),
    genero char(50),
    duracao int,
    lancamento date,
    primary key(id)
);