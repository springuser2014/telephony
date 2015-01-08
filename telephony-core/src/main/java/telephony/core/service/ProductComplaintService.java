package telephony.core.service;

import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.query.filter.ProductComplaintFilterCriteria;
import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.ProductComplaintEditDto;
import telephony.core.service.dto.ProductDetailedComplaintDto;
import telephony.core.service.dto.request.ProductComplaintDetailsFetchRequest;
import telephony.core.service.dto.request.ProductComplaintEditRequest;
import telephony.core.service.dto.request.ProductComplaintFetchRequest;
import telephony.core.service.dto.request.ReportProductComplaintRequest;
import telephony.core.service.dto.response.ProductComplaintDetailsFetchResponse;
import telephony.core.service.dto.response.ProductComplaintEditResponse;
import telephony.core.service.dto.response.ProductComplaintFetchResponse;
import telephony.core.service.dto.response.ReportProductComplaintResponse;

/**
 * asd.
 */
public interface ProductComplaintService extends ComplaintService
        <ProductComplaint,
        ProductComplaintDetailsFetchResponse, ProductComplaintDetailsFetchRequest, ProductDetailedComplaintDto,
        ReportProductComplaintResponse, ReportProductComplaintRequest, ProductComplaintDto,
        ProductComplaintEditResponse, ProductComplaintEditRequest, ProductComplaintEditDto,
        ProductComplaintFetchResponse, ProductComplaintFetchRequest, ProductComplaintFilterCriteria> {

}
