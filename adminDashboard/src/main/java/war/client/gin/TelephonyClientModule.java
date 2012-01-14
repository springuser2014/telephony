package war.client.gin;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Singleton;
import war.client.ui.widget.ContentBox;
import war.client.ui.widget.TopMenu;
import war.client.ui.widget.form.LoginForm;


public class TelephonyClientModule extends AbstractGinModule {
    @Override
    protected void configure() {
        bind(TopMenu.class).in(Singleton.class);
        bind(ContentBox.class).in(Singleton.class);
        bind(LoginForm.class).in(Singleton.class);
    }
}
