package telephony.core.service;

import java.util.Collection;
import java.util.List;

import telephony.core.entity.jpa.Model;
import telephony.core.service.dto.request.ModelFetchRequestDto;
import telephony.core.service.dto.response.ModelFetchResponseDto;
import telephony.core.service.dto.SessionDto;

/**
 * asd.
 */
public interface ModelService extends BasicService<Model> {
	

	/**
	 * asd.
	 * @param req a.
	 * @return a.
	 */
	ModelFetchResponseDto find(ModelFetchRequestDto req);
	
	// TODO delete the stuff below
	
	/**
	 * asd.
	 * @param session TODO
	 * @param label a.
	 * @return a.
	 */
	@Deprecated
	Model findByLabel(SessionDto session, String label);
	
	
	/**
	 * asd.
	 * @param session TODO
	 * @param id a.
	 * @return d.
	 */
	@Deprecated
	Model findById(SessionDto session, long id);

	
	/**
	 * asd.
	 * @param session TODO
	 * @param ids a.
	 * @return a.
	 */
	@Deprecated
	Collection<Model> findByIds(SessionDto session, List<Long> ids);

	
	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 * @return asd.
	 */
	@Deprecated
	Model edit(SessionDto session, Model model);

	
	/**
	 * asd.
	 * @param session TODO
	 * @param coll a.
	 * @return ad.
	 */
	@Deprecated
	Collection<Model> update(SessionDto session, Collection<Model> coll);

	
	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 */
	@Deprecated
	void removeById(SessionDto session, Long model);

	
	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 */
	@Deprecated
	void removeById(SessionDto session, Collection<Long> model);

	
	/**
	 * asd.
	 * @param session TODO
	 * @param model a.
	 * 
	 */
	@Deprecated
	void remove(SessionDto session, Model model);
	
}
