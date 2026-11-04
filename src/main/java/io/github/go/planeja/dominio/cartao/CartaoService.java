package io.github.go.planeja.dominio.cartao;


import io.github.go.planeja.common.exceptions.RegistroNaoEncontradoException;
import io.github.go.planeja.common.exceptions.ValidationException;
import io.github.go.planeja.dominio.cartao.dto.CartaoDetalhes;
import io.github.go.planeja.dominio.cartao.dto.CartaoForm;
import io.github.go.planeja.dominio.cartao.mapper.CartaoMapper;
import io.github.go.planeja.dominio.cartao.model.CartaoEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CartaoService {

    @Autowired
    private CartaoValidator validator;

    @Autowired
    private CartaoRepository repository;

    @Autowired
    private CartaoMapper mapper;

    public CartaoDetalhes criar(CartaoForm form){
        var result = validator.validar(form, null);

        if(result.isInvalido()){
            throw new ValidationException(result.getCamposInvalidos());
        }

        CartaoEntity entity = mapper.toEntity(form);
        repository.save(entity);
        return mapper.toDetalhes(entity);
    }

    public CartaoDetalhes obterDetalhes(UUID id){
        return repository.findById(id)
                .map(mapper::toDetalhes)
                .orElseThrow(() -> new RegistroNaoEncontradoException());
    }

    @Transactional
    public void atualizar(UUID id, CartaoForm cartaoAtualizado){
        var entity = repository.findById(id)
                .orElseThrow(() -> new RegistroNaoEncontradoException());

        var result = validator.validar(cartaoAtualizado, id);
        if(result.isInvalido()) {
            throw new ValidationException(result.getCamposInvalidos());
        }
        mapper.updateEntity(entity, cartaoAtualizado);
    }
}
