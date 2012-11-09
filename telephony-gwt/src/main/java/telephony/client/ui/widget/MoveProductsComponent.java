package telephony.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
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
import telephony.server.core.entity.Store;
import telephony.server.core.entity.User;
import telephony.server.core.entity.common.Money;
import telephony.shared.RPCServiceStatus;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MoveProductsComponent extends VLayout implements TelephonyComponent {

    private final ProductRPCServiceAsync productService = GWT.create(ProductRPCService.class);
    private final StoreRPCServiceAsync storeService = GWT.create(StoreRPCService.class);
    private final UserRPCServiceAsync userService = GWT.create(UserRPCService.class);

    private ListGrid productsListGrid;
    private SelectItem selectStoreCombo;
    private SelectItem selectProductStatusCombo;
    private IButton reloadButton;
    private TextItem imeibox;
    private SelectItem selectDestinationStoreCombo;
    private IButton doButton;
    private IButton selectUnselectButton;
    private SelectItem selectUserCombo;
    private Label numberOfElementsLabel;

    private boolean listOfStoresLoaded = false;
    private boolean listOfUsersLoaded = false;

    private List<Product> listOfSelectedProducts = new ArrayList<Product>();
    private List<Store> listOfStores = new ArrayList<Store>();
    private List<User> listOfUsers = new ArrayList<User>();

    public MoveProductsComponent() {
        super();

        Log.debug("Initializing MoveProductsComponent widget..");

        this.setHeight(SIZE.CONTEXT_BOX_HEIGHT);

        this.setMembersMargin(10);
        this.setAlign(Alignment.CENTER);
        this.setAlign(VerticalAlignment.TOP);
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

            private MoveProductsComponentRecord record;

            public FormItem getEditor(ListGridEditorContext context) {
                ListGridField field = context.getEditField();

                if (field.getName().equals("delete_item")) {
                    record = (MoveProductsComponentRecord) context.getEditedRecord();

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

        this.productsListGrid.setCanEdit(true);
        this.productsListGrid.setEditEvent(ListGridEditEvent.CLICK);

        this.productsListGrid.setFields(new ListGridField[]{field1, field2, field3, field4, field5, field6});

        this.selectStoreCombo = new SelectItem();
        this.selectStoreCombo.setTitle("Magazyn");
        this.selectStoreCombo.addChangedHandler(new ChangedHandler() {
            public void onChanged(ChangedEvent event) {
                refreshDestinationStoreComboValues();
                clearData();
            }
        });

        this.selectProductStatusCombo = new SelectItem();
        this.selectProductStatusCombo.setTitle("Status produktu");

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

        DynamicForm form2 = new DynamicForm();
        this.imeibox = new TextItem();
        this.imeibox.setTitle("Zaznacz/odznacz produkt (wg imei)");

        this.imeibox.addKeyUpHandler(new KeyUpHandler() {
            public void onKeyUp(KeyUpEvent event) {
                if (event.getKeyName().equals("Enter")) {
                    tryToAddProductToList();
                }
            }
        });

        form2.setFields(imeibox);
        form2.setTitleWidth(200);

        HLayout formLay2 = new HLayout();
        formLay2.setWidth100();
        formLay2.setHeight(5);
        formLay2.setMembersMargin(10);
        formLay2.addMember(form2);

        this.selectUnselectButton = new IButton("Zaznacz/odznacz");
        formLay2.addMember(selectUnselectButton);

        this.selectUnselectButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                tryToAddProductToList();
            }
        });

        form2.setFields(imeibox);
        form2.setWidth(400);
        form2.setTitleWidth(200);
        this.addMember(formLay2);

        HLayout formLay3 = new HLayout();
        formLay3.setWidth100();
        formLay3.setHeight(5);
        formLay3.setMembersMargin(10);


        DynamicForm form3 = new DynamicForm();
        this.selectDestinationStoreCombo = new SelectItem();
        this.selectDestinationStoreCombo.setTitle("Zaznaczone produkty przenieś do");
        form3.setTitleWidth(200);
        form3.setFields(selectDestinationStoreCombo);
        form3.setWidth(400);
        formLay3.addMember(form3);

        DynamicForm form4 = new DynamicForm();
        this.selectUserCombo = new SelectItem();
        this.selectUserCombo.setTitle("przenoszący");
        form4.setFields(selectUserCombo);

        formLay3.addMember(form4);
        this.doButton = new IButton("Wykonaj");

        this.doButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                tryToMoveProducts();
            }
        });

        formLay3.addMember(doButton);
        this.addMember(formLay3);
        this.addMember(productsListGrid);

        this.numberOfElementsLabel = new Label();
        this.numberOfElementsLabel.setContents("Ilość produktów : 0");

        this.addMember(numberOfElementsLabel);

        this.loadData();

        Log.debug("MoveProductsComponent was initialized..");
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

    private void tryToMoveProducts() {
        String msg = validateData();

        if (msg == null) {
            MoveProducts();
        } else {
            SC.say(msg);
        }
    }

    private void MoveProducts() {
        this.productService.moveProducts(getSelectedDestinationStore(), getSelectedProdcuts(), getChangedUser(), new AsyncCallback<RPCServiceStatus>() {
            public void onFailure(Throwable caught) {
                SC.say("Niestety operacja przenoszenia produktów nie powiodła się ");
            }

            public void onSuccess(RPCServiceStatus result) {

                SC.say(result.getOperationStatusInfo());

                if (result.getStatus().equals(RPCServiceStatus.Status.SUCCESS)) {
                    clearData();
                }
            }
        });
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

    private List<Product> getSelectedProdcuts() {
        return this.listOfSelectedProducts;
    }

    private void clearData() {

        this.listOfSelectedProducts = new ArrayList<Product>();


        refreshProductsGrid();
        refreshProductsGridInfo();
    }

    private void refreshProductsGridInfo() {
        this.numberOfElementsLabel.setContents("Ilość produktów : " + this.listOfSelectedProducts.size());
    }

    private String validateData() {

        if (this.listOfSelectedProducts.size() == 0) {
            return "Brak zaznaczonych produktów do przeniesienia";
        } else {
            return null;
        }
    }

    private ListGridRecord findRecordWithImei(String imei) {
        ListGridRecord[] records = this.productsListGrid.getRecords();

        for (int i = 0; i < records.length; i++) {
            MoveProductsComponentRecord record = (MoveProductsComponentRecord) records[i];

            if (record.getImei().equals(imei)) {
                return record;
            }

        }

        return null;
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

    private void clearForm() {
        this.imeibox.setValue("");
        this.imeibox.focusInItem();
    }

    class MoveProductsComponentRecord extends ListGridRecord {

        public MoveProductsComponentRecord() {
        }

        public MoveProductsComponentRecord(Product product) {
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

        public String getDeleteItem() {
            return getAttributeAsString("delete_item");
        }
    }

    public void loadData() {
        loadStores();
        loadUsers();
    }

    private void loadUsers() {
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

    private long getSelectedStoreId() {

        String val = this.selectStoreCombo.getValueAsString();
        long l = Long.parseLong(val);
        return l;

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

    public void fillWithData() {
        this.loadData();
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
            MoveProductsComponentRecord record = new MoveProductsComponentRecord(p);
            this.productsListGrid.addData(record);
        }
    }

    private void refreshUserComboValues() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (User user : listOfUsers) {
            valueMap.put(user.getId().toString(), user.getEmail());
        }

        this.selectUserCombo.setValueMap(valueMap);
        this.selectUserCombo.setDefaultToFirstOption(true);
    }

    public void refreshData() {

        this.refreshUserComboValues();
        this.refreshStoresCombo();
        this.refreshDestinationStoreComboValues();
    }

    private void refreshStoresCombo() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        for (Store store : listOfStores) {
            valueMap.put(store.getId().toString(), store.getLabel());
        }

        this.selectStoreCombo.setValueMap(valueMap);
        this.selectStoreCombo.setDefaultToFirstOption(true);
    }

    private void refreshDestinationStoreComboValues() {
        LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();

        String selecteStoreIdStr = selectStoreCombo.getValueAsString();

        for (Store store : listOfStores) {
            if (!(store.getId().toString().equals(selecteStoreIdStr))) {
                valueMap.put(store.getId().toString(), store.getLabel());
            }
        }

        this.selectDestinationStoreCombo.clearValue();
        this.selectDestinationStoreCombo.setValueMap(valueMap);
        this.selectDestinationStoreCombo.setDefaultToFirstOption(true);
    }

    public void validate() {
    }

    public Store getSelectedStore() {

        long l = getSelectedStoreId();

        for (Store s : this.listOfStores) {
            if (s.getId().equals(l)) {
                return s;
            }
        }

        return null;
    }

    private Store getSelectedDestinationStore() {
        long l = getSelectedDestinationStoreId();

        for (Store s : this.listOfStores) {
            if (s.getId().equals(l)) {
                return s;
            }
        }

        return null;
    }

    private long getSelectedDestinationStoreId() {

        String val = this.selectDestinationStoreCombo.getValueAsString();
        long l = Long.parseLong(val);
        return l;
    }
}

