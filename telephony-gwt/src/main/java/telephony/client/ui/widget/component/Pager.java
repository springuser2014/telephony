package telephony.client.ui.widget.component;


import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * @todo delete?
 */
public class Pager extends HLayout {

    private int numberOfElements;
    private PerPage numberOfElementsPerPage;

    private SelectItem selectPage;

    public Pager() {
    }

    public void setNumberOfElementsPerPage(PerPage ten) {
        this.numberOfElementsPerPage = ten;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public PerPage getNumberOfElementsPerPage() {
        return numberOfElementsPerPage;
    }

    public enum PerPage {
        TEN,

        TWELVE,

        FIFTY,

        ONE_HOUNDRED
    }

    public void setNumberOfElements(int i) {
        this.numberOfElements = i;
    }
}
