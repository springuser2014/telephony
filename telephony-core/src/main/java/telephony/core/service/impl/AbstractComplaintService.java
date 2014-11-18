package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.persist.Transactional;

import telephony.core.dao.GenericDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.service.dto.request.*;
import telephony.core.service.dto.response.ComplaintChangeStatusResponseDto;
import telephony.core.service.dto.response.ComplaintEditResponseDto;
import telephony.core.service.ComplaintService;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.response.ComplaintFetchResponseDto;
import telephony.core.service.dto.response.ReportComplaintResponseDto;


/**
 * asd.
 * @param <T> a.
 * @param <D> asd .
 */
public class AbstractComplaintService<T extends Complaint, D extends GenericDao<T>>
extends AbstractGenericService<T, D>
implements ComplaintService<T> {

	public AbstractComplaintService(Class<D> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

	private D complaintDao;
	
	/**
	 * asd.
	 * @return a.
	 */
	protected D getComplaintDao() {
		return complaintDao;
	}

	@Transactional
	@Override
	public void report(SessionDto session, T complaint) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public Complaint update(SessionDto session, T complaint) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional
	@Override
	public Collection<T> update(SessionDto session, Collection<T> complaints) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void markAsInProgress(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void markAsAccepted(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void markAsRejected(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void markAsResolved(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void removeById(SessionDto session, long complaintId) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	@Override
	public void removeByIds(SessionDto session, Collection<Long> complaintIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T findByHash(String hashUnique) {
		// TODO Auto-generated method stub
		return null;
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

