package telephony.client.gwtp.presenter;


import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootLayoutContentEvent;
import telephony.client.gwtp.place.Token;
import telephony.client.gwtp.uihandlers.LoginHandlers;
import telephony.client.service.SecurityRPCService;
import telephony.client.service.SecurityRPCServiceAsync;

public class LoginPresenter
        extends Presenter<LoginPresenter.LoginView, LoginPresenter.LoginProxy>
        implements LoginHandlers {

    SecurityRPCServiceAsync service = GWT.create(SecurityRPCService.class);

    private EventBus eventbus;
    private DispatchAsync dispatcher;
    private PlaceManager placemanager;


    @Inject
    public LoginPresenter(final EventBus eventBus,
                          final LoginView view,
                          final LoginProxy proxy,
                          final DispatchAsync dispatcher,
                          final PlaceManager placeManager) {
        super(eventBus, view, proxy);

        getView().setUiHandlers(this);

        this.eventbus = eventBus;
        this.dispatcher = dispatcher;
        this.placemanager = placeManager;
    }

    @Override
    public void onReset() {
        super.onReset();

        getView().resetAndFocus();
    }

    @Override
    protected void revealInParent() {
        RevealRootLayoutContentEvent.fire(this, this);
    }

    public void onOkButtonClicked() {
        sendLoginDataToServer();
    }

    private void sendLoginDataToServer() {
        String login = getView().getLogin();
        String password = getView().getPassword();


    }

    public interface LoginView extends View, HasUiHandlers<LoginHandlers> {
        
        String getPassword();
        String getLogin();

        void resetAndFocus();
        void setError(String error);

    }

    @ProxyCodeSplit
    @NameToken(Token.LOGIN_PRESENTER)
    @NoGatekeeper
    public interface LoginProxy extends ProxyPlace<LoginPresenter> {
    }
}
