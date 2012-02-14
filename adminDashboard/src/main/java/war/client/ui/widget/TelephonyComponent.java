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
     * Checks if all required data to show component is loaded
     *
     * @return
     */
    public boolean isDataLoaded();


    public void dataChanged();

    /**
     * Presents the component in the window
     */
    public void show();

    /**
     * Hides the component from the window
     */
    public void hide();

    /**
     * Refresh component's data and visualization
     */
    public void refreshData();

    /**
     * Validate components data (provided/selected by user)
     */
    public void validate();

}
