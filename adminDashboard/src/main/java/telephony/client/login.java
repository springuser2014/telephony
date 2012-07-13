package telephony.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import telephony.client.gin.GuiInjector;
import telephony.client.ui.widget.form.LoginForm;

public class login implements EntryPoint {

    private final GuiInjector injector = GWT.create(GuiInjector.class);

    private LoginForm loginForm = injector.getLoginForm();

    public void onModuleLoad() {
        Log.debug("Initializing EntryPoint : login");
        loginForm.draw();
        RootLayoutPanel.get().add(loginForm);
    }

}