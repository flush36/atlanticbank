

CREATE TABLE usuario (
    iduser int not null auto_increment,
    nome varchar(50) not null,
    login varchar(50) not null,
    senha varchar(50) not null,
    token varchar(70),
    tipo_usuario varchar(1),
    PRIMARY KEY (iduser)
);


