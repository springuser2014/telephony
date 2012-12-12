package telephony.gwt.server.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class EntityManagerProvider implements Provider<EntityManager> {


    private EntityManagerFactory emf;

    @Inject
    EntityManagerProvider(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager get() {
        return emf.createEntityManager();
    }

}
