package war.client;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import war.client.configuration.SIZE;
import war.client.gin.GuiInjector;
import war.client.ui.widget.*;


/**
 * GENERAL 'ARCHITECTURE' REFACTORING ELEMENTS TODO :

 * @todo refactor constants, labels etc
 * @todo better logging on client side
 * @todo better logging on server side
 * @todo sending client side errors to remote server
 * @todo divide GUI into components
 * @todo internationalization GUI elements
 * @todo better integration with guice injection (gin)
 * @todo better integration with guice (server side)
 * @todo integration with gwtp : event bus, dispatch
 * @todo prepare continuous integration server and app config
 * @todo prepare second instance of producation server for preview
 *
 * GENERAL APPLICATION FEATURES TODO :
 * @todo add searching, filtering, ordering products in deliveries and sales pages
 * @todo sign in, users management, stores management
 *
 */
public class adminDashboard implements EntryPoint {

    private final GuiInjector injector = GWT.create(GuiInjector.class);

    private VLayout mainLayout;

    public void onModuleLoad() {

        configModule();

        StoreProductsComponent storeProductsPage = injector.getStoreProductsPage();
        DeliveriesComponent deliveriesPage = injector.getDeliveriesPage();
        SalesComponent salesPage = injector.getSalesPage();

        AddStoreComponent addStoreComponent = injector.getAddStorePage();
        MoveProductsComponent editStoreComponent = injector.getEditStoreComponent();
        SaleProductsComponent saleProductsComponent = injector.getSalesProductsComponent();

        Log.debug("Initializing EntryPoint : adminDashboard ");

        // get rid of scroll bars, and clear out the window's built-in margin,
        // because we want to take advantage of the entire client area
        Window.enableScrolling(false);
        Window.setMargin("0px");


        final TabSet topTabSet = new TabSet();
        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setWidth(SIZE.CONTEXT_BOX_WIDTH + 50);
        topTabSet.setHeight(SIZE.CONTEXT_BOX_HEIGHT + 50);


        Tab tTab1 = new Tab("Produkty w magazynie");
        tTab1.setPane(storeProductsPage);

        Tab tTab2 = new Tab("Dodaj produkty");
        tTab2.setPane(addStoreComponent);

        Tab tTab3 = new Tab("Przenieś produkty");
        tTab3.setPane(editStoreComponent);

        Tab tTab4 = new Tab("Sprzedaj produkty");
        tTab4.setPane(saleProductsComponent);

        Tab tTab5 = new Tab("Dostawy");
        tTab5.setPane(deliveriesPage);

        Tab tTab6 = new Tab("Sprzedaż");
        tTab6.setPane(salesPage);
        
        topTabSet.setMargin(10);

        topTabSet.addTab(tTab1);
        topTabSet.addTab(tTab2);
        topTabSet.addTab(tTab3);
        topTabSet.addTab(tTab4);
        topTabSet.addTab(tTab5);
        topTabSet.addTab(tTab6);

        mainLayout = new VLayout();
        mainLayout.setWidth100();
        mainLayout.setHeight100();
        mainLayout.setAlign(VerticalAlignment.TOP);

        mainLayout.addMember(topTabSet);


        RootLayoutPanel.get().add(mainLayout);
    }

    private void configModule() {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

            public void onUncaughtException(Throwable e) {

                Log.error("UncaughtException : ",e);
            }
        });
    }

}
