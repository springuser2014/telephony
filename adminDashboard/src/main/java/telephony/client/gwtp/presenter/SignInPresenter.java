package telephony.client.gwtp.presenter;


import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;

public class SignInPresenter extends Presenter<SignInPresenter.MyView, SignInPresenter.MyProxy> {

    public static final String nameToken = "signin";

    private final PlaceManager placeManager;

    public interface MyView extends View {

    }

    @ProxyCodeSplit
    @NameToken(nameToken)
    public interface MyProxy extends ProxyPlace<SignInPresenter> {}


    @Inject
    public SignInPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager placeManager1) {
        super(eventBus, view, proxy);
        this.placeManager = placeManager1;
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this,this);
    }
}
