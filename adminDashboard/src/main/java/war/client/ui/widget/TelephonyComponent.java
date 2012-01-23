package war.client.ui.widget;

/**
 * That's basic interface for all application UI's.
 */
public interface TelephonyComponent {

    /**
     * Cleans whole component elements from data
     */
    public void clear();

    /**
     * Calls that method should effected filled component with previously
     * provided data
     */
    public void fillWithData();

    /**
     * Presents the component in the window
     */
    public void show();

    /**
     * Hides the component from the window
     */
    public void hide();

}
