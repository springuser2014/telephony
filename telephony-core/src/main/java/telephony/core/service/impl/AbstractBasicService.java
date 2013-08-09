package telephony.core.service.impl;

import javax.persistence.EntityManager;

import telephony.core.entity.jpa.BaseEntity;
import telephony.core.service.BasicService;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public abstract class AbstractBasicService<T extends BaseEntity> implements BasicService<T> {

    /**
     * asd.
     */
    @Inject
    private Provider<EntityManager> entityManagerProvider;

    /**
     * asd.
     * @return asd.
     */
    @Override
    public final EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }

	@Override
	abstract public long count() ;
}
