CREATE TABLE cedulas(
    idCedula int not null auto_increment,
    quantidade int,
    descricao varchar(50) not null,
    valor_real int not null,
    PRIMARY KEY (idCedula)
);
insert into cedulas(descricao, valor_real) values('50R$', 50);
insert into cedulas(descricao, valor_real) values('20R$', 20);
insert into cedulas(descricao, valor_real) values('10R$', 10);
insert into cedulas(descricao, valor_real) values('5R$', 5);
insert into cedulas(descricao, valor_real) values('2R$', 2);
