package telephony.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.BlurEvent;
import com.smartgwt.client.widgets.form.fields.events.BlurHandler;
import com.smartgwt.client.widgets.grid.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import telephony.client.configuration.SIZE;
import telephony.client.service.*;
import telephony.client.ui.widget.interfaces.TelephonyComponent;
import telephony.server.core.entity.Product;
import telephony.server.core.entity.Sale;
import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;
import telephony.server.core.entity.common.Money;
import telephony.shared.ListOrder;
import telephony.shared.RPCServiceStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SalesComponent extends VLayout implements TelephonyComponent {

    private final UserRPCServiceAsync userService = GWT.create(UserRPCService.class);
    private final ProductRPCServiceAsync productService = GWT.create(ProductRPCService.class);
    private final StoreRPCServiceAsync storeService = GWT.create(StoreRPCService.class);
    private final SaleRPCServiceAsync saleService = GWT.create(SaleRPCService.class);
    private final InformationRPCServiceAsync informationService = GWT.create(InformationRPCService.class);

    private ListGrid productsListGrid;
    private SelectItem selectStoreCombo;
    private SelectItem selectProductStatusCombo;
    private IButton reloadButton;
    private SelectItem selectUserCombo;
    private SelectItem selectOrder;
    private IButton doButton;
    private SelectItem selectPage;
    private IButton filterButton;

    private List<Product> listOfEditedProducts = new ArrayList<Product>();
    private List<Product> listOfDeletedProducts = new ArrayList<Product>();
    private List<Product> listOfProducts = new ArrayList<Product>();
    private List<Store> listOfStores = new ArrayList<Store>();

    private boolean listOfStoresLoaded = false;
    private boolean listOfProductsLoaded = false;
    private boolean numberOfSalesLoaded = false;
    private boolean listOfColorsLoaded = false;
    private boolean listOfProducersLoaded = false;
    private boolean listOfModelsLoaded = false;
    private boolean listOfUsersLoaded = false;
    private boolean listOfSalesLoaded = false;

    private List<String> listOfColors = new ArrayList<String>();
    private List<String> listOfProducers = new ArrayList<String>();
    private List<String> listOfModels = new ArrayList<String>();
    private List<User> listOfUsers = new ArrayList<User>();
    private List<Sale> listOfSales = new ArrayList<Sale>();

    private Long numberOfSales;
    private TextItem imeibox;


    private List<Product> getListOfEditedProducts() {
        return listOfEditedProducts;
    }

    private List<Product> getListOfDeletedProducts() {
        return listOfDeletedProducts;
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

    private void updateProducts() {

        Log.debug("nb of edited products : " + getListOfEditedProducts().size());
        Log.debug("nb of deleted products : " + getListOfDeletedProducts().size());

        this.productService.updateProducts(getListOfEditedProducts(), getEmptyList(), getListOfDeletedProducts(), getChangedUser(), new AsyncCallback<RPCServiceStatus>() {
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
        this.listOfEditedProducts = getEmptyList();
        this.listOfDeletedProducts = getEmptyList();
    }

    private List<Product> getEmptyList() {
        return new ArrayList<Product>();
    }


    public SalesComponent() {
        super();

        Log.debug("Initializing SalesComponent widget..");

        this.setMembersMargin(10);
        this.setHeight(SIZE.CONTEXT_BOX_HEIGHT);
        this.setAlign(Alignment.CENTER);
        this.setAlign(VerticalAlignment.TOP);

        this.productsListGrid = new ListGrid();
        this.productsListGrid.setWidth(1010);
        this.productsListGrid.setHeight(400);
        this.productsListGrid.setShowAllRecords(true);

        ListGridField field0 = new ListGridField("label", "Tytuł sprzedaży", 200);
        ListGridField field1 = new ListGridField("imei", "IMEI", 150);
        ListGridField field2 = new ListGridField("producer", "Producent", 120);
        ListGridField field3 = new ListGridField("model", "Model", 120);
        ListGridField field4 = new ListGridField("color", "Kolor", 100);
        ListGridField field5 = new ListGridField("price_in", "Cena zakupu", 90);
        ListGridField field6 = new ListGridField("price_out", "Cena sprzedaży", 100);
        ListGridField field7 = new ListGridField("cancel_sale", "Wycofaj sprzedaż", 100);

        field0.setCanEdit(false);
        field1.setCanEdit(false);
        field2.setCanEdit(false);
        field3.setCanEdit(false);
        field4.setCanEdit(false);
        field5.setCanEdit(false);

        this.productsListGrid.setFields(new ListGridField[]{field0, field1, field2, field3, field4, field5, field6, field7});

        this.productsListGrid.setShowAllRecords(true);
        this.productsListGrid.setCanEdit(true);
        this.productsListGrid.setGroupStartOpen(GroupStartOpen.FIRST);
        this.productsListGrid.setGroupByField("label");

        this.selectStoreCombo = new SelectItem();
        this.selectStoreCombo.setTitle("Magazyn");

        this.reloadButton = new IButton("Odśwież listę");
        this.reloadButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                clearBuffor();
                fillWithData();
            }
        });

        this.selectPage = new SelectItem();
        this.selectPage.setTitle("Zmień stronę");

        DynamicForm form9 = new DynamicForm();
        form9.setFields(selectPage);
        form9.setTitleWidth(120);

        DynamicForm form = new DynamicForm();
        form.setFields(selectStoreCombo);
        form.setHeight(10);
        form.setMargin(0);

        HLayout formLay = new HLayout();
        formLay.setWidth100();
        formLay.setHeight(10);

        this.selectOrder = new SelectItem();
        this.selectOrder.setTitle("Sortuj wg");

        DynamicForm form8 = new DynamicForm();
        form8.setFields(selectOrder);

        formLay.setMembersMargin(10);
        formLay.addMember(form);
        formLay.addMember(form9);
        formLay.addMember(form8);
        formLay.addMember(reloadButton);

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

        this.imeibox = new TextItem();
        this.imeibox.setTitle("IMEI");

        DynamicForm form0 = new DynamicForm();
        form0.setFields(this.imeibox);

        this.filterButton = new IButton();
        this.filterButton.setTitle("Filtruj");

        HLayout form3Lay = new HLayout();
        form3Lay.setWidth100();
        form3Lay.setHeight(10);
        form3Lay.addMember(form0);
        form3Lay.addMember(filterButton);

        this.addMember(form3Lay);
        this.addMember(form2Lay);
        this.addMember(productsListGrid);
        this.addMember(formLay);

        this.loadData();

        Log.debug("ContentBox SalesComponent was initialized..");
    }

    public ListGrid getProductsListGrid() {
        return this.productsListGrid;
    }

    private void setupEditorCustomizer() {
        this.productsListGrid.setEditorCustomizer(new ListGridEditorCustomizer() {

            private SaleComponentRecord record;
            private Product editingProduct;
            private Money oldPriceOut;

            public FormItem getEditor(ListGridEditorContext context) {
                ListGridField field = context.getEditField();

                record = (SaleComponentRecord) context.getEditedRecord();

                String imei = record.getAttribute("imei");

                editingProduct = findProductByImei(imei);
                oldPriceOut = editingProduct.getPriceOut();

                if (field.getName().equals("cancel_sale")) {

                    ButtonItem button = new ButtonItem();
                    button.setTitle("anuluj");

                    button.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
                        public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                            getProductsListGrid().removeData(record);
                            tryAddProductToDeletingList(editingProduct);
                            getProductsListGrid().refreshFields();
                        }
                    });

                    return button;
                }

                if (field.getName().equals("price_out")) {

                    TextItem priceOut = new TextItem();

                    priceOut.addBlurHandler(new BlurHandler() {
                        public void onBlur(BlurEvent event) {
                            String priceOutStr = record.getAttribute("price_out");

                            Log.debug("priceOutStr before: " + priceOutStr);

                            priceOutStr = priceOutStr.replace(".", "");
                            priceOutStr = priceOutStr.replace(",", "");

                            Money m = null;
                            try {
                                Log.debug("priceOutStr after: " + priceOutStr);
                                Long priceOut = Long.parseLong(priceOutStr);
                                m = new Money(priceOut);
                                editingProduct.setPriceOut(m);

                                tryAddProductToEditingList(editingProduct);
                            } catch (Exception e) {
                                Log.debug("Error", e);
                                SC.say("Prawidlowy format ceny to X.YZ ");

                                record.setPriceOut(oldPriceOut.toString());
                            }
                        }
                    });


                    return priceOut;
                }

                return context.getDefaultProperties();
            }
        });
    }

    private Product findProductByImei(String imei) {

        for (Product product : this.listOfProducts) {
            if (product.getImei().equals(imei)) {
                return product;
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

    class SaleComponentRecord extends ListGridRecord {

        public SaleComponentRecord() {
        }

        public SaleComponentRecord(String label, Product product) {

            setLabel(label);
            setModel(product.getModel());
            setProducer(product.getProducer());
            setColor(product.getColor());
            setImei(product.getImei());
            setPriceIn(product.getPriceIn().toString());
            setPriceOut(product.getPriceOut().toString());
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

        public void setPriceOut(String priceOut) {
            setAttribute("price_out", priceOut);
        }
    }

    private void loadNumberOfSales() {

        this.informationService.getNumberOfSales(getSelectedStore(), new AsyncCallback<Long>() {
            public void onFailure(Throwable caught) {
                numberOfSalesLoaded = false;
            }

            public void onSuccess(Long result) {
                numberOfSalesLoaded = true;
                numberOfSales = result;

                Log.debug("informationservice.getNumberOfSales : " + result);

                refreshPager();

                dataChanged();
            }
        });
    }

    private void refreshPager() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        Long numberOfPages = this.numberOfSales / 10L;
        Long rest = this.numberOfSales % 10L;

        if (rest > 0)
            numberOfPages++;

        if (numberOfPages == 0L) numberOfPages = 1L;

        for (int i = 1; i <= numberOfPages; i++)
            valueMap.put(Integer.toString(i - 1), Integer.toString(i));

        selectPage.setValueMap(valueMap);
        selectPage.setDefaultToFirstOption(true);
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

    public void loadData() {

        loadStores();
        loadColors();
        loadModels();
        loadProducers();
        loadUsers();

    }

    private void loadStores() {

        this.storeService.fetchAllStores(new AsyncCallback<List<Store>>() {

            public void onFailure(Throwable caught) {
                SC.say("Niestety pobranie sklepów nie powiodło się, jeżeli problem będzie się powtarzał skontaktuj się z administratorem");

                listOfStoresLoaded = false;
            }

            public void onSuccess(List<Store> stores) {
                listOfStores = stores;
                listOfStoresLoaded = true;

                refreshStoresCombo();

                loadNumberOfSales();

                dataChanged();
            }
        });
    }

    public void fillWithData() {

        this.saleService.fetchSalesFrom(getSelectedStore(), getSelectedPage(), getSelectedOrder(), new AsyncCallback<List<Product>>() {

            public void onFailure(Throwable caught) {

                listOfProductsLoaded = false;
            }

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
//        else if (value.equals(ListOrder.BY_TITLE_DESC.toString()))
        else
            return ListOrder.BY_TITLE_DESC;
    }

    private Store getSelectedStore() {
        Long l = getSelectedStoreId();

        for (Store s : listOfStores) {
            if (s.getId().equals(l)) {
                return s;
            }
        }

        return null;
    }

    public void dataChanged() {
        if (isDataLoaded()) {
            refreshUserComboValues();
            refreshOrderCombo();
            setupEditorCustomizer();
        }
    }

    public void presentComponent() {
        this.show();
    }

    public void foldComponent() {
        this.hide();
    }

    public boolean isDataLoaded() {
        return (this.listOfStoresLoaded && this.listOfUsersLoaded && this.numberOfSalesLoaded);
    }

    private void refreshProductsGrid() {

        ListGridRecord[] records = this.productsListGrid.getRecords();

        for (int i = 0; i < records.length; i++) {
            this.productsListGrid.removeData(records[i]);
        }

        Log.debug("SalesComponentRecord - number of sales : " + this.listOfProducers.size());

        for (Product p : this.listOfProducts) {
            Sale s = p.getSale();

            String date = DateTimeFormat.getFormat("dd-MM-yyyy").format(s.getDateOut());
            String label = s.getLabel() + " ( data sprzedaży : " + date + ", id " + s.getId() + " ) ";

            SaleComponentRecord record = new SaleComponentRecord(label, p);
            this.productsListGrid.addData(record);

        }
    }

    public void refreshData() {
    }

    private void refreshStoresCombo() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (Store store : listOfStores) {
            valueMap.put(store.getId().toString(), store.getLabel());
        }

        this.selectStoreCombo.setValueMap(valueMap);
        this.selectStoreCombo.setDefaultValue(this.listOfStores.get(0).getId().toString());
    }

    public void validate() {
    }

    public Long getSelectedStoreId() {
        String val = this.selectStoreCombo.getValueAsString();
        long l = Long.parseLong(val);
        return l;
    }

    public int getSelectedPage() {
        String val = this.selectPage.getValueAsString();

        return Integer.parseInt(val);
    }


}

