package telephony.core.service.dto.request;

import telephony.core.service.dto.ProductComplaintDto;
import telephony.core.service.dto.SessionDto;

public class ReportProductComplaintRequest extends ReportComplaintRequest<ProductComplaintDto> {

    public ReportProductComplaintRequest() {
        super();
    }

    public ReportProductComplaintRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
