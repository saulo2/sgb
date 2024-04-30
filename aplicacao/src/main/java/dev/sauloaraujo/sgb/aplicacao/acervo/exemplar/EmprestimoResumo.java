package dev.sauloaraujo.sgb.aplicacao.acervo.exemplar;

import dev.sauloaraujo.sgb.aplicacao.administracao.socio.SocioResumo;

public interface EmprestimoResumo {
	PeriodoResumo getPeriodo();

	SocioResumo getTomador();
}