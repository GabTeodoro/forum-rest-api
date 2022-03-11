package br.com.alura.forum.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.forum.modelo.Topico;

@Repository
public interface TopicoRepository extends CrudRepository<Topico, Long> {

	List<Topico> findByCursoNome(String nomeCurso);

}
