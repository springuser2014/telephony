package telephony.client.gwtp;


import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import telephony.client.gwtp.presenter.MainPagePresenter;


public class MyPlaceManager extends PlaceManagerImpl {

    @Inject
    public MyPlaceManager(EventBus eventBus, TokenFormatter tokfenFormatter) {
        super(eventBus, tokfenFormatter);
    }

    public void revealDefaultPlace() {
        revealPlace(new PlaceRequest(MainPagePresenter.nameToken), false);
    }
}
