package telephony.client.gwtp.place;


import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManagerImpl;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;


public class TelephonyPlaceManager extends PlaceManagerImpl {

    @Inject
    public TelephonyPlaceManager(EventBus eventBus, TokenFormatter tokfenFormatter) {
        super(eventBus, tokfenFormatter);
    }


    public void revealDefaultPlace() {
        revealPlace(new PlaceRequest(Token.LOGIN_PRESENTER), false);
    }

    /**
     * @todo Handle transition to UnauthorizedPlace
     * @param unauthorizedHistoryToken
     */
    @Override
    public void revealUnauthorizedPlace(String unauthorizedHistoryToken) {
        PlaceRequest placeRequest = new PlaceRequest(Token.LOGIN_PRESENTER);
        placeRequest = placeRequest.with("redirect", unauthorizedHistoryToken);
        revealPlace(placeRequest);
    }

    /**
     * @todo Handle transition to ErrorPlace
     * @param invalidHistoryToken
     */
    @Override
    public void revealErrorPlace(String invalidHistoryToken) {
        PlaceRequest placeRequest = new PlaceRequest(Token.MAIN_PRESENTER);
        revealPlace(placeRequest);
    }
}
