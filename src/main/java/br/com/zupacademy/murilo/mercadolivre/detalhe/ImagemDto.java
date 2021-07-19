package br.com.zupacademy.murilo.mercadolivre.detalhe;

import br.com.zupacademy.murilo.mercadolivre.imagem.Imagem;

public class ImagemDto {
	
	private String link;

	public ImagemDto(Imagem imagem) {
		this.link = imagem.getLink();
	}

	public String getLink() {
		return link;
	}
	
}
