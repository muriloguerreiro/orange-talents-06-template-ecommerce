package br.com.zupacademy.murilo.mercadolivre.caracteristica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;

@Entity
public class Caracteristica {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    @ManyToOne
    private Produto produto;

	public Caracteristica(@NotBlank String nome, @NotBlank String descricao, @NotNull Produto produto) {
		this.nome = nome;
		this.descricao = descricao;
		this.produto = produto;
	}
}
