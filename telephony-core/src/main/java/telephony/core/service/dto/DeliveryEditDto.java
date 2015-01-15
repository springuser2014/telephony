package telephony.core.service.dto;

import java.util.ArrayList;
import java.util.List;

public class DeliveryEditDto extends AbstractDeliveryDto {

    private Long id;

    public DeliveryEditDto() {
        productsToAdd = new ArrayList<ProductAddDto>();
        productsToEdit = new ArrayList<ProductEditDto>();
        productsToDelete = new ArrayList<Long>();
    }

    private List<ProductAddDto> productsToAdd;

    private List<ProductEditDto> productsToEdit;

    private List<Long> productsToDelete;

    public List<ProductAddDto> getProductsToAdd() {
        return productsToAdd;
    }

    public void setProductsToAdd(List<ProductAddDto> productsToAdd) {
        this.productsToAdd = productsToAdd;
    }

    public void addProductToAdd(ProductAddDto product) {

        if (!this.productsToAdd.contains(product)) {
            this.productsToAdd.add(product);
        }
    }

    public void removeProductToAdd(ProductAddDto product) {

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
