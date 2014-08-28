package telephony.core.dao.impl;

import telephony.core.dao.ProductTaxDao;
import telephony.core.entity.jpa.ProductTax;

public class ProductTaxDaoImpl 
extends GenericDaoImpl<ProductTax>
implements ProductTaxDao {

	public ProductTaxDaoImpl() {
		super(ProductTax.class);
	}

}
