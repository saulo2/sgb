package dev.sauloaraujo.sgb;

import static java.util.Objects.nonNull;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import dev.sauloaraujo.sgb.acervo.dominio.livro.Livro;

public class LivroFormulario extends VerticalLayout {
	private static final long serialVersionUID = -4762635896445399303L;

	private TextField isbnCampo;
	private TextField tituloCampo;
	private TextField subtituloCampo;

	public LivroFormulario(boolean edicao) {
		setPadding(false);

		isbnCampo = new TextField("ISBN");
		isbnCampo.setWidthFull();
		isbnCampo.setRequired(true);
		isbnCampo.setReadOnly(edicao);
		add(isbnCampo);

		tituloCampo = new TextField("Título");
		tituloCampo.setWidthFull();
		tituloCampo.setRequired(true);
		add(tituloCampo);

		subtituloCampo = new TextField("Subtítulo");
		subtituloCampo.setWidthFull();
		add(subtituloCampo);
	}

	public void ler(Livro livro) {
		nonNull(livro);

		var isbn = livro.getId().toString();
		var titulo = livro.getTitulo();
		var subtitulo = livro.getSubTitulo();

		setValue(isbnCampo, isbn);
		setValue(tituloCampo, titulo);
		setValue(subtituloCampo, subtitulo);
	}

	private void setValue(TextField campo, String valor) {
		if (valor != null) {
			campo.setValue(valor);
		} else {
			campo.setValue("");
		}
	}

	public void escrever(Livro livro) {
		nonNull(livro);

		livro.setTitulo(valor(tituloCampo));
		livro.setSubTitulo(valor(subtituloCampo));
	}

	private String valor(TextField campo) {
		var valor = campo.getValue();
		return valor.length() > 0 ? valor : null;
	}
}