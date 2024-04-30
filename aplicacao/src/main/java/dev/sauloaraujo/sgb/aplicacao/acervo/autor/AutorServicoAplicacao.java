package dev.sauloaraujo.sgb.aplicacao.acervo.autor;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.List;

public class AutorServicoAplicacao {
	private AutorRepositorioAplicacao repositorio;

	public AutorServicoAplicacao(AutorRepositorioAplicacao repositorio) {
		notNull(repositorio, "O id não pode ser nulo");

		this.repositorio = repositorio;
	}

	public List<AutorResumo> pesquisarResumos() {
		return repositorio.pesquisarResumos();
	}
}