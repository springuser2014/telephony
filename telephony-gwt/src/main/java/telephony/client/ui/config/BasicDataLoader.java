package telephony.client.ui.config;


import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;

import java.util.List;

public interface BasicDataLoader {

    public List<Store> getStores();

    public void forceReloadStores();

    public List<User> getUsers();

    public void forceReloadUsers();

    public List<String> getProducers();

    public void forceReloadProducers();

    public List<String> getModels();

    public void forceReloadModels();

    public List<String> getColors();

    public void forceReloadColors();

    public Long getNumberOfDeliveries();

    public void forceReloadNumberOfDeliveries();

    public Long getNumberOfSales();

    public void forceReloadNumberOfSales();

    public List<String> getImeisInUse();

    public void forceReloadImeisInUse();

    public void forceReloadAllData();
}
