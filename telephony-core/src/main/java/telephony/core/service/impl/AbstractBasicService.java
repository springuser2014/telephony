package telephony.core.service.impl;

import javax.persistence.EntityManager;

import telephony.core.entity.jpa.BaseEntity;
import telephony.core.service.BasicService;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

/**
 * General usage basic service implementation.
 * @param <T> Basic entities class type.
 */
public abstract class AbstractBasicService<T extends BaseEntity> implements BasicService<T> {

    @Inject
    private Provider<EntityManager> entityManagerProvider;

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
	public abstract long count();
}
