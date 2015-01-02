package telephony.core.service.impl;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import telephony.core.entity.jpa.BaseEntity;
import telephony.core.service.BasicService;
import telephony.core.service.dto.SessionDto;

import com.google.inject.Inject;
import com.google.inject.Provider;
import telephony.core.service.dto.response.*;
import telephony.core.service.dto.response.Error;
import telephony.core.service.exception.SessionServiceException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * General usage basic service implementation.
 * @param <T> Basic entities class type.
 */
public abstract class AbstractBasicService<T extends BaseEntity> implements BasicService<T> {
	
	protected Logger logger = LoggerFactory.getLogger(getClass());

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    public List<Error> getEmptyErrors() {
        return new ArrayList<Error>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract long count(SessionDto session) throws SessionServiceException;
}
