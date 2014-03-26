GRANT ALL PRIVILEGES ON db_info TO siga;
GRANT ALL PRIVILEGES ON perfil TO siga;
GRANT ALL PRIVILEGES ON perfil_permissoes TO siga;
GRANT ALL PRIVILEGES ON permissoes TO siga;
GRANT ALL PRIVILEGES ON pessoa TO siga;
GRANT ALL PRIVILEGES ON usuario TO siga;
GRANT ALL PRIVILEGES ON usuario_perfil TO siga;

GRANT ALL PRIVILEGES ON perfil_sequence TO siga;
GRANT ALL PRIVILEGES ON pessoa_sequence TO siga;
GRANT ALL PRIVILEGES ON usuario_sequence TO siga;

ALTER SEQUENCE perfil_sequence RESTART WITH 100;
ALTER SEQUENCE pessoa_sequence RESTART WITH 100;
ALTER SEQUENCE usuario_sequence RESTART WITH 100;

UPDATE Db_info SET script = 'SCRIPT004';