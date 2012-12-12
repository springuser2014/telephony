package telephony.gwt.client.gin;


import com.google.gwt.inject.client.AsyncProvider;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.inject.Provider;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import telephony.gwt.client.gwtp.presenter.DashboardPresenter;
import telephony.gwt.client.gwtp.presenter.HeaderPresenter;
import telephony.gwt.client.gwtp.presenter.LoginPresenter;
import telephony.gwt.client.ui.config.BasicDataLoader;
import telephony.gwt.client.ui.layout.TelephonyContentLayout;
import telephony.gwt.client.ui.layout.TelephonyHeaderLayout;
import telephony.gwt.client.ui.layout.TelephonyMainLayout;
import telephony.gwt.client.ui.widget.*;
import telephony.gwt.client.ui.widget.form.LoginForm;

@GinModules({TelephonyClientModule.class, GwtpModule.class})
public interface GuiInjector extends Ginjector {

    /* GwtpModule */
    PlaceManager getPlaceManager();

    EventBus getEventBus();

    /* Presenters */

    Provider<DashboardPresenter> getMainPagePresenter();

    AsyncProvider<HeaderPresenter> getHeaderPresenter();

    AsyncProvider<LoginPresenter> getLoginPresenter();

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

    TelephonyMainLayout getMainLayout();

    TelephonyContentLayout getContentLayout();

    TelephonyHeaderLayout getHeaderLayout();


}