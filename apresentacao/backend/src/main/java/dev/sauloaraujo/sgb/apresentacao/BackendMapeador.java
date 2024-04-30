package dev.sauloaraujo.sgb.apresentacao;

import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorId;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.ExemplarId;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;
import dev.sauloaraujo.sgb.acervo.dominio.livro.IsbnFabrica;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Livro;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioId;
import dev.sauloaraujo.sgb.apresentacao.acervo.livro.LivroFormulario.LivroDto;

@Component
public class BackendMapeador extends ModelMapper {
	private IsbnFabrica isbnFabrica;

	BackendMapeador() {
		isbnFabrica = new IsbnFabrica();

		addConverter(new AbstractConverter<LivroDto, Livro>() {
			@Override
			protected Livro convert(LivroDto source) {
				var id = map(source.id, Isbn.class);
				List<AutorId> autores = map(source.autores, new TypeToken<List<AutorId>>() {
				}.getType());

				return new Livro(id, source.titulo, source.subTitulo, autores);
			}
		});

		addConverter(new AbstractConverter<String, Isbn>() {
			@Override
			protected Isbn convert(String source) {
				return isbnFabrica.construir(source);
			}
		});

		addConverter(new AbstractConverter<Integer, SocioId>() {
			@Override
			protected SocioId convert(Integer source) {
				return new SocioId(source);
			}
		});

		addConverter(new AbstractConverter<Integer, ExemplarId>() {
			@Override
			protected ExemplarId convert(Integer source) {
				return new ExemplarId(source);
			}
		});
	}

	@Override
	public <D> D map(Object source, Class<D> destinationType) {
		return source != null ? super.map(source, destinationType) : null;
	}
}