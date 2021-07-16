package br.com.zupacademy.murilo.mercadolivre.imagem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;

@Entity
public class Imagem {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String link;

    @NotNull
    @ManyToOne
    private Produto produto;

    @Deprecated
    private Imagem() {
    }

    public Imagem(@NotNull @URL String link, @NotNull Produto produto) {
        this.link = link;
        this.produto = produto;
    }
}
