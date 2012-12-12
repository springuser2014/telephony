package telephony.core.service.impl;

import com.google.inject.Inject;
import com.google.inject.Provider;
import telephony.core.service.interfaces.BasicService;

import javax.persistence.EntityManager;

public class AbstractBasicService implements BasicService {

    @Inject
    private Provider<EntityManager> entityManagerProvider;

    @Override
    public EntityManager getEntityManager() {
        return entityManagerProvider.get();
    }
}
