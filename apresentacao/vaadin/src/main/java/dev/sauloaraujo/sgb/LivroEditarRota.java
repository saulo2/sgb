package dev.sauloaraujo.sgb;

import static com.vaadin.flow.component.UI.getCurrent;
import static com.vaadin.flow.component.button.ButtonVariant.LUMO_PRIMARY;
import static com.vaadin.flow.component.notification.Notification.Position.TOP_CENTER;
import static com.vaadin.flow.component.notification.NotificationVariant.LUMO_SUCCESS;
import static com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment.END;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;

import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;
import dev.sauloaraujo.sgb.acervo.dominio.livro.IsbnFabrica;
import dev.sauloaraujo.sgb.acervo.dominio.livro.LivroServico;
import jakarta.annotation.PostConstruct;

@Route("livro/alterar")
public class LivroEditarRota extends VerticalLayout implements HasUrlParameter<String> {
	private static final long serialVersionUID = -405348699654790564L;

	private @Autowired LivroServico servico;

	private LivroFormulario formulario;
	private Isbn isbn;

	@PostConstruct
	private void configurar() {
		var titulo = new H1("Edição de livro");
		add(titulo);

		formulario = new LivroFormulario(true);
		add(formulario);

		var salvar = new Button("Salvar");
		salvar.addThemeVariants(LUMO_PRIMARY);
		salvar.addClickListener(this::salvar);
		add(salvar);
		setHorizontalComponentAlignment(END, salvar);
	}

	@Override
	public void setParameter(BeforeEvent event, String parameter) {
		var fabrica = new IsbnFabrica();
		isbn = fabrica.construir(parameter);

		var livro = servico.obter(isbn);
		formulario.ler(livro);
	}

	private void salvar(ClickEvent<Button> evento) {
		var livro = servico.obter(isbn);
		formulario.escrever(livro);
		servico.salvar(livro);

		var notificacao = new Notification("Livro salvo com sucesso", 10000, TOP_CENTER);
		notificacao.addThemeVariants(LUMO_SUCCESS);
		notificacao.open();

		var ui = getCurrent();
		var pagina = ui.getPage();
		var historico = pagina.getHistory();
		historico.back();
	}
}