package telephony.core.service.dto.response;

import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.ProductComplaintEditDto;

import java.util.List;

public class ProductComplaintFetchResponse extends ComplaintFetchResponse {

    private List<ProductComplaintEditDto> complaints;

    public List<ProductComplaintEditDto> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<ProductComplaintEditDto> complaints) {
        this.complaints = complaints;
    }

    public void addComplaint(ProductComplaintEditDto dto) {

        if (!complaints.contains(dto)) {
            complaints.add(dto);
        }
    }

    public void removeComplaint(ProductComplaintEditDto dto) {

        if (complaints.contains(dto)) {
            complaints.remove(dto);
        }
    }
}
