package telephony.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.grid.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import telephony.client.configuration.SIZE;
import telephony.client.service.*;
import telephony.client.ui.widget.interfaces.TelephonyComponent;
import telephony.server.core.entity.Delivery;
import telephony.server.core.entity.Product;
import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;
import telephony.server.core.entity.common.Money;
import telephony.server.core.entity.common.ProductStatus;
import telephony.shared.RPCServiceStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;


public class AddStoreComponent extends VLayout implements TelephonyComponent {

    private final ProductRPCServiceAsync productService = GWT.create(ProductRPCService.class);
    private final StoreRPCServiceAsync storeService = GWT.create(StoreRPCService.class);
    private final UserRPCServiceAsync userService = GWT.create(UserRPCService.class);
    private final DeliveryRPCServiceAsync deliveryService = GWT.create(DeliveryRPCService.class);

    private ListGrid productsListGrid;
    private SelectItem selectStoreCombo;
    private SelectItem selectSalemanCombo;
    private DateItem selectDate;
    private TextItem imeibox;
    private TextItem pricebox;
    private ComboBoxItem selectProducerCombo;
    private ComboBoxItem selectModelCombo;
    private ComboBoxItem selectColorCombo;
    private TextItem deliveryTitle;
    private Label numberOfElementsLabel;
    private ButtonItem addButton;
    private ButtonItem saveDelivery;

    private boolean listOfStoresLoaded = false;
    private boolean listOfProductsLoaded = false;
    private boolean listOfUsersLoaded = false;
    private boolean listOfColorsLoaded = false;
    private boolean listOfProducersLoaded = false;
    private boolean listOfModelsLoaded = false;
    private boolean listOfImeisLoaded = false;

    private List<Product> listOfProducts = new ArrayList<Product>();
    private List<Store> listOfStores = new ArrayList<Store>();
    private List<User> listOfUsers = new ArrayList<User>();
    private List<String> listOfColors = new ArrayList<String>();
    private List<String> listOfProducers = new ArrayList<String>();
    private List<String> listOfModels = new ArrayList<String>();
    private List<String> listOfImeis = new ArrayList<String>();
    private List<String> listOfNewImeis = new ArrayList<String>();
    private List<Product> listOfNewProducts = new ArrayList<Product>();

