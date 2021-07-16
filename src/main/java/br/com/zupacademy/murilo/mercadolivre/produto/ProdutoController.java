package br.com.zupacademy.murilo.mercadolivre.produto;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.murilo.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.murilo.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CadastroProdutoForm produtoForm, @AuthenticationPrincipal Usuario usuarioDono) {
		Produto produto = produtoForm.converter(usuarioDono, categoriaRepository);
		produtoRepository.save(produto);
		return ResponseEntity.ok().build();
	}
}
