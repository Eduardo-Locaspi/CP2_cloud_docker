insert into pessoa(nome,cpf,data_nascimento,email) values('Pessoa 1','123','2005-01-01','pessoa1@fiap.com.br');
insert into pessoa(nome,cpf,data_nascimento,email) values('Pessoa 2','124','2006-01-01','pessoa2@fiap.com.br');
insert into pessoa(nome,cpf,data_nascimento,email) values('Pessoa 3','125','2004-01-01','pessoa3@fiap.com.br');

insert into usuario(id,rm,senha,status,fk_pessoa) values(1,'rm1','senha1','ATIVO',1);
insert into usuario(id,rm,senha,status,fk_pessoa) values(2,'rm2','senha2','INATIVO',2);
insert into usuario(id,rm,senha,status,fk_pessoa) values(3,'rm3','senha3','SUSPENSO',3);