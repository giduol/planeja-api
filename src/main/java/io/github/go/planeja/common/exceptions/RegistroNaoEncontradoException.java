package io.github.go.planeja.common.exceptions;

public class RegistroNaoEncontradoException extends RuntimeException {

    public RegistroNaoEncontradoException() {
        super("Registro não encontrado");
    }
}
