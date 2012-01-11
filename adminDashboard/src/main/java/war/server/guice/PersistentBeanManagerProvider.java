package war.server.guice;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.sf.gilead.core.PersistentBeanManager;
import net.sf.gilead.core.hibernate.jpa.HibernateJpaUtil;
import net.sf.gilead.gwt.GwtConfigurationHelper;
import javax.persistence.EntityManager;

public class PersistentBeanManagerProvider implements Provider<PersistentBeanManager> {

//    private final EntityManager em;

//    @Inject
//    public PersistentBeanManagerProvider(EntityManager em) {
//        this.em = em;
//    }

    public PersistentBeanManager get() {
//        HibernateJpaUtil hibernateJpaUtil = new HibernateJpaUtil();
//        hibernateJpaUtil.setEntityManagerFactory(this.em.getEntityManagerFactory());
//
//        PersistentBeanManager persistentBeanManager =
//                GwtConfigurationHelper.initGwtStatelessBeanManager(hibernateJpaUtil);
//
//        return persistentBeanManager;


        return null;
    }
}
