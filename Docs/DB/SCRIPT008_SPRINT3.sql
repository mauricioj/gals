CREATE SEQUENCE calendario_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 127
  CACHE 1;
ALTER TABLE calendario_sequence
  OWNER TO postgres;
GRANT ALL ON TABLE calendario_sequence TO postgres;
GRANT ALL ON TABLE calendario_sequence TO siga;

create table calendario
(
 id integer not null DEFAULT NEXTVAL('calendario_sequence'),
 informacoes_calendario text,
 constraint calendario_pk primary key (id)
);

create table calendario_curso_disciplina_professor
(
 id_calendario integer not null,
 id_disciplina integer not null,
 id_curso integer not null,
 id_pessoa integer not null, --representa professor tipo_pessoa_professor = true na tabela pessoa
 data_aula timestamp not null,
 aula_distancia boolean,
 informacoes_aula text,
 constraint calendario_curso_disciplina_professor_pk primary key (id_calendario, id_disciplina, id_curso, id_pessoa, data_aula),
 constraint calendario_curso_disciplina_professor_id_disciplina_fk foreign key (id_disciplina, id_curso, id_pessoa) references 
 curso_disciplina_professor(id_disciplina, id_curso, id_pessoa),
 constraint calendario_curso_disciplina_professor_id_calendario_fk foreign key (id_calendario) references calendario(id)
);

UPDATE Db_info SET script = 'SCRIPT008';
