package dev.sauloaraujo.sgb.apresentacao.acervo.exemplar;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sauloaraujo.sgb.acervo.dominio.exemplar.EmprestimoServico;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.ExemplarId;
import dev.sauloaraujo.sgb.aplicacao.acervo.exemplar.ExemplarResumo;
import dev.sauloaraujo.sgb.aplicacao.acervo.exemplar.ExemplarResumoExpandido;
import dev.sauloaraujo.sgb.aplicacao.acervo.exemplar.ExemplarServicoAplicacao;
import dev.sauloaraujo.sgb.apresentacao.BackendMapeador;

@RestController
@RequestMapping("exemplar")
class ExemplarControlador {
	private @Autowired EmprestimoServico emprestimoServico;
	private @Autowired ExemplarServicoAplicacao exemplarServicoConsulta;

	private @Autowired BackendMapeador mapeador;

	@RequestMapping(method = GET, path = "pesquisa")
	List<ExemplarResumo> pesquisa() {
		return exemplarServicoConsulta.pesquisarResumos();
	}

	@RequestMapping(method = GET, path = "pesquisa-emprestados")
	List<ExemplarResumoExpandido> pesquisaEmprestados() {
		return exemplarServicoConsulta.pesquisarEmprestados();
	}

	@RequestMapping(method = POST, path = "{id}/devolver")
	void realizarEmprestimo(@PathVariable("id") int id) {
		var exemplarId = mapeador.map(id, ExemplarId.class);
		emprestimoServico.devolver(exemplarId);
	}
}