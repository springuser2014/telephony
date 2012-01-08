package war.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.smartgwt.client.widgets.layout.VLayout;
import war.client.gin.GuiIjector;
import war.client.ui.widgets.ContentBox;
import war.client.ui.widgets.TopMenu;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class adminDashboard implements EntryPoint {

    private final GuiIjector injector = GWT.create(GuiIjector.class);

    private VLayout mainLayout;

    public void onModuleLoad() {

        ContentBox contentBox = injector.getContentBox();
        TopMenu topMenu = injector.getTopMenu();

        Log.debug("Initializing EntryPoint : adminDashboard ");

        // get rid of scroll bars, and clear out the window's built-in margin,
        // because we want to take advantage of the entire client area
        Window.enableScrolling(false);
        Window.setMargin("0px");

        // initialise the main layout container
        mainLayout = new VLayout();
        mainLayout.setWidth100();
        mainLayout.setHeight100();

        // add the top menu container to the main layout container
        mainLayout.addMember(topMenu);

        // add the default content container to the main layout container
        mainLayout.addMember(contentBox);

        // add the main layout container to GWT's root panel
        RootLayoutPanel.get().add(mainLayout);
    }

}
