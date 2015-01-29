package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import telephony.core.entity.enumz.ComplaintStatus;
import telephony.core.entity.jpa.ProductComplaint;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductComplaintDto extends ComplaintDto {

    Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}