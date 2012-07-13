package telephony.client.gwtp.presenter;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import telephony.shared.gwtp.SendTextToServer;
import telephony.shared.gwtp.SendTextToServerResult;

public class ResponsePresenter extends Presenter<ResponsePresenter.MyView, ResponsePresenter.MyProxy> {

    public static final String nameToken = "response";
    public static final String textToServerParam = "textToServer";
    private String textToServer;

    private final PlaceManager placeManager;
    private final DispatchAsync dispater;

    @ProxyCodeSplit
    @NameToken(nameToken)
    public interface MyProxy extends ProxyPlace<ResponsePresenter> {
    }

    public interface MyView extends View {
        Button getCloseButton();

        void setServerResponse(String serverResponse);

        void setTextToServer(String textToServer);
    }

    @Inject
    public ResponsePresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager, DispatchAsync dispatcher) {
        super(eventBus, view, proxy);

        this.placeManager = placeManager;
        this.dispater = dispatcher;
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        textToServer = request.getParameter(textToServerParam, null);
    }

    @Override
    protected void onBind() {
        
        super.onBind();
        registerHandler(getView().getCloseButton().addClickHandler(
                new ClickHandler() {
                    public void onClick(ClickEvent event) {
                        placeManager.revealPlace(new PlaceRequest(MainPagePresenter.nameToken));
                    }
                }
        ));

    }

    @Override
    protected void onReset() {
        super.onReset();

        getView().setTextToServer(textToServer);
        getView().setServerResponse("Waiting for resonse...");

        dispater.execute(new SendTextToServer(textToServer),
                new AsyncCallback<SendTextToServerResult>() {
                    public void onFailure(Throwable caught) {
                        getView().setServerResponse("An error occured: " + caught.getMessage());
                    }

                    public void onSuccess(SendTextToServerResult result) {
                        getView().setServerResponse(result.getResponse());
                    }
                });
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }
}
