package dev.sauloaraujo.sgb.apresentacao.acervo.livro;

import java.util.List;

import dev.sauloaraujo.sgb.aplicacao.acervo.autor.AutorResumo;

public class LivroFormulario {
	public LivroDto livro;
	public List<AutorResumo> autores;

	public LivroFormulario(LivroDto livro, List<AutorResumo> autores) {
		this.livro = livro;
		this.autores = autores;
	}

	public static class LivroDto {
		public String id;
		public String titulo;
		public String subTitulo;
		public List<Integer> autores;
	}
}