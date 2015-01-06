package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.persist.Transactional;

import telephony.core.dao.ComplaintDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.query.filter.ComplaintFilterCriteria;
import telephony.core.service.ComplaintService;
import telephony.core.service.dto.ComplaintDto;
import telephony.core.service.dto.ComplaintEditDto;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.dto.request.ComplaintEditRequest;
import telephony.core.service.dto.request.ComplaintFetchRequest;
import telephony.core.service.dto.request.ReportComplaintRequest;
import telephony.core.service.dto.response.ComplaintEditResponse;
import telephony.core.service.dto.response.ComplaintFetchResponse;
import telephony.core.service.dto.response.ReportComplaintResponse;

/**
 * asd.
 */
public abstract class ComplaintServiceImpl 
extends AbstractGenericService<Complaint, ComplaintDao> 
implements ComplaintService<Complaint, ReportComplaintResponse, ReportComplaintRequest<ComplaintDto>, ComplaintDto,
		 ComplaintEditResponse, ComplaintEditRequest<ComplaintEditDto>, ComplaintEditDto,
		 ComplaintFetchResponse, ComplaintFetchRequest<ComplaintFilterCriteria>, ComplaintFilterCriteria> {

	/**
	 * asd.
	 * @param clazz a.
	 */
	public ComplaintServiceImpl(Class<ComplaintDao> clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Complaint findByHash(String hashUnique) {
		
		return dao().findByHash(hashUnique);
	}


	@Transactional
	@Override
	public long count(SessionDto session) {

		return dao().count();
	}


}
