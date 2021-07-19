package br.com.zupacademy.murilo.mercadolivre.pergunta;

import org.springframework.stereotype.Component;

@Component
public class FakeEmailParaVendedor implements EmailParaVendedor {

	@Override
	public void enviar(Pergunta pergunta) {
		String tituloProduto = "Moto G8";
		String nomeComprador = "Maria";
		String nomeVendedor = "João";
		String instanteCriacao = "2021-07-19T09:46";
				
		System.out.println("Assunto: Nova pergunta sobre seu produto " + tituloProduto);
		System.out.println("De: " + nomeComprador);
		System.out.println("Para: " + nomeVendedor);
		System.out.println("Data do envio: " + instanteCriacao);
	}

}
