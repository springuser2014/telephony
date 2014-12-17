package telephony.core.service;

import javax.persistence.EntityManager;

import telephony.core.entity.jpa.BaseEntity;
import telephony.core.service.dto.SessionDto;
import telephony.core.service.exception.SessionServiceException;

/**
 * Basic business services API.
 * @param <T> Basic entity class.
 */
public interface BasicService<T extends BaseEntity> {

    /**
     * TODO to remove, EM should be hidden
     * Returns entityManager object.
     * @return entities manager.
     */
    @Deprecated
    EntityManager getEntityManager();
    
    /**
     * TODO probably deprecated
	 * Counts number of entities in persistence layer.
     * @param session TODO
	 * @return number of stored entities.
	 */
    long count(SessionDto session) throws SessionServiceException;
	
	// TODO : add basic methods, for example findById
}
