package dev.sauloaraujo.sgb.administracao.dominio.socio;

import org.apache.commons.validator.routines.EmailValidator;
import org.jmolecules.ddd.types.ValueObject;

public class Email implements ValueObject {
	private final String endereco;

	public Email(String endereco) {
		boolean passou = EmailValidator.getInstance().isValid(endereco);
		if (!passou) {
			throw new IllegalArgumentException("Endereço inválido");
		}

		this.endereco = endereco;
	}

	public String getEndereco() {
		return endereco;
	}

	@Override
	public String toString() {
		return endereco;
	}
}