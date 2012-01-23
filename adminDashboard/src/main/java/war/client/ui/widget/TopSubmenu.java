package war.client.ui.widget;


import com.allen_sauer.gwt.log.client.Log;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
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
        label = new Label();
        label.setContents("TopMenu");
        label.setAlign(Alignment.CENTER);
        label.setOverflow(Overflow.HIDDEN);

        // add the label to the layout container
        this.addMember(label);

        Log.debug("TopSubmenu widget was initialized..");
    }
}