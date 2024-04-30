package dev.sauloaraujo.sgb.aplicacao.acervo.exemplar;

import dev.sauloaraujo.sgb.aplicacao.acervo.livro.LivroResumo;

public interface ExemplarResumoExpandido extends ExemplarResumo {
	String getId();

	LivroResumo getLivro();

	EmprestimoResumo getEmprestimo();
}