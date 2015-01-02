package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.ContactsDao;
import telephony.core.dao.ProductsDao;
import telephony.core.dao.StoresDao;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.Store;
import telephony.core.service.dto.DetailedSaleDto;
import telephony.core.service.dto.SaleAddDto;
import telephony.core.service.dto.SaleDto;

public class SaleConverter {

    @Inject
    ProductConverter productConverter;

    @Inject
    ContactConverter contactConverter;

    @Inject
    StoreConverter storeConverter;

    @Inject
    ProductsDao productsDao;

    @Inject
    ContactsDao contactsDao;

    @Inject
    StoresDao storesDao;

    public void toSaleDto(SaleDto dto , Sale entity) {

        dto.setContactId(entity.getContact().getId());
        dto.setDateOut(entity.getDateOut());
        dto.setId(entity.getId());
        dto.setLabel(entity.getLabel());
        dto.setStoreId(entity.getStore().getId());

        for(Product product : entity.getProducts()) {
            dto.addProduct(productConverter.toProductDto(product));
        }
    }

    public SaleDto toSaleDto(Sale entity) {

        SaleDto dto = new SaleDto();
        dto.setContactId(entity.getContact().getId());
        dto.setDateOut(entity.getDateOut());
        dto.setId(entity.getId());
        dto.setLabel(entity.getLabel());
        dto.setStoreId(entity.getStore().getId());

        for(Product product : entity.getProducts()) {
            dto.addProduct(productConverter.toProductDto(product));
        }

        return dto;
    }

    public DetailedSaleDto toDetailedSaleDto(Sale entity) {

        DetailedSaleDto dto = new DetailedSaleDto();

        toSaleDto(dto, entity);

        dto.setContact(contactConverter.contactToContactDto(entity.getContact()));
        dto.setStore(storeConverter.toStoreDto(entity.getStore()));

        return dto;
    }

    public Sale toEntity(SaleAddDto dto) {
        Sale entity = new Sale();

        entity.setDateOut(dto.getDateOut());
        entity.setLabel(dto.getLabel());

        Contact contact = contactsDao.findById(dto.getContactId());
        Store store = storesDao.findById(dto.getStoreId());

        entity.setContact(contact);
        entity.setStore(store);

        for (Long productId : dto.getProductsIds()) {
            Product product = productsDao.findById(productId);
            entity.addProduct(product);
        }

        return entity;
    }
}
