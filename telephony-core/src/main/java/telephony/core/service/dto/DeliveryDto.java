package telephony.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeliveryDto
extends AbstractDeliveryDto {
	
	private List<ProductEditDto> products;

	private Long id;
	
	public DeliveryDto() {
		products = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ProductEditDto> getProducts() {
		return products;
	}

	public void setProducts(List<ProductEditDto> products) {
		this.products = products;
	}

	public void addProduct(ProductEditDto dto) {

		if (!products.contains(dto)) {
			this.products.add(dto);
		}
	}

	public void removeProduct(ProductEditDto dto) {

		if (products.contains(dto)) {
			this.products.remove(dto);
		}
	}
}
