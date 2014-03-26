INSERT INTO disciplina (id, descricao) VALUES (1, 'Frameworks para Desenvolvimento WEB');
INSERT INTO disciplina (id, descricao) VALUES (2, 'Projeto de Banco de Dados');
INSERT INTO disciplina (id, descricao) VALUES (3, 'Arquitetura Orientada a Serviços e Web Services');
INSERT INTO disciplina (id, descricao) VALUES (4, 'Certificação Java');
INSERT INTO disciplina (id, descricao) VALUES (5, 'Desenvolvimento Ágil de Software');
INSERT INTO disciplina (id, descricao) VALUES (6, 'Design e Avaliação de Interfaces de Usuário para Web');
INSERT INTO disciplina (id, descricao) VALUES (7, 'Padrões e Frameworks para Persistência Objeto Relacional');
INSERT INTO disciplina (id, descricao) VALUES (8, 'Plataforma de Desenvolvimento em Software Livre');
INSERT INTO disciplina (id, descricao) VALUES (9, 'Programação para WEB');
INSERT INTO disciplina (id, descricao) VALUES (10, 'Projeto de Software Baseado em Padrões');

UPDATE pessoa SET tipo_pessoa_coordenador = 't' WHERE id = 1;
UPDATE pessoa SET tipo_pessoa_coordenador = 't' WHERE id = 2;
UPDATE pessoa SET tipo_pessoa_coordenador = 't' WHERE id = 3;
UPDATE pessoa SET tipo_pessoa_professor = 't' WHERE id = 1;
UPDATE pessoa SET tipo_pessoa_professor = 't' WHERE id = 4;
UPDATE pessoa SET tipo_pessoa_professor = 't' WHERE id = 5;
UPDATE pessoa SET tipo_pessoa_aluno = 't' WHERE id = 4;
UPDATE pessoa SET tipo_pessoa_aluno = 't' WHERE id = 6;
UPDATE pessoa SET tipo_pessoa_aluno = 't' WHERE id = 7;


INSERT INTO curso (id, descricao, id_pessoa, carga_horaria_total, carga_horaria_distancia, data_inicial, data_final) 
VALUES (1, 'Desenvolvimento de Software WEB 2013', 1, 500, 100, '01/02/2013', '07/12/2013');

INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (1, 1, 50, 10);
INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (2, 1, 50, 10);
INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (3, 1, 50, 10);
INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (4, 1, 50, 10);
INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (5, 1, 50, 10);
INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (6, 1, 50, 10);
INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (7, 1, 50, 10);
INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (8, 1, 50, 10);
INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (9, 1, 50, 10);
INSERT INTO curso_disciplina (id_disciplina, id_curso, carga_horaria, carga_horaria_distancia) VALUES (10, 1, 50, 10);

INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (1, 1, 1);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (1, 1, 4);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (2, 1, 5);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (2, 1, 1);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (3, 1, 4);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (3, 1, 5);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (4, 1, 1);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (4, 1, 4);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (5, 1, 5);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (5, 1, 1);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (6, 1, 4);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (6, 1, 5);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (7, 1, 1);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (7, 1, 4);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (8, 1, 5);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (8, 1, 1);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (9, 1, 4);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (9, 1, 5);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (10, 1, 1);
INSERT INTO curso_disciplina_professor (id_disciplina, id_curso, id_pessoa) VALUES (10, 1, 4);

UPDATE Db_info SET script = 'SCRIPT007';
