package telephony.core.service.impl;

import java.util.Collection;

import telephony.core.dao.ProductComplaintDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.entity.jpa.ProductComplaint;
import telephony.core.service.ProductComplaintService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.*;

import com.google.inject.persist.Transactional;

/**
 * asd.
 */
public class ProductComplaintServiceImpl 
extends AbstractGenericService<ProductComplaint, ProductComplaintDao> 
implements ProductComplaintService {
	
	/**
	 * asd.
	 */
	public ProductComplaintServiceImpl() {
		super();
	}

	/**
	 * sad.
	 * @param clazz a.
	 */
	public ProductComplaintServiceImpl(Class<ProductComplaintDao> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}


	@Transactional
	@Override
	public void report(SessionDto session, ProductComplaint complaint) {
		
		// TODO session's validation
		
		dao().save(complaint);		
	}

	@Transactional
	@Override
	public Complaint update(SessionDto session, ProductComplaint complaint) {
		
		// TODO session's validation
		
		return dao().saveOrUpdate(complaint);
	}

	@Transactional
	@Override
	public Collection<ProductComplaint> update(SessionDto session, Collection<ProductComplaint> cs) {
		
		// TODO session's validation
		
		return dao().saveOrUpdate(cs);
	}

	@Transactional
	@Override
	public void markAsInProgress(SessionDto session, long complaintId) {

		// TODO session's validation
		
		dao().markAsInProgress(complaintId);
	}


	@Transactional
	@Override
	public void markAsAccepted(SessionDto session, long complaintId) {
	
		// TODO session's validation

		dao().markAsAccepted(complaintId);
	}

	@Transactional
	@Override
	public void markAsRejected(SessionDto session, long complaintId) {

		// TODO session's validation

		dao().markAsRejected(complaintId);
	}

	@Transactional
	@Override
	public void markAsResolved(SessionDto session, long complaintId) {

		// TODO session's validation

		dao().markAsResolved(complaintId);		
	}

	@Transactional
	@Override
	public void removeById(SessionDto session, long complaintId) {
		
		// TODO session's validation
		
		dao().removeById(complaintId);		
	}

	@Transactional
	@Override
	public void removeByIds(SessionDto session, Collection<Long> complaintIds) {

		// TODO session's validation
		
		dao().removeByIds(complaintIds);
	}

	@Transactional
	@Override
	public ProductComplaint findByHash(String hashUnique) {

		return dao().findByHash(hashUnique);
	}

	@Override
	public ReportComplaintResponseDto report(ReportComplaintRequestDto req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintFetchResponseDto fetch(ComplaintFetchRequestDto req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintEditResponseDto editComplaint(ComplaintEditRequestDto req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintChangeStatusResponseDto markAsRejected(
			ComplaintChangeStatusRequestDto req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintChangeStatusResponseDto markAsInProgress(
			ComplaintChangeStatusRequestDto req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintChangeStatusResponseDto markAsAccepted(
			ComplaintChangeStatusRequestDto req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ComplaintChangeStatusResponseDto markAsResolved(
			ComplaintChangeStatusRequestDto req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteComplaint(DeleteComplaintRequestDto req) {
		// TODO Auto-generated method stub
		
	}
	
}
