package dev.sauloaraujo.sgb.aplicacao.analise;

import static org.apache.commons.lang3.Validate.notNull;

import java.time.LocalDate;

import dev.sauloaraujo.analise.dominio.emprestimo.EmprestimoRegistro;
import dev.sauloaraujo.analise.dominio.emprestimo.EmprestimoRegistroRepositorio;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Exemplar.EmprestimoRealizadoEvento;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Exemplar.ExemplarDevolvidoEvento;
import dev.sauloaraujo.sgb.evento.dominio.EventoBarramento;

public class EmprestimoRegistroServicoAplicacao {
	private EmprestimoRegistroRepositorio repositorio;
	private EmprestimoRegistroRepositorioAplicacao repositorioAplicacao;

	public EmprestimoRegistroServicoAplicacao(EmprestimoRegistroRepositorio repositorio,
			EmprestimoRegistroRepositorioAplicacao repositorioAplicacao, EventoBarramento servico) {
		notNull(repositorio, "repositorio não pode ser nulo");
		notNull(repositorioAplicacao, "repositorioAplicacao não pode ser nulo");
		notNull(servico, "servico não pode ser nulo");

		this.repositorio = repositorio;
		this.repositorioAplicacao = repositorioAplicacao;

		servico.adicionar(this::tratarEmprestimoRealizado);
		servico.adicionar(this::tratarExemplarDevolvido);
	}

	private void tratarEmprestimoRealizado(EmprestimoRealizadoEvento evento) {
		var exemplar = evento.getExemplar();
		var exemplarId = exemplar.getId();
		var emprestimo = exemplar.getEmprestimo();
		var registro = new EmprestimoRegistro(exemplarId, emprestimo);
		repositorio.salvar(registro);
	}

	private void tratarExemplarDevolvido(ExemplarDevolvidoEvento evento) {
		var exemplar = evento.getExemplar();
		var exemplarId = exemplar.getId();
		var emprestimo = evento.getEmprestimo();
		var registro = repositorioAplicacao.buscar(exemplarId, emprestimo);
		registro.setDevolucao(LocalDate.now());
		repositorio.salvar(registro);
	}
}