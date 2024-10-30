package dev.sauloaraujo.sgb.apresentacao.acervo.livro;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.sauloaraujo.sgb.acervo.dominio.exemplar.EmprestimoServico;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Livro;
import dev.sauloaraujo.sgb.acervo.dominio.livro.LivroServico;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioId;
import dev.sauloaraujo.sgb.aplicacao.acervo.autor.AutorServicoAplicacao;
import dev.sauloaraujo.sgb.aplicacao.acervo.livro.LivroServicoAplicacao;
import dev.sauloaraujo.sgb.apresentacao.BackendMapeador;
import dev.sauloaraujo.sgb.apresentacao.acervo.livro.LivroFormulario.LivroDto;

@RestController
@RequestMapping("backend/livro")
class LivroControlador {
	private @Autowired AutorServicoAplicacao autorServicoConsulta;
	private @Autowired EmprestimoServico emprestimoServico;
	private @Autowired LivroServico livroServico;
	private @Autowired LivroServicoAplicacao livroServicoConsulta;

	private @Autowired BackendMapeador mapeador;

	@RequestMapping(method = GET, path = "pesquisa")
	List<? extends Object> pesquisar(@RequestParam(required = false, defaultValue = "false") boolean expandir) {
		if (expandir) {
			return livroServicoConsulta.pesquisarResumosExpandidos();
		} else {
			return livroServicoConsulta.pesquisarResumos();
		}
	}

	@RequestMapping(method = GET, path = "criacao")
	LivroFormulario criacao() {
		var livro = new LivroDto();
		var autores = autorServicoConsulta.pesquisarResumos();
		return new LivroFormulario(livro, autores);
	}

	@RequestMapping(method = POST, path = "salvar")
	void salvar(@RequestBody LivroDto dto) {
		dto.id = null;
		var livro = mapeador.map(dto, Livro.class);
		livroServico.salvar(livro);
	}

	@RequestMapping(method = POST, path = "{id}/realizar-emprestimo")
	void realizarEmprestimo(@PathVariable("id") String id, @RequestBody int socio) {
		var livroId = mapeador.map(id, Isbn.class);
		var socioId = mapeador.map(socio, SocioId.class);
		emprestimoServico.realizarEmprestimo(livroId, socioId);
	}
}