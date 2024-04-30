package dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa;

import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import dev.sauloaraujo.analise.dominio.emprestimo.EmprestimoRegistro;
import dev.sauloaraujo.analise.dominio.emprestimo.EmprestimoRegistroId;
import dev.sauloaraujo.sgb.acervo.dominio.autor.Autor;
import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorId;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Emprestimo;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Exemplar;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.ExemplarId;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Periodo;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;
import dev.sauloaraujo.sgb.acervo.dominio.livro.IsbnFabrica;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Livro;
import dev.sauloaraujo.sgb.administracao.dominio.socio.Email;
import dev.sauloaraujo.sgb.administracao.dominio.socio.Socio;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioId;

@Component
class JpaMapeador extends ModelMapper {
	private IsbnFabrica isbnFabrica;

	private @Autowired LivroJpaRepository livroRepositorio;
	private @Autowired SocioJpaRepository socioRepositorio;

	JpaMapeador() {
		isbnFabrica = new IsbnFabrica();

		var configuracao = getConfiguration();
		configuracao.setFieldMatchingEnabled(true);
		configuracao.setFieldAccessLevel(AccessLevel.PRIVATE);

		addConverter(new AbstractConverter<AutorJpa, Autor>() {
			@Override
			protected Autor convert(AutorJpa source) {
				var id = map(source.id, AutorId.class);
				return new Autor(id, source.nome);
			}
		});

		addConverter(new AbstractConverter<Integer, AutorId>() {
			@Override
			protected AutorId convert(Integer source) {
				return new AutorId(source);
			}
		});

		addConverter(new AbstractConverter<AutorJpa, AutorId>() {
			@Override
			protected AutorId convert(AutorJpa source) {
				return map(source.id, AutorId.class);
			}
		});

		addConverter(new AbstractConverter<LivroJpa, Livro>() {
			@Override
			protected Livro convert(LivroJpa source) {
				var id = map(source.id, Isbn.class);
				List<AutorId> autores = map(source.autores, new TypeToken<List<AutorId>>() {
				}.getType());
				return new Livro(id, source.titulo, source.subtitulo, autores);
			}
		});

		addConverter(new AbstractConverter<String, Isbn>() {
			@Override
			protected Isbn convert(String source) {
				return isbnFabrica.construir(source);
			}
		});

		addConverter(new AbstractConverter<LivroJpa, Isbn>() {
			@Override
			protected Isbn convert(LivroJpa source) {
				return map(source.id, Isbn.class);
			}
		});

		addConverter(new AbstractConverter<SocioJpa, Socio>() {
			@Override
			protected Socio convert(SocioJpa source) {
				var id = map(source.id, SocioId.class);
				var email = map(source.email, Email.class);
				return new Socio(id, source.nome, email);
			}
		});

		addConverter(new AbstractConverter<Integer, SocioId>() {
			@Override
			protected SocioId convert(Integer source) {
				return new SocioId(source);
			}
		});

		addConverter(new AbstractConverter<String, Email>() {
			@Override
			protected Email convert(String source) {
				return new Email(source);
			}
		});

		addConverter(new AbstractConverter<ExemplarJpa, Exemplar>() {
			@Override
			protected Exemplar convert(ExemplarJpa source) {
				var id = map(source.id, ExemplarId.class);
				var livro = map(source.livro, Isbn.class);
				var emprestimo = map(source.emprestimo, Emprestimo.class);
				return new Exemplar(id, livro, emprestimo);
			}
		});

		addConverter(new AbstractConverter<Integer, ExemplarId>() {
			@Override
			protected ExemplarId convert(Integer source) {
				return new ExemplarId(source);
			}
		});

		addConverter(new AbstractConverter<Isbn, LivroJpa>() {
			@Override
			protected LivroJpa convert(Isbn source) {
				return livroRepositorio.findById(source.getCodigo()).get();
			}
		});

		addConverter(new AbstractConverter<EmprestimoJpa, Emprestimo>() {
			@Override
			protected Emprestimo convert(EmprestimoJpa source) {
				var periodo = map(source.periodo, Periodo.class);
				var tomador = map(source.tomador, SocioId.class);
				return new Emprestimo(periodo, tomador);
			}
		});

		addConverter(new AbstractConverter<PeriodoJpa, Periodo>() {
			@Override
			protected Periodo convert(PeriodoJpa source) {
				return new Periodo(source.inicio, source.fim);
			}
		});

		addConverter(new AbstractConverter<SocioJpa, SocioId>() {
			@Override
			protected SocioId convert(SocioJpa source) {
				return map(source.id, SocioId.class);
			}
		});

		addConverter(new AbstractConverter<SocioId, SocioJpa>() {
			@Override
			protected SocioJpa convert(SocioId source) {
				return socioRepositorio.findById(source.getId()).get();
			}
		});

		addConverter(new AbstractConverter<EmprestimoRegistroJpa, EmprestimoRegistro>() {
			@Override
			protected EmprestimoRegistro convert(EmprestimoRegistroJpa source) {
				var id = map(source.id, EmprestimoRegistroId.class);
				var exemplar = map(source.exemplar.id, ExemplarId.class);
				var emprestimo = map(source.emprestimo, Emprestimo.class);
				return new EmprestimoRegistro(id, exemplar, emprestimo, source.devolucao);
			}
		});

		addConverter(new AbstractConverter<Integer, EmprestimoRegistroId>() {
			@Override
			protected EmprestimoRegistroId convert(Integer source) {
				return new EmprestimoRegistroId(source);
			}
		});
	}

	@Override
	public <D> D map(Object source, Class<D> destinationType) {
		return source != null ? super.map(source, destinationType) : null;
	}
}