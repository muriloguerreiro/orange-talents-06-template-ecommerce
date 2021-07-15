package br.com.zupacademy.murilo.mercadolivre.categoria;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.murilo.mercadolivre.validacao.anotacao.ExisteOuNulo;
import br.com.zupacademy.murilo.mercadolivre.validacao.anotacao.ValorUnico;

public class CadastroCategoriaForm {

	@NotBlank
	@ValorUnico(domainClass = Categoria.class, fieldName = "nome")
	private String nome;

	@ExisteOuNulo(domainClass = Categoria.class, fieldName = "id")
	private Long categoriaMaeId;

	public CadastroCategoriaForm(@NotBlank String nome, Long categoriaMaeId) {
		this.nome = nome;
		this.categoriaMaeId = categoriaMaeId;
	}
	
	/**
	 * Método que converte os dados recebidos para a classe Categoria.
	 * @param nome é obrigatório e deve ser unico.
	 * @param categoriaMaeId deve existir no banco de dados ou ser nulo.
	 */
	public Categoria converter(CategoriaRepository categoriaRepo) {
		Categoria categoria = new Categoria(nome);
		
		if (categoriaMaeId != null) {
			Optional<Categoria> buscaCategoria = categoriaRepo.findById(categoriaMaeId);
			categoria.setCategoriaMae(buscaCategoria.get());
		}

		return categoria;
	}

}
