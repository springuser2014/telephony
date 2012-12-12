package telephony.gwt.server.gilead.adapter;

import net.sf.gilead.core.PersistentBeanManager;

/**
 * @todo Remove after migration to REST services
 */
public interface JPARemoteServiceAdapter extends RemoteServiceAdapter {
    public PersistentBeanManager getBeanManager();
}
