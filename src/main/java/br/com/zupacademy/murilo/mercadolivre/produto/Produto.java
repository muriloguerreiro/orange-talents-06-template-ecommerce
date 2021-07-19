package br.com.zupacademy.murilo.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.murilo.mercadolivre.caracteristica.CadastroCaracteristicaForm;
import br.com.zupacademy.murilo.mercadolivre.caracteristica.Caracteristica;
import br.com.zupacademy.murilo.mercadolivre.categoria.Categoria;
import br.com.zupacademy.murilo.mercadolivre.detalhe.CaracteristicaDto;
import br.com.zupacademy.murilo.mercadolivre.detalhe.ImagemDto;
import br.com.zupacademy.murilo.mercadolivre.detalhe.OpiniaoDto;
import br.com.zupacademy.murilo.mercadolivre.detalhe.PerguntaDto;
import br.com.zupacademy.murilo.mercadolivre.imagem.Imagem;
import br.com.zupacademy.murilo.mercadolivre.opiniao.Opiniao;
import br.com.zupacademy.murilo.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.murilo.mercadolivre.usuario.Usuario;

@Entity
public class Produto {
	
	@Id
    @GeneratedValue
    private Long id;
	
    @NotBlank
    private String nome;

    @NotNull @Positive
    private BigDecimal valor;

    @NotNull @PositiveOrZero
    private Integer quantidadeDisponivel;

    @Size(min = 3)
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<Caracteristica> caracteristicas = new HashSet<>();
    
    @NotBlank @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Usuario usuarioDono;

    @NotNull
    @ManyToOne
    private Categoria categoria;
    
    @NotNull
	private LocalDateTime instanteCadastro;
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Imagem> imagens = new HashSet<>();
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Opiniao> opinioes = new HashSet<>();
    
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Pergunta> perguntas = new HashSet<>();

    @Deprecated
    private Produto(){
    }

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor,
			@NotNull @PositiveOrZero Integer quantidadeDisponivel, @Size(min = 3) Set<CadastroCaracteristicaForm> caracteristicas,
			@NotBlank @Size(max = 1000) String descricao, @NotNull Usuario usuarioDono, @NotNull Categoria categoria) {
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.caracteristicas = caracteristicas.stream().map(caracteristica -> caracteristica.converter(this)).collect(Collectors.toSet());
		this.descricao = descricao;
		this.usuarioDono = usuarioDono;
		this.categoria = categoria;
		this.instanteCadastro = LocalDateTime.now();
	}
	
	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCategoriaNome() {
		return categoria.getNome();
	}
	
	public Double getMediaNotas() {
		List<Integer> notas = opinioes.stream().map(opiniao -> opiniao.getNota()).collect(Collectors.toList());
		
		Integer somaNotas = 0;
		for (Integer nota : notas) {
			somaNotas += nota;
		}
		
		return somaNotas/(double) getTotalNotas();
	}
	
	public Integer getTotalNotas() {
		Integer totalNotas = opinioes.size();
		return totalNotas;
	}
	
	public Set<CaracteristicaDto> getCaracteristicasDto() {
		return caracteristicas.stream().map(caracteristica -> new CaracteristicaDto(caracteristica)).collect(Collectors.toSet());
	}
	
	public Set<ImagemDto> getImagensDto() {
		return imagens.stream().map(imagem -> new ImagemDto(imagem)).collect(Collectors.toSet());
	}
	
	public Set<OpiniaoDto> getOpinioesDto() {
		return opinioes.stream().map(opiniao -> new OpiniaoDto(opiniao)).collect(Collectors.toSet());
	}
	
	public Set<PerguntaDto> getPerguntasDto() {
		return perguntas.stream().map(pergunta -> new PerguntaDto(pergunta)).collect(Collectors.toSet());
	}

	/**
	 * Método que verifica se o produto pertence ao usuario informado.
	 * @param usuario autenticado deve ser o usuario dono do produto.
	 */
	
	public boolean pertenceAoUsuario(Usuario usuario) {
		return this.usuarioDono.equals(usuario);
	}
	
	/**
	 * Método que associa os links das imagens ao Produto.
	 * @param images deve ser um conjunto de no mínimo uma imagem.
	 */
	
	public void adicionaImagens(Set<String> links) {
		this.imagens.addAll(links.stream().map(link -> new Imagem(link, this)).collect(Collectors.toSet()));
	}
	
	/**
	 * Método que adiciona uma opiniao à lista de opinioes sobre um Produto.
	 * @param opinioes deve ser uma lista de opinioes.
	 */
	
	public void adicionaOpiniao(Opiniao opiniao){
        this.opinioes.add(opiniao);
    }

	/**
	 * Método que adiciona uma pergunta à lista de perguntas sobre um Produto.
	 * @param perguntas deve ser uma lista de perguntas.
	 */
	
	public void adicionaPergunta(Pergunta pergunta) {
		this.perguntas.add(pergunta);
	}

}
