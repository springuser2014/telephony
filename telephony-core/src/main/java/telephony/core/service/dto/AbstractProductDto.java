package telephony.core.service.dto;

public abstract
class AbstractProductDto<T extends ProductTaxAddDto, P extends PricingAddDto> {

    private String imei;
    private String color;

    private Double priceIn;

    private P currentPrice;

    private T productTax;

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

    public P getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(P currentPrice) {
        this.currentPrice = currentPrice;
    }

    public T getProductTax() {
        return productTax;
    }

    public void setProductTax(T productTax) {
        this.productTax = productTax;
    }
}
