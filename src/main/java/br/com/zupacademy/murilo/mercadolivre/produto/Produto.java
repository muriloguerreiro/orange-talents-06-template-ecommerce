package br.com.zupacademy.murilo.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
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
}
