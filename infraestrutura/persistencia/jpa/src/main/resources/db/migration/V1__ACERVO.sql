create table AUTOR (
    ID int generated always as identity not null,
    NOME varchar not null,
	primary key (ID)
);

create table LIVRO (
	ID varchar not null,
	TITULO varchar not null,
	SUBTITULO varchar,
	primary key (ID)
);

create table LIVRO_AUTOR (
	LIVRO_ID varchar not null,
	AUTOR_ID integer not null,
	AUTOR_ORDEM integer not null,
	primary key (LIVRO_ID, AUTOR_ID),
	foreign key (AUTOR_ID) references AUTOR(ID),
	foreign key (LIVRO_ID) references LIVRO(ID)
);

create table SOCIO (
	ID int generated always as identity not null,
	NOME varchar not null,
	EMAIL varchar not null,
	primary key (ID)
);

create table EXEMPLAR (
	ID integer not null,
	LIVRO_ID varchar not null,
	EMPRESTIMO_PERIODO_INICIO date null,
	EMPRESTIMO_PERIODO_FIM date null,
	EMPRESTIMO_TOMADOR_ID int null,	
	primary key (ID),
	foreign key (LIVRO_ID) references LIVRO(ID),
	foreign key (EMPRESTIMO_TOMADOR_ID) references SOCIO(ID)
);

create table EMPRESTIMO_REGISTRO (
    ID int generated always as identity not null,
    EXEMPLAR_ID integer not null,
    EMPRESTIMO_PERIODO_INICIO date not null,
    EMPRESTIMO_PERIODO_FIM date not null,    
    EMPRESTIMO_TOMADOR_ID integer not null,
    DEVOLUCAO date null,
    primary key (ID),
    foreign key (EXEMPLAR_ID) references EXEMPLAR(ID),
    foreign key (EMPRESTIMO_TOMADOR_ID) references SOCIO(ID)
);