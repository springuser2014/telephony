package war.client.ui.widget;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
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
import war.client.service.*;
import war.client.ui.widget.interfaces.TelephonyComponent;
import war.server.core.entity.Product;
import war.server.core.entity.Sale;
import war.server.core.entity.Store;
import war.server.core.entity.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SalesComponent extends VLayout implements TelephonyComponent {

    private final UserRPCServiceAsync userService = GWT.create(UserRPCService.class);
    private final ProductRPCServiceAsync productService = GWT.create(ProductRPCService.class);
    private final StoreRPCServiceAsync storeService = GWT.create(StoreRPCService.class);
    private final SaleRPCServiceAsync saleService = GWT.create(SaleRPCService.class);

    private ListGrid productsListGrid;
    private SelectItem selectStoreCombo;
    private SelectItem selectProductStatusCombo;
    private IButton reloadButton;

    /* ladowanie danych */
    private boolean listOfStoresLoaded = false;
    private boolean listOfProductsLoaded = false;

    private List<Product> listOfProducts = new ArrayList<Product>();
    private List<Store> listOfStores = new ArrayList<Store>();

    private boolean listOfColorsLoaded = false;
    private boolean listOfProducersLoaded = false;
    private boolean listOfModelsLoaded = false;

    private List<String> listOfColors = new ArrayList<String>();
    private List<String> listOfProducers = new ArrayList<String>();
    private List<String> listOfModels = new ArrayList<String>();
    private List<User> listOfUsers = new ArrayList<User>();
    private List<Sale> listOfSales = new ArrayList<Sale>();

    private SelectItem selectUserCombo;
    private IButton doButton;
    private SelectItem selectPage;
    private boolean listOfUsersLoaded;


    public SalesComponent() {
        super();

        Log.debug("Initializing SalesComponent widget..");


        this.setMembersMargin(10);

        // initialise the layout container
        this.setHeight(SIZE.CONTEXT_BOX_HEIGHT);

        this.setAlign(Alignment.CENTER);

        this.setAlign(VerticalAlignment.TOP);

        this.productsListGrid = new ListGrid();
        productsListGrid.setWidth(1010);
        productsListGrid.setHeight(400);
        productsListGrid.setShowAllRecords(true);

        ListGridField field0 = new ListGridField("label", "Tytuł zakupu", 250);
        ListGridField field1 = new ListGridField("imei", "IMEI", 150);
        ListGridField field2 = new ListGridField("producer", "Producent", 120);
        ListGridField field3 = new ListGridField("model", "Model", 150);
        ListGridField field4 = new ListGridField("color", "Kolor", 100);
        ListGridField field5 = new ListGridField("price_in", "Cena zakupu", 100);
        ListGridField field6 = new ListGridField("price_out", "Cena sprzedaży", 100);

        this.productsListGrid.setFields(new ListGridField[]{field0, field1, field2, field3, field4, field5, field6});

        this.selectStoreCombo = new SelectItem();
        selectStoreCombo.setTitle("Magazyn");


        this.reloadButton = new IButton("Odśwież listę");
        reloadButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                fillWithData();
            }
        });

        this.selectPage = new SelectItem();
        selectPage.setTitle("Zmień stronę");

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

        formLay.setMembersMargin(10);
        formLay.addMember(form);
        formLay.addMember(form9);
        formLay.addMember(reloadButton);

        DynamicForm form4 = new DynamicForm();
        selectUserCombo = new SelectItem();
        selectUserCombo.setTitle("Edytujący");
        form4.setFields(selectUserCombo);

        doButton = new IButton("Zapisz zmiany");


        doButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
//                tryToAddNewSale();
            }
        });

        HLayout form2Lay = new HLayout();
        form2Lay.setWidth100();
        form2Lay.setHeight(10);
        form2Lay.setMembersMargin(10);

        form2Lay.addMember(form4);
        form2Lay.addMember(doButton);


        this.addMember(formLay);
        this.addMember(form2Lay);

        this.addMember(productsListGrid);

        this.loadData();

        Log.debug("ContentBox SalesComponent was initialized..");
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

//        public void setLabel(String label) {
//            setAttribute("label", label);
//        }
//
//        public String getLabel() {
//            return getAttributeAsString("label");
//        }
//
//        public void setDateOut(String label) {
//            setAttribute("date_out", label);
//        }
//
//        public String getDateOut() {
//            return getAttributeAsString("date_out");
//        }
//
//        public void setNumberOfProducts(String label) {
//            setAttribute("number_of_products", label);
//        }
//
//        public String getNumberOfProducts() {
//            return getAttributeAsString("number_of_products");
//        }
//
//        public void setStore(String label) {
//            setAttribute("store", label);
//        }
//
//        public String getStore() {
//            return getAttributeAsString("store");
//        }
//
//        public void setWho(String label) {
//            setAttribute("who", label);
//        }
//
//        public String getWho() {
//            return getAttributeAsString("who");
//        }
//
//        public void setSumPriceOut(String sumPriceOut) {
//            setAttribute("sum_price_out", sumPriceOut);
//        }
//
//        public void getSumPriceOut(String sumPriceOut) {
//            setAttribute("sum_price_out", sumPriceOut);
//        }
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

        /* pobranie listy sklepów do tabeli */
        this.storeService.fetchAllStores(new AsyncCallback<List<Store>>() {

            public void onFailure(Throwable caught) {
                SC.say("Niestety pobranie sklepów nie powiodło się, jeżeli problem będzie się powtarzał skontaktuj się z administratorem");

                listOfStoresLoaded = false;
            }

            public void onSuccess(List<Store> stores) {
                listOfStores = stores;
                listOfStoresLoaded = true;

                dataChanged();
            }
        });

        loadColors();
        loadModels();
        loadProducers();
        loadUsers();
    }

    public void fillWithData() {


        this.saleService.fetchSalesFrom(getSelectedStore(), new AsyncCallback<List<Sale>>() {

            public void onFailure(Throwable caught) {

                listOfProductsLoaded = false;
            }

            public void onSuccess(List<Sale> result) {
                listOfProductsLoaded = true;

                SC.say("found " + result.size() + " elements ");

                for (int i = 0; i < result.size(); i++) {

                    if (result.get(i) != null && result.get(i).getProducts() != null) {
                        SC.say(i + " element have " + result.get(i).getProducts().size() + " products");
                    }
                }


            }
        });
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
            refreshStoresCombo();
            refreshUserComboValues();
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
                this.listOfUsersLoaded);
    }

    private void refreshProductsGrid() {

        ListGridRecord[] records = this.productsListGrid.getRecords();

        for (int i = 0; i < records.length; i++) {
            this.productsListGrid.removeData(records[i]);
        }

        for (Sale d : this.listOfSales) {

            String label = d.getLabel() + " ( data dodania :" + d.getDateOut() + ", ilość produktów " + d.getProducts().size() + " ) ";

            if (d.getProducts() != null)
                SC.say(d.getId() + " id - ma elementow " + d.getProducts().size());

            for (Product p : d.getProducts()) {
                SaleComponentRecord record = new SaleComponentRecord(label, p);
                this.productsListGrid.addData(record);
            }

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

    public Long getSelectedStoreId() {
        String val = this.selectStoreCombo.getValueAsString();
        long l = Long.parseLong(val);
        return l;
    }


}

