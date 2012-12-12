package telephony.gwt.client.gwtp.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import telephony.gwt.client.gwtp.place.Token;
import telephony.gwt.client.ui.layout.TelephonyContentLayout;
import telephony.gwt.client.ui.layout.TelephonyHeaderLayout;
import telephony.shared.gwtp.action.LoginAction;
import telephony.shared.gwtp.result.LoginResult;

public class DashboardPresenter extends
        Presenter<DashboardPresenter.StartView, DashboardPresenter.StartProxy> {

    private final PlaceManager placeManager;

    private final DispatchAsync dispatcher;

    private final EventBus eventBus;

    public DispatchAsync getDispatcher() {
        return dispatcher;
    }

    public interface StartView extends View {

        TelephonyHeaderLayout getHeaderLayout();

        TelephonyContentLayout getContentLayout();

        String getName();

        Button getSendButton();

        void resetAndFocus();

        void setError(String errorText);
    }

    @ProxyStandard
    @NameToken(Token.MAIN_PRESENTER)
    @NoGatekeeper
    public interface StartProxy extends ProxyPlace<DashboardPresenter> {
    }

    @Inject
    public DashboardPresenter(final EventBus eventBus1, final DispatchAsync dispatchAsync, final PlaceManager placeManager1, StartView view, StartProxy proxy) {
        super(eventBus1, view, proxy);

        this.eventBus = eventBus1;
        this.placeManager = placeManager1;
        this.dispatcher = dispatchAsync;
    }

    @Override
    protected void onBind() {
        super.onBind();

    }

    @Override
    protected void onReset() {
        super.onReset();
        getView().resetAndFocus();
    }


    @Override
    protected void revealInParent() {
        RevealRootLayoutContentEvent.fire(this, this);
    }

    private void sendNameToServer() {
        getView().setError("");


        getDispatcher().execute(new LoginAction(),
                new AsyncCallback<LoginResult>() {
                    public void onFailure(Throwable caught) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }

                    public void onSuccess(LoginResult result) {
                        //To change body of implemented methods use File | Settings | File Templates.
                    }
                });


//        String textToServer = getView().getName();

//        if (FieldVerifier.isValidUserName(textToServer)) {
//            placeManager.revealPlace(new PlaceRequest(ResponsePresenter.nameToken)
//                        .with(ResponsePresenter.textToServerParam, textToServer));
//        }
//        else {
//            getView().setError("Please enter at least four characters");
//        }
    }
}