package br.com.zupacademy.murilo.mercadolivre.caracteristica;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;

public class CadastroCaracteristicaForm {

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	public CadastroCaracteristicaForm(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroCaracteristicaForm other = (CadastroCaracteristicaForm) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	/**
	 * Método que converte os dados recebidos para a classe Caracteristica.
	 * @param nome deve ser obrigatório. 
	 * @param descricao deve ser obrigatória.
	 * @param produto não pode ser nulo.
	 */

	public Caracteristica converter(Produto produto) {
		return new Caracteristica(nome, descricao, produto);
	}
}
