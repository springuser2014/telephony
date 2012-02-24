package war.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import war.client.configuration.SIZE;
import war.client.service.ProductRPCService;
import war.client.service.ProductRPCServiceAsync;
import war.client.service.StoreRPCService;
import war.client.service.StoreRPCServiceAsync;
import war.client.ui.widget.interfaces.TelephonyComponent;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.common.ProductStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DeliveriesComponent extends VLayout implements TelephonyComponent {

    private final ProductRPCServiceAsync productService = GWT.create(ProductRPCService.class);
    private final StoreRPCServiceAsync storeService = GWT.create(StoreRPCService.class);

    private ListGrid productsListGrid;
    private SelectItem selectStoreCombo;
    private SelectItem selectProductStatusCombo;
    private IButton reloadButton;

    /* ladowanie danych */
    private boolean listOfStoresLoaded   = false;
    private boolean listOfProductsLoaded = false;

    private List<Product> listOfProducts = new ArrayList<Product>();
    private List<Store> listOfStores     = new ArrayList<Store>();

    public DeliveriesComponent() {
        super();

        Log.debug("Initializing DeliveriesComponent widget..");

        this.setMembersMargin(10);
        this.setAlign(VerticalAlignment.TOP);
        // initialise the layout container
        this.setHeight(SIZE.CONTEXT_BOX_HEIGHT);
//        this.setBackgroundColor(COLOR.CONTENT_BOX_BACKGROUND);

        this.productsListGrid = new ListGrid();
        productsListGrid.setWidth(1010);
        productsListGrid.setHeight(400);
        productsListGrid.setShowAllRecords(true);

        ListGridField field1 = new ListGridField("label", "Tytuł zakupu", 250);
        ListGridField field2 = new ListGridField("number_of_elements", "Ilość produktów", 150);
        ListGridField field3 = new ListGridField("date_in", "Data zakupu", 100);
        ListGridField field4 = new ListGridField("who", "Odbierający", 200);
        ListGridField field5 = new ListGridField("store", "Magazyn dostawy", 150);
        ListGridField field6 = new ListGridField("sum_price_in", "Sumaryczna kwota dostawy", 150);

        this.productsListGrid.setFields(new ListGridField[]{field1, field2, field3, field4, field5, field6});

        this.selectStoreCombo = new SelectItem();
        selectStoreCombo.setTitle("Magazyn");

        this.selectProductStatusCombo = new SelectItem();
        selectProductStatusCombo.setTitle("Status produktu");

        this.reloadButton = new IButton("Odśwież listę");
        reloadButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                fillWithData();
            }
        });

//        reloadButton.addClickHandler(new ClickHandler() {
//            @Override
//            public void onClick(ClickEvent event) {
//
//                fillWithData();
//            }
//        });



        DynamicForm form = new DynamicForm();
        form.setFields(selectStoreCombo);

        HLayout formLay = new HLayout();
        formLay.setWidth100();
        formLay.setHeight(10);

        formLay.setMembersMargin(10);

        formLay.addMember(form);
        formLay.addMember(reloadButton);

//        this.addMember(formLay);

        this.addMember(reloadButton);

