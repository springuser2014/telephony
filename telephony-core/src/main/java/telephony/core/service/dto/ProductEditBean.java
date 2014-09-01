package telephony.core.service.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductEditBean {
	
	private Long id;

	private String model;
	private String producer;
	private String imei;
	private String color;
	
	private Double priceIn;
	private String priceFrom;
	private String priceTo;
	
	private Long taxId;
	private String taxFrom;
	private String taxTo;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
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
	public Double getPriceIn() {
		return priceIn;
	}
	public void setPriceIn(Double priceIn) {
		this.priceIn = priceIn;
	}
	public Long getTaxId() {
		return taxId;
	}
	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}
	public String getTaxFrom() {
		return taxFrom;
	}
	public void setTaxFrom(Date taxFrom) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		this.taxFrom = sdf.format(taxFrom);
	}
	public String getTaxTo() {
		return taxTo;
	}
	public void setTaxTo(Date taxTo) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		this.taxTo = sdf.format(taxTo);
	}
	public String getPriceFrom() {
		return priceFrom;
	}
	public void setPriceFrom(Date priceFrom) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		this.priceFrom = sdf.format(priceFrom);
	}
	public String getPriceTo() {
		return priceTo;
	}
	public void setPriceTo(Date priceTo) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		this.priceTo = sdf.format(priceTo);
	}
	

}
