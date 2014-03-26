GRANT ALL PRIVILEGES ON calendario TO siga;
GRANT ALL PRIVILEGES ON calendario_curso_disciplina_professor TO siga;

GRANT ALL PRIVILEGES ON calendario_sequence TO siga;

ALTER SEQUENCE calendario_sequence RESTART WITH 100;

UPDATE Db_info SET script = 'SCRIPT009';
