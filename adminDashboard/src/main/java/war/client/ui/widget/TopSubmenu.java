package war.client.ui.widget;


import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import war.client.configuration.COLOR;
import war.client.configuration.SIZE;

/**
 * Klasa reprezentująca obiekt menu pomocniczego aplikacji
 */
public class TopSubmenu extends HLayout {


    Label label;

    public TopSubmenu() {
        super();

        Log.debug("Initializing TopSubmenu widget..");

        // initialise the layout container
        this.setHeight(SIZE.TOP_SUBMENU_HEIGHT);
        this.setBackgroundColor(COLOR.SUBMENU_BACKGROUND);

        // initialise the masthead label
//        label = new Label();
//        label.setContents("TopSubmenu");
//        label.setAlign(Alignment.CENTER);
//        label.setOverflow(Overflow.HIDDEN);
//
//        // add the label to the layout container
//        this.addMember(label);

        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth("100%");
        toolStrip.setHeight("100%");
        toolStrip.addFill();

        this.addMember(toolStrip);

        Log.debug("TopSubmenu widget was initialized..");
    }
}