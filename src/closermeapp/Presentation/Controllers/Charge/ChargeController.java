package closermeapp.Presentation.Controllers.Charge;

import closermeapp.Presentation.Controllers.AbstractViewController;
import closermeapp.Presentation.Views.Charge.ChargeView;

import static java.lang.String.valueOf;

/**
 * Created by André on 28/11/2015.
 */
public class ChargeController extends AbstractViewController {
    private ChargeView chargeView;

    public ChargeController() {
        this.setChargeView( new ChargeView() );
        initializeView();
    }


    public void openWindow() {
        getChargeView().setVisible( true );
    }


    public void setTotalChargeMessage(double totalCharge) {
        String totalChargeText = getTotalChargeText(totalCharge);
        getChargeView().getTotalTextField().setText( totalChargeText );
    }

    private String getTotalChargeText(double totalCharge) {
        String totalChargeText = valueOf(totalCharge);
        return totalChargeText;
    }

    private void charge() {
        String title = "Cobrado";
        String message = "Miembro cobrado";
        getNotifier().showSuccessMessage( title, message );
        getChargeView().dispose();
    }

    @Override
    protected void initializeView() {
        configureWindow( getChargeView() );
        setEvents();
    }

    @Override
    protected void setEvents() {
        getChargeView().getChargeButton().addActionListener( actionEvent -> charge() );
    }

    private void setChargeView(ChargeView chargeView) {
        this.chargeView = chargeView;
    }

    private ChargeView getChargeView() {
        return chargeView;
    }


}
