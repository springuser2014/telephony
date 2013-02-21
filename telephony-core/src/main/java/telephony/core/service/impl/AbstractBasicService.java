package telephony.core.service.impl;

import javax.persistence.EntityManager;

import telephony.core.service.interfaces.BasicService;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * asd.
 * @author Paweł Henek <pawelhenek@gmail.com>
 *
 */
public class AbstractBasicService implements BasicService {

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
}
