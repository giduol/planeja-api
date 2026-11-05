package io.github.go.planeja.dominio.cartao;

import io.github.go.planeja.dominio.cartao.dto.CartaoDetalhes;
import io.github.go.planeja.dominio.cartao.dto.CartaoForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cartoes")
@Tag(name = "Cartoes", description = "Gerenciamento de cartoes")
public class CartaoController {

    @Autowired
    private CartaoService service;

    @PostMapping
    @Operation(
            summary = "Criar cartão",
            description = "Cria um novo cartão"
    )
    public ResponseEntity<CartaoDetalhes> criar(@RequestBody @Valid CartaoForm novo){
        CartaoDetalhes detalhes = service.criar(novo);
        return ResponseEntity.status(HttpStatus.CREATED).body(detalhes);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os cartões",
            description = "Retorna a lista de todos os cartões cadastrados"
    )
    public ResponseEntity<List<CartaoDetalhes>> listarTodos(){
        List<CartaoDetalhes> cartoes = service.listarTodos();
        return ResponseEntity.ok(cartoes);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Obter detalhes do cartão",
            description = "Retorna os detalhes de um cartão específico"
    )
    public ResponseEntity<CartaoDetalhes> obterDetalhes(@PathVariable UUID id){
        CartaoDetalhes detalhes = service.obterDetalhes(id);
        return ResponseEntity.status(HttpStatus.OK).body(detalhes);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Atualizar cartão",
            description = "Atualiza os dados de um cartão específico"
    )
    public ResponseEntity<CartaoDetalhes> atualizar(@PathVariable UUID id, @RequestBody @Valid CartaoForm cartaoAtualizado){
        service.atualizar(id, cartaoAtualizado);
        return ResponseEntity.noContent().build();
    }
}
