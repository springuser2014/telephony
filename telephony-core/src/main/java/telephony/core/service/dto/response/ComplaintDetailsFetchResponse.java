package telephony.core.service.dto.response;

import telephony.core.service.dto.DetailedComplaintDto;

public abstract class ComplaintDetailsFetchResponse<T extends DetailedComplaintDto> extends BasicResponse {

    T detailedComplaint;

    public T getDetailedComplaint() {
        return detailedComplaint;
    }

    public void setDetailedComplaint(T detailedComplaint) {
        this.detailedComplaint = detailedComplaint;
    }
}
