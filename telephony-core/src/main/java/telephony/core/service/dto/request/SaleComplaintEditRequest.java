package telephony.core.service.dto.request;

import telephony.core.service.dto.SaleComplaintEditDto;
import telephony.core.service.dto.SessionDto;

public class SaleComplaintEditRequest extends ComplaintEditRequest<SaleComplaintEditDto> {

    public SaleComplaintEditRequest(SessionDto sessionDto) {
        super(sessionDto);
    }
}
