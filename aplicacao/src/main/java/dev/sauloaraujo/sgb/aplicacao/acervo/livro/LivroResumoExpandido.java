package dev.sauloaraujo.sgb.aplicacao.acervo.livro;

import dev.sauloaraujo.sgb.aplicacao.acervo.autor.AutorResumo;

public interface LivroResumoExpandido {
	LivroResumo getLivro();

	AutorResumo getAutor();

	int getExemplaresDisponiveis();

	int getTotalExemplares();
}