package war.server.guice.gilead.renewed.adapter;

import net.sf.gilead.core.PersistentBeanManager;

public interface JPARemoteServiceAdapter extends RemoteServiceAdapter {
    PersistentBeanManager getBeanManager();
}
