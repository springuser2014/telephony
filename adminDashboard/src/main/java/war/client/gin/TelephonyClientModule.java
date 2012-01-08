package war.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import war.client.ui.widgets.ContentBox;
import war.client.ui.widgets.TopMenu;


public class TelephonyClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(TopMenu.class).in(Singleton.class);
        bind(ContentBox.class).in(Singleton.class);
    }
}
