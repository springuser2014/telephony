package war.client.ui.widgets;


import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import war.client.GreetingService;
import war.client.GreetingServiceAsync;


/**
 * Klasa reprezentująca obiekt górnego menu aplikacji
 */
public class TopMenu extends HLayout {

    private static final int MASTHEAD_HEIGHT = 58;

    Label label;

    public TopMenu() {
        super();

        Log.debug("Initializing TopMenu widget..");

        // initialise the layout container
        this.setHeight(MASTHEAD_HEIGHT);
        this.setBackgroundColor("#C3D9FF");

        // initialise the masthead label
        label = new Label();
        label.setContents("TopMenu");
        label.setAlign(Alignment.CENTER);
        label.setOverflow(Overflow.HIDDEN);

        // add the label to the layout container
        this.addMember(label);

        Log.debug("TopmMenu widget was initialized..");
    }
}
