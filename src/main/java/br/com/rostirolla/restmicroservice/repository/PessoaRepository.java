package br.com.rostirolla.restmicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.rostirolla.restmicroservice.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	
	@Query("SELECT p FROM Pessoa p WHERE p.nome LIKE %:nome%")
	List<Pessoa> findAllByNome(@Param("nome")String nome);

}
