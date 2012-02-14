package war.client.ui.widget;


import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import war.client.configuration.COLOR;
import war.client.configuration.SIZE;
import war.client.gin.GuiInjector;


/**
 * Klasa reprezentująca obiekt górnego menu aplikacji
 */
public class TopMenu extends HLayout {

    private final GuiInjector injector = GWT.create(GuiInjector.class);

    Label label;

    public TopMenu() {
        super();

        Log.debug("Initializing TopMenu widget..");

        // initialise the layout container
        this.setHeight(SIZE.TOPMENU_HEIGHT);
        this.setBackgroundColor(COLOR.TOPMENU_BACKGROUND);

        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth("80%");
        toolStrip.setHeight("100%");
        toolStrip.addFill();

        ToolStripButton productsButton = new ToolStripButton();
        productsButton.setTitle("Produkty");
        productsButton.setWidth(100);
        productsButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SC.say("productsButton.onClick");
            }
        });

        ToolStripButton deliveriesButton = new ToolStripButton();
        deliveriesButton.setTitle("Dostawy");
        deliveriesButton.setWidth(100);
        deliveriesButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SC.say("deliveriesButton.onClick");
            }
        });

        ToolStripButton salesButton = new ToolStripButton();
        salesButton.setTitle("Sprzedaż");
        salesButton.setWidth(100);
        salesButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                SC.say("salesButton.onClick");
            }
        });


        toolStrip.addButton(productsButton);
        toolStrip.addButton(deliveriesButton);
        toolStrip.addButton(salesButton);

        toolStrip.addFill();
        toolStrip.setAlign(Alignment.LEFT);

        this.addMember(toolStrip);

        Log.debug("TopmMenu widget was initialized..");
    }
}
