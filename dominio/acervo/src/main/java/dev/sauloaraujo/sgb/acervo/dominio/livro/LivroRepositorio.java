package dev.sauloaraujo.sgb.acervo.dominio.livro;

public interface LivroRepositorio {
	void salvar(Livro livro);

	Livro obter(Isbn isbn);
}