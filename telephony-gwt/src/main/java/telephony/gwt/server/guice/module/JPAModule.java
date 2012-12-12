package telephony.gwt.server.guice.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import net.sf.gilead.core.PersistentBeanManager;
import telephony.gwt.server.gilead.handler.JPAProcessCallHandler;
import telephony.gwt.server.guice.provider.EntityManagerFactoryProvider;
import telephony.gwt.server.guice.provider.EntityManagerProvider;
import telephony.gwt.server.guice.provider.PersistentBeanManagerProvider;

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
    }
}
