package br.com.alura.forum.controller.request;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;

public class AtualizaTopicoRequest {

	@NotBlank
	private String titulo;

	@NotBlank
	private String mensagem;

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Topico atualizar(Long id, TopicoRepository topicoRepository) {

		Optional<Topico> topicoTemp = topicoRepository.findById(id);
		Topico topico = topicoTemp.get();
		topico.setTitulo(this.titulo);
		topico.setMensagem(this.mensagem);

		return topico;
	}
}
