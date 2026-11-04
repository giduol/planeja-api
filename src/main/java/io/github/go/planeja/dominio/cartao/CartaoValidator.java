package io.github.go.planeja.dominio.cartao;


import io.github.go.planeja.common.validation.CampoInvalido;
import io.github.go.planeja.common.validation.ValidationResult;
import io.github.go.planeja.dominio.cartao.dto.CartaoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CartaoValidator {

    @Autowired
    private CartaoRepository cartaoRepository;

    public ValidationResult validar(CartaoForm form, UUID id){
        var result = ValidationResult.novo();

        var isListaNaoVazia = !cartaoRepository.findByNomeAndNotId((form.nome()), id).isEmpty();
        if(isListaNaoVazia) {
            result.add(new CampoInvalido("nome", "Já existe um cartão com esse nome"));
        }

        return result;
    };
}
