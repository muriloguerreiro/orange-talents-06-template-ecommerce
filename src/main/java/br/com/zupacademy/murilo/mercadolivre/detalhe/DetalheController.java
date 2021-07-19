package br.com.zupacademy.murilo.mercadolivre.detalhe;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.murilo.mercadolivre.produto.Produto;
import br.com.zupacademy.murilo.mercadolivre.produto.ProdutoRepository;

@RestController
@RequestMapping("/produtos/{id}")
public class DetalheController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping
	public ResponseEntity<DetalheDto> detalhar(@PathVariable Long id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		
		if (produto.isPresent()) {
			DetalheDto detalheDto = new DetalheDto(produto.get());
			return ResponseEntity.ok(detalheDto);
		}
		
		return ResponseEntity.notFound().build();
	}
}