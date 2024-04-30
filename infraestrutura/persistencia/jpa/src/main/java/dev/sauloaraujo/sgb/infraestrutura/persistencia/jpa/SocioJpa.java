package dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sauloaraujo.sgb.administracao.dominio.socio.Socio;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioId;
import dev.sauloaraujo.sgb.administracao.dominio.socio.SocioRepositorio;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "SOCIO")
class SocioJpa {
	@Id
	int id;

	String nome;
	String email;

	@Override
	public String toString() {
		return nome;
	}
}

interface SocioJpaRepository extends JpaRepository<SocioJpa, Integer> {
}

@Repository
class SocioRepositorioImpl implements SocioRepositorio {
	@Autowired
	SocioJpaRepository repositorio;

	@Autowired
	JpaMapeador mapeador;

	@Override
	public void salvar(Socio socio) {
		var socioJpa = mapeador.map(socio, SocioJpa.class);
		repositorio.save(socioJpa);
	}

	@Override
	public Socio obter(SocioId id) {
		var socioJpa = repositorio.findById(id.getId()).get();
		return mapeador.map(socioJpa, Socio.class);
	}
}