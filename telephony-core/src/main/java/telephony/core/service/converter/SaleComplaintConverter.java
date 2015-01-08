package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.SalesDao;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.*;
import telephony.core.service.dto.*;

import static telephony.core.assertion.CommonAssertions.isNotEmpty;
import static telephony.core.assertion.CommonAssertions.isNotNull;

public class SaleComplaintConverter {

    @Inject
    SalesDao salesDao;

    public SaleComplaint toEntity(SaleComplaintDto dto) {

        Sale sale = salesDao.findById(dto.getSaleId());

        SaleComplaint pc = new SaleComplaint();
        pc.setDescription(dto.getDescription());
        pc.setReportedDate(dto.getReportedDate());
        pc.setStatus(ComplaintStatus.JUST_REPORTED);
        pc.setTitle(dto.getTitle());
        pc.setUniqueHash(dto.getUniqueHash());
        pc.setSale(sale);

        return pc;
    }

    public void update(SaleComplaint saleComplaint, SaleComplaintEditDto complaintEditDto) {

        if (isNotNull(complaintEditDto.getDescription())) {
            saleComplaint.setDescription(complaintEditDto.getDescription());
        }

        if (isNotNull(complaintEditDto.getReportedDate())) {
            saleComplaint.setReportedDate(complaintEditDto.getReportedDate());
        }

        if (isNotNull(complaintEditDto.getStatus())) {
            saleComplaint.setStatus(complaintEditDto.getStatus());
        }

        if (isNotNull(complaintEditDto.getTitle())) {
            saleComplaint.setTitle(complaintEditDto.getTitle());
        }

        if (isNotNull(complaintEditDto.getSaleId())) {
            Sale sale = salesDao.findById(complaintEditDto.getSaleId());
            saleComplaint.setSale(sale);
        }

        if (isNotEmpty(complaintEditDto.getUniqueHash())) {
            saleComplaint.setUniqueHash(complaintEditDto.getUniqueHash());
        }
    }

    public SaleDetailedComplaintDto toDetailedDto(SaleComplaint entity) {

        SaleDetailedComplaintDto dto = new SaleDetailedComplaintDto();
        dto.setComplaintId(entity.getId());
        dto.setDescription(entity.getDescription());
        dto.setSaleId(entity.getSale().getId());
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
