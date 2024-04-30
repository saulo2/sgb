package dev.sauloaraujo.sgb.acervo.dominio.livro;

import org.apache.commons.validator.routines.ISBNValidator;

class Isbn10 extends Isbn {
	Isbn10(String codigo) {
		super(codigo);
	}

	@Override
	boolean testarCodigo(String codigo) {
		return ISBNValidator.getInstance().isValidISBN10(codigo);
	}
}