package br.com.zupacademy.murilo.mercadolivre.detalhe;

import br.com.zupacademy.murilo.mercadolivre.pergunta.Pergunta;

public class PerguntaDto {
	
	private String titulo;
	
	public PerguntaDto(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
	}

	public String getTitulo() {
		return titulo;
	}
	
}
