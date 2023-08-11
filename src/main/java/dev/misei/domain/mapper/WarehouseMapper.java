package dev.misei.domain.mapper;

import dev.misei.domain.entity.Warehouse;
import dev.misei.domain.payload.WarehousePayload;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    Warehouse toEntity(WarehousePayload payload);

    WarehousePayload toPayload(Warehouse entity);
}
