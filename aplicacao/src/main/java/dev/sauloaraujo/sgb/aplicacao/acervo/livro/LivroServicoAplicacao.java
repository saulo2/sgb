package dev.sauloaraujo.sgb.aplicacao.acervo.livro;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.List;

public class LivroServicoAplicacao {
	private LivroRepositorioAplicacao repositorio;

	public LivroServicoAplicacao(LivroRepositorioAplicacao repositorio) {
		notNull(repositorio, "O id não pode ser nulo");

		this.repositorio = repositorio;
	}

	public List<LivroResumo> pesquisarResumos() {
		return repositorio.pesquisarResumos();
	}

	public List<LivroResumoExpandido> pesquisarResumosExpandidos() {
		return repositorio.pesquisarResumosExpandidos();
	}
}