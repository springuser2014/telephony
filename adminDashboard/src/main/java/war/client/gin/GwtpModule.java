package war.client.gin;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;
import war.client.gwtp.MyPlaceManager;
import war.client.gwtp.presenter.MainPagePresenter;
import war.client.gwtp.view.MainPageView;


public class GwtpModule extends AbstractPresenterModule {
    protected void configure() {
        install(new DefaultModule(MyPlaceManager.class));

        bindPresenter(MainPagePresenter.class,
                      MainPagePresenter.MyView.class,
                      MainPageView.class,
                      MainPagePresenter.MyProxy.class);


    }
}
