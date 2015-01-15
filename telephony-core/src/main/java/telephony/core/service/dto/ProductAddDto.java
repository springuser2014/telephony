package telephony.core.service.dto;

public class ProductAddDto extends AbstractProductDto {

	private String model;
	private String producer;

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
}
