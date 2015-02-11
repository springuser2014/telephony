package telephony.core.dao.impl;

import telephony.core.dao.ProductComplaintCommentDao;
import telephony.core.entity.jpa.ProductComplaintComment;

public class ProductComplaintCommentDaoImpl
extends GenericDaoImpl<ProductComplaintComment>
implements ProductComplaintCommentDao {

	public ProductComplaintCommentDaoImpl() {
		super(ProductComplaintComment.class);
	}

}