//        productsListGrid.setMargin(20);

        this.addMember(productsListGrid);

        this.loadData();

        Log.debug("ContentBox DeliveriesComponent was initialized..");
    }

    class StoreProductsComponentRecord extends ListGridRecord {

        public StoreProductsComponentRecord() {
        }

        public StoreProductsComponentRecord(Product product) {
            setLabel("label");
            setDateIn("dateIn");
            setNumberOfProducts("numberOfProducts");
            setStore("store");
            setWho("who");
            setSumPriceIn("sumPriceIn");
        }

        public void setLabel(String label) {
            setAttribute("label", label);
        }

        public String getLabel() {
            return getAttributeAsString("label");
        }

        public void setDateIn(String label) {
            setAttribute("date_in", label);
        }

        public String getDateIn() {
            return getAttributeAsString("date_in");
        }

        public void setNumberOfProducts(String label) {
            setAttribute("number_of_products", label);
        }

        public String getNumberOfProducts() {
            return getAttributeAsString("number_of_products");
        }

        public void setStore(String label) {
            setAttribute("store", label);
        }

        public String getStore() {
            return getAttributeAsString("store");
        }

        public void setWho(String label) {
            setAttribute("who", label);
        }

        public String getWho() {
            return getAttributeAsString("who");
        }

        public void setSumPriceIn(String label) {
            setAttribute("sum_price_in", label);
        }

        public String getSumPriceIn() {
            return getAttributeAsString("sum_price_in");
        }
    }

    public void loadData() {

        /* pobranie listy sklepów do tabeli */
        this.storeService.fetchAllStores(new AsyncCallback<List<Store>>() {

            public void onFailure(Throwable caught) {
                SC.say("Niestety pobranie produktów nie powiodło się, jeżeli problem będzie się powtarzał skontaktuj się z administratorem");

                listOfStoresLoaded = false;
            }

            public void onSuccess(List<Store> stores) {
                listOfStores = stores;
                listOfStoresLoaded = true;

                dataChanged();
            }
        });

    }

    public void fillWithData() {
         /* pobranie produktow do tabeli */
        this.productService.fetchAllProducts(getSelectedStore(), getSelectedProductsStatus(), new AsyncCallback<List<Product>>() {

            public void onFailure(Throwable caught) {
                SC.say("Niestety pobranie produktów nie powiodło się, jeżeli problem będzie się powtarzał skontaktuj się z administratorem");
                listOfProductsLoaded = false;
            }

            public void onSuccess(List<Product> result) {

                listOfProductsLoaded = true;
                listOfProducts = result;

                refreshProductsGrid();
            }
        });
    }

    public void dataChanged() {
        if (isDataLoaded()) {
            refreshProductsStatusCombo();
            refreshStoresCombo();
        }
    }

    public void presentComponent() {
        this.show();
    }

    public void foldComponent() {
        this.hide();
    }

    public boolean isDataLoaded() {
        return (this.listOfStoresLoaded);
    }

    private void refreshProductsGrid() {

        ListGridRecord [] records = this.productsListGrid.getRecords();

        for (int i = 0; i < records.length; i++ ) {
            this.productsListGrid.removeData(records[i]);
        }

        for (Product p : listOfProducts) {
            StoreProductsComponentRecord record = new StoreProductsComponentRecord(p);
            this.productsListGrid.addData(record);
        }
    }
    
    
    protected void refreshProductsStatusCombo() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        valueMap.put(ProductStatus.IN_STORE.toString(), "Na magazynie");
        valueMap.put(ProductStatus.SOLD.toString(), "Sprzedane");

        this.selectProductStatusCombo.setValueMap(valueMap);
        this.selectProductStatusCombo.setDefaultToFirstOption(true);
    }
    

    public void refreshData() {

//        this.refreshProductsGrid();
//        this.refreshProductsStatusCombo();
//        this.refreshStoresCombo();
    }


    private void refreshStoresCombo() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();
        
        for (Store store : listOfStores) {
            valueMap.put(store.getId().toString(), store.getLabel());
        }

        this.selectStoreCombo.setValueMap(valueMap);
        this.selectStoreCombo.setDefaultToFirstOption(true);
    }

    public void validate() {}

    public Long getSelectedStore() {
        String val = this.selectStoreCombo.getValueAsString();
        long l = Long.parseLong(val);
        return l;
    }

    public ProductStatus getSelectedProductsStatus() {
//        String val = this.selectProductStatusCombo.getValueAsString();
//
//        if (val.equals(ProductStatus.IN_STORE.toString())) {
//            return ProductStatus.IN_STORE;
//        }
////        else if (val.equals(ProductStatus.SOLD.toString())) {
//        else {
//            return ProductStatus.SOLD;
//        }

        return ProductStatus.IN_STORE;

    }
}

