GRANT ALL PRIVILEGES ON disciplina TO siga;
GRANT ALL PRIVILEGES ON curso TO siga;
GRANT ALL PRIVILEGES ON curso_disciplina TO siga;
GRANT ALL PRIVILEGES ON curso_disciplina_professor TO siga;

GRANT ALL PRIVILEGES ON disciplina_sequence TO siga;
GRANT ALL PRIVILEGES ON curso_sequence TO siga;

ALTER SEQUENCE disciplina_sequence RESTART WITH 100;
ALTER SEQUENCE curso_sequence RESTART WITH 100;

UPDATE Db_info SET script = 'SCRIPT006';
