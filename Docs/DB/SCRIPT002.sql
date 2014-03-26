CREATE TABLE Permissoes (
	id				int	CONSTRAINT permissoes_pk PRIMARY KEY,
	descricao			varchar(100)
);

CREATE TABLE Perfil (
	id				int	CONSTRAINT perfil_pk PRIMARY KEY,
	descricao			varchar(50)
);

CREATE TABLE Perfil_permissoes (
	id_perfil			int,
	id_permissao			int,
	permite				boolean,
	inclui				boolean,
	edita				boolean,
	exclui				boolean,
	CONSTRAINT			perfil_permissoes_PK PRIMARY KEY(id_perfil, id_permissao)
);

ALTER TABLE Perfil_permissoes
  ADD CONSTRAINT id_perfil_fkey FOREIGN KEY (id_perfil)
      REFERENCES Perfil (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
	  
ALTER TABLE Perfil_permissoes
  ADD CONSTRAINT id_permissao_fkey FOREIGN KEY (id_permissao)
      REFERENCES Permissoes (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
	  
CREATE TABLE Usuario_perfil (
	id_usuario			int,
	id_perfil			int,
	CONSTRAINT			usuario_perfil_PK PRIMARY KEY(id_usuario, id_perfil)
);

CREATE TABLE Usuario (
	id				int,
	id_pessoa			int UNIQUE,
	user_name			varchar(30),
	password			varchar(40),
	CONSTRAINT			usuario_PK PRIMARY KEY(id)
);

CREATE TABLE Pessoa (
	id				int CONSTRAINT pessoa_pk PRIMARY KEY,
	cpf				varchar(11) UNIQUE,
	nome				varchar(100),
	e_mail				varchar(60)
);

ALTER TABLE Usuario_perfil
  ADD CONSTRAINT id_usuario_fkey FOREIGN KEY (id_usuario)
      REFERENCES Usuario (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
	  
ALTER TABLE Usuario_perfil
  ADD CONSTRAINT id_perfil_fkey FOREIGN KEY (id_perfil)
      REFERENCES Perfil (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
	  
ALTER TABLE Usuario
  ADD CONSTRAINT id_pessoa_fkey FOREIGN KEY (id_pessoa)
      REFERENCES Pessoa (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
	  
CREATE TABLE Db_info (
	script				varchar(10)
);

CREATE SEQUENCE Perfil_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE Perfil_sequence
  OWNER TO siga;

CREATE SEQUENCE Usuario_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE Usuario_sequence
  OWNER TO siga;
  
CREATE SEQUENCE Pessoa_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE Pessoa_sequence
  OWNER TO siga;

INSERT INTO Db_info VALUES ('SCRIPT002');
