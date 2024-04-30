package dev.sauloaraujo.sgb.acervo.dominio.livro;

import static org.apache.commons.lang3.Validate.notNull;

public class LivroServico {
	private final LivroRepositorio livroRepositorio;

	public LivroServico(LivroRepositorio livroRepositorio) {
		notNull(livroRepositorio, "O repositório de livros não pode ser nulo");

		this.livroRepositorio = livroRepositorio;
	}

	public void salvar(Livro livro) {
		notNull(livro, "O livro não pode ser nulo");

		livroRepositorio.salvar(livro);
	}

	public Livro obter(Isbn id) {
		notNull(id, "O ISBN do livro não pode ser nulo");

		return livroRepositorio.obter(id);
	}
}