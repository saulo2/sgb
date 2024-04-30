package dev.sauloaraujo.sgb.acervo.dominio;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.ArrayList;
import java.util.List;

import org.jmolecules.event.types.DomainEvent;

import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorServico;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.EmprestimoServico;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.ExemplarServico;
import dev.sauloaraujo.sgb.acervo.dominio.livro.IsbnFabrica;
import dev.sauloaraujo.sgb.acervo.dominio.livro.LivroServico;
import dev.sauloaraujo.sgb.evento.dominio.EventoBarramento;
import dev.sauloaraujo.sgb.evento.dominio.EventoObservador;
import dev.sauloaraujo.sgb.infraestrutura.persistencia.memoria.Repositorio;

public class AcervoFuncionalidade implements EventoBarramento {
	protected IsbnFabrica isbnFabrica;
	protected AutorServico autorServico;
	protected LivroServico livroServico;
	protected ExemplarServico exemplarServico;
	protected EmprestimoServico emprestimoServico;
	protected List<DomainEvent> eventos;

	public AcervoFuncionalidade() {
		isbnFabrica = new IsbnFabrica();

		var repositorio = new Repositorio();

		autorServico = new AutorServico(repositorio);
		livroServico = new LivroServico(repositorio);
		exemplarServico = new ExemplarServico(repositorio);
		emprestimoServico = new EmprestimoServico(repositorio, this);

		eventos = new ArrayList<>();
	}

	@Override
	public <T extends DomainEvent> void adicionar(EventoObservador<T> observador) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void postar(DomainEvent evento) {
		notNull(evento, "O evento não pode ser nulo");

		eventos.add(evento);
	}
}