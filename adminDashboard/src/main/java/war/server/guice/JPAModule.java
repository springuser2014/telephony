package war.server.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import net.sf.gilead.core.PersistentBeanManager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class JPAModule extends AbstractModule {

    private String persistenceUnitName;

    public JPAModule(String persistenceUnitName) {
        this.persistenceUnitName = persistenceUnitName;
    }

    @Override
    protected void configure() {
        bindConstant().annotatedWith(Names.named("persistence.unit.name")).to(persistenceUnitName);
        bind(JPAProcessCallHandler.class).asEagerSingleton();
        bind(EntityManagerFactory.class).toProvider(EntityManagerFactoryProvider.class).in(Singleton.class);
        bind(EntityManager.class).toProvider(EntityManagerProvider.class).in(Singleton.class);
        bind(PersistentBeanManager.class).toProvider(PersistentBeanManagerProvider.class).in(Singleton.class);

        configureJPA();
    }

    protected void configureJPA() {
    }

}
