package war.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
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
import war.client.ui.widget.component.Pager;
import war.client.ui.widget.interfaces.TelephonyComponent;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.common.Money;
import war.server.core.entity.common.ProductStatus;

import java.util.ArrayList;
import java.util.Date;
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
    private boolean listOfStoresLoaded = false;
    private boolean listOfProductsLoaded = false;
    private boolean listOfColorsLoaded = false;
    private boolean listOfModelsLoaded = false;
    private boolean listOfProducersLoaded = false;

    private List<Product> listOfProducts = new ArrayList<Product>();
    private List<Store> listOfStores = new ArrayList<Store>();
    private List<String> listOfColors = new ArrayList<String>();
    private List<String> listOfProducers = new ArrayList<String>();
    private List<String> listOfModels = new ArrayList<String>();

    private TextItem imeibox;
    private ComboBoxItem selectColorCombo;
    private ComboBoxItem selectProducerCombo;

    private ComboBoxItem selectModelCombo;
    private DateItem deliveryDateFrom;
    private DateItem deliveryDateTo;
    private Label numberOfElementsLabel;


    public StoreProductsComponent() {
        super();

        Log.debug("Initializing ContextBox widget..");

        this.setMembersMargin(10);

        // initialise the layout container
        this.setHeight(SIZE.CONTEXT_BOX_HEIGHT);
//        this.setBackgroundColor(COLOR.CONTENT_BOX_BACKGROUND);

        this.setAlign(Alignment.CENTER);
        this.setAlign(VerticalAlignment.TOP);
        this.productsListGrid = new ListGrid();
        productsListGrid.setWidth(1010);
        productsListGrid.setHeight(400);
        productsListGrid.setShowAllRecords(true);

        ListGridField field1 = new ListGridField("imei", "IMEI", 160);
        ListGridField field2 = new ListGridField("color", "Kolor", 160);
        ListGridField field3 = new ListGridField("producer", "Producent", 120);
        ListGridField field4 = new ListGridField("model", "Model", 120);
        ListGridField field5 = new ListGridField("price_in", "Cena zakupu", 120);
        ListGridField field6 = new ListGridField("date_in", "Data dodania", 120);
        ListGridField field7 = new ListGridField("delivery_title", "Nazwa dostawy", 200);

        this.productsListGrid.setFields(new ListGridField[]{field1, field2, field3, field4, field5, field6, field7});

        this.numberOfElementsLabel = new Label();
        this.numberOfElementsLabel.setContents("Ilość produktów : 0");

        this.selectStoreCombo = new SelectItem();
        selectStoreCombo.setTitle("Magazyn");

        this.selectProductStatusCombo = new SelectItem();
        selectProductStatusCombo.setTitle("Status produktu");

        this.reloadButton = new IButton("Pokaż produkty");
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

        this.addMember(formLay);

//        this.addMember(reloadButton);

//        productsListGrid.setMargin(20);


        Pager pager = new Pager();
        pager.setNumberOfElements(2000);
        pager.setNumberOfElementsPerPage(Pager.PerPage.TEN);


        HLayout filterLayout = new HLayout();

        // dolna-ogólna część formularza
        DynamicForm form0 = new DynamicForm();
        imeibox = new TextItem();
        imeibox.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                if (event.getKeyName().equals("Enter")) {
                    fillWithData();
                }
            }
        });

        imeibox.setTitle("IMEI");
        imeibox.setWidth(160);
        form0.setTitleWidth(40);
        imeibox.setTitleAlign(Alignment.LEFT);
        form0.setFields(imeibox);

        DynamicForm form2 = new DynamicForm();
        selectColorCombo = new ComboBoxItem();
        selectColorCombo.setTitle("Kolor");
        selectColorCombo.setWidth(160);
        form2.setTitleWidth(40);
        selectColorCombo.setTitleAlign(Alignment.LEFT);
        form2.setFields(selectColorCombo);

        DynamicForm form3 = new DynamicForm();
        selectProducerCombo = new ComboBoxItem();
        selectProducerCombo.setTitle("Producent");
        selectProducerCombo.setTitleAlign(Alignment.LEFT);
        selectProducerCombo.setWidth(150);
        form3.setTitleWidth(50);
        form3.setFields(selectProducerCombo);

        DynamicForm form4 = new DynamicForm();
        selectModelCombo = new ComboBoxItem();
        selectModelCombo.setTitle("Model");
        selectModelCombo.setTitleAlign(Alignment.LEFT);
        selectModelCombo.setWidth(160);
        form4.setTitleWidth(40);
        form4.setFields(selectModelCombo);
        form4.setWidth(200);

        DynamicForm formdel3 = new DynamicForm();
        formdel3.setWidth(300);
        formdel3.setTitleWidth(100);
        deliveryDateFrom = new DateItem();
        deliveryDateFrom.setTitle("Data dostawy od ");
        deliveryDateFrom.setValue(new Date("01-01-2012"));

        DateUtil.setShortDateDisplayFormatter(new DateDisplayFormatter() {
            public String format(Date date) {
                if (date == null) return null;

                final DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd-MM-yyyy");
                String format = dateFormatter.format(date);
                return format;
            }

        });

        deliveryDateTo = new DateItem();
        deliveryDateTo.setTitle("Data dostawy do ");

        formdel3.setFields(deliveryDateFrom, deliveryDateTo);

        filterLayout.addMember(form0);
        filterLayout.addMember(form2);
        filterLayout.addMember(form3);
        filterLayout.addMember(form4);
        filterLayout.addMember(formdel3);

        this.addMember(filterLayout);

        this.addMember(productsListGrid);

        this.addMember(numberOfElementsLabel);

        this.loadData();

        Log.debug("ContentBox widget was initialized..");
    }

    public Date getDeliveryDateStart() {
        return this.deliveryDateFrom.getValueAsDate();
    }

    public Date getDeliveryDateEnd() {
        return this.deliveryDateTo.getValueAsDate();
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
            setDateIn(product.getDelivery().getDateIn());
            setDeliveryTitle(product.getDelivery().getLabel());
        }

        public void setDateIn(Date dateIn) {
            setAttribute("date_in", dateIn);
        }
        
        public void setDeliveryTitle(String label) {
            setAttribute("delivery_title", label);
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

        loadStores();
        loadColors();
        loadModels();
        loadProducers();
//        loadUsers();
//        loadImeisInUse();
    }

    private void loadProducers() {
        this.productService.fetchAllProducers(new AsyncCallback<List<String>>() {
            public void onFailure(Throwable caught) {
                SC.say("Niestety nie powiodło się pobranie wszystkich wymaganych danych");

                listOfProducersLoaded = false;
            }

            public void onSuccess(List<String> result) {
                listOfProducers = result;
                listOfProducersLoaded = true;

                dataChanged();
            }
        });
    }

    private void loadModels() {
        this.productService.fetchAllModels(new AsyncCallback<List<String>>() {
            public void onFailure(Throwable caught) {
                SC.say("Niestety nie powiodło się pobranie wszystkich wymaganych danych");

                listOfModelsLoaded = false;
            }

            public void onSuccess(List<String> result) {
                listOfModels = result;
                listOfModelsLoaded = true;

                dataChanged();
            }
        });
    }

    private void loadColors() {
        this.productService.fetchAllColors(new AsyncCallback<List<String>>() {
            public void onFailure(Throwable caught) {
                SC.say("Niestety nie powiodło się pobranie wszystkich wymaganych danych");

                listOfColorsLoaded = false;
            }

            public void onSuccess(List<String> result) {
                listOfColors = result;
                listOfColorsLoaded = true;

                dataChanged();
            }
        });
    }

    private void loadStores() {
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
        this.productService.fetchAllProductsByCriteria(getImei(), getProducer(), getModel(), getColor(), getSelectedStore(), getDeliveryDateStart(), getDeliveryDateEnd(), getSelectedProductsStatus(), new AsyncCallback<List<Product>>() {


            public void onFailure(Throwable caught) {
                SC.say("Niestety pobranie produktów nie powiodło się, jeżeli problem będzie się powtarzał skontaktuj się z administratorem");
                listOfProductsLoaded = false;
            }

            public void onSuccess(List<Product> result) {

                listOfProductsLoaded = true;
                listOfProducts = result;

                refreshProductsGrid();

                numberOfElementsLabel.setContents("Ilość produktów : " + result.size());
            }
        });
    }

    private String getProducer() {
        return this.selectProducerCombo.getValueAsString();
    }

    private String getModel() {
        return this.selectModelCombo.getValueAsString();
    }

    private String getColor() {
        return this.selectColorCombo.getValueAsString();
    }

    private String getImei() {
        return this.imeibox.getValueAsString();
    }

    public void dataChanged() {
        if (isDataLoaded()) {
            refreshData();
        }
    }

    public void presentComponent() {
        this.show();
    }

    public void foldComponent() {
        this.hide();
    }

    public boolean isDataLoaded() {
        return (this.listOfStoresLoaded &&
                this.listOfColorsLoaded &&
                this.listOfModelsLoaded &&
                this.listOfProducersLoaded);
    }

    private void refreshProductsGrid() {

        ListGridRecord[] records = this.productsListGrid.getRecords();

        for (int i = 0; i < records.length; i++) {
            this.productsListGrid.removeData(records[i]);
        }

        for (Product p : listOfProducts) {
            StoreProductsComponentRecord record = new StoreProductsComponentRecord(p);
            this.productsListGrid.addData(record);
        }
    }

    public void refreshData() {

        refreshStoresCombo();
        refreshColorComboValues();
        refreshSelectModelComboValues();
        refreshSelectProducerComboValues();
    }


    private void refreshColorComboValues() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        valueMap.put("", "");
        for (String color : listOfColors) {
            valueMap.put(color, color);
        }

        this.selectColorCombo.setValueMap(valueMap);
        this.selectColorCombo.setDefaultToFirstOption(true);
    }

    private void refreshSelectModelComboValues() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        valueMap.put("", "");
        for (String model : listOfModels) {
            valueMap.put(model, model);
        }

        this.selectModelCombo.setValueMap(valueMap);
        this.selectModelCombo.setDefaultToFirstOption(true);
    }

    private void refreshSelectProducerComboValues() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        valueMap.put("", "");
        for (String producer : listOfProducers) {
            valueMap.put(producer, producer);
        }

        this.selectProducerCombo.setValueMap(valueMap);
        this.selectProducerCombo.setDefaultToFirstOption(true);
    }


    private void refreshStoresCombo() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (Store store : listOfStores) {
            valueMap.put(store.getId().toString(), store.getLabel());
        }

        this.selectStoreCombo.setValueMap(valueMap);
        this.selectStoreCombo.setDefaultToFirstOption(true);
    }

    public void validate() {
    }

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

