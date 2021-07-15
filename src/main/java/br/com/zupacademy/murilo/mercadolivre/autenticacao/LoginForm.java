package br.com.zupacademy.murilo.mercadolivre.autenticacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	@NotBlank
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;

	public LoginForm(@NotBlank String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(login, senha);
	}

}
