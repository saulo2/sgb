package dev.sauloaraujo.sgb;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import dev.sauloaraujo.sgb.aplicacao.acervo.livro.LivroResumo;
import dev.sauloaraujo.sgb.aplicacao.acervo.livro.LivroServicoAplicacao;
import jakarta.annotation.PostConstruct;

@Route("/livro/pesquisar")
public class LivroPesquisarRota extends VerticalLayout implements AfterNavigationObserver {
	private static final long serialVersionUID = -9031401005322963865L;

	private @Autowired LivroServicoAplicacao servico;

	private Grid<LivroResumo> grade;

	@PostConstruct
	private void configurar() {
		var titulo = new H1("Livros");
		add(titulo);

		grade = new Grid<>(LivroResumo.class, false);
		grade.addComponentColumn(this::link).setHeader("ISBN");
		grade.addColumn("titulo").setHeader("Título");
		add(grade);
	}

	private RouterLink link(LivroResumo resumo) {
		var id = resumo.getId();
		var titulo = resumo.getTitulo();

		return new RouterLink(titulo, LivroEditarRota.class, id);
	}

	@Override
	public void afterNavigation(AfterNavigationEvent event) {
		var livros = servico.pesquisarResumos();
		grade.setItems(livros);
	}
}