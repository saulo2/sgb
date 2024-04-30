package dev.sauloaraujo.sgb.acervo.dominio.exemplar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import dev.sauloaraujo.sgb.acervo.dominio.AcervoFuncionalidade;
import dev.sauloaraujo.sgb.acervo.dominio.autor.Autor;
import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorId;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Exemplar.ExemplarDevolvidoEvento;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Livro;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioId;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DevolverFuncionalidade extends AcervoFuncionalidade {
	private AutorId autorId = new AutorId(1);
	private Isbn livroId = isbnFabrica.construir("9788582606162");
	private ExemplarId exemplarId = new ExemplarId(1);
	private SocioId socioId = new SocioId(1);

	private RuntimeException excecao;

	@Given("um exemplar {string} emprestado")
	public void um_exemplar_p1_emprestado(String disponibilidade) {
		var autor = new Autor(autorId, "Andrew Tanenbaum");
		autorServico.salvar(autor);

		var livro = new Livro(livroId, "Sistemas operacionais modernos", null, Arrays.asList(new AutorId(1)));
		livroServico.salvar(livro);

		var exemplar = new Exemplar(exemplarId, livroId, null);
		exemplarServico.salvar(exemplar);

		if ("foi".startsWith(disponibilidade)) {
			emprestimoServico.realizarEmprestimo(exemplarId, socioId);
			eventos.clear();
		}
	}

	@When("o exemplar for devolvido")
	public void o_exemplar_for_devolvido() {
		try {
			emprestimoServico.devolver(exemplarId);
		} catch (IllegalArgumentException | IllegalStateException excecao) {
			this.excecao = excecao;
		}
	}

	@Then("o empréstimo é concluído com sucesso")
	public void o_empréstimo_é_concluído_com_sucesso() {
		var exemplar = exemplarServico.obter(exemplarId);
		assertTrue(exemplar.disponivel());
	}

	@Then("o sistema notifica a devolução do exemplar")
	public void o_sistema_notifica_a_devolução_do_exemplar() {
		assertEquals(1, eventos.size());

		var evento = eventos.get(0);
		assertTrue(evento instanceof ExemplarDevolvidoEvento);

		var exemplarDevolvido = (ExemplarDevolvidoEvento) evento;
		var exemplarId = exemplarDevolvido.getExemplar();
		assertTrue(this.exemplarId.equals(exemplarId));
	}

	@Then("o sistema informa que o exemplar não está emprestado")
	public void o_sistema_informa_que_o_exemplar_não_está_emprestado() {
		assertNotNull(excecao);
	}
}