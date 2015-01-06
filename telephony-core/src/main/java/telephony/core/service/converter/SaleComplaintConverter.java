package telephony.core.service.converter;

import com.google.inject.Inject;
import telephony.core.dao.SalesDao;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.Product;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.entity.jpa.Sale;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.ProductComplaintEditDto;
import telephony.core.service.dto.SaleComplaintDto;
import telephony.core.service.dto.SaleComplaintEditDto;

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
}
