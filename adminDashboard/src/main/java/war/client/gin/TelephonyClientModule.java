package war.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import war.client.ui.widget.StoreProductsComponent;
import war.client.ui.widget.TopMenu;
import war.client.ui.widget.TopSubmenu;
import war.client.ui.widget.form.LoginForm;


public class TelephonyClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(TopMenu.class).in(Singleton.class);
        bind(TopSubmenu.class).in(Singleton.class);
        bind(StoreProductsComponent.class).in(Singleton.class);
        bind(LoginForm.class).in(Singleton.class);
    }
}
