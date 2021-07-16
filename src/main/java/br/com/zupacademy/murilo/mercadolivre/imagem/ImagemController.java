package br.com.zupacademy.murilo.mercadolivre.imagem;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;
import br.com.zupacademy.murilo.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.murilo.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos/{id}/imagens")
public class ImagemController {
	
	@Autowired
	private ProdutoRepository produtoRepository;

    @Autowired
    private Uploader fakeUploader;

    public ImagemController(ProdutoRepository produtoRepository, Uploader fakeUploader) {
        this.produtoRepository = produtoRepository;
        this.fakeUploader = fakeUploader;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@Valid CadastroImagemForm imagemForm, @PathVariable Long id, @AuthenticationPrincipal Usuario usuarioDono) {
        Optional<Produto> buscaProduto = produtoRepository.findById(id);
        
        if (buscaProduto.isEmpty() || !buscaProduto.get().pertenceAoUsuario(usuarioDono) ) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
        
        Produto produto = buscaProduto.get();

        Set<String> links = fakeUploader.enviar(imagemForm.getImagens());
        produto.adicionaImagens(links);
        produtoRepository.save(produto);
        
        return ResponseEntity.ok().build();
    }

}
