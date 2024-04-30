package dev.sauloaraujo.sgb.acervo.dominio.exemplar;

import static org.apache.commons.lang3.Validate.notNull;

import java.time.LocalDate;

import org.jmolecules.ddd.types.AggregateRoot;
import org.jmolecules.event.types.DomainEvent;

import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioId;

public class Exemplar implements Cloneable, AggregateRoot<Exemplar, ExemplarId> {
	private final ExemplarId id;

	private Isbn livro;
	private Emprestimo emprestimo;

	public Exemplar(Isbn livro, Emprestimo emprestimo) {
		id = null;

		setLivro(livro);
		setEmprestimo(emprestimo);
	}

	public Exemplar(ExemplarId id, Isbn livro, Emprestimo emprestimo) {
		notNull(id, "O id não pode ser nulo");
		this.id = id;

		setLivro(livro);
		setEmprestimo(emprestimo);
	}

	@Override
	public ExemplarId getId() {
		return id;
	}

	private void setLivro(Isbn livro) {
		notNull(id, "O livro não pode ser nulo");

		this.livro = livro;
	}

	public Isbn getLivro() {
		return livro;
	}

	private void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public boolean disponivel() {
		return emprestimo == null;
	}

	public boolean indisponivel() {
		return emprestimo != null;
	}

	public boolean emprestado() {
		return indisponivel();
	}

	@Override
	public Exemplar clone() {
		try {
			return (Exemplar) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e);
		}
	}

	@Override
	public String toString() {
		return id.toString();
	}

	public EmprestimoRealizadoEvento realizarEmprestimo(SocioId tomador) {
		if (indisponivel()) {
			throw new IllegalStateException("O exemplar não está disponível no momento");
		}

		var inicio = LocalDate.now();
		var fim = inicio.plusDays(7);
		var periodo = new Periodo(inicio, fim);
		emprestimo = new Emprestimo(periodo, tomador);

		return new EmprestimoRealizadoEvento(this);
	}

	public ExemplarDevolvidoEvento devolver() {
		if (!emprestado()) {
			throw new IllegalArgumentException("O exemplar não está emprestado");
		}

		var ultimoEmprestimo = emprestimo;
		emprestimo = null;
		return new ExemplarDevolvidoEvento(this, ultimoEmprestimo);
	}

	public static class ExemplarEvento implements DomainEvent {
		private final Exemplar exemplar;

		private ExemplarEvento(Exemplar exemplar) {
			this.exemplar = exemplar;
		}

		public Exemplar getExemplar() {
			return exemplar;
		}
	}

	public static class EmprestimoRealizadoEvento extends ExemplarEvento {
		private EmprestimoRealizadoEvento(Exemplar exemplar) {
			super(exemplar);
		}
	}

	public static class ExemplarDevolvidoEvento extends ExemplarEvento {
		private final Emprestimo emprestimo;

		private ExemplarDevolvidoEvento(Exemplar exemplar, Emprestimo emprestimo) {
			super(exemplar);

			this.emprestimo = emprestimo;
		}

		public Emprestimo getEmprestimo() {
			return emprestimo;
		}
	}

	public static class EmprestimoVencido extends ExemplarEvento {
		private EmprestimoVencido(Exemplar exemplar) {
			super(exemplar);
		}
	}
}