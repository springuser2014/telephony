package telephony.client.gin;

import com.google.inject.Singleton;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.gwtplatform.dispatch.client.gin.DispatchAsyncModule;
import com.gwtplatform.mvp.client.RootPresenter;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalytics;
import com.gwtplatform.mvp.client.googleanalytics.GoogleAnalyticsImpl;
import com.gwtplatform.mvp.client.proxy.ParameterTokenFormatter;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.TokenFormatter;
import telephony.client.gin.provider.FirstClientActionHandlerProvider;
import telephony.client.gwtp.place.TelephonyPlaceManager;
import telephony.client.gwtp.handler.FirstClientActionHandler;
import telephony.client.gwtp.presenter.DashboardPresenter;
import telephony.client.gwtp.presenter.HeaderPresenter;
import telephony.client.gwtp.presenter.LoginPresenter;
import telephony.client.gwtp.view.DashboardView;
import telephony.client.gwtp.view.HeaderView;
import telephony.client.gwtp.view.LoginView;


public class GwtpModule extends AbstractPresenterModule {
    protected void configure() {

        install(new DispatchAsyncModule(TelephonyClientActionHandlerRegistry.class));

        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceManager.class).to(TelephonyPlaceManager.class).in(Singleton.class);
        bind(TokenFormatter.class).to(ParameterTokenFormatter.class).in(
            Singleton.class);
        bind(RootPresenter.class).asEagerSingleton();
        bind(GoogleAnalytics.class).to(GoogleAnalyticsImpl.class).in(Singleton.class);

        // Presenters
        bindPresenter(DashboardPresenter.class, DashboardPresenter.StartView.class,
                DashboardView.class, DashboardPresenter.StartProxy.class);
        
        bindPresenter(HeaderPresenter.class, HeaderPresenter.HeaderView.class,
                      HeaderView.class, HeaderPresenter.HeaderProxy.class );
        bindPresenter(LoginPresenter.class, LoginPresenter.LoginView.class,
                      LoginView.class, LoginPresenter.LoginProxy.class);
        
        bind(FirstClientActionHandler.class).toProvider(FirstClientActionHandlerProvider.class);





    }
}
