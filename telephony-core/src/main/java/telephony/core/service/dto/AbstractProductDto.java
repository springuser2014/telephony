package telephony.core.service.dto;

public abstract class AbstractProductDto {

    private String imei;
    private String color;

    private Double priceIn;

    private PricingDto currentPrice;

    private ProductTaxDto productTax;

    public Double getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(Double priceIn) {
        this.priceIn = priceIn;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public PricingDto getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(PricingDto currentPrice) {
        this.currentPrice = currentPrice;
    }

    public ProductTaxDto getProductTax() {
        return productTax;
    }

    public void setProductTax(ProductTaxDto productTax) {
        this.productTax = productTax;
    }
}
