package dev.sauloaraujo.sgb.acervo.dominio.exemplar;

import java.util.List;

import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;

public interface ExemplarRepositorio {
	void salvar(Exemplar exemplar);

	Exemplar obter(ExemplarId id);

	List<Exemplar> pesquisarDisponiveis(Isbn livro);
}