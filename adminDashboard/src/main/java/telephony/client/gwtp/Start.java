package telephony.client.gwtp;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import telephony.client.configuration.SIZE;
import telephony.client.gin.GuiInjector;
import telephony.client.ui.widget.*;


/**
 * @todo delete me?
 */
public class Start implements EntryPoint {

    private final GuiInjector injector = GWT.create(GuiInjector.class);

    private VLayout mainLayout;

    private VLayout newMainLayout;

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


        newMainLayout = new VLayout();
        newMainLayout.setWidth100();
        newMainLayout.setHeight100();
        newMainLayout.setAlign(VerticalAlignment.TOP);
        newMainLayout.setMembersMargin(20);
        newMainLayout.setLayoutMargin(5);


        VLayout headerLayout = new VLayout();
        headerLayout.setWidth100();
        headerLayout.setHeight(150);
        headerLayout.addMember(new BlueBox("*", null, "width *"));
        headerLayout.setLayoutMargin(5);

        HLayout contentLayout = new HLayout();
        contentLayout.setWidth100();
        contentLayout.setHeight100();
        contentLayout.addMember(new BlueBox(50, (Integer) null, "width 50"));
        contentLayout.addMember(new BlueBox("*", null, "width *"));
        contentLayout.addMember(new BlueBox("30%", null, "width 30%"));
        contentLayout.setMembersMargin(20);
        contentLayout.setLayoutMargin(5);

        newMainLayout.addMember(headerLayout);
        newMainLayout.addMember(contentLayout);

        RootLayoutPanel.get().add(newMainLayout);

    }

    class BlueBox extends Label {

        public BlueBox(String contents) {
            setAlign(Alignment.CENTER);
            setBorder("1px solid #808080");
            setBackgroundColor("#C3D9FF");
            setContents(contents);
        }

        public BlueBox(Integer width, Integer height, String contents) {
            this(contents);
            if (width != null) setWidth(width);
            if (height != null) setHeight(height);
        }

        public BlueBox(Integer width, String height, String contents) {
            this(contents);
            if (width != null) setWidth(width);
            if (height != null) setHeight(height);
        }

        public BlueBox(String width, String height, String contents) {
            this(contents);
            if (width != null) setWidth(width);
            if (height != null) setHeight(height);
        }
    }

    private void configModule() {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {

            public void onUncaughtException(Throwable e) {

                Log.error("UncaughtException : ", e);
            }
        });
    }

}
