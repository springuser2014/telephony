package telephony.core.service.converter;

import telephony.core.entity.jpa.Store;
import telephony.core.service.dto.StoreDto;

public class StoreConverter {

    public static StoreDto toDto(Store store) {
        StoreDto dto = new StoreDto();

        dto.setLabel(store.getLabel());
        dto.setStoreId(store.getId());

        return dto;
    }

    public static Store toEntity(StoreDto dto) {
        Store entity = new Store();
        entity.setLabel(dto.getLabel());

        return entity;
    }

    public static void updateDto(Store entity, StoreDto dto) {

        dto.setLabel(entity.getLabel());
        dto.setStoreId(entity.getId());
    }


    public static void updateEntity(StoreDto dto, Store entity) {

        entity.setId(dto.getStoreId());
        entity.setLabel(dto.getLabel());
    }
}
