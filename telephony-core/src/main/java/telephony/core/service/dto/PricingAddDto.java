package telephony.core.service.dto;

import java.util.Date;

public class PricingAddDto {

    Double rate;
    Date from;

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

}
