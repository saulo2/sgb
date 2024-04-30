package dev.sauloaraujo.sgb.apresentacao.acervo.autor;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorServico;
import dev.sauloaraujo.sgb.aplicacao.acervo.autor.AutorResumo;
import dev.sauloaraujo.sgb.aplicacao.acervo.autor.AutorServicoAplicacao;
import dev.sauloaraujo.sgb.apresentacao.BackendMapeador;

@RestController
@RequestMapping("autor")
class AutorControlador {
	private @Autowired AutorServico autorServico;
	private @Autowired AutorServicoAplicacao autorServicoConsulta;

	private @Autowired BackendMapeador mapeador;

	@RequestMapping(method = GET, path = "pesquisa")
	List<AutorResumo> pesquisa() {
		return autorServicoConsulta.pesquisarResumos();
	}
}