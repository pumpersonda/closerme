package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Views.Enterprise.EnterpriseMenuView;

/**
 * Created by André on 11/12/2015.
 */
public class EnterpriseMenuController extends AbstractViewController {
    private EnterpriseMenuView enterpriseMenuView;

    public EnterpriseMenuController() {
        this.enterpriseMenuView = new EnterpriseMenuView();
    }

    private void initializeTable() {
        String[] headers = {"", "Name", "Phone", "Role"};
    }

    @Override
    public void openWindow() {
        enterpriseMenuView.setVisible(true);
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void setEvents() {

    }
}
