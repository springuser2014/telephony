package war.server.guice;


import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class TelephonyServerModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(TestClass.class).in(Singleton.class);
    }

//    @Provides
//    PersistentBeanManager getPersistentBeanManager() {
//        String PERSISTENCE_UNIT_NAME = "telephony";
//        EntityManagerFactory emf =
//                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
//
//        HibernateJpaUtil hibernateJpaUtil = new HibernateJpaUtil();
//        hibernateJpaUtil.setEntityManagerFactory(emf);
//
//        PersistentBeanManager persistentBeanManager =
//                GwtConfigurationHelper.initGwtStatelessBeanManager(hibernateJpaUtil);
//
//        return persistentBeanManager;
//    }

}
