package br.com.zupacademy.murilo.mercadolivre.pergunta;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;
import br.com.zupacademy.murilo.mercadolivre.usuario.Usuario;

public class CadastroPerguntaForm {
	
	@NotBlank
    private String titulo;

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public CadastroPerguntaForm(@NotBlank String titulo) {
        this.titulo = titulo;
    }

	public Pergunta converter(Usuario usuarioPergunta, Produto produto) {
		return new Pergunta(titulo, usuarioPergunta, produto);
	}
}
