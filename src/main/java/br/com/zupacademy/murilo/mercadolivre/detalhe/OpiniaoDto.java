package br.com.zupacademy.murilo.mercadolivre.detalhe;

import br.com.zupacademy.murilo.mercadolivre.opiniao.Opiniao;

public class OpiniaoDto {
	
	private String titulo;
	private Integer nota;
	private String descricao;
	
	public OpiniaoDto(Opiniao opiniao) {
		this.titulo = opiniao.getTitulo();
		this.nota = opiniao.getNota();
		this.descricao = opiniao.getDescricao();
	}

	public String getTitulo() {
		return titulo;
	}

	public Integer getNota() {
		return nota;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
