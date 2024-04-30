package dev.sauloaraujo.sgb.acervo.dominio.autor;

public interface AutorRepositorio {
	void salvar(Autor autor);

	Autor obter(AutorId id);
}