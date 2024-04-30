package dev.sauloaraujo.sgb.administracao.dominio.socio;

public interface SocioRepositorio {
	void salvar(Socio socio);

	Socio obter(SocioId id);
}