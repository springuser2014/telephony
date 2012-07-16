package telephony.client.gin;


import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import telephony.client.gwtp.presenter.MainPagePresenter;
import telephony.client.gwtp.presenter.ResponsePresenter;
import telephony.client.ui.config.BasicDataLoader;
import telephony.client.ui.widget.*;
import telephony.client.ui.widget.form.LoginForm;

@GinModules( { TelephonyClientModule.class, GwtpModule.class } )
public interface GuiInjector extends Ginjector {

    /* GwtpModule */
    PlaceManager getPlaceManager();

    EventBus getEventBus();

    AsyncProvider<ResponsePresenter> getResponsePresenter();

    AsyncProvider<MainPagePresenter> getMainPagePresenter();

    /* TelephonyClientModule */
    TopMenu getTopMenu();

    StoreProductsComponent getStoreProductsPage();

    LoginForm getLoginForm();

    TopSubmenu getTopSubmenu();

    DeliveriesComponent getDeliveriesPage();

    SalesComponent getSalesPage();

    AddStoreComponent getAddStorePage();

    MoveProductsComponent getEditStoreComponent();

    SaleProductsComponent getSalesProductsComponent();

    BasicDataLoader getBasicDataLoader();
}