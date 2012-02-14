package war.client.gin;


import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import war.client.ui.widget.StoreProductsComponent;
import war.client.ui.widget.TopMenu;
import war.client.ui.widget.TopSubmenu;
import war.client.ui.widget.form.LoginForm;

@GinModules(TelephonyClientModule.class)
public interface GuiInjector extends Ginjector {
    TopMenu getTopMenu();

    StoreProductsComponent getContentBox();

    LoginForm getLoginForm();

    TopSubmenu getTopSubmenu();
}