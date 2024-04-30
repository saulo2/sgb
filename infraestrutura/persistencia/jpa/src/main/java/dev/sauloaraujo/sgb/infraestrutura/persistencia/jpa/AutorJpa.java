package dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sauloaraujo.sgb.acervo.dominio.autor.Autor;
import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorId;
import dev.sauloaraujo.sgb.acervo.dominio.autor.AutorRepositorio;
import dev.sauloaraujo.sgb.aplicacao.acervo.autor.AutorRepositorioAplicacao;
import dev.sauloaraujo.sgb.aplicacao.acervo.autor.AutorResumo;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "AUTOR")
class AutorJpa {
	@Id
	int id;

	String nome;

	@ManyToMany(mappedBy = "autores")
	Set<LivroJpa> livros;

	@Override
	public String toString() {
		return nome;
	}
}

interface AutorJpaRepository extends JpaRepository<AutorJpa, Integer> {
	List<AutorResumo> findAutorResumoByOrderByNome();
}

@Repository
class AutorRepositorioImpl implements AutorRepositorio, AutorRepositorioAplicacao {
	@Autowired
	AutorJpaRepository repositorio;

	@Autowired
	JpaMapeador mapeador;

	@Override
	public void salvar(Autor autor) {
		var autorJpa = mapeador.map(autor, AutorJpa.class);
		repositorio.save(autorJpa);
	}

	@Override
	public Autor obter(AutorId id) {
		var autorJpa = repositorio.findById(id.getId()).get();
		return mapeador.map(autorJpa, Autor.class);
	}

	@Override
	public List<AutorResumo> pesquisarResumos() {
		return repositorio.findAutorResumoByOrderByNome();
	}
}