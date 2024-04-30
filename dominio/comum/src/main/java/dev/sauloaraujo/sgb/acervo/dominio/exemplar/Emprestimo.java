package dev.sauloaraujo.sgb.acervo.dominio.exemplar;

import static org.apache.commons.lang3.Validate.notNull;

import org.jmolecules.ddd.types.ValueObject;

import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioId;

public class Emprestimo implements ValueObject {
	private final Periodo periodo;
	private final SocioId tomador;

	public Emprestimo(Periodo periodo, SocioId tomador) {
		notNull(periodo, "O período não pode ser nulo");
		this.periodo = periodo;

		notNull(tomador, "O tomador não pode ser nulo");
		this.tomador = tomador;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public SocioId getTomador() {
		return tomador;
	}
}