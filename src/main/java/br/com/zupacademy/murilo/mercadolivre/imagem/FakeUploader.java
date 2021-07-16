package br.com.zupacademy.murilo.mercadolivre.imagem;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FakeUploader implements Uploader {
	
    @Override
    public Set<String> enviar(List<MultipartFile> imagens) {
        return imagens.stream().map(imagem -> "https://bucket.io/imagens/" + imagem.getOriginalFilename()).collect(Collectors.toSet());
    }
}