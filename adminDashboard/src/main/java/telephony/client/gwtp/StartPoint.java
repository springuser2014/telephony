package telephony.client.gwtp;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import telephony.client.gin.GuiInjector;

public class StartPoint implements EntryPoint {

    public final GuiInjector guiInjector = GWT.create(GuiInjector.class);

    public void onModuleLoad() {

        configModule();

        configGui();

        DelayedBindRegistry.bind(guiInjector);
        guiInjector.getPlaceManager().revealDefaultPlace();
    }

    private void configGui() {
        Window.enableScrolling(false);
        Window.setMargin("0px");
    }

    private void configModule() {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

            public void onUncaughtException(Throwable e) {

                Log.error("UncaughtException : ", e);
            }
        });
    }
}
