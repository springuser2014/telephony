package telephony.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.DateDisplayFormatter;
import com.smartgwt.client.util.DateUtil;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
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
import telephony.server.core.entity.common.ProductStatus;
import telephony.shared.RPCServiceStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

public class SaleProductsComponent extends VLayout implements TelephonyComponent {

    private final ProductRPCServiceAsync productService = GWT.create(ProductRPCService.class);
    private final StoreRPCServiceAsync storeService = GWT.create(StoreRPCService.class);
    private final UserRPCServiceAsync userService = GWT.create(UserRPCService.class);
    private final SaleRPCServiceAsync saleService = GWT.create(SaleRPCService.class);

    private ListGrid productsListGrid;
    private SelectItem selectStoreCombo;
    private SelectItem selectProductStatusCombo;
    private IButton reloadButton;

    /* ladowanie danych */
    private boolean listOfStoresLoaded = false;
    //    private boolean listOfProductsLoaded = false;
    private boolean listOfUsersLoaded = false;

    private List<Product> listOfSelectedProducts = new ArrayList<Product>();
    //    private List<Product> listOfProducts = new ArrayList<Product>();
    private List<Store> listOfStores = new ArrayList<Store>();
    private List<User> listOfUsers = new ArrayList<User>();


    private TextItem imeibox;
    private TextItem saleTitle;
    private IButton doButton;
    private IButton selectUnselectButton;
    private SelectItem selectUserCombo;
    private DateItem selectSaleDate;
    private Label numberOfElementsLabel;

    public SaleProductsComponent() {
        super();

        Log.debug("Initializing EditStoreComponent widget..");

        // initialise the layout container
        this.setHeight(SIZE.CONTEXT_BOX_HEIGHT);
        this.setAlign(VerticalAlignment.TOP);
        this.setMembersMargin(10);

        this.productsListGrid = new ListGrid();
        productsListGrid.setWidth(1010);
        productsListGrid.setHeight(400);
        productsListGrid.setShowAllRecords(true);

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

            private SaleProductsComponentRecord record;

            public FormItem getEditor(ListGridEditorContext context) {
                ListGridField field = context.getEditField();

                if (field.getName().equals("delete_item")) {
                    record = (SaleProductsComponentRecord) context.getEditedRecord();

                    ButtonItem button = new ButtonItem();
                    button.setTitle("usuń produkt");

                    button.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
                        public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                            deleteFromSelectedProductsListWhereImei(record.getImei());
                            getProductsListGrid().removeData(record);
                            refreshProductsGrid();
                            refreshProductsGridInfo();
                        }


                    });

