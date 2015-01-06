package telephony.core.service.dto.request;

import telephony.core.service.dto.SaleComplaintDto;
import telephony.core.service.dto.SessionDto;

public class ReportSaleComplaintRequest extends ReportComplaintRequest<SaleComplaintDto> {
    public ReportSaleComplaintRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
