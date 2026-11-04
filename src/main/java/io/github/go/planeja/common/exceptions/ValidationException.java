package io.github.go.planeja.common.exceptions;

import io.github.go.planeja.common.validation.CampoInvalido;

import java.util.List;

public class ValidationException extends RuntimeException{

    private List<CampoInvalido> camposInvalidos;

    public ValidationException(List<CampoInvalido> camposInvalidos) {
        super("Erro de validação.");
        this.camposInvalidos = camposInvalidos;
    }

    public List<CampoInvalido> getCamposInvalidos() {
        return camposInvalidos;
    }
}
