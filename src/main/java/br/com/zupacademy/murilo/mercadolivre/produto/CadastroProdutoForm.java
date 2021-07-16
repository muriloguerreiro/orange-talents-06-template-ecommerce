package br.com.zupacademy.murilo.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.murilo.mercadolivre.caracteristica.CadastroCaracteristicaForm;
import br.com.zupacademy.murilo.mercadolivre.categoria.Categoria;
import br.com.zupacademy.murilo.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.murilo.mercadolivre.usuario.Usuario;
import br.com.zupacademy.murilo.mercadolivre.validacao.anotacao.Existe;

public class CadastroProdutoForm {

	@NotBlank
	private String nome;

	@NotNull
	@Positive
	private BigDecimal valor;

	@NotNull
	@PositiveOrZero
	private Integer quantidadeDisponivel;
	
	@Size(min = 3)
	@Valid
	private Set<CadastroCaracteristicaForm> caracteristicas = new HashSet<>();

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@NotNull
	@Existe(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaId;

	public CadastroProdutoForm(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidadeDisponivel, @NotBlank @Size(max = 1000) String descricao,
			@NotNull Long categoriaId, @Size(min = 3) @Valid Set<CadastroCaracteristicaForm> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
		this.caracteristicas.addAll(caracteristicas);
	}
	
	/**
	 * Método que converte os dados recebidos para a classe Produto.
	 * @param nome deve ser obrigatório. 
	 * @param valor deve ser obrigatório e maior que zero.
	 * @param quantidadeDisponivel deve ser obrigatório e maior ou igual a zero.
	 * @param caracteristicas deve ser um conjunto de no mínimo 3 características.
	 * @param descricao deve ser obrigatória e ter no máximo 1000 caracteres.
	 * @param categoria não pode ser nula.
	 * @param usuarioDono não pode ser nulo.
	 */

	public Produto converter(Usuario usuarioDono, CategoriaRepository categoriaRepository) {
		Optional<Categoria> buscaCategoria = categoriaRepository.findById(categoriaId);
		
		Categoria categoria = buscaCategoria.get();
		
		return new Produto(nome, valor, quantidadeDisponivel, caracteristicas, descricao, usuarioDono, categoria);
	}

}
