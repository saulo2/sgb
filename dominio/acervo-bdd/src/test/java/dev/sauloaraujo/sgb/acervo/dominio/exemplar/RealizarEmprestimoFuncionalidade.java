package dev.sauloaraujo.sgb.acervo.dominio.exemplar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import dev.sauloaraujo.sgb.acervo.dominio.AcervoFuncionalidade;
import dev.sauloaraujo.sgb.acervo.dominio.autor.Autor;
import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorId;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Exemplar.EmprestimoRealizadoEvento;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Livro;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioId;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RealizarEmprestimoFuncionalidade extends AcervoFuncionalidade {
	private AutorId autorId = new AutorId(1);
	private Isbn livroId = isbnFabrica.construir("9788582606162");
	private ExemplarId exemplarId = new ExemplarId(1);
	private SocioId socioId = new SocioId(1);

	private RuntimeException excecao;

	@Given("um {string} {string} disponível")
	public void um_p1_p2_disponível(String classe, String disponibilidade) {
		var autor = new Autor(autorId, "Andrew Tanenbaum");
		autorServico.salvar(autor);

		var livro = new Livro(livroId, "Sistemas operacionais modernos", null, Arrays.asList(new AutorId(1)));
		livroServico.salvar(livro);

		var exemplar = new Exemplar(exemplarId, livroId, null);
		exemplarServico.salvar(exemplar);

		if ("não está".equals(disponibilidade)) {
			emprestimoServico.realizarEmprestimo(exemplarId, socioId);
		}
	}

	@When("um sócio solicita o empréstimo do exemplar")
	public void um_sócio_solicita_o_empréstimo_do_exemplar() {
		try {
			emprestimoServico.realizarEmprestimo(exemplarId, socioId);
		} catch (IllegalArgumentException | IllegalStateException excecao) {
			this.excecao = excecao;
		}
	}

	@Then("o sistema realiza o empréstimo")
	public void o_sistema_realiza_o_empréstimo() {
		var exemplar = exemplarServico.obter(exemplarId);
		assertTrue(exemplar.indisponivel());
	}

	@Then("o sistema notifica a realização do empréstimo")
	public void o_sistema_notifica_a_realização_do_empréstimo() {
		assertEquals(1, eventos.size());

		var evento = eventos.get(0);
		assertTrue(evento instanceof EmprestimoRealizadoEvento);

		var emprestimoRealizado = (EmprestimoRealizadoEvento) evento;
		var exemplarId = emprestimoRealizado.getExemplar();
		assertTrue(this.exemplarId.equals(exemplarId));
	}

	@Then("o sistema informa que o {string} não está disponível")
	public void o_sistema_informa_que_o_p1_não_está_disponível(String classe) {
		assertNotNull(excecao);
	}

	@When("um sócio solicita o empréstimo do livro")
	public void um_sócio_solicita_o_empréstimo_do_livro() {
		try {
			emprestimoServico.realizarEmprestimo(livroId, socioId);
		} catch (IllegalArgumentException excecao) {
			this.excecao = excecao;
		}
	}
}