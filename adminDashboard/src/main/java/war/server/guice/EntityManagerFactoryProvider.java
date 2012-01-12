package war.server.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactoryProvider implements Provider<EntityManagerFactory> {

    private String persistenceUnitName;

    @Inject
    EntityManagerFactoryProvider(@Named("persistence.unit.name") String persistenceUnitName)  {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    public EntityManagerFactory get() {
        return Persistence.createEntityManagerFactory(persistenceUnitName);
    }

}