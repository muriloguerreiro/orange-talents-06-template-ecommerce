package br.com.zupacademy.murilo.mercadolivre.imagem;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class CadastroImagemForm {
	
	@NotNull
    @Size(min = 1)
    private List<MultipartFile> imagens = new ArrayList<>();
	
    public CadastroImagemForm(@NotNull @Size(min = 1) List<MultipartFile> imagens) {
		this.imagens.addAll(imagens);
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}
}
