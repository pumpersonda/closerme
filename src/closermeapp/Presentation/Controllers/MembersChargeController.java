package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Views.VisitorManagement.MembersChargeView;

import javax.swing.*;

import static java.lang.String.valueOf;

/**
 * Created by André on 28/11/2015.
 */
public class MembersChargeController {
    private MembersChargeView membersChargeView;

    public MembersChargeController() {
        this.membersChargeView = new MembersChargeView();

        membersChargeView.getTotalTextField().setEditable(false);
        membersChargeView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        membersChargeView.setLocationRelativeTo(null);
        membersChargeView.setResizable(false);

        setEvents();
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

    private void setEvents() {
        membersChargeView.getChargeButton().addActionListener(actionEvent -> charge());
    }
}
