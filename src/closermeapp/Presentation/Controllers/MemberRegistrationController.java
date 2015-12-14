package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Cashier.Cashier;
import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.MemberManager.MembersManager;
import closermeapp.Presentation.Views.VisitorManagement.MemberRegistrationView;

import static java.lang.Double.parseDouble;

public class MemberRegistrationController extends AbstractViewController {
    private MemberRegistrationView memberRegistrationView;
    private MembersMenuController membersMenuController;
    private ChargeController chargeController;

    public MemberRegistrationController(MembersMenuController membersMenuController) {
        setMemberRegistrationView( new MemberRegistrationView() );
        setMembersMenuController( membersMenuController );
        setChargeController( new ChargeController() );

        initializeView();
    }

    public void openWindow() {
        getMemberRegistrationView().setVisible( true );
    }


    private void registerMemberData() {
        String name = getMemberRegistrationView().getMemberNameTextBox().getText();
        String phone = getMemberRegistrationView().getMemberPhoneTextBox().getText();
        String cellphone = getMemberRegistrationView().getMemberCellPhoneTextBox().getText();
        String address = getFormattedAddress();
        String membershipType = (String) getMemberRegistrationView().getMembershipTypeComboBox().getSelectedItem();
        String message;
        try {

            double discount = parseDouble( getMemberRegistrationView().getMembershipDiscountTextBox().getText() );

            if (isEmptyFields(name, phone, cellphone, address)) {
                message = "Por favor rellene todos los campos";
                getNotifier().showWarningMessage( message );
            } else {
                sendMemberDataToManager(name, phone, cellphone, address, membershipType, discount);
            }

        } catch (NumberFormatException numberFormatException) {
            message = "Ingrese un descuento valido";
            getNotifier().showFailMessage( message );
        }
    }

    private String getFormattedAddress() {
        String whiteSpace = " ";

        String address = getMemberRegistrationView().getMemberAddressStreetTextBox().getText() +
                whiteSpace + getMemberRegistrationView().getMemberAddressNumberTextBox().getText() +
                whiteSpace + getMemberRegistrationView().getMemberAddressFirstSideTextBox().getText() +
                whiteSpace + getMemberRegistrationView().getAndWordLabel().getText() +
                whiteSpace + getMemberRegistrationView().getMemberAddressSecondSideTextBox().getText() +
                whiteSpace + getMemberRegistrationView().getMemberAddressNeighborTextBox().getText();
        return address;
    }

    private boolean isEmptyFields(String name, String phone, String cellphone, String address) {
        return (name.isEmpty() || phone.isEmpty() || cellphone.isEmpty() || address.isEmpty());
    }

    private void sendMemberDataToManager(
            String name,
            String phone,
            String cellphone,
            String address,
            String membershipType,
            Double discount
    ) {
        MembersManager membersManager = MembersManager.getMembersManager();
        Member member = membersManager.createMember(name, phone, address, cellphone, membershipType, discount);
        membersManager.addMember(member);

        double totalCharge = getTotalCharge(member, discount);
        windowsUpdate(member, totalCharge);

        String title = "Agregado";
        String message = "Miembro agregado correctamente";
        getNotifier().showSuccessMessage( title, message );

    }

    private void resetFields() {
        String whiteSpace = "";
        String defaultDiscount = "0";
        this.getMemberRegistrationView().getMemberAddressFirstSideTextBox().setText( whiteSpace );
        this.getMemberRegistrationView().getMemberAddressNeighborTextBox().setText( whiteSpace );
        this.getMemberRegistrationView().getMemberAddressNumberTextBox().setText( whiteSpace );
        this.getMemberRegistrationView().getMemberAddressSecondSideTextBox().setText( whiteSpace );
        this.getMemberRegistrationView().getMemberAddressStreetTextBox().setText( whiteSpace );
        this.getMemberRegistrationView().getMemberCellPhoneTextBox().setText( whiteSpace );
        this.getMemberRegistrationView().getMemberNameTextBox().setText( whiteSpace );
        this.getMemberRegistrationView().getMemberPhoneTextBox().setText( whiteSpace );
        this.getMemberRegistrationView().getMembershipDiscountTextBox().setText( defaultDiscount );
    }

    private void CancelButton() {
        closeWindow();
    }

    private void windowsUpdate(Member member, double totalCharge) {
        resetFields();
        getMembersMenuController().addMemberToTable( member );
        getChargeController().setTotalChargeMessage( totalCharge );
        showChargeView();
    }

    private double getTotalCharge(Member member, double discount) {
        Cashier cashier = Cashier.getInstance();
        double totalCharge = cashier.chargeTheMember(member, discount);
        return totalCharge;
    }

    private void showChargeView() {
        getChargeController().openWindow();
    }

    private void closeWindow() {
        getMemberRegistrationView().dispose();
    }

    private void setDefaultDiscount() {
        String defaultDiscount = "0";
        getMemberRegistrationView().getMembershipDiscountTextBox().setText( defaultDiscount );
    }

    @Override
    protected void initializeView() {
        configureWindow( getMemberRegistrationView() );
        setDefaultDiscount();
        setEvents();
    }

    @Override
    protected void setEvents() {
        getMemberRegistrationView().getRegisterMemberButton().addActionListener( actionEvent -> registerMemberData() );
        getMemberRegistrationView().getCancelButton().addActionListener( actionEvent -> CancelButton() );

    }

    private void setMembersMenuController(MembersMenuController membersMenuController) {
        this.membersMenuController = membersMenuController;
    }

    private void setMemberRegistrationView(MemberRegistrationView memberRegistrationView) {
        this.memberRegistrationView = memberRegistrationView;
    }

    private void setChargeController(ChargeController chargeController) {
        this.chargeController = chargeController;
    }

    private MembersMenuController getMembersMenuController() {
        return membersMenuController;
    }

    private MemberRegistrationView getMemberRegistrationView() {
        return memberRegistrationView;
    }

    private ChargeController getChargeController() {
        return chargeController;
    }


}
