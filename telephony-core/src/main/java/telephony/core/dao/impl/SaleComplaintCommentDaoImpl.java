package telephony.core.dao.impl;

import telephony.core.dao.SaleComplaintCommentDao;
import telephony.core.entity.jpa.SaleComplaintComment;

public class SaleComplaintCommentDaoImpl
extends GenericDaoImpl<SaleComplaintComment>
implements SaleComplaintCommentDao {

	public SaleComplaintCommentDaoImpl() {
		super(SaleComplaintComment.class);
	}

}
