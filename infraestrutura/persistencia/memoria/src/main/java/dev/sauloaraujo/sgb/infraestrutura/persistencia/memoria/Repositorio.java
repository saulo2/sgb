package dev.sauloaraujo.sgb.infraestrutura.persistencia.memoria;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import dev.sauloaraujo.sgb.acervo.dominio.autor.Autor;
import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorId;
import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorRepositorio;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Exemplar;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.ExemplarId;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.ExemplarRepositorio;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Livro;
import dev.sauloaraujo.sgb.acervo.dominio.livro.LivroRepositorio;
import dev.sauloaraujo.sgb.administracao.dominio.socio.Socio;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioId;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioRepositorio;

public class Repositorio implements SocioRepositorio, AutorRepositorio, LivroRepositorio, ExemplarRepositorio {
	/*-----------------------------------------------------------------------*/
	private Map<SocioId, Socio> socios = new HashMap<>();

	@Override
	public void salvar(Socio socio) {
		notNull(socio, "O sócio não pode ser nulo");

		socios.put(socio.getId(), socio);
	}

	@Override
	public Socio obter(SocioId id) {
		notNull(id, "O id do sócio não pode ser nulo");

		var socio = socios.get(id);
		return Optional.ofNullable(socio).get().clone();
	}
	/*-----------------------------------------------------------------------*/

	/*-----------------------------------------------------------------------*/
	private Map<AutorId, Autor> autores = new HashMap<>();

	@Override
	public void salvar(Autor autor) {
		notNull(autor, "O autor não pode ser nulo");

		autores.put(autor.getId(), autor);
	}

	@Override
	public Autor obter(AutorId id) {
		notNull(id, "O id do autor não pode ser nulo");

		var autor = autores.get(id);
		return Optional.ofNullable(autor).get().clone();
	}
	/*-----------------------------------------------------------------------*/

	/*-----------------------------------------------------------------------*/
	private Map<Isbn, Livro> livros = new HashMap<>();

	@Override
	public void salvar(Livro livro) {
		notNull(livro, "O livro não pode ser nulo");

		livros.put(livro.getId(), livro);
	}

	@Override
	public Livro obter(Isbn id) {
		notNull(id, "O ISBN do livro não pode ser nulo");

		var livro = livros.get(id);
		return Optional.ofNullable(livro).get().clone();
	}
	/*-----------------------------------------------------------------------*/

	/*-----------------------------------------------------------------------*/
	private Map<ExemplarId, Exemplar> exemplares = new HashMap<>();

	@Override
	public void salvar(Exemplar exemplar) {
		notNull(exemplar, "O exemplar não pode ser nulo");

		exemplares.put(exemplar.getId(), exemplar);
	}

	@Override
	public Exemplar obter(ExemplarId id) {
		notNull(id, "O id do exemplar não pode ser nulo");

		var exemplar = exemplares.get(id);
		return Optional.ofNullable(exemplar).get().clone();
	}

	@Override
	public List<Exemplar> pesquisarDisponiveis(Isbn livro) {
		return exemplares.values().stream()
				.filter(exemplar -> exemplar.getLivro().equals(livro) && exemplar.disponivel()).map(Exemplar::clone)
				.toList();
	}
	/*-----------------------------------------------------------------------*/
}