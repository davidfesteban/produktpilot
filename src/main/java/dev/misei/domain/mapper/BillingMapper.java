package dev.misei.domain.mapper;

import dev.misei.domain.entity.Billing;
import dev.misei.domain.payload.BillingPayload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillingMapper {
    BillingMapper INSTANCE = Mappers.getMapper(BillingMapper.class);

    @Mapping(target = "id", ignore = true)
    Billing toEntity(BillingPayload payload);

    BillingPayload toPayload(Billing entity);
}

