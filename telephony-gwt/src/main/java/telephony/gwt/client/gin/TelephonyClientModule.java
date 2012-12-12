package telephony.gwt.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import telephony.gwt.client.ui.config.BasicDataLoader;
import telephony.gwt.client.ui.config.BasicDataLoaderImpl;
import telephony.gwt.client.ui.layout.TelephonyContentLayout;
import telephony.gwt.client.ui.layout.TelephonyHeaderLayout;
import telephony.gwt.client.ui.layout.TelephonyMainLayout;
import telephony.gwt.client.ui.widget.*;
import telephony.gwt.client.ui.widget.form.LoginForm;


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

        bind(BasicDataLoader.class).to(BasicDataLoaderImpl.class).in(Singleton.class);
    }

    @Provides
    @Singleton
    public TelephonyMainLayout getMainLayout() {
        return new TelephonyMainLayout();
    }

    @Provides
    @Singleton
    public TelephonyContentLayout getContentLayout() {
        return new TelephonyContentLayout();
    }

    @Provides
    @Singleton
    public TelephonyHeaderLayout getHeaderLayout() {
        return new TelephonyHeaderLayout();
    }
}
