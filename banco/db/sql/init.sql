CREATE SCHEMA JURIDICO;

CREATE TABLE JURIDICO.PROCESSO (
                                   ID SERIAL PRIMARY KEY,
                                   DATA_DE_ABERTURA TIMESTAMP,
                                   DESCRICAO_DO_CASO TEXT,
                                   STATUS INTEGER
);

CREATE TABLE JURIDICO.PARTE_ENVOLVIDA (
                                          ID SERIAL PRIMARY KEY,
                                          NOME VARCHAR(255),
                                          CPF_CNPJ VARCHAR(20),
                                          EMAIL VARCHAR(255),
                                          TELEFONE VARCHAR(20),
                                          TIPO_PARTE_ENVOLVIDA INTEGER,
                                          PROCESSO_ID INTEGER REFERENCES JURIDICO.PROCESSO(ID) ON DELETE CASCADE
);

CREATE TABLE JURIDICO.ACAO (
                               ID SERIAL PRIMARY KEY,
                               TIPO INTEGER,
                               DATA_REGISTRO TIMESTAMP,
                               DESCRICAO TEXT,
                               PROCESSO_ID INTEGER REFERENCES JURIDICO.PROCESSO(ID) ON DELETE CASCADE
);
