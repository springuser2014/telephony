package telephony.core.service.impl;

import java.util.Collection;

import com.google.inject.persist.Transactional;

import telephony.core.dao.ComplaintDao;
import telephony.core.entity.jpa.Complaint;
import telephony.core.service.ComplaintService;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public abstract class ComplaintServiceImpl 
extends AbstractGenericService<Complaint, ComplaintDao> 
implements ComplaintService<Complaint> {

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
