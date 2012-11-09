package telephony.client.ui.widget;


import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import telephony.client.configuration.COLOR;
import telephony.client.configuration.SIZE;

/**
 * Klasa reprezentująca obiekt menu pomocniczego aplikacji
 */
public class TopSubmenu extends HLayout {


    Label label;

    public TopSubmenu() {
        super();

        Log.debug("Initializing TopSubmenu widget..");

        this.setHeight(SIZE.TOP_SUBMENU_HEIGHT);
        this.setBackgroundColor(COLOR.SUBMENU_BACKGROUND);

        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth("100%");
        toolStrip.setHeight("100%");
        toolStrip.addFill();

        this.addMember(toolStrip);

        Log.debug("TopSubmenu widget was initialized..");
    }
}