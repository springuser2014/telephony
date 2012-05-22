package war.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.fields.events.*;
import com.smartgwt.client.widgets.grid.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import war.client.configuration.SIZE;
import war.client.service.*;
import war.client.ui.widget.interfaces.TelephonyComponent;
import war.server.core.entity.Delivery;
import war.server.core.entity.Product;
import war.server.core.entity.Store;
import war.server.core.entity.User;
import war.server.core.entity.common.Money;
import war.shared.ListOrder;
import war.shared.RPCServiceStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DeliveriesComponent extends VLayout implements TelephonyComponent {

    private final ProductRPCServiceAsync productService = GWT.create(ProductRPCService.class);
    private final StoreRPCServiceAsync storeService = GWT.create(StoreRPCService.class);
    private final DeliveryRPCServiceAsync deliveryService = GWT.create(DeliveryRPCService.class);
    private final UserRPCServiceAsync userService = GWT.create(UserRPCService.class);
    private final InformationRPCServiceAsync informationService = GWT.create(InformationRPCService.class);

    private ListGrid productsListGrid;
    private SelectItem selectStoreCombo;
    private SelectItem selectProductStatusCombo;
    private IButton reloadButton;
    private SelectItem selectUserCombo;
    private IButton doButton;
    private SelectItem selectPage;
    private SelectItem selectOrder  ;
    private TextItem imeibox    ;
    private IButton filterButton;

    private boolean listOfColorsLoaded = false;
    private boolean listOfProducersLoaded = false;
    private boolean listOfModelsLoaded = false;
    private boolean listOfUsersLoaded = false;
    private boolean numberOfDeliveriesLoaded = false;
    private boolean listOfStoresLoaded = false;
    private boolean listOfProductsLoaded = false;

    private Long numberOfDeliveries;

    private List<String> listOfColors = new ArrayList<String>();
    private List<String> listOfProducers = new ArrayList<String>();
    private List<String> listOfModels = new ArrayList<String>();
    private List<User> listOfUsers = new ArrayList<User>();
    private List<Store> listOfStores = new ArrayList<Store>();
    private List<Product> listOfProducts = new ArrayList<Product>();
    private List<Product> listOfEditedProducts = new ArrayList<Product>();
    private List<Product> listOfDeletedProducts = new ArrayList<Product>();



    private List<Product> getListOfEditedProducts() {
        return listOfEditedProducts;
    }

    private List<Product> getListOfDeletedProducts() {
        return listOfDeletedProducts;
    }

    public DeliveriesComponent() {
        super();

        Log.debug("Initializing DeliveriesComponent widget");

        this.setMembersMargin(10);
        this.setAlign(VerticalAlignment.TOP);
        this.setHeight(SIZE.CONTEXT_BOX_HEIGHT);

        this.productsListGrid = new ListGrid();
        this.productsListGrid.setWidth(1010);
        this.productsListGrid.setHeight(400);
        this.productsListGrid.setShowAllRecords(true);

        ListGridField field0 = new ListGridField("label", "Tytuł dostawy", 250);
        ListGridField field1 = new ListGridField("imei", "IMEI", 150);
        ListGridField field2 = new ListGridField("producer", "Producent", 120);
        ListGridField field3 = new ListGridField("model", "Model", 150);
        ListGridField field4 = new ListGridField("color", "Kolor", 100);
        ListGridField field5 = new ListGridField("price_in", "Cena zakupu", 100);
        ListGridField field6 = new ListGridField("delete", "Usuń", 80);

        field0.setCanEdit(false);
        field1.setCanEdit(false);

        this.productsListGrid.setFields(new ListGridField[]{field0, field1, field2, field3, field4, field5, field6});
        this.productsListGrid.setShowAllRecords(true);
        this.productsListGrid.setCanEdit(true);
        this.productsListGrid.setGroupStartOpen(GroupStartOpen.FIRST);
        this.productsListGrid.setGroupByField("label");

        this.selectStoreCombo = new SelectItem();
        this.selectStoreCombo.setTitle("Magazyn");

        this.selectProductStatusCombo = new SelectItem();
        this.selectProductStatusCombo.setTitle("Status produktu");

        this.reloadButton = new IButton("Odśwież listę");
        this.reloadButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                clearBuffor();
                fillWithData();
            }
        });


        DynamicForm form4 = new DynamicForm();
        this.selectUserCombo = new SelectItem();
        this.selectUserCombo.setTitle("Edytujący");
        form4.setFields(selectUserCombo);

        this.doButton = new IButton("Zapisz zmiany");


        this.doButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Log.debug("doButton 1" + listOfEditedProducts.size());
                Log.debug("doButton 2" + listOfDeletedProducts.size());
                
                updateProducts();
            }
        });

        HLayout form2Lay = new HLayout();
        form2Lay.setWidth100();
        form2Lay.setHeight(10);
        form2Lay.setMembersMargin(10);

        form2Lay.addMember(form4);
        form2Lay.addMember(doButton);

        this.selectPage = new SelectItem();
        this.selectPage.setTitle("Zmień stronę");

        DynamicForm form9 = new DynamicForm();
        form9.setFields(selectPage);
        form9.setTitleWidth(120);
        
        this.selectOrder = new SelectItem();
        this.selectOrder.setTitle("Sortuj wg");
        
        DynamicForm form8 = new DynamicForm();
        form8.setFields(selectOrder);

        DynamicForm form = new DynamicForm();
        form.setFields(selectStoreCombo);

        HLayout formLay = new HLayout();
        formLay.setWidth100();
        formLay.setHeight(10);

        formLay.setMembersMargin(10);

        formLay.addMember(form);
        formLay.addMember(form9);
        formLay.addMember(form8);
        formLay.addMember(reloadButton);

        this.imeibox = new TextItem();
        this.imeibox.setTitle("IMEI");
        
        DynamicForm form0 = new DynamicForm();
        form0.setFields(this.imeibox);

        this.filterButton = new IButton();
        this.filterButton.setTitle("Filtruj");

        HLayout form3Lay = new HLayout();
        form3Lay.setWidth100();
        form3Lay.addMember(form0);
        form3Lay.setHeight(10);
        form3Lay.addMember(filterButton);

        this.addMember(form3Lay);
        this.addMember(form2Lay);
        this.addMember(productsListGrid);
        this.addMember(formLay);
        
        this.loadData();

        Log.debug("DeliveriesComponent widget was initialized..");
    }

    private void updateProducts() {

        Log.debug("DeliveriesComponent - updateProducts");
        
        Log.debug("Number of edited products : " + getListOfEditedProducts().size());
        Log.debug("Number of deleted products : " + getListOfDeletedProducts().size());

        this.productService.updateProducts(getListOfEditedProducts(), getListOfDeletedProducts(), getEmptyList(), getChangedUser(), new AsyncCallback<RPCServiceStatus>() {
            public void onFailure(Throwable caught) {
                SC.say("Niestety wystąpił błąd podczas wykonywania operacji");

                clearBuffor();
            }

            public void onSuccess(RPCServiceStatus result) {
                
                SC.say(result.getOperationStatusInfo());

                if (result.getStatus().equals(RPCServiceStatus.Status.SUCCESS)) {
                    clearBuffor();
                    fillWithData();
                }
            }
        });
    }

    private void clearBuffor() {
        this.listOfEditedProducts  = getEmptyList();
        this.listOfDeletedProducts = getEmptyList();
    }

    private List<Product> getEmptyList() {
        return new ArrayList<Product>();
    }

    public ListGrid getProductsListGrid() {
        return this.productsListGrid;
    }

    private void setupEditorCustomizers() {
        this.productsListGrid.setEditorCustomizer(new ListGridEditorCustomizer() {

            private DeliveriesComponentRecord record;
            private Product editingProduct;
            private Money oldPriceIn;

            public FormItem getEditor(ListGridEditorContext context) {
                ListGridField field = context.getEditField();

                record = (DeliveriesComponentRecord) context.getEditedRecord();

                String imei = record.getAttribute("imei");


                editingProduct = findProductByImei(imei);
                oldPriceIn = editingProduct.getPriceIn();

                if (field.getName().equals("delete")) {

                    ButtonItem button = new ButtonItem();
                    button.setTitle("usuń");

                    button.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
                        public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                            getProductsListGrid().removeData(record);
                            tryAddProductToDeletingList(editingProduct);
                            getProductsListGrid().refreshFields();
                        }
                    });

                    return button;
                }

                if (field.getName().equals("producer")) {

                    ComboBoxItem producer = new ComboBoxItem();

                    LinkedHashMap<String, String> producers = getProducersValueMap();
                    producer.setValueMap(producers);
                    producer.setAddUnknownValues(true);

                    producer.addBlurHandler(new BlurHandler() {
                        public void onBlur(BlurEvent event) {
                            editingProduct.setProducer(record.getAttribute("producer"));
                            tryAddProductToEditingList(editingProduct);
                        }
                    });

                    return producer;
                }

                if (field.getName().equals("model")) {

                    ComboBoxItem model = new ComboBoxItem();
                    model.setAddUnknownValues(true);

                    LinkedHashMap<String, String> models = getModelsValueMap();
                    model.setValueMap(models);
                    model.addBlurHandler(new BlurHandler() {
                        public void onBlur(BlurEvent event) {
                            editingProduct.setModel(record.getAttribute("model"));
                            tryAddProductToEditingList(editingProduct);
                        }
                    });

                    return model;
                }

                if (field.getName().equals("color")) {

                    ComboBoxItem color = new ComboBoxItem();

                    LinkedHashMap<String, String> colors = getColorsValueMap();
                    color.setValueMap(colors);
                    color.setAddUnknownValues(true);
                    
                    color.addBlurHandler(new BlurHandler() {
                        public void onBlur(BlurEvent event) {
                            editingProduct.setColor(record.getAttribute("color"));
                            tryAddProductToEditingList(editingProduct);
                        }
                    });

                    return color;
                }
                if (field.getName().equals("price_in")) {

                    TextItem priceIn = new TextItem();

                    priceIn.addBlurHandler(new BlurHandler() {
                        public void onBlur(BlurEvent event) {
                            String priceInStr = record.getAttribute("price_in");

                            Log.debug("priceInStr before: " + priceInStr);

                            priceInStr = priceInStr.replace(".", "");
                            priceInStr = priceInStr.replace(",", "");

                            Money m = null;
                            try {
                                Log.debug("priceInStr after: " + priceInStr);
                                Long priceIn = Long.parseLong(priceInStr);
                                m = new Money(priceIn);
                                editingProduct.setPriceIn(m);

                                tryAddProductToEditingList(editingProduct);
                            } catch (Exception e) {
                                Log.debug("Error", e);
                                SC.say("Prawidlowy format ceny to X.YZ ");

                                record.setPriceIn(oldPriceIn.toString());
                            }
                        }
                    });

                    return priceIn;
                }

                return context.getDefaultProperties();
            }
        });
    }

    private void tryAddProductToEditingList(Product editingProduct) {

        boolean added = false;
        for (Product p : getListOfEditedProducts()) {
            if (p.getId().equals(editingProduct.getId())) {
                added = true;
            }
        }

        if (added == false) {
            getListOfEditedProducts().add(editingProduct);
        }
    }

    private void tryAddProductToDeletingList(Product deleteingProduct) {

        boolean added = false;
        for (Product p : getListOfDeletedProducts()) {
            if (p.getId().equals(deleteingProduct.getId())) {
                added = true;
            }
        }

        if (added == false) {
            getListOfDeletedProducts().add(deleteingProduct);
        }
    }

    private Product findProductByImei(String imei) {

        for (Product product : this.listOfProducts) {
            if (product.getImei().equals(imei)) {
                return product;
            }
        }

        return null;
    }

    private LinkedHashMap<String, String> getColorsValueMap() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        valueMap.put("", "");
        for (String color : listOfColors) {
            valueMap.put(color, color);
        }

        return valueMap;
    }

    private LinkedHashMap<String, String> getModelsValueMap() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        valueMap.put("", "");
        for (String color : listOfColors) {
            valueMap.put(color, color);
        }

        return valueMap;
    }

    private LinkedHashMap<String, String> getProducersValueMap() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (String producer : listOfProducers) {
            valueMap.put(producer, producer);
        }

        return valueMap;
    }


    class DeliveriesComponentRecord extends ListGridRecord {

        public DeliveriesComponentRecord() {
        }

        public DeliveriesComponentRecord(String label, Product product) {

            setLabel(label);
            setModel(product.getModel());
            setProducer(product.getProducer());
            setColor(product.getColor());
            setImei(product.getImei());
            setPriceIn(product.getPriceIn().toString());
        }

        public void setLabel(String label) {
            setAttribute("label", label);
        }

        public String getLabel() {
            return getAttributeAsString("label");
        }

        public void setImei(String imei) {
            setAttribute("imei", imei);
        }

        public void setProducer(String producer) {
            setAttribute("producer", producer);
        }

        public void setModel(String model) {
            setAttribute("model", model);
        }

        public void setColor(String color) {
            setAttribute("color", color);
        }

        public void setPriceIn(String priceIn) {
            setAttribute("price_in", priceIn);
        }

    }

    private User getChangedUser() {
        String userIdStr = this.selectUserCombo.getValueAsString();
        Long userId = null;

        try {
            userId = Long.parseLong(userIdStr);
        } catch (Exception e) {
        }

        for (User u : this.listOfUsers) {
            if (u.getId().equals(userId)) {
                return u;
            }
        }

        return null;
    }

    private void refreshUserComboValues() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (User user : listOfUsers) {
            valueMap.put(user.getId().toString(), user.getEmail());
        }

        this.selectUserCombo.setValueMap(valueMap);
        this.selectUserCombo.setDefaultToFirstOption(true);
    }


    private void loadUsers() {

        Log.debug("DeliveriesComponent - loadUsers");

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
        Log.debug("DeliveriesComponent - loadProducers");

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
        Log.debug("DeliveriesComponent - loadModels");

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
        Log.debug("DeliveriesComponent - loadColors");

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

    public void loadData() {

        loadStores();
        loadColors();
        loadProducers();
        loadModels();
        loadUsers();

    }

    private void loadStores() {
        Log.debug("DeliveriesComponent - loadStores");

        this.storeService.fetchAllStores(new AsyncCallback<List<Store>>() {

            public void onFailure(Throwable caught) {
                SC.say("Niestety pobranie listy sklepów nie powiodło się, jeżeli problem będzie się powtarzał skontaktuj się z administratorem");

                listOfStoresLoaded = false;
            }

            public void onSuccess(List<Store> stores) {
                listOfStores = stores;
                listOfStoresLoaded = true;

                refreshStoresCombo();

                loadNumberOfDeliveries();

                dataChanged();
            }
        });
    }

    private void loadNumberOfDeliveries() {
        Log.debug("DeliveriesComponent - loadNumberOfDeliveries");

        this.informationService.getNumberOfDeliveries(getSelectedStore(), new AsyncCallback<Long>() {
            public void onFailure(Throwable caught) {

                SC.say("Niestety pobranie listy sklepów nie powiodło się, jeżeli problem będzie się powtarzał skontaktuj się z administratorem");
                numberOfDeliveriesLoaded = false;
            }

            public void onSuccess(Long result) {
                numberOfDeliveriesLoaded = true;
                numberOfDeliveries = result;

                refreshPager();

                dataChanged();
            }
        });
    }

    private void refreshPager() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        long numberOfPages = this.numberOfDeliveries / 10L;
        long rest = this.numberOfDeliveries % 10L;

        if (rest > 0)
            numberOfPages++;

        if (numberOfPages == 0L) numberOfPages = 1L;

        for (int i = 1; i <= numberOfPages; i++)
            valueMap.put(Integer.toString(i - 1), Integer.toString(i));

        selectPage.setValueMap(valueMap);
        selectPage.setDefaultToFirstOption(true);
    }

    public void fillWithData() {

        this.deliveryService.fetchDeliveriesFrom(getSelectedStore(), getSelectedPage(), getSelectedOrder(), new AsyncCallback<List<Product>>() {

            public void onFailure(Throwable caught) { }

            public void onSuccess(List<Product> result) {

                listOfProductsLoaded = true;
                listOfProducts = result;

                refreshProductsGrid();
            }
        });
    }

    private ListOrder getSelectedOrder() {

        String value = this.selectOrder.getValueAsString();

        if (value.equals(ListOrder.BY_DATE_ASC.toString()))
            return ListOrder.BY_DATE_ASC;
        else if (value.equals(ListOrder.BY_DATE_DESC.toString()))
            return ListOrder.BY_DATE_DESC;
        else if (value.equals(ListOrder.BY_TITLE_ASC.toString()))
            return ListOrder.BY_TITLE_ASC;
        else
            return ListOrder.BY_TITLE_DESC;
    }

    private long getSelectedStoreId() {
        String value = this.selectStoreCombo.getValueAsString();

        long l = Long.parseLong(value);
        return l;
    }

    public void dataChanged() {
        if (isDataLoaded()) {
            refreshUserComboValues();
            refreshOrderCombo();
            setupEditorCustomizers();
        }
    }

    public void presentComponent() {
        this.show();
    }

    public void foldComponent() {
        this.hide();
    }

    public boolean isDataLoaded() {
        return (this.listOfStoresLoaded && this.listOfModelsLoaded &&
                this.listOfColorsLoaded && this.listOfProducersLoaded &&
                this.listOfUsersLoaded && this.numberOfDeliveriesLoaded);
    }

    private void refreshProductsGrid() {

        ListGridRecord[] records = this.productsListGrid.getRecords();

        for (int i = 0; i < records.length; i++) {
            this.productsListGrid.removeData(records[i]);
        }

        Log.debug("DeliveriesComponentRecord - number of deliveries : " + this.listOfProducts.size());

        for (Product p : this.listOfProducts) {
            Delivery d = p.getDelivery();

            String date = DateTimeFormat.getFormat("dd-MM-yyyy").format(d.getDateIn());
            String label = d.getLabel() + " ( data dostawy : " + date + ", id " + d.getId() + " ) ";

            DeliveriesComponentRecord record = new DeliveriesComponentRecord(label, p);
            this.productsListGrid.addData(record);
        }
    }

    public void refreshData() {
    }

    private void refreshStoresCombo() {
        Log.debug("DeliveriesComponent - refreshStoresCombo");

        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (Store store : listOfStores) {

            Log.debug("id : " + store.getId().toString() + " label : " + store.getLabel());
            valueMap.put(store.getId().toString(), store.getLabel());
        }

        this.selectStoreCombo.setValueMap(valueMap);
        this.selectStoreCombo.setDefaultValue(this.listOfStores.get(0).getId().toString());
    }

    private void refreshOrderCombo() {
        Log.debug("DeliveriesComponent - refreshOrderCombo");

        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        valueMap.put(ListOrder.BY_DATE_ASC.toString(), "daty dodania rosnąco" );
        valueMap.put(ListOrder.BY_DATE_DESC.toString(), "daty dodania malejąco" );
        valueMap.put(ListOrder.BY_TITLE_ASC.toString(), "tytułu dostawy rosnąco");
        valueMap.put(ListOrder.BY_TITLE_DESC.toString(), "tytułu dostawy malejąco");

        this.selectOrder.setValueMap(valueMap);
        this.selectOrder.setDefaultValue(ListOrder.BY_DATE_ASC.toString());
    }

    public void validate() {
    }

    public Store getSelectedStore() {
        Log.debug("DeliveriesComponent - getSelectedStore");

        long l = getSelectedStoreId();

        for (Store store : listOfStores) {
            if (store.getId().equals(l))
                return store;
        }

        return null;
    }

    public int getSelectedPage() {
        Log.debug("DeliveriesComponent - getSelectedPage");

        String val = this.selectPage.getValueAsString();

        return Integer.parseInt(val);
    }
}



