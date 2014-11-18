package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

import telephony.core.dao.ComplaintCommentDao;
import telephony.core.entity.jpa.ComplaintComment;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.entity.jpa.SaleComplaint;
import telephony.core.service.ComplaintCommentService;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.SaleComplaintService;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public class ComplaintCommentServiceImpl 
extends AbstractBasicService<ComplaintComment> 
implements ComplaintCommentService {

	@Inject
	private ProductComplaintService productComplaint;
	
	@Inject
	private SaleComplaintService saleComplaint;
	
	@Inject
	private ComplaintCommentDao complaintCommentDao;
	
	@Transactional
	@Override
	public void comment(SessionDto session, ComplaintComment comment, long complaintId) {
		
		// TODO : session's validation
		
		ProductComplaint pc = productComplaint.findById(complaintId);
		
		SaleComplaint sc = saleComplaint.findById(complaintId);
		
		if (pc != null) {
			comment.setComplaint(pc);
			productComplaint.update(pc);
		} else if (sc != null) {
			comment.setComplaint(sc);
			saleComplaint.update(sc);
		} else {
			throw new IllegalArgumentException(); 
		}
		
		complaintCommentDao.save(comment);
	}

	@Transactional
	@Override
	public void comment(String hashUnique, ComplaintComment comment) {
		// TODO : session's validation
		
		ProductComplaint pc = productComplaint.findByHash(hashUnique);
		
		SaleComplaint sc = saleComplaint.findByHash(hashUnique);
		
		if (pc != null) {
			comment.setComplaint(pc);
			productComplaint.update(pc);
		} else if (sc != null) {
			comment.setComplaint(sc);
			saleComplaint.update(sc);
		} else {
			throw new IllegalArgumentException(); 
		}
		
		complaintCommentDao.save(comment);
	}

	@Transactional
	@Override
	public long count(SessionDto session) {

		return complaintCommentDao.count();
	}

}
