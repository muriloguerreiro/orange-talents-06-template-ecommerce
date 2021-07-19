package br.com.zupacademy.murilo.mercadolivre.pergunta;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;
import br.com.zupacademy.murilo.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotNull
	@ManyToOne
	@Valid
	private Usuario usuarioPergunta;

	@NotNull
	@ManyToOne
	@Valid
	private Produto produto;

	@Deprecated
	private Pergunta() {
	}

	public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario usuarioPergunta, @NotNull @Valid Produto produto) {
		this.titulo = titulo;
		this.usuarioPergunta = usuarioPergunta;
		this.produto = produto;
	}

	public String getTitulo() {
		return titulo;
	}

}
