package war.server.guice;

import net.sf.gilead.core.PersistentBeanManager;

public interface JPARemoteServiceAdapter extends RemoteServiceAdapter {
    PersistentBeanManager getBeanManager();
}
