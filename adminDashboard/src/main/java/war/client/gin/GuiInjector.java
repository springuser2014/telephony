package war.client.gin;


import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import war.client.ui.widget.*;
import war.client.ui.widget.form.LoginForm;

@GinModules(TelephonyClientModule.class)
public interface GuiInjector extends Ginjector {
    TopMenu getTopMenu();

    StoreProductsComponent getStoreProductsPage();

    LoginForm getLoginForm();

    TopSubmenu getTopSubmenu();

    DeliveriesComponent getDeliveriesPage();

    SalesComponent getSalesPage();

    AddStoreComponent getAddStorePage();

    MoveProductsComponent getEditStoreComponent();

    SaleProductsComponent getSalesProductsComponent();
}