package br.com.alura.forum.controller.request;

import javax.validation.constraints.NotBlank;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;

public class TopicoRequest {

	@NotBlank
	private String titulo;

	@NotBlank
	private String mensagem;

	@NotBlank
	private String curso;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Topico ToTopico(CursoRepository cursoRepository) {

		Curso cursoObj = cursoRepository.findByNome(this.curso);
		Topico topico = new Topico(this.titulo, this.mensagem, cursoObj);
		return topico;
	}

}
