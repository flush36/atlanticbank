CREATE TABLE conta (
    idconta int not null auto_increment,
    saldo decimal(10,2),
    saque decimal(10,2),
    deposito decimal(10,2),
    data_operacao datetime not null,
    fkuser int not null,
    constraint fk_user foreign key(fkuser)
	references usuario (iduser),
    PRIMARY KEY (idconta)
);
insert into conta(saldo, data_operacao, fkuser) values(300, '2018-09-13', 1);
