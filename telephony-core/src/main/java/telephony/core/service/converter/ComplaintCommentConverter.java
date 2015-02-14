package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.ProductComplaintDao;
import telephony.core.dao.SaleComplaintDao;
import telephony.core.entity.jpa.*;
import telephony.core.service.dto.AnonymousComplaintCommentDto;
import telephony.core.service.dto.ComplaintCommentDto;

import java.util.Date;

import static telephony.core.assertion.CommonAssertions.*;

public class ComplaintCommentConverter {

    @Inject
    ProductComplaintDao productComplaintDao;

    @Inject
    SaleComplaintDao saleComplaintDao;

    public ProductComplaintComment toEntity(ComplaintCommentDto complaintComment) {

        ProductComplaintComment cc = new ProductComplaintComment();

        ProductComplaint pc = productComplaintDao.findById(complaintComment.getComplaintId());

        if (isNotNull(pc)) {
            cc.setComplaint(pc);
        }

        cc.setAuthor(complaintComment.getAuthor());
        cc.setContent(complaintComment.getComment());
        cc.setReportedDate(new Date());

        return cc;
    }

    public ProductComplaintComment toEntity(AnonymousComplaintCommentDto complaintComment) {
        ProductComplaintComment cc = new ProductComplaintComment();

        ProductComplaint pc = productComplaintDao.findByHash(complaintComment.getHashUnique());

        if (isNotNull(pc)) {
            cc.setComplaint(pc);
        }

        cc.setAuthor(complaintComment.getAuthor());
        cc.setContent(complaintComment.getComment());
        cc.setReportedDate(new Date());

        return cc;
    }

    public SaleComplaintComment toSaleComplaintEntity(ComplaintCommentDto complaintComment) {

        SaleComplaintComment cc = new SaleComplaintComment();

        SaleComplaint sc = saleComplaintDao.findById(complaintComment.getComplaintId());

        if (isNotNull(sc)) {
            cc.setComplaint(sc);
        }

        cc.setAuthor(complaintComment.getAuthor());
        cc.setContent(complaintComment.getComment());
        cc.setReportedDate(new Date());

        return cc;
    }

    public SaleComplaintComment toSaleComplaintEntity(AnonymousComplaintCommentDto complaintComment) {
        SaleComplaintComment cc = new SaleComplaintComment();

        SaleComplaint sc = saleComplaintDao.findByHash(complaintComment.getHashUnique());

        if (isNotNull(sc)) {
            cc.setComplaint(sc);
        }

        cc.setAuthor(complaintComment.getAuthor());
        cc.setContent(complaintComment.getComment());
        cc.setReportedDate(new Date());

        return cc;
    }
}
