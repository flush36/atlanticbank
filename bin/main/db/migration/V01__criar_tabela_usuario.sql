

CREATE TABLE usuario (
    iduser int not null auto_increment,
    login varchar(50),
    senha varchar(50),
    tipo_usuario varchar(1),
    PRIMARY KEY (iduser)
);

insert into usuario(login, senha, tipo_usuario) values('atlantico', '21e38fe741f770d1cf019b800f0f1bff', 'U');
insert into usuario(login, senha, tipo_usuario) values('admin', '21e38fe741f770d1cf019b800f0f1bff', 'A');

