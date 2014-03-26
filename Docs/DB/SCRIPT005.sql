--Alterado por Mauricio

--Consideracoes:
--     Retirei o campo carga horaria da disciplina, pois cada curso pode variar a carga horaria para a mesma disciplina
--     Acho que a melhor estrategia e a SingleTable, pois sao poucos os campos que vao variar, caso precise ser alterado isso nao e complicado para alterar


--Alterado por Matheus 17/10/2013

--Alterações:

--Ajustes nos nomes dos campos das tabelas curso_disciplina e curso_disciplina_professor pra melhorar a identificação posteriormente nas classes de entidade java, mais especificamente nos campos: curso para id_curso, 
--disciplina para id_disciplina e professor para id_pessoa

--Relacionamento m:n entre tabela disciplina e pessoa gerando a tabela disciplina_professor, achei importante esse relacionamento pra facilitar no cadastro de cursos, no momento
--em que for selecinar os professores de uma disciplina vai ter como fazer um join somente nos professores da respectiva disciplina.

--Atualização do projeto de modelagem do banco no power architect.

--Alterado por Matheus 23/10/2013

--Alterações: Remoção do relacionamento disciplina_professor, iria ficar mais trabalhoso ter que atualizar o cadastro de disciplinas sempre que houvesse alterações de professores de uma determinada discplina.

--Remoção do campo tipo_pessoa e constraint ckc_tipo_pessoa da tabela pessoa e adição dos campos tipo_pessoa_professor, tipo_pessoa_aluno, tipo_pessoa_coordenador todos do tipo boolean.

--Atualização do projeto de modelagem do banco no power architect.


alter table pessoa
add tipo_pessoa_aluno boolean,
add tipo_pessoa_professor boolean,
add tipo_pessoa_coordenador boolean,
add matricula char(50);

update pessoa set tipo_pessoa_aluno = false, tipo_pessoa_professor = false, tipo_pessoa_coordenador = false;
  
create table disciplina
(
 id integer not null,
 descricao char(200) unique,
 constraint disciplina_pk primary key (id)
);

create table curso
(
 id integer not null,
 descricao char(200) unique,
 id_pessoa integer not null, --representa coordenador tipo_pessoa_coordenador = true na tabela pessoa
 carga_horaria_total integer,
 carga_horaria_distancia integer,
 data_inicial timestamp not null,
 data_final timestamp not null,
 constraint curso_pk primary key (id),
 constraint curso_coordenador_id_pessoa_fk foreign key (id_pessoa) references pessoa(id)
);

create table curso_disciplina
(
 id_disciplina integer not null,
 id_curso integer not null,
 carga_horaria integer not null,
 carga_horaria_distancia integer not null,
 constraint curso_disciplina_pk primary key (id_disciplina, id_curso),
 constraint curso_disciplina_id_disciplina_fk foreign key (id_disciplina) references disciplina(id),
 constraint curso_disciplina_id_curso_fk foreign key (id_curso) references curso(id)
);

create table curso_disciplina_professor
(
 id_disciplina integer not null,
 id_curso integer not null,
 id_pessoa integer not null, --representa professor tipo_pessoa_professor = true na tabela pessoa
 constraint curso_disciplina_professor_pk primary key (id_disciplina, id_curso, id_pessoa),
 constraint curso_disciplina_professor_id_disciplina_fk foreign key (id_disciplina, id_curso) references curso_disciplina(id_disciplina, id_curso),
 constraint curso_disciplina_professor_id_pessoa_fk foreign key (id_pessoa) references pessoa(id)
);

CREATE SEQUENCE disciplina_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 127
  CACHE 1;
ALTER TABLE disciplina_sequence
  OWNER TO postgres;
GRANT ALL ON TABLE disciplina_sequence TO postgres;
GRANT ALL ON TABLE disciplina_sequence TO siga;

CREATE SEQUENCE curso_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 127
  CACHE 1;
ALTER TABLE curso_sequence
  OWNER TO postgres;
GRANT ALL ON TABLE curso_sequence TO postgres;
GRANT ALL ON TABLE curso_sequence TO siga;

UPDATE Db_info SET script = 'SCRIPT005';
