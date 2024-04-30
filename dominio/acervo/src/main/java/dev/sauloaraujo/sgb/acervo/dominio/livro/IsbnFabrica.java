package dev.sauloaraujo.sgb.acervo.dominio.livro;

import static org.apache.commons.lang3.Validate.notNull;

public class IsbnFabrica {
	public Isbn construir(String codigo) {
		notNull(codigo, "O código não pode ser nulo");

		try {
			return new Isbn10(codigo);
		} catch (IllegalArgumentException e) {
		}

		try {
			return new Isbn13(codigo);
		} catch (IllegalArgumentException e) {
		}

		throw new IllegalArgumentException("Código inválido");
	}
}