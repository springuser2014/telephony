package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.ProductComplaintDao;
import telephony.core.dao.SaleComplaintDao;
import telephony.core.entity.jpa.ComplaintComment;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.dto.AnonymousComplaintCommentDto;
import telephony.core.service.dto.ComplaintCommentDto;

import java.util.Date;

import static telephony.core.assertion.CommonAssertions.*;

public class ComplaintCommentConverter {

    @Inject
    ProductComplaintDao productComplaintDao;

    @Inject
    SaleComplaintDao saleComplaintDao;

    public ComplaintComment toEntity(ComplaintCommentDto complaintComment) {

        ComplaintComment cc = new ComplaintComment();

        SaleComplaint sc = saleComplaintDao.findById(complaintComment.getComplaintId());
        ProductComplaint pc = productComplaintDao.findById(complaintComment.getComplaintId());

        if (isNotNull(sc)) {
//            cc.setComplaint(sc);
        }

        if (isNotNull(pc)) {
//            cc.setComplaint(pc);
        }

        cc.setAuthor(complaintComment.getAuthor());
        cc.setContent(complaintComment.getComment());
        cc.setReportedDate(new Date());

        return cc;
    }

    public ComplaintComment toEntity(AnonymousComplaintCommentDto complaintComment) {
        ComplaintComment cc = new ComplaintComment();

        SaleComplaint sc = saleComplaintDao.findByHash(complaintComment.getHashUnique());
        ProductComplaint pc = productComplaintDao.findByHash(complaintComment.getHashUnique());

        if (isNotNull(sc)) {
//            cc.setComplaint(sc);
        }

        if (isNotNull(pc)) {
//            cc.setComplaint(pc);
        }

        cc.setAuthor(complaintComment.getAuthor());
        cc.setContent(complaintComment.getComment());
        cc.setReportedDate(new Date());

        return cc;
    }
}
