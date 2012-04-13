package war.client.ui.widget;


import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import war.client.configuration.COLOR;
import war.client.configuration.SIZE;
import war.client.gin.GuiInjector;


public class TopMenu extends HLayout {

    private final GuiInjector injector = GWT.create(GuiInjector.class);

    public TopMenu() {
        super();

        Log.debug("Initializing TopMenu widget..");
        this.setHeight(SIZE.TOPMENU_HEIGHT);
        this.setBackgroundColor(COLOR.TOPMENU_BACKGROUND);

        ToolStrip toolStrip = new ToolStrip();
        toolStrip.setWidth("80%");
        toolStrip.setHeight("100%");
        toolStrip.addFill();

        ToolStripButton productsButton = new ToolStripButton();
        productsButton.setTitle("Produkty w magazynie");
        productsButton.setWidth(100);
        productsButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                showStoreProductsPage();
            }
        });

        ToolStripButton editProductsButton = new ToolStripButton();
        editProductsButton.setTitle("Edycja produktów");
        editProductsButton.setWidth(100);
        editProductsButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                showEditProductsPage();
            }
        });

        ToolStripButton deliveriesButton = new ToolStripButton();
        deliveriesButton.setTitle("Dostawy");
        deliveriesButton.setWidth(100);
        deliveriesButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                showDeliveriesPage();
            }
        });

        ToolStripButton salesButton = new ToolStripButton();
        salesButton.setTitle("Sprzedaż");
        salesButton.setWidth(100);
        salesButton.addClickHandler(new ClickHandler() {

            public void onClick(ClickEvent event) {
                showSalesPage();
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

    private void showSalesPage() {
        SC.say("showSalesPage");
    }

    private void showDeliveriesPage() {
        SC.say("showDeliveriesPage");
    }

    private void showEditProductsPage() {
        SC.say("showEditProductsPage");
    }

    private void showStoreProductsPage() {

        SC.say("showStoreProductsPage");

        StoreProductsComponent page = injector.getStoreProductsPage();
        page.presentComponent();
    }
}
