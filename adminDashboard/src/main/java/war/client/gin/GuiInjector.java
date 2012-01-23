package war.client.gin;


import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import war.client.ui.widget.ContentBox;
import war.client.ui.widget.TopMenu;
import war.client.ui.widget.TopSubmenu;
import war.client.ui.widget.form.LoginForm;

@GinModules(TelephonyClientModule.class)
public interface GuiInjector extends Ginjector {
    TopMenu getTopMenu();

    ContentBox getContentBox();

    LoginForm getLoginForm();

    TopSubmenu getTopSubmenu();
}