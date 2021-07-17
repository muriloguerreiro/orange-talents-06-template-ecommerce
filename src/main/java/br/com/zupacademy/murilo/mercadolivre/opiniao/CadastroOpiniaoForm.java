package br.com.zupacademy.murilo.mercadolivre.opiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;
import br.com.zupacademy.murilo.mercadolivre.usuario.Usuario;

public class CadastroOpiniaoForm {

	@NotNull
	@Min(1)
	@Max(5)
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	public CadastroOpiniaoForm(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opiniao converter(Usuario usuarioOpiniao, Produto produto) {
		return new Opiniao(nota, titulo, descricao, usuarioOpiniao, produto);
	}
}
