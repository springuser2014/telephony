package war.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import war.client.ui.widget.*;
import war.client.ui.widget.form.LoginForm;


public class TelephonyClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(TopMenu.class).in(Singleton.class);
        bind(TopSubmenu.class).in(Singleton.class);
        bind(StoreProductsComponent.class).in(Singleton.class);
        bind(LoginForm.class).in(Singleton.class);

        bind(DeliveriesComponent.class).in(Singleton.class);
        bind(SalesComponent.class).in(Singleton.class);
        
        bind(AddStoreComponent.class).in(Singleton.class);
        bind(MoveProductsComponent.class).in(Singleton.class);
    }
}
