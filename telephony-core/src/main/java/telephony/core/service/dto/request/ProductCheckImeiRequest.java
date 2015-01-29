package telephony.core.service.dto.request;

import telephony.core.service.dto.SessionDto;

public class ProductCheckImeiRequest extends AuthRequest {

    private String imei;

    public ProductCheckImeiRequest() {
        super();
    }

    public ProductCheckImeiRequest(SessionDto sessionDto) {
        super(sessionDto);
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
