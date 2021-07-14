package br.com.zupacademy.murilo.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	@Column(nullable = false)
	private String login;

	@NotBlank
	@Size(min = 6)
	@Column(nullable = false)
	private String senha;

	@NotNull
	@PastOrPresent
	@Column(nullable = false)
	private LocalDateTime instanteCriacao;

	/**
	 * Método construtor da classe Usuario
	 * @param login é obrigatório e deve estar no formato de email.
	 * @param senha é obrigatória, mínimo de 6 caracteres e deve ser recebida limpa.
	 */
	public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		this.instanteCriacao = LocalDateTime.now();
	}

}
