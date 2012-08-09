package telephony.client.gwtp.presenter;


import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealRootContentEvent;


public class HeaderPresenter extends Presenter<HeaderPresenter.HeaderView, HeaderPresenter.HeaderProxy> {

    @Inject
    public HeaderPresenter(final EventBus eventbus, final HeaderView headerview, final HeaderProxy headerproxy) {
        super(eventbus, headerview, headerproxy);
    }

    @Override
    protected void revealInParent() {
        RevealRootContentEvent.fire(this, this);
    }

    public interface HeaderView extends View {
    }

    @ProxyCodeSplit
    public interface HeaderProxy extends Proxy<HeaderPresenter> {

    }
}
