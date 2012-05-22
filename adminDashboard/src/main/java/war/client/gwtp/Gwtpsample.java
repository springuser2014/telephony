package war.client.gwtp;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.gwtplatform.mvp.client.DelayedBindRegistry;
import war.client.gin.GuiInjector;

public class Gwtpsample implements EntryPoint {

    public final GuiInjector guiInjector = GWT.create(GuiInjector.class);

    public void onModuleLoad() {
        DelayedBindRegistry.bind(guiInjector);
        guiInjector.getPlaceManager().revealCurrentPlace();
    }
}
