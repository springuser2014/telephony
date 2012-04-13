package war.server.gilead.adapter;

import net.sf.gilead.core.PersistentBeanManager;

public interface JPARemoteServiceAdapter extends RemoteServiceAdapter {
    public PersistentBeanManager getBeanManager();
}
