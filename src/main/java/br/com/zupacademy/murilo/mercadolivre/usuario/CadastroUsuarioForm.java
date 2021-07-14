package br.com.zupacademy.murilo.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CadastroUsuarioForm {

	@NotBlank
	@Email
	private String login;

	@NotBlank
	@Size(min = 6)
	private String senha;

	public CadastroUsuarioForm(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	/**
	 * Método que converte os dados recebidos para a classe Usuario.
	 * @param login é obrigatório e deve estar no formato de email.
	 * @param senha é obrigatória e deve ter no mínimo 6 caracteres.
	 */
	public Usuario converter() {
		return new Usuario(login, senha);
	}
	
}
