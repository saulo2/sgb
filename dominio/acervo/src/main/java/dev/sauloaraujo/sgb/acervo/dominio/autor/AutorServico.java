package dev.sauloaraujo.sgb.acervo.dominio.autor;

import static org.apache.commons.lang3.Validate.notNull;

public class AutorServico {
	private final AutorRepositorio autorRepositorio;

	public AutorServico(AutorRepositorio autorRepositorio) {
		notNull(autorRepositorio, "O repositório de autores não pode ser nulo");

		this.autorRepositorio = autorRepositorio;
	}

	public void salvar(Autor autor) {
		notNull(autor, "O autor não pode ser nulo");

		autorRepositorio.salvar(autor);
	}

	public Autor obter(AutorId id) {
		notNull(id, "O id do autor não pode ser nulo");

		return autorRepositorio.obter(id);
	}
}