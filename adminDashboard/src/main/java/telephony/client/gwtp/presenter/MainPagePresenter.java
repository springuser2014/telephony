package telephony.client.gwtp.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.PlaceRequest;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;
import telephony.shared.gwtp.FieldVerifier;

public class MainPagePresenter extends
        Presenter<MainPagePresenter.MyView, MainPagePresenter.MyProxy> {

    public static final String nameToken = "main";

    private final PlaceManager placeManager;

    private final DispatchAsync dispatcher;

    private final EventBus eventBus;

    public DispatchAsync getDispatcher() {
        return dispatcher;
    }

    public interface MyView extends View {
        String getName();
        Button getSendButton();
        void resetAndFocus();
        void setError(String errorText);
    }

    @ProxyCodeSplit
    @NameToken(nameToken)
    public interface MyProxy extends ProxyPlace<MainPagePresenter> {
    }

    @Inject
    public MainPagePresenter(final EventBus eventBus1, final DispatchAsync dispatchAsync, final PlaceManager placeManager1, MyView view, MyProxy proxy) {
        super(eventBus1, view, proxy);

        this.eventBus = eventBus1;
        this.placeManager = placeManager1;
        this.dispatcher = dispatchAsync;
    }

    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getView().getSendButton().addClickHandler(
                new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        sendNameToServer();
                    }
                }
        ));
    }

    @Override
    protected void onReset() {
        super.onReset();
        getView().resetAndFocus();
    }


    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    private void sendNameToServer() {
        getView().setError("");

        String textToServer = getView().getName();

        if (FieldVerifier.isValidUserName(textToServer)) {
            placeManager.revealPlace(new PlaceRequest(ResponsePresenter.nameToken)
                        .with(ResponsePresenter.textToServerParam, textToServer));
        }
        else {
            getView().setError("Please enter at least four characters");
        }
    }
}