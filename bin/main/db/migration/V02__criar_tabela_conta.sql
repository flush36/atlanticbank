CREATE TABLE  (
    idconta int not null auto_increment,
    saldo decimal(10,2) not null,
    saque decimal(10,2) not null,
    deposito decimal(10,2) not null,
    data_operacao datetime not null,
    fkuser int not null,
    constraint fk_user foreign key(fkuser)
	references usuario (iduser),
    PRIMARY KEY (idconta)
);
insert into conta(saldo, saque, deposito, data_operacao, fkuser) values(300, 230, 100,'2018-09-13', 1 );
insert into conta(saldo, saque, deposito, data_operacao, fkuser) values(0, 0, 0,'2018-09-13', 2 );
