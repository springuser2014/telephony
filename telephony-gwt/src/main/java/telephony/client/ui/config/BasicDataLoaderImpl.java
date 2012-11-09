package telephony.client.ui.config;


import com.google.gwt.core.client.GWT;
import telephony.client.service.*;
import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;

import java.util.List;

public class BasicDataLoaderImpl implements BasicDataLoader {

    private final ProductRPCServiceAsync productService = GWT.create(ProductRPCService.class);
    private final StoreRPCServiceAsync storeService = GWT.create(StoreRPCService.class);
    private final UserRPCServiceAsync userService = GWT.create(UserRPCService.class);
    private final InformationRPCServiceAsync informationService = GWT.create(InformationRPCService.class);

    public BasicDataLoaderImpl() {

    }


    public List<Store> getStores() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void forceReloadStores() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<User> getUsers() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void forceReloadUsers() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> getProducers() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void forceReloadProducers() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> getModels() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void forceReloadModels() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> getColors() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void forceReloadColors() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Long getNumberOfDeliveries() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void forceReloadNumberOfDeliveries() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public Long getNumberOfSales() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void forceReloadNumberOfSales() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public List<String> getImeisInUse() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void forceReloadImeisInUse() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void forceReloadAllData() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
