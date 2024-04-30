package dev.sauloaraujo.sgb.aplicacao.acervo.exemplar;

import java.util.List;

public interface ExemplarRepositorioAplicacao {
	List<ExemplarResumo> pesquisarResumos();

	List<ExemplarResumoExpandido> pesquisarEmprestados();
}