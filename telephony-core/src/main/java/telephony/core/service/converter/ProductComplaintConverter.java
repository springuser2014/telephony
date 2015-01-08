package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.ContactsDao;
import telephony.core.dao.ProductsDao;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.ComplaintComment;
import telephony.core.entity.jpa.Contact;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.dto.*;

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

    public ProductComplaintEditDto toDto(ProductComplaint entity) {

        ProductComplaintEditDto dto = new ProductComplaintEditDto();
        dto.setComplaintId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setProductId(entity.getProduct().getId());
        dto.setReportedDate(entity.getReportedDate());
        dto.setTitle(entity.getTitle());
        dto.setUniqueHash(entity.getUniqueHash());
        dto.setStatus(entity.getStatus());

        return dto;
    }

    public ProductDetailedComplaintDto toDetailedDto(ProductComplaint entity) {

        ProductDetailedComplaintDto dto = new ProductDetailedComplaintDto();
        dto.setComplaintId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setProductId(entity.getProduct().getId());
        dto.setReportedDate(entity.getReportedDate());
        dto.setTitle(entity.getTitle());
        dto.setUniqueHash(entity.getUniqueHash());
        dto.setStatus(entity.getStatus());

        for (ComplaintComment cc :entity.getComments()) {
            dto.addComment(toDetailedComplaintCommentDto(cc));
        }

        return dto;
    }

    public DetailedComplaintCommentDto toDetailedComplaintCommentDto(ComplaintComment cc) {

        DetailedComplaintCommentDto dto = new DetailedComplaintCommentDto();
        dto.setCommentId(cc.getId());
        dto.setComment(cc.getContent());
        dto.setAuthor(cc.getAuthor());
        dto.setComplaintId(cc.getComplaint().getId());

        return dto;
    }
}
