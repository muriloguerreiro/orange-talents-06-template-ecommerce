package br.com.zupacademy.murilo.mercadolivre.pergunta;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;
import br.com.zupacademy.murilo.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.murilo.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos/{id}/pergunta")
public class PerguntaController {
	
	@Autowired
	private EmailParaVendedor emailParaVendedor;

	@Autowired
	private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroPerguntaForm perguntaForm, @PathVariable Long id, @AuthenticationPrincipal Usuario usuarioPergunta) {
        Optional<Produto> buscaProduto = produtoRepository.findById(id);
        
        if (buscaProduto.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
        
        Produto produto = buscaProduto.get();
        Pergunta pergunta = perguntaForm.converter(usuarioPergunta, produto);

        produto.adicionaPergunta(pergunta);
        produtoRepository.save(produto);
        
        emailParaVendedor.enviar(pergunta);
        
        return ResponseEntity.ok().build();
    }
	
}
