CREATE TABLE cedulas(
    id int not null auto_increment,
    quantidade int not null,
    descricao varchar(50) not null,
    valor_real int not null,
    PRIMARY KEY (id)
);

insert into cedulas(descricao, valor_real, quantidade) values('50R$', 50, 20);
insert into cedulas(descricao, valor_real, quantidade) values('20R$', 20, 20);
insert into cedulas(descricao, valor_real, quantidade) values('10R$', 10, 20);
insert into cedulas(descricao, valor_real, quantidade) values('5R$', 5, 20);
insert into cedulas(descricao, valor_real, quantidade) values('2R$', 2, 20);
