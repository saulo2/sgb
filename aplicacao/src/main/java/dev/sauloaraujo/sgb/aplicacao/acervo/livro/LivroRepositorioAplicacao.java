package dev.sauloaraujo.sgb.aplicacao.acervo.livro;

import java.util.List;

public interface LivroRepositorioAplicacao {
	List<LivroResumo> pesquisarResumos();

	List<LivroResumoExpandido> pesquisarResumosExpandidos();
}