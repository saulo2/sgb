package dev.sauloaraujo.analise.dominio.emprestimo;

import static org.apache.commons.lang3.Validate.isTrue;

import java.util.Objects;

import org.jmolecules.ddd.types.Identifier;
import org.jmolecules.ddd.types.ValueObject;

public class EmprestimoRegistroId implements ValueObject, Identifier {
	private final int id;

	public EmprestimoRegistroId(int id) {
		isTrue(id > 0, "O id deve ser positivo");

		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof EmprestimoRegistroId) {
			var emprestimoRegistroId = (EmprestimoRegistroId) obj;
			return id == emprestimoRegistroId.id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return Integer.toString(id);
	}
}