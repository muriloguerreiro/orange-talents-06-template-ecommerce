package br.com.zupacademy.murilo.mercadolivre.detalhe;

import java.math.BigDecimal;
import java.util.Set;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;

public class DetalheDto {
	
	private Set<ImagemDto> imagens;
	private String nome;
	private BigDecimal valor;
	private String categoriaNome;
	private Set<CaracteristicaDto> caracteristicas;
	private String descricao;
	private Double mediaNotas;
	private Integer totalNotas;
	private Set<OpiniaoDto> opinioes;
	private Set<PerguntaDto> perguntas;
	
	public DetalheDto(Produto produto) {
		this.imagens = produto.getImagensDto();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.categoriaNome = produto.getCategoriaNome();
		this.caracteristicas = produto.getCaracteristicasDto();
		this.descricao = produto.getDescricao();
		this.mediaNotas = produto.getMediaNotas();
		this.totalNotas = produto.getTotalNotas();
		this.opinioes = produto.getOpinioesDto();
		this.perguntas = produto.getPerguntasDto();
	}

	public Set<ImagemDto> getImagens() {
		return imagens;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getCategoriaNome() {
		return categoriaNome;
	}

	public Set<CaracteristicaDto> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Double getMediaNotas() {
		return mediaNotas;
	}

	public Integer getTotalNotas() {
		return totalNotas;
	}

	public Set<OpiniaoDto> getOpinioes() {
		return opinioes;
	}

	public Set<PerguntaDto> getPerguntas() {
		return perguntas;
	}
	
}
