package telephony.client.gin;

import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import telephony.client.gwtp.MyPlaceManager;
import telephony.client.gwtp.presenter.MainPagePresenter;
import telephony.client.gwtp.presenter.ResponsePresenter;
import telephony.client.gwtp.view.MainPageView;
import telephony.client.gwtp.view.ResponseView;


public class GwtpModule extends AbstractPresenterModule {
    protected void configure() {

        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceManager.class).to(MyPlaceManager.class).in(Singleton.class);
        bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(
            Singleton.class);
        bind(RootPresenter.class).asEagerSingleton();
//        bind(AsyncCallFailHandler.class).to(AsyncCallFailEvent.class).in(
//            Singleton.class);

        // Presenters
        bindPresenter(MainPagePresenter.class, MainPagePresenter.MyView.class,
            MainPageView.class, MainPagePresenter.MyProxy.class);
        bindPresenter(ResponsePresenter.class, ResponsePresenter.MyView.class,
            ResponseView.class, ResponsePresenter.MyProxy.class);





    }
}
