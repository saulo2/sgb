package dev.sauloaraujo.sgb.acervo.dominio.livro;

import org.jmolecules.ddd.types.Identifier;
import org.jmolecules.ddd.types.ValueObject;

public abstract class Isbn implements ValueObject, Identifier {
	private final String codigo;

	Isbn(String codigo) {
		var passou = testarCodigo(codigo);
		if (!passou) {
			throw new IllegalArgumentException("Código inválido");
		}

		this.codigo = codigo;
	}

	abstract boolean testarCodigo(String codigo);

	public String getCodigo() {
		return codigo;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Isbn) {
			var isbn = (Isbn) obj;
			return codigo.equals(isbn.codigo);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return codigo.hashCode();
	}

	@Override
	public String toString() {
		return codigo;
	}
}