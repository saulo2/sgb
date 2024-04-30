package dev.sauloaraujo.sgb.administracao.dominio.socio;

import static org.apache.commons.lang3.Validate.notNull;

public class SocioServico {
	private final SocioRepositorio socioRepositorio;

	public SocioServico(SocioRepositorio socioRepositorio) {
		notNull(socioRepositorio, "O repositório de sócios não pode ser nulo");

		this.socioRepositorio = socioRepositorio;
	}

	public void salvar(Socio socio) {
		notNull(socio, "O sócio não pode ser nulo");

		socioRepositorio.salvar(socio);
	}

	public Socio obter(SocioId id) {
		notNull(id, "O id do sócio não pode ser nulo");

		return socioRepositorio.obter(id);
	}
}