package dev.sauloaraujo.sgb.acervo.dominio.livro;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jmolecules.ddd.types.AggregateRoot;

import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorId;

public class Livro implements Cloneable, AggregateRoot<Livro, Isbn> {
	private final Isbn id;

	private String titulo;
	private String subTitulo;

	private List<AutorId> autores = new ArrayList<>();

	public Livro(Isbn id, String titulo, String subTitulo, List<AutorId> autores) {
		notNull(id, "O id não pode ser nulo");
		this.id = id;

		setTitulo(titulo);
		setSubTitulo(subTitulo);
		setAutores(autores);
	}

	@Override
	public Isbn getId() {
		return id;
	}

	public void setTitulo(String titulo) {
		notNull(titulo, "O título não pode ser nulo");
		notBlank(titulo, "O título não pode estar em branco");

		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setSubTitulo(String subTitulo) {
		if (subTitulo != null) {
			notBlank(titulo, "O subtítulo não pode estar em branco");
		}
		this.subTitulo = subTitulo;
	}

	public String getSubTitulo() {
		return subTitulo;
	}

	private void setAutores(Collection<AutorId> autores) {
		notNull(autores, "O vetor de autores não pode ser nulo");
		notEmpty(autores, "O livro deve ter pelo menos um autor");

		for (var autor : autores) {
			adicionarAutor(autor);
		}
	}

	public Collection<AutorId> getAutores() {
		var copia = new ArrayList<AutorId>();
		copia.addAll(autores);
		return copia;
	}

	public void adicionarAutor(AutorId autor) {
		notNull(autores, "O autor não pode ser nulo");

		autores.add(autor);
	}

	@Override
	public Livro clone() {
		try {
			return (Livro) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}

	@Override
	public String toString() {
		return titulo;
	}
}