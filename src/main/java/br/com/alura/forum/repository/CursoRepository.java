package br.com.alura.forum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.forum.modelo.Curso;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {

	Curso findByNome(String nome);
}
