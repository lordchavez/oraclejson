DROP TABLE KIO.CLIENTE PURGE;

CREATE TABLE KIO.CLIENTE(
	id INTEGER NOT NULL,
    ITEM CLOB
    CONSTRAINT ensure_json CHECK ( ITEM IS JSON )
);

CREATE INDEX CLIENTE_ITEM_IDX ON KIO.CLIENTE (ITEM) indextype IS CTXSYS.CONTEXT parameters ('section group ctxsys.json_section_group sync (on commit)');

CREATE SEQUENCE KIO.SEQ_CLI INCREMENT BY 1 MINVALUE 1 NOCYCLE NOCACHE NOORDER ;

SELECT * FROM KIO.CLIENTE;

DELETE FROM KIO.CLIENTE;

INSERT INTO KIO.CLIENTE (ID, ITEM) VALUES (124, ('{"id":1,"first_name":"Paxon","last_name":"Castiglione","email":"pcastiglione0@globo.com","gender":"Agender","age":30}') );

INSERT INTO KIO.CLIENTE (ID, ITEM) VALUES (125, ('{"id":2,"first_name":"Philip","last_name":"McLenaghan","email":"pmclenaghan1@sohu.com","gender":"Genderqueer","age":72}') );

SELECT c.ITEM FROM KIO.CLIENTE c WHERE json_textcontains ( c.ITEM, '$.first_name', 'Paxon' );

-- UPDATE KIO.CLIENTE c SET c.ITEM = json_mergepatch ( c.ITEM, '{"first_name":"Terminator"}' ) WHERE c.ID = 124; --Funcion OK

-- UPDATE KIO.CLIENTE c SET c.ITEM = json_mergepatch ( c.ITEM, '{"first_name":"Terminator"}' ) WHERE json_value ( c.ITEM, '$.id' returning NUMBER ) = 1 --Funcion OK

-- UPDATE KIO.CLIENTE c SET c.ITEM = json_mergepatch ( c.ITEM, json_object( 'first_name' VALUE 'Terminator' ) ) WHERE c.ID = 123 -- Funciona OK

SELECT c.ITEM FROM KIO.CLIENTE c WHERE json_textcontains ( c.ITEM, '$.first_name', 'Paxon' );

SELECT c.ITEM FROM KIO.CLIENTE c WHERE json_textcontains ( c.ITEM, '$.id', '1' );