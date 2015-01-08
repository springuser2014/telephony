package telephony.core.service.dto.response;

import telephony.core.service.dto.SaleComplaintDto;
import telephony.core.service.dto.SaleComplaintEditDto;

import java.util.List;

public class SaleComplaintFetchResponse extends ComplaintFetchResponse {

    private List<SaleComplaintEditDto> complaints;

    public List<SaleComplaintEditDto> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<SaleComplaintEditDto> complaints) {
        this.complaints = complaints;
    }

    public void addComplaint(SaleComplaintEditDto dto) {

        if (!complaints.contains(dto)) {
            complaints.add(dto);
        }
    }

    public void removeComplaint(SaleComplaintEditDto dto) {

        if (complaints.contains(dto)) {
            complaints.remove(dto);
        }
    }
}
