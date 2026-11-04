package io.github.go.planeja.dominio.cartao;

import io.github.go.planeja.dominio.cartao.dto.CartaoDetalhes;
import io.github.go.planeja.dominio.cartao.dto.CartaoForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @PostMapping
    public ResponseEntity<CartaoDetalhes> criar(@RequestBody @Valid CartaoForm novo){
        CartaoDetalhes detalhes = service.criar(novo);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

    @GetMapping("{id}")
    public ResponseEntity<CartaoDetalhes> obterDetalhes(@PathVariable UUID id){
        CartaoDetalhes detalhes = service.obterDetalhes(id);
        return ResponseEntity.status(HttpStatus.OK).body(detalhes);
    }

    @PutMapping("{id}")
    public ResponseEntity<CartaoDetalhes> atualizar(@PathVariable UUID id, @RequestBody @Valid CartaoForm cartaoAtualizado){
        service.atualizar(id, cartaoAtualizado);
        return ResponseEntity.noContent().build();
    }
}
