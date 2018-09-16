

CREATE TABLE usuario (
    iduser int not null auto_increment,
    nome varchar(50) not null,
    login varchar(50) not null,
    senha varchar(50) not null,
    token varchar(70),
    tipo_usuario varchar(1),
    PRIMARY KEY (iduser)
);

insert into usuario(login, senha, tipo_usuario, nome) values('atlantico', '21e38fe741f770d1cf019b800f0f1bff', 'U', 'Jon Snow');
insert into usuario(login, senha, tipo_usuario, nome) values('admin', '21e38fe741f770d1cf019b800f0f1bff', 'A', 'Khal Drogo');

