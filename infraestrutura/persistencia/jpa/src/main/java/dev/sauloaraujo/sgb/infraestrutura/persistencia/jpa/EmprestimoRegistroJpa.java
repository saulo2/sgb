package dev.sauloaraujo.sgb.infraestrutura.persistencia.jpa;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.sauloaraujo.analise.dominio.emprestimo.EmprestimoRegistro;
import dev.sauloaraujo.analise.dominio.emprestimo.EmprestimoRegistroRepositorio;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.Emprestimo;
import dev.sauloaraujo.sgb.acervo.dominio.exemplar.ExemplarId;
import dev.sauloaraujo.sgb.aplicacao.analise.EmprestimoRegistroRepositorioAplicacao;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EMPRESTIMO_REGISTRO")
@AttributeOverrides({
		@AttributeOverride(name = "emprestimo.periodo.inicio", column = @Column(name = "EMPRESTIMO_PERIODO_INICIO")),
		@AttributeOverride(name = "emprestimo.periodo.fim", column = @Column(name = "EMPRESTIMO_PERIODO_FIM")) })
class EmprestimoRegistroJpa {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	int id;

	@ManyToOne
	ExemplarJpa exemplar;

	@Embedded
	EmprestimoJpa emprestimo;

	LocalDate devolucao;
}

interface EmprestimoRegistroJpaRepository extends JpaRepository<EmprestimoRegistroJpa, Integer> {
	Optional<EmprestimoRegistroJpa> findByExemplarIdAndEmprestimoAndDevolucaoIsNull(int exemplarId,
			EmprestimoJpa emprestimo);
}

@Repository
class EmprestimoRegistroRepositorioImpl
		implements EmprestimoRegistroRepositorio, EmprestimoRegistroRepositorioAplicacao {
	@Autowired
	EmprestimoRegistroJpaRepository repositorio;

	@Autowired
	JpaMapeador mapeador;

	@Override
	public void salvar(EmprestimoRegistro emprestimoRegistro) {
		var emprestimoRegistroJpa = mapeador.map(emprestimoRegistro, EmprestimoRegistroJpa.class);
		repositorio.save(emprestimoRegistroJpa);
	}

	@Override
	public EmprestimoRegistro buscar(ExemplarId exemplar, Emprestimo emprestimo) {
		var exemplarId = exemplar.getId();
		var emprestimoJpa = mapeador.map(emprestimo, EmprestimoJpa.class);
		var emprestimoRegistroJpa = repositorio
				.findByExemplarIdAndEmprestimoAndDevolucaoIsNull(exemplarId, emprestimoJpa).get();
		return mapeador.map(emprestimoRegistroJpa, EmprestimoRegistro.class);
	}
}