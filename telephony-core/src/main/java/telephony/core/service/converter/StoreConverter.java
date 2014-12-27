package telephony.core.service.converter;

import telephony.core.entity.jpa.Store;
import telephony.core.service.dto.StoreDto;

public class StoreConverter {

    public StoreDto toDto(Store store) {
        StoreDto dto = new StoreDto();

        dto.setLabel(store.getLabel());
        dto.setStoreId(store.getId());

        return dto;
    }

    public Store toEntity(StoreDto dto) {
        Store entity = new Store();
        entity.setLabel(dto.getLabel());

        return entity;
    }

    public void updateDto(Store entity, StoreDto dto) {

        dto.setLabel(entity.getLabel());
        dto.setStoreId(entity.getId());
    }

    public void updateEntity(StoreDto dto, Store entity) {

        entity.setId(dto.getStoreId());
        entity.setLabel(dto.getLabel());
    }
}
