package war.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.VLayout;
import war.client.configuration.COLOR;
import war.client.configuration.SIZE;
import war.client.service.ProductRPCService;
import war.client.service.ProductRPCServiceAsync;
import war.client.service.StoreRPCServiceAsync;
import war.client.service.StoreRPCService;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.common.Money;
import war.server.core.entity.common.ProductStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class StoreProductsComponent extends VLayout implements TelephonyComponent {

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

    public StoreProductsComponent() {
        super();

        Log.debug("Initializing ContextBox widget..");

        // initialise the layout container
        this.setHeight(SIZE.CONTEXT_BOX_HEIGHT);
        this.setBackgroundColor(COLOR.CONTENT_BOX_BACKGROUND);

        this.setAlign(Alignment.CENTER);

        this.productsListGrid = new ListGrid();
        productsListGrid.setWidth(1000);
        productsListGrid.setHeight(550);
        productsListGrid.setShowAllRecords(true);

        ListGridField field1 = new ListGridField("imei", "IMEI", 200);
        ListGridField field2 = new ListGridField("color", "Kolor", 200);
        ListGridField field3 = new ListGridField("producer", "Producent", 200);
        ListGridField field4 = new ListGridField("model", "Model", 200);
        ListGridField field5 = new ListGridField("price_in", "Cena przychodząca", 200);

        this.productsListGrid.setFields(new ListGridField[]{field1, field2, field3, field4, field5});

        this.selectStoreCombo = new SelectItem();
        selectStoreCombo.setTitle("Magazyn");

        this.selectProductStatusCombo = new SelectItem();
        selectProductStatusCombo.setTitle("Status produktu");

        this.reloadButton = new IButton("Pokaż produkty");
        reloadButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                fillWithData();
            }
        });

        DynamicForm form = new DynamicForm();
        form.setFields(selectStoreCombo, selectProductStatusCombo);

        form.setMargin(10);
        this.addMember(form);
        reloadButton.setMargin(10);

        this.addMember(reloadButton);

        productsListGrid.setMargin(10);
        this.addMember(productsListGrid);

        this.loadData();

        Log.debug("ContentBox widget was initialized..");
    }

    class StoreProductsComponentRecord extends ListGridRecord {

        public StoreProductsComponentRecord() {
        }

        public StoreProductsComponentRecord(Product product) {
            setImei(product.getImei());
            setColor(product.getColor());
            setProducer(product.getProducer());
            setModel(product.getModel());
            setPriceIn(product.getPriceIn());
        }

        public void setImei(String imei) {
            setAttribute("imei", imei);
        }

        public String getImei() {
            return getAttributeAsString("imei");
        }

        public void setColor(String color) {
            setAttribute("color", color);
        }

        public String getColor() {
            return getAttributeAsString("color");
        }

        public void setModel(String model) {
            setAttribute("model", model);
        }

        public String getModel() {
            return getAttributeAsString("model");
        }

        public void setProducer(String producer) {
            setAttribute("producer", producer);
        }

        public String getProducer() {
            return getAttributeAsString("producer");
        }
        
        public void setPriceIn(Money money) {
            setAttribute("price_in", money.toString());
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
        String val = this.selectProductStatusCombo.getValueAsString();
        
        if (val.equals(ProductStatus.IN_STORE.toString())) {
            return ProductStatus.IN_STORE;
        }
//        else if (val.equals(ProductStatus.SOLD.toString())) {
        else {
            return ProductStatus.SOLD;
        }

    }
}

