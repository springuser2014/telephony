package telephony.core.service.dto.response;

import telephony.core.service.dto.SaleComplaintDto;

import java.util.List;

public class SaleComplaintFetchResponse extends ComplaintFetchResponse {

    private List<SaleComplaintDto> complaints;

    public List<SaleComplaintDto> getComplaints() {
        return complaints;
    }

    public void setComplaints(List<SaleComplaintDto> complaints) {
        this.complaints = complaints;
    }

    public void addComplaint(SaleComplaintDto dto) {

        if (!complaints.contains(dto)) {
            complaints.add(dto);
        }
    }

    public void removeComplaint(SaleComplaintDto dto) {

        if (complaints.contains(dto)) {
            complaints.remove(dto);
        }
    }
}
