package telephony.core.service;

import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.query.filter.SaleComplaintFilterCriteria;
import telephony.core.service.dto.SaleComplaintDto;
import telephony.core.service.dto.SaleComplaintEditDto;
import telephony.core.service.dto.request.ReportSaleComplaintRequest;
import telephony.core.service.dto.request.SaleComplaintEditRequest;
import telephony.core.service.dto.request.SaleComplaintFetchRequest;
import telephony.core.service.dto.response.ReportSaleComplaintResponse;
import telephony.core.service.dto.response.SaleComplaintEditResponse;
import telephony.core.service.dto.response.SaleComplaintFetchResponse;

/**
 * asd.
 */
public interface SaleComplaintService extends ComplaintService
        <SaleComplaint,
         ReportSaleComplaintResponse, ReportSaleComplaintRequest, SaleComplaintDto,
         SaleComplaintEditResponse, SaleComplaintEditRequest, SaleComplaintEditDto,
         SaleComplaintFetchResponse, SaleComplaintFetchRequest, SaleComplaintFilterCriteria> {


}
