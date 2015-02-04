package telephony.core.service.dto.response;

import telephony.core.service.dto.ProductComplaintEditDto;

import java.util.List;

public class ProductComplaintFetchResponse extends ComplaintFetchResponse {

    private List<ProductComplaintEditDto> complaints;
    private Long countTotal;

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

    public Long getCountTotal() {
        return countTotal;
    }

    public void setCountTotal(Long countTotal) {
        this.countTotal = countTotal;
    }
}
