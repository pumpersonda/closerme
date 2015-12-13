package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.Views.Charge.ChargeView;

import static java.lang.String.valueOf;

/**
 * Created by André on 28/11/2015.
 */
public class ChargeController extends AbstractViewController {
    private ChargeView chargeView;

    public ChargeController() {
        this.chargeView = new ChargeView();
        initializeView();
    }


    public void openWindow() {
        chargeView.setVisible(true);
    }


    public void setTotalChargeMessage(double totalCharge) {
        String totalChargeText = getTotalChargeText(totalCharge);
        chargeView.getTotalTextField().setText(totalChargeText);
    }

    private String getTotalChargeText(double totalCharge) {
        String totalChargeText = valueOf(totalCharge);
        return totalChargeText;
    }

    private void charge() {
        chargeView.dispose();
    }

    @Override
    protected void initializeView() {
        configureWindow(chargeView);
        setEvents();
    }

    @Override
    protected void setEvents() {
        chargeView.getChargeButton().addActionListener(actionEvent -> charge());
    }
}