    public AddStoreComponent() {
        super();

        Log.debug("Initializing AddStoreComponent widget");

        this.setHeight(SIZE.CONTEXT_BOX_HEIGHT);
        this.setAlign(Alignment.CENTER);
        this.setAlign(VerticalAlignment.TOP);
        this.setMembersMargin(10);

        this.productsListGrid = new ListGrid();
        this.productsListGrid.setWidth(1010);
        this.productsListGrid.setHeight(400);
        this.productsListGrid.setShowAllRecords(true);

        ListGridField field1 = new ListGridField("imei", "IMEI", 180);
        ListGridField field2 = new ListGridField("color", "Kolor", 180);
        ListGridField field3 = new ListGridField("producer", "Producent", 180);
        ListGridField field4 = new ListGridField("model", "Model", 180);
        ListGridField field5 = new ListGridField("price_in", "Cena zakupu", 180);
        ListGridField field6 = new ListGridField("delete_item", "Usuń", 100);

        field1.setCanEdit(false);
        field2.setCanEdit(false);
        field3.setCanEdit(false);
        field4.setCanEdit(false);
        field5.setCanEdit(false);

        this.productsListGrid.setEditorCustomizer(new ListGridEditorCustomizer() {

            private AddStoreComponentRecord record;

            public FormItem getEditor(ListGridEditorContext context) {
                ListGridField field = context.getEditField();

                if (field.getName().equals("delete_item")) {
                    record = (AddStoreComponentRecord) context.getEditedRecord();

                    ButtonItem button = new ButtonItem();
                    button.setTitle("usuń produkt");

                    button.addClickHandler(new ClickHandler() {
                        public void onClick(ClickEvent event) {
                            deleteRecordWhereImei(record.getImei());
                            getProductsListGrid().removeData(record);
                            refreshProductsGridInfo();
                            refreshProductsGrid();
                        }
                    });

                    return button;
                }

                return context.getDefaultProperties();
            }
        });

        this.productsListGrid.setCanEdit(true);
        this.productsListGrid.setEditEvent(ListGridEditEvent.CLICK);

        this.productsListGrid.setFields(new ListGridField[]{field1, field2, field3, field4, field5, field6});

        HLayout formLaydelivery = new HLayout();
        formLaydelivery.setMembersMargin(30);
        formLaydelivery.setHeight(25);

        DynamicForm form0 = new DynamicForm();
        form0.setTitleWidth(100);
        form0.setWidth(300);
        this.deliveryTitle = new TextItem();
        this.deliveryTitle.setTitle("Tytuł dostawy");
        form0.setFields(this.deliveryTitle);

        DynamicForm formdel = new DynamicForm();
        formdel.setTitleWidth(60);
        this.selectStoreCombo = new SelectItem();
        this.selectStoreCombo.setTitle("Magazyn");
        formdel.setFields(this.selectStoreCombo);

        DynamicForm formdel2 = new DynamicForm();
        this.selectSalemanCombo = new SelectItem();
        this.selectSalemanCombo.setTitle("Dodający");
        formdel2.setFields(this.selectSalemanCombo);

        DynamicForm formdel3 = new DynamicForm();
        formdel3.setWidth(300);
        formdel3.setTitleWidth(100);
        this.selectDate = new DateItem();
        this.selectDate.setTitle("Data dostawy");

        DateUtil.setShortDateDisplayFormatter(new DateDisplayFormatter() {
            public String format(Date date) {
                if (date == null) return null;

                final DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd-MM-yyyy");
                String format = dateFormatter.format(date);
                return format;
            }

        });

        formdel3.setFields(this.selectDate);

        formLaydelivery.addMember(form0);
        formLaydelivery.addMember(formdel);
        formLaydelivery.addMember(formdel2);
        formLaydelivery.addMember(formdel3);

        this.addMember(formLaydelivery);

        DynamicForm form = new DynamicForm();
        this.imeibox = new TextItem();
        this.imeibox.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                if (event.getKeyName().equals("Enter")) {
                    tryToAddProductToList();
                }
            }
        });

        this.imeibox.setTitle("IMEI");
        this.imeibox.setWidth(160);
        form.setTitleWidth(40);
        this.imeibox.setTitleAlign(Alignment.LEFT);
        form.setFields(this.imeibox);

        DynamicForm form2 = new DynamicForm();
        this.selectColorCombo = new ComboBoxItem();
        this.selectColorCombo.setTitle("Kolor");
        this.selectColorCombo.setWidth(160);
        form2.setTitleWidth(40);
        this.selectColorCombo.setTitleAlign(Alignment.LEFT);
        form2.setFields(this.selectColorCombo);

        DynamicForm form3 = new DynamicForm();
        this.selectProducerCombo = new ComboBoxItem();
        this.selectProducerCombo.setTitle("Producent");
        this.selectProducerCombo.setTitleAlign(Alignment.LEFT);
        this.selectProducerCombo.setWidth(150);
        form3.setTitleWidth(50);
        form3.setFields(this.selectProducerCombo);

        DynamicForm form4 = new DynamicForm();
        this.selectModelCombo = new ComboBoxItem();
        this.selectModelCombo.setTitle("Model");
        this.selectModelCombo.setTitleAlign(Alignment.LEFT);
        this.selectModelCombo.setWidth(160);
        form4.setTitleWidth(40);
        form4.setFields(this.selectModelCombo);
        form4.setWidth(200);

        DynamicForm form5 = new DynamicForm();

        this.pricebox = new TextItem();
        this.pricebox.setTitle("Cena zakupu");
        this.pricebox.setWidth(130);
        form5.setTitleWidth(70);
        form5.setWidth(200);
        form5.setFields(this.pricebox);

        DynamicForm form6 = new DynamicForm();
        this.addButton = new ButtonItem();
        this.addButton.setTitle("Dodaj produkt");

        this.addButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                tryToAddProductToList();
            }
        });

        form6.setFields(this.addButton);
        HLayout formLay = new HLayout();
        formLay.setWidth100();
        formLay.setHeight(25);

        formLay.setMembersMargin(5);

        formLay.addMember(form);
        formLay.addMember(form2);
        formLay.addMember(form3);
        formLay.addMember(form4);
        formLay.addMember(form5);
        formLay.addMember(form6);

        this.addMember(formLay);

        HLayout formLaybottom = new HLayout();
        DynamicForm form7 = new DynamicForm();
        this.saveDelivery = new ButtonItem();
        this.saveDelivery.setTitle("Zapisz dostawę");
        form7.setFields(this.saveDelivery);

        this.saveDelivery.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {

                String msg = validateAll();

                if (msg == null) {
                    tryToAddDelivery();
                } else {
                    SC.say(msg);
                }
            }
        });

        formLaybottom.addMember(form7);
        formLaybottom.setHeight(25);

        this.addMember(formLaybottom);
        this.addMember(this.productsListGrid);

        this.numberOfElementsLabel = new Label();
        this.numberOfElementsLabel.setContents("Ilość produktów : 0");

        this.addMember(numberOfElementsLabel);

        this.loadData();

        Log.debug("AddStoreComponent widget was initialized");
    }

    private void tryToAddDelivery() {

        Log.debug("AddStoreComponent - tryToAddDelivery");

        this.deliveryService.addNewDelivery(getDelivery(), getProducts(), getUser(), getStore(), new AsyncCallback<RPCServiceStatus>() {
            public void onFailure(Throwable caught) {
                SC.say("Dodanie dostawy nie powiodło się, jeżeli problem będzie się powtarzał skontaktuj się z administratorem");
            }

            public void onSuccess(RPCServiceStatus result) {
                SC.say(result.getOperationStatusInfo());

                if (result.getStatus().equals(RPCServiceStatus.Status.SUCCESS)) {
                    clearForm();
                    refreshProductsGrid();
                    loadData();
                } else {
                    SC.say(result.getOperationStatusInfo());
                }
            }
        });

    }

    private void clearForm() {
        this.deliveryTitle.setValue("");
        this.imeibox.setValue("");

        this.listOfNewProducts = new ArrayList<Product>();
        this.listOfNewImeis = new ArrayList<String>();
    }

    private Store getStore() {
        String storeIdStr = this.selectStoreCombo.getValueAsString();

        Long storeId = Long.parseLong(storeIdStr);

        for (Store s : this.listOfStores) {
            if (s.getId().equals(storeId)) {
                return s;
            }
        }

        return null;
    }

    private User getUser() {
        String userIdStr = this.selectSalemanCombo.getValueAsString();

        Long userId = Long.parseLong(userIdStr);

        for (User u : this.listOfUsers) {
            if (u.getId().equals(userId)) {
                return u;
            }
        }

        return null;
    }

    private List<Product> getProducts() {
        return this.listOfNewProducts;
    }

    private Delivery getDelivery() {
        Delivery d = new Delivery();

        d.setLabel(deliveryTitle.getValueAsString());
        d.setDateIn(this.selectDate.getValueAsDate());

        return d;
    }

    public ListGrid getProductsListGrid() {
        return this.productsListGrid;
    }

    private String validateAll() {

        if (deliveryTitle.getValueAsString() == null || deliveryTitle.getValueAsString().length() < 5) {
            return "Tytuł dostawy to conajmniej 5 znaków";
        }

        if (this.listOfNewProducts.size() == 0) {
            return "Nie dodano żadnych produktów";
        }

        return null;
    }

    private void deleteRecordWhereImei(String imei) {

        for (Product p : this.listOfNewProducts) {

            if (p.getImei().equals(imei)) {
                this.listOfNewProducts.remove(p);

                this.listOfNewImeis.remove(p.getImei());
            }
        }
    }

    private void tryToAddProductToList() {

        String msg = validteNewProduct();

        if (msg == null) {
            addNewProduct();
        } else {
            SC.say(msg);
        }
    }

    private void addNewProduct() {
        Product product = new Product();
        product.setImei(getNewProductImei());
        product.setColor(getNewProductColor());
        product.setProducer(getNewProductProducer());
        product.setModel(getNewProductModel());
        String priceInStr = getNewProductPriceIn();

        Log.debug("priceInStr before: " + priceInStr);

        priceInStr = priceInStr.replace(".", "");
        priceInStr = priceInStr.replace(",", "");

        Money m = null;
        try {
            Log.debug("priceInStr after: " + priceInStr);
            Long priceIn = Long.parseLong(priceInStr);
            m = new Money(priceIn);
        } catch (Exception e) {
            Log.debug("Error", e);
        }

        product.setPriceIn(m);

        this.listOfNewImeis.add(getNewProductImei());
        this.listOfNewProducts.add(product);
        this.imeibox.setValue("");
        this.imeibox.focusInItem();

        this.refreshProductsGrid();
        this.refreshProductsGridInfo();
    }

    private void refreshProductsGridInfo() {
        this.numberOfElementsLabel.setContents("Ilość produktów : " + this.listOfNewProducts.size());
    }

    private String validteNewProduct() {

        String imei, color, producer, model, priceIn;
        imei = getNewProductImei();
        color = getNewProductColor();
        producer = getNewProductProducer();
        model = getNewProductModel();
        priceIn = getNewProductPriceIn();

        RegExp regExp1 = RegExp.compile("^[\\w]{5,}$");
        boolean imeiformat = regExp1.test(imei);

        RegExp regExp2 = RegExp.compile("^[\\d]{1,}\\.[\\d]{2}$");
        boolean priceformat1 = regExp2.test(priceIn);

        RegExp regExp3 = RegExp.compile("^[\\d]{1,},[\\d]{2}$");
        boolean priceformat2 = regExp3.test(priceIn);

        if (imeiformat == false)
            return "Wymagany format IMEI to conajmniej 5 znaków (cyfr, liter, myślników)";
        else if (listOfImeis.contains(imei))
            return "Wprowadzony IMEI jest już w bazie";
        else if (listOfNewImeis.contains(imei))
            return "Wprowadzony IMEI jest już w na liście";
        else if (color == null || color.length() < 2)
            return "Kolor jest wymagany";
        else if (producer == null || producer.length() < 2)
            return "Producent jest wymagany";
        else if (model == null || model.length() < 2)
            return "Model jest wymagany";
        else if (priceformat1 == false && priceformat2 == false)
            return "Cena powinna byc w najstępującym formacie 100.10 lub 100,00";


        return null;
    }

    private String getNewProductPriceIn() {
        return this.pricebox.getValueAsString();
    }

    private String getNewProductModel() {
        return this.selectModelCombo.getValueAsString();
    }

    private String getNewProductProducer() {
        return this.selectProducerCombo.getValueAsString();
    }

    private String getNewProductColor() {
        return this.selectColorCombo.getValueAsString();
    }

    private String getNewProductImei() {
        return this.imeibox.getValueAsString();
    }

    class AddStoreComponentRecord extends ListGridRecord {

        public AddStoreComponentRecord() {
        }

        public AddStoreComponentRecord(Product product) {
            setImei(product.getImei());
            setColor(product.getColor());
            setProducer(product.getProducer());
            setModel(product.getModel());
            setPriceIn(product.getPriceIn());
            setDeleteItem("");
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
            if (money != null)
                setAttribute("price_in", money.toString());
            else
                setAttribute("price_in", "");
        }

        public void setDeleteItem(String imei) {
            setAttribute("delete_item", imei);
        }
    }

    public void loadData() {

        loadStores();
        loadColors();
        loadModels();
        loadProducers();
        loadUsers();
        loadImeisInUse();
    }

    private void loadImeisInUse() {
        this.productService.fetchAllImeiInUse(new AsyncCallback<List<String>>() {
            public void onFailure(Throwable caught) {
                SC.say("Niestety nie powiodło się pobranie wszystkich wymaganych danych");

                listOfImeisLoaded = false;
            }

            public void onSuccess(List<String> result) {
                listOfImeis = result;
                listOfImeisLoaded = true;

                dataChanged();
            }
        });
    }

    private void loadUsers() {
        this.userService.fetchAllUsers(new AsyncCallback<List<User>>() {
            public void onFailure(Throwable caught) {
                SC.say("Niestety nie powiodło się pobranie wszystkich wymaganych danych");

                listOfUsersLoaded = false;
            }

            public void onSuccess(List<User> result) {
                listOfUsers = result;
                listOfUsersLoaded = true;

                dataChanged();
            }
        });
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
            refreshData();
        }
    }

    public void presentComponent() {
        this.show();
    }

    public void foldComponent() {
        this.hide();
    }

    public void refreshData() {
        refreshColorComboValues();
        refreshSelectSalemanComboValues();
        refreshSelectStoreComboValues();
        refreshSelectModelComboValues();
        refreshSelectProducerComboValues();
    }

    public boolean isDataLoaded() {
        return (this.listOfStoresLoaded &&
                this.listOfColorsLoaded &&
                this.listOfModelsLoaded &&
                this.listOfProducersLoaded &&
                this.listOfUsersLoaded &&
                this.listOfImeisLoaded);
    }

    private void refreshProductsGrid() {

        ListGridRecord[] records = this.productsListGrid.getRecords();

        for (int i = 0; i < records.length; i++) {
            this.productsListGrid.removeData(records[i]);
        }

        for (int j = listOfNewProducts.size(); j > 0; j--) {
            Product p = listOfNewProducts.get(j - 1);
            AddStoreComponentRecord record = new AddStoreComponentRecord(p);
            this.productsListGrid.addData(record);
        }
    }

    private void refreshSelectStoreComboValues() {

        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (Store store : listOfStores) {
            valueMap.put(store.getId().toString(), store.getLabel());
        }

        this.selectStoreCombo.setValueMap(valueMap);
        this.selectStoreCombo.setDefaultToFirstOption(true);
    }

    private void refreshSelectSalemanComboValues() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (User user : listOfUsers) {
            valueMap.put(user.getId().toString(), user.getEmail());
        }

        this.selectSalemanCombo.setValueMap(valueMap);
        this.selectSalemanCombo.setDefaultToFirstOption(true);
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

    public void validate() {
    }

    public Long getSelectedStore() {
        String val = this.selectStoreCombo.getValueAsString();
        long l = Long.parseLong(val);
        return l;
    }

    public ProductStatus getSelectedProductsStatus() {

        return ProductStatus.IN_STORE;
    }
}


