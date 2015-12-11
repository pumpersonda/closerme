package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Views.VisitorManagement.MembersChargeView;

import static java.lang.String.valueOf;

/**
 * Created by André on 28/11/2015.
 */
public class MembersChargeController extends AbstractViewController {
    private MembersChargeView membersChargeView;

    public MembersChargeController() {
        this.membersChargeView = new MembersChargeView();
        initializeView();
    }


    public void openWindow() {
        membersChargeView.setVisible(true);
    }


    public void setTotalChargeMessage(double totalCharge) {
        String totalChargeText = getTotalChargeText(totalCharge);
        membersChargeView.getTotalTextField().setText(totalChargeText);
    }

    private String getTotalChargeText(double totalCharge) {
        String totalChargeText = valueOf(totalCharge);
        return totalChargeText;
    }

    private void charge() {
        membersChargeView.dispose();
    }

    @Override
    protected void initializeView() {
        configureWindow(membersChargeView);
        setEvents();
    }

    @Override
    protected void setEvents() {
        membersChargeView.getChargeButton().addActionListener(actionEvent -> charge());
    }
}
