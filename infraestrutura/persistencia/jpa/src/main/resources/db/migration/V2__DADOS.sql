insert into AUTOR(NOME) values (
	'Andrew Tanenbaum'
);

insert into LIVRO(ID, TITULO) values (
	'9788582606162',
	'Sistemas operacionais modernos'
);

insert into LIVRO_AUTOR(LIVRO_ID, AUTOR_ID, AUTOR_ORDEM) values (
	'9788582606162',
	1,
	0
);

insert into EXEMPLAR (ID, LIVRO_ID) values (
	1,
	'9788582606162'
);

insert into LIVRO(ID, TITULO) values (
	'9788582605608',
	'Redes de computadores'
);

insert into LIVRO_AUTOR(LIVRO_ID, AUTOR_ID, AUTOR_ORDEM) values (
	'9788582605608',
	1,
	0
);

insert into EXEMPLAR (ID, LIVRO_ID) values (
	2,
	'9788582605608'
);

insert into LIVRO(ID, TITULO) values (
	'9788581435398',
	'Organização estruturada de computadores'
);

insert into LIVRO_AUTOR(LIVRO_ID, AUTOR_ID, AUTOR_ORDEM) values (
	'9788581435398',
	1,
	0
);

insert into EXEMPLAR (ID, LIVRO_ID) values (
	3,
	'9788581435398'
);



insert into AUTOR(NOME) values (
	'Ian Sommerville'
);

insert into LIVRO(ID, TITULO) values (
	'9788543024974',
	'Engenharia de software'
);

insert into LIVRO_AUTOR(LIVRO_ID, AUTOR_ID, AUTOR_ORDEM) values (
	'9788543024974',
	2,
	0
);

insert into EXEMPLAR (ID, LIVRO_ID) values (
	4,
	'9788543024974'
);



insert into AUTOR(NOME) values (
	'Marco Tulio Valente'
);

insert into LIVRO(ID, TITULO) values (
	'9786500019506',
	'Engenharia de software moderna'
);

insert into LIVRO_AUTOR(LIVRO_ID, AUTOR_ID, AUTOR_ORDEM) values (
	'9786500019506',
	3,
	0
);

insert into EXEMPLAR (ID, LIVRO_ID) values (
	5,
	'9786500019506'
);



insert into AUTOR(NOME) values (
	'Eric Evans'
);

insert into LIVRO(ID, TITULO, SUBTITULO) values (
	'9788550800653',
	'Domain-driven design',
	'Atacando as complexidades no coração do software'
);

insert into LIVRO_AUTOR(LIVRO_ID, AUTOR_ID, AUTOR_ORDEM) values (
	'9788550800653',
	4,
	0
);

insert into EXEMPLAR (ID, LIVRO_ID) values (
	6,
	'9788550800653'
);



insert into AUTOR(NOME) values (
	'Vaughn Vernon'
);

insert into LIVRO(ID, TITULO) values (
	'9788576089520',
	'Implementando Domain-Driven Design'
);

insert into LIVRO_AUTOR(LIVRO_ID, AUTOR_ID, AUTOR_ORDEM) values (
	'9788576089520',
	5,
	0
);

insert into EXEMPLAR (ID, LIVRO_ID) values (
	7,
	'9788576089520'
);



insert into SOCIO(NOME, EMAIL) values (
	'Saulo Medeiros de Araujo',
	'contato@sauloaraujo.dev'
);