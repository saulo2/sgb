package dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.sauloaraujo.sgb.acervo.dominio.livro.Isbn;
import dev.sauloaraujo.sgb.acervo.dominio.livro.Livro;
import dev.sauloaraujo.sgb.acervo.dominio.livro.LivroRepositorio;
import dev.sauloaraujo.sgb.aplicacao.acervo.livro.LivroRepositorioAplicacao;
import dev.sauloaraujo.sgb.aplicacao.acervo.livro.LivroResumo;
import dev.sauloaraujo.sgb.aplicacao.acervo.livro.LivroResumoExpandido;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

@Entity
@Table(name = "LIVRO")
class LivroJpa {
	@Id
	String id;

	String titulo;
	String subtitulo;

	@ManyToMany
	@JoinTable(name = "LIVRO_AUTOR", joinColumns = @JoinColumn(name = "LIVRO_ID"), inverseJoinColumns = @JoinColumn(name = "AUTOR_ID"))
	@OrderColumn(name = "AUTOR_ORDEM")
	List<AutorJpa> autores;

	@OneToMany(mappedBy = "livro")
	Set<ExemplarJpa> exemplares;

	@Override
	public String toString() {
		return titulo;
	}
}

interface LivroJpaRepository extends JpaRepository<LivroJpa, String> {
	List<LivroResumo> findLivroResumoBy();

	// @formatter:off
	@Query("""
			SELECT l                  AS livro,
                   a                  AS autor,
                   COUNT(e)           AS exemplaresDisponiveis,
			       SIZE(l.exemplares) AS totalExemplares
			  FROM LivroJpa     l
	    JOIN FETCH l.autores    a
         LEFT JOIN l.exemplares e
                ON e.emprestimo IS NULL	    
			 WHERE INDEX(a) = 0
          GROUP BY l
          ORDER BY l.titulo
			""")
	// @formatter:on	
	List<LivroResumoExpandido> pesquisarResumosExpandidos();
}

@Repository
class LivroRepositorioImpl implements LivroRepositorio, LivroRepositorioAplicacao {
	@Autowired
	LivroJpaRepository repositorio;

	@Autowired
	JpaMapeador mapeador;

	@Override
	public void salvar(Livro livro) {
		var livroJpa = mapeador.map(livro, LivroJpa.class);
		repositorio.save(livroJpa);
	}

	@Transactional
	@Override
	public Livro obter(Isbn isbn) {
		var codigo = isbn.getCodigo();
		var livroJpa = repositorio.findById(codigo).get();
		return mapeador.map(livroJpa, Livro.class);
	}

	@Override
	public List<LivroResumo> pesquisarResumos() {
		return repositorio.findLivroResumoBy();
	}

	@Override
	public List<LivroResumoExpandido> pesquisarResumosExpandidos() {
		return repositorio.pesquisarResumosExpandidos();
	}
}