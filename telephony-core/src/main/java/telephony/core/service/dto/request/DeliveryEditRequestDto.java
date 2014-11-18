package telephony.core.service.dto.request;

import java.util.ArrayList;
import java.util.List;

import telephony.core.service.dto.ProductEditDto;
import telephony.core.service.dto.ProductDto;

public class DeliveryEditRequestDto extends AuthRequestDto {
	
	private Long id;

	private String label;
	private String dateIn;
	
	private Long storeId;
	private Long contactId;
	
	private List<ProductDto> productsToAdd = new ArrayList<ProductDto>();
	
	private List<ProductEditDto> productsToEdit = new ArrayList<ProductEditDto>();
	
	private List<Long> productsToDelete = new ArrayList<Long>();

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDateIn() {
		return dateIn;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Long getContactId() {
		return contactId;
	}

	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	public List<ProductDto> getProductsToAdd() {
		return productsToAdd;
	}

	public void setProductsToAdd(List<ProductDto> productsToAdd) {
		this.productsToAdd = productsToAdd;
	}
	
	public void addProductToAdd(ProductDto product) {
		
		if (!this.productsToAdd.contains(product)) {
			this.productsToAdd.add(product);
		}
	}
	
	public void removeProductToAdd(ProductDto product) {
		
		if (this.productsToAdd.contains(product)) {
			this.productsToAdd.remove(product);
		}
	}
	
	public void addProductToEdit(ProductEditDto product) {
		
		if (!this.productsToEdit.contains(product)) {
			this.productsToEdit.add(product);
		}
	}
	
	public void removeProductToDelete(ProductEditDto product) {
		
		if (this.productsToEdit.contains(product)) {
			this.productsToEdit.remove(product);
		}
	}

	public List<ProductEditDto> getProductsToEdit() {
		return productsToEdit;
	}

	public void setProductsToEdit(List<ProductEditDto> productsToEdit) {
		this.productsToEdit = productsToEdit;
	}
	
	public void addProductToDelete(Long id) {
		
		if (!this.productsToDelete.contains(id)) {
			this.productsToDelete.add(id);
		}
	}
	
	public void removeProductToDelete(Long id) {
		
		if (this.productsToDelete.contains(id)) {
			this.productsToDelete.remove(id);
		}
	}

	public List<Long> getProductsToDelete() {
		return productsToDelete;
	}

	public void setProductsToDelete(List<Long> productsToDelete) {
		this.productsToDelete = productsToDelete;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
