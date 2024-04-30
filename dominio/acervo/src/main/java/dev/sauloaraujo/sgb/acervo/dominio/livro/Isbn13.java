package dev.sauloaraujo.sgb.acervo.dominio.livro;

import org.apache.commons.validator.routines.ISBNValidator;

class Isbn13 extends Isbn {
	Isbn13(String codigo) {
		super(codigo);
	}

	@Override
	boolean testarCodigo(String codigo) {
		return ISBNValidator.getInstance().isValidISBN13(codigo);
	}
}