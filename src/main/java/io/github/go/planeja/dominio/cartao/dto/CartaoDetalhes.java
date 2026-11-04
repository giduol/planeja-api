package io.github.go.planeja.dominio.cartao.dto;

import java.time.LocalDateTime;

public record CartaoDetalhes (String id, String nome, String bandeira, LocalDateTime dataCadastro){
}
