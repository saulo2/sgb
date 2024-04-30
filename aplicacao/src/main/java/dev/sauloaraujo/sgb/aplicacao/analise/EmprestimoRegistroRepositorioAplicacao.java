package dev.sauloaraujo.sgb.aplicacao.analise;

import dev.sauloaraujo.analise.dominio.emprestimo.EmprestimoRegistro;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Emprestimo;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.ExemplarId;

public interface EmprestimoRegistroRepositorioAplicacao {
	EmprestimoRegistro buscar(ExemplarId exemplar, Emprestimo emprestimo);
}