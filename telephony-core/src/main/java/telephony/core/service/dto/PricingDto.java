package telephony.core.service.dto;

import java.util.Date;

public class PricingDto extends PricingAddDto {

    Date to;

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
