package telephony.core.service.impl;

import javax.persistence.EntityManager;

import telephony.core.entity.jpa.BaseEntity;
import telephony.core.service.BasicService;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * General usage basic service implementation.
 * @param <T> Basic entities class type.
 * @author Paweł Henek <pawelhenek@gmail.com>
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
