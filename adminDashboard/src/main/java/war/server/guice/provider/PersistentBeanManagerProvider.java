package war.server.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.jpa.HibernateJpaUtil;
import net.sf.gilead.core.serialization.GwtProxySerialization;
import net.sf.gilead.core.store.stateless.StatelessProxyStore;

import javax.persistence.EntityManagerFactory;

public class PersistentBeanManagerProvider implements Provider<PersistentBeanManager> {

    private EntityManagerFactory emf;

    @Inject
    public PersistentBeanManagerProvider(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public PersistentBeanManager get() {
        HibernateJpaUtil persistenceUtil = new HibernateJpaUtil();

        persistenceUtil.setEntityManagerFactory(emf);

        PersistentBeanManager beanManager = new PersistentBeanManager();

        beanManager.setPersistenceUtil(persistenceUtil);

        StatelessProxyStore stetelessProxyStore = new StatelessProxyStore();
        stetelessProxyStore.setProxySerializer(new GwtProxySerialization());
        beanManager.setProxyStore(stetelessProxyStore);
        return beanManager;
    }

}