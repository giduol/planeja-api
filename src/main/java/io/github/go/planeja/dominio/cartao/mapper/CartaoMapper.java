package io.github.go.planeja.dominio.cartao.mapper;

import io.github.go.planeja.dominio.cartao.dto.CartaoDetalhes;
import io.github.go.planeja.dominio.cartao.dto.CartaoForm;
import io.github.go.planeja.dominio.cartao.model.CartaoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CartaoMapper {
    CartaoEntity toEntity(CartaoForm form);

    CartaoDetalhes toDetalhes(CartaoEntity entity);

    void updateEntity(@MappingTarget CartaoEntity entity, CartaoForm dadosAtualizados);
}