                    return button;
                }

                return context.getDefaultProperties();
            }
        });

        productsListGrid.setCanEdit(true);
        productsListGrid.setEditEvent(ListGridEditEvent.CLICK);

        this.productsListGrid.setFields(new ListGridField[]{field1, field2, field3, field4, field5, field6});

        this.selectStoreCombo = new SelectItem();
        selectStoreCombo.setTitle("Magazyn");

        this.selectStoreCombo.addChangedHandler(new ChangedHandler() {
            public void onChanged(ChangedEvent event) {
                clearData();
            }
        });

        this.selectProductStatusCombo = new SelectItem();
        selectProductStatusCombo.setTitle("Status produktu");

        // formularz wyboru magazynu
        DynamicForm form = new DynamicForm();
        form.setFields(selectStoreCombo);
        form.setWidth(400);
        form.setTitleWidth(200);

        HLayout formLay = new HLayout();
        formLay.setWidth100();
        formLay.setHeight(5);

        formLay.setMembersMargin(10);

        formLay.addMember(form);


        this.addMember(formLay);

        // formularz wyboru produktu po IMEI
        DynamicForm form2 = new DynamicForm();
        imeibox = new TextItem();
        imeibox.setTitle("Zaznacz/odznacz produkt (wg imei)");

        imeibox.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                if (event.getKeyName().equals("Enter")) {
                    tryToAddProductToList();
                }
            }
        });
        form2.setTitleWidth(200);

        form2.setFields(imeibox);
        form2.setWidth(400);

        HLayout formLay2 = new HLayout();
        formLay2.setWidth100();
        formLay2.setHeight(5);
        formLay2.setMembersMargin(10);
        formLay2.addMember(form2);

        selectUnselectButton = new IButton("Zaznacz/odznacz");
        formLay2.addMember(selectUnselectButton);

        selectUnselectButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                tryToAddProductToList();
            }
        });

        this.addMember(formLay2);

        // formularz przenoszenia produktów do innego magazynu
        HLayout formLay3 = new HLayout();
        formLay3.setWidth100();
        formLay3.setHeight(5);
        formLay3.setMembersMargin(10);

        DynamicForm form3 = new DynamicForm();
        form3.setTitleWidth(200);
        form3.setWidth(400);
        saleTitle = new TextItem();
        saleTitle.setTitle("Tytuł sprzedaży");
        form3.setFields(saleTitle);

        DynamicForm form4 = new DynamicForm();
        selectUserCombo = new SelectItem();
        selectUserCombo.setTitle("sprzedający");
        form4.setFields(selectUserCombo);

        DynamicForm form5 = new DynamicForm();
        form5.setTitleWidth(120);
        form5.setWidth(300);
        selectSaleDate = new DateItem();
        selectSaleDate.setTitle("dzień transakcji");

        DateUtil.setShortDateDisplayFormatter(new DateDisplayFormatter() {
            public String format(Date date) {
                if (date == null) return null;

                final DateTimeFormat dateFormatter = DateTimeFormat.getFormat("dd-MM-yyyy");
                String format = dateFormatter.format(date);
                return format;
            }

        });
        selectSaleDate.setWidth(180);
        form5.setFields(selectSaleDate);

        formLay3.addMember(form3);
        formLay3.addMember(form4);
        formLay3.addMember(form5);

        doButton = new IButton("Wykonaj");

        doButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                tryToAddNewSale();
            }
        });

        formLay3.addMember(doButton);
        this.addMember(formLay3);

        this.addMember(productsListGrid);

        this.numberOfElementsLabel = new Label();
        this.numberOfElementsLabel.setContents("Ilość produktów : 0");

        this.addMember(numberOfElementsLabel);

        this.loadData();

        Log.debug("EditStoreComponent was initialized..");
    }

    private void deleteFromSelectedProductsListWhereImei(String imei) {
        for (Product p : this.listOfSelectedProducts) {
            if (p.getImei().equals(imei)) {
                this.listOfSelectedProducts.remove(p);
            }
        }
    }

    public ListGrid getProductsListGrid() {
        return this.productsListGrid;
    }

    private void tryToAddNewSale() {
        String msg = validateForm();

        if (msg == null) {
            AddNewSale();
        } else {
            SC.say(msg);
        }
    }

    private String validateForm() {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    private Date getSelectedSaleDate() {
        return this.selectSaleDate.getValueAsDate();
    }

    private User getSelectedUser() {
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

    private void AddNewSale() {

        this.saleService.addNewSale(getNewSale(), getSelectedProdcuts(), getSelectedUser(), getSelectedStore(), new AsyncCallback<RPCServiceStatus>() {

            public void onFailure(Throwable caught) {
                SC.say("Niestety wystąpił błąd podczas zapisu, jeżeli problem będzie się powtarzał skontaktuj się z administratorem");
//                listOfProductsLoaded = false;
            }

            public void onSuccess(RPCServiceStatus result) {

                SC.say(result.getOperationStatusInfo());

                if (result.getStatus().equals(RPCServiceStatus.Status.SUCCESS)) {
                    clearData();
                    refreshProductsGrid();
                    refreshProductsGridInfo();
                }
            }
        });
    }

    private Sale getNewSale() {
        Sale s = new Sale();
        s.setDateOut(getSelectedSaleDate());
        s.setLabel(saleTitle.getValueAsString());

        return s;
    }

    private void clearData() {
        this.listOfSelectedProducts = new ArrayList<Product>();
        this.saleTitle.setValue("");

        refreshProductsGrid();
        refreshProductsGridInfo();
    }

    private void tryToAddProductToList() {

        String imei = this.imeibox.getValueAsString();

        RegExp regExp1 = RegExp.compile("^[\\w]{5,}$");
        boolean imeiformat = regExp1.test(imei);

        for (Product p : this.listOfSelectedProducts) {
            if (p.getImei().equals(imei)) {
                SC.say("Produkt znajduje już się na liście");

                return;
            }
        }

        if (imeiformat) {

            fetchProductByImeiAndStoreId(imei, getSelectedStoreId());

        } else {
            SC.say("Wymagana długośc IMEI to conajmniej 5 znaków (cyfr, liter, myślników)");
        }
    }


    private void fetchProductByImeiAndStoreId(String imei, Long selectedStoreId) {

        this.productService.fetchProductByImeiAndStoreId(imei, selectedStoreId, new AsyncCallback<Product>() {
            public void onFailure(Throwable caught) {
                SC.say("Niestety, pobranie danych nie powiodło się, jeżeli bład będzie się powtarzał skontaktuj się z administratorem");

                clearForm();
            }

            public void onSuccess(Product result) {
                if (result == null) {
                    SC.say("Nie ma produktu o takim IMEI w wybranym magazynie");
                } else {
                    listOfSelectedProducts.add(result);

                    clearForm();

                    refreshProductsGrid();
                    refreshProductsGridInfo();
                }

            }
        });
    }

    private void refreshProductsGridInfo() {
            this.numberOfElementsLabel.setContents("Ilość produktów : " + this.listOfSelectedProducts.size());
        }

    private void clearForm() {
        this.imeibox.setValue("");
        this.imeibox.focusInItem();
    }


    private long getSelectedStoreId() {

        String val = this.selectStoreCombo.getValueAsString();
        long l = Long.parseLong(val);
        return l;

    }

    private ListGridRecord findRecordWithImei(String imei) {
        ListGridRecord[] records = this.productsListGrid.getRecords();

        for (int i = 0; i < records.length; i++) {
            SaleProductsComponentRecord record = (SaleProductsComponentRecord) records[i];

            if (record.getImei().equals(imei)) {
                return record;
            }

        }

        return null;
    }

//    private void selectProductWithImei(String imei) {
//
//        for (Product p : this.listOfProducts) {
//            if (p.getImei().equals(imei)) {
//                listOfSelectedProducts.add(p);
//            }
//        }
//    }
//
//    private void unselectProductWithImei(String imei) {
//        for (Product p : this.listOfProducts) {
//            if (p.getImei().equals(imei)) {
//                listOfSelectedProducts.remove(p);
//            }
//        }
//    }

    class SaleProductsComponentRecord extends ListGridRecord {

        public SaleProductsComponentRecord() {
        }

        public SaleProductsComponentRecord(Product product) {
            setImei(product.getImei());
            setColor(product.getColor());
            setProducer(product.getProducer());
            setModel(product.getModel());
            setPriceIn(product.getPriceIn());
            setDeleteItem(" ");
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

        public void setDeleteItem(String deleteItem) {
            setAttribute("delete_item", deleteItem);
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

        this.userService.fetchAllUsers(new AsyncCallback<List<User>>() {

            public void onFailure(Throwable caught) {
                SC.say("Niestety pobranie wszystkich wymaganych danych nie powiodło się");

                listOfUsersLoaded = false;
            }

            public void onSuccess(List<User> result) {
                listOfUsersLoaded = true;
                listOfUsers = result;

                dataChanged();
            }
        });

    }

    public void fillWithData() {

        this.productService.fetchAllProducts(getSelectedStore().getId(), ProductStatus.IN_STORE, new AsyncCallback<List<Product>>() {
            public void onFailure(Throwable caught) {
//                listOfProductsLoaded = false;
            }

            public void onSuccess(List<Product> result) {
//                listOfProductsLoaded = true;
//                listOfProducts = result;

                refreshProductsGrid();
                refreshProductsGridInfo();
            }
        });

    }

    private List<Product> getSelectedProdcuts() {
//        ListGridRecord[] records = this.productsListGrid.getSelectedRecords();
//
//        List<Product> result = new ArrayList<Product>();
//
//        for (int i = 0; i < records.length; i++) {
//
//            SaleProductsComponentRecord record = (SaleProductsComponentRecord) records[i];
//
//            for (Product p : this.listOfProducts) {
//                if (p.getImei().equals(record.getImei())) {
//                    result.add(p);
//                }
//            }
//        }

        return this.listOfSelectedProducts;

//        return result;
    }

    public void dataChanged() {
        if (isDataLoaded()) {
            refreshStoresCombo();
            refreshSalemanComboValues();
        }
    }

    private void refreshSalemanComboValues() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (User user : listOfUsers) {
            valueMap.put(user.getId().toString(), user.getEmail());
        }

        this.selectUserCombo.setValueMap(valueMap);
        this.selectUserCombo.setDefaultToFirstOption(true);
    }

    public void presentComponent() {
        this.show();
    }

    public void foldComponent() {
        this.hide();
    }

    public boolean isDataLoaded() {
        return (this.listOfStoresLoaded && this.listOfUsersLoaded);
    }

    private void refreshProductsGrid() {

        ListGridRecord[] records = this.productsListGrid.getRecords();

        for (int i = 0; i < records.length; i++) {
            this.productsListGrid.removeData(records[i]);
        }

        for (int j = listOfSelectedProducts.size(); j > 0; j--) {
            Product p = listOfSelectedProducts.get(j - 1);
            SaleProductsComponentRecord record = new SaleProductsComponentRecord(p);
            this.productsListGrid.addData(record);
        }
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

    public void validate() {
    }

    public Store getSelectedStore() {
        String val = this.selectStoreCombo.getValueAsString();
        long l = Long.parseLong(val);

        for (Store s : this.listOfStores) {
            if (s.getId().equals(l)) {
                return s;
            }
        }

        return null;
    }
}

