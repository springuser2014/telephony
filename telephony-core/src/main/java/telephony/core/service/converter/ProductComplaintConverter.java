package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.ContactsDao;
import telephony.core.dao.ProductsDao;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.ProductComplaintEditDto;

import static telephony.core.assertion.CommonAssertions.*;

public class ProductComplaintConverter {

    @Inject
    ProductsDao productsDao;

    @Inject
    ContactsDao contactsDao;

    public ProductComplaint toEntity(ProductComplaintDto dto) {

        Product product = productsDao.findById(dto.getProductId());

        ProductComplaint pc = new ProductComplaint();
        pc.setDescription(dto.getDescription());
        pc.setReportedDate(dto.getReportedDate());
        pc.setStatus(ComplaintStatus.JUST_REPORTED);
        pc.setTitle(dto.getTitle());
        pc.setUniqueHash(dto.getUniqueHash());
        pc.setProduct(product);

        return pc;
    }

    public void update(ProductComplaint productComplaint, ProductComplaintEditDto complaintEditDto) {

        if (isNotNull(complaintEditDto.getDescription())) {
            productComplaint.setDescription(complaintEditDto.getDescription());
        }

        if (isNotNull(complaintEditDto.getReportedDate())) {
            productComplaint.setReportedDate(complaintEditDto.getReportedDate());
        }

        if (isNotNull(complaintEditDto.getStatus())) {
            productComplaint.setStatus(complaintEditDto.getStatus());
        }

        if (isNotNull(complaintEditDto.getTitle())) {
            productComplaint.setTitle(complaintEditDto.getTitle());
        }

        if (isNotNull(complaintEditDto.getProductId())) {
            Product product = productsDao.findById(complaintEditDto.getProductId());
            productComplaint.setProduct(product);
        }

        if(isNotEmpty(complaintEditDto.getUniqueHash())) {
            productComplaint.setUniqueHash(complaintEditDto.getUniqueHash());
        }
    }
}
