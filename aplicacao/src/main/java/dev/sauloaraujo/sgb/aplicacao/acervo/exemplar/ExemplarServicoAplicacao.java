package dev.sauloaraujo.sgb.aplicacao.acervo.exemplar;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.List;

public class ExemplarServicoAplicacao {
	private ExemplarRepositorioAplicacao repositorio;

	public ExemplarServicoAplicacao(ExemplarRepositorioAplicacao repositorio) {
		notNull(repositorio, "O id não pode ser nulo");

		this.repositorio = repositorio;
	}

	public List<ExemplarResumo> pesquisarResumos() {
		return repositorio.pesquisarResumos();
	}

	public List<ExemplarResumoExpandido> pesquisarEmprestados() {
		return repositorio.pesquisarEmprestados();
	}
}