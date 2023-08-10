package es.misei.everi.domain.mapper;

import es.misei.everi.domain.entity.Billing;
import es.misei.everi.domain.payload.BillingPayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BillingMapper {
    BillingMapper INSTANCE = Mappers.getMapper(BillingMapper.class);

    Billing toEntity(BillingPayload payload);

    BillingPayload toPayload(Billing entity);
}
