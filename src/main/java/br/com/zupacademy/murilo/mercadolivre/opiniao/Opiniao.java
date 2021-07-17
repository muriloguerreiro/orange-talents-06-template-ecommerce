package br.com.zupacademy.murilo.mercadolivre.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;
import br.com.zupacademy.murilo.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(1)
	@Max(5)
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	@NotNull
	@ManyToOne
	@Valid
	private Usuario usuarioOpiniao;

	@NotNull
	@ManyToOne
	@Valid
	private Produto produto;

	@Deprecated
	private Opiniao() {
	}

	public Opiniao(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull @Valid Usuario usuarioOpiniao,
			@NotNull @Valid Produto produto) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuarioOpiniao = usuarioOpiniao;
		this.produto = produto;
	}

}
