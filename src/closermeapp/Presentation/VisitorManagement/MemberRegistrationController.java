package closermeapp.Presentation.VisitorManagement;

import closermeapp.Bussiness.MemberManager.MembersManager;
import closermeapp.Presentation.Util.NotificationMessage;

import java.awt.event.ActionEvent;

public class MemberRegistrationController {
    private MemberRegistrationView memberRegistrationView;
    private NotificationMessage notification;

    public MemberRegistrationController() {
        this.memberRegistrationView = new MemberRegistrationView();
        this.notification = new NotificationMessage();

        memberRegistrationView.setLocationRelativeTo(null);
        memberRegistrationView.setResizable(false);
        memberRegistrationView.setVisible(true);

        memberRegistrationView.getRegisterMemberButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                registerMember();
            }
        });
        memberRegistrationView.getCancelButton().addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CancelButton();
            }
        });
    }

    private void registerMember() {
        String name = memberRegistrationView.getMemberNameTextBox().getText();
        String phone = memberRegistrationView.getMemberPhoneTextBox().getText();
        String cellphone = memberRegistrationView.getMemberCellPhoneTextBox().getText();
        String address = getFormattedAddress();
        String membershipType = (String) memberRegistrationView.getMembershipTypeComboBox().getSelectedItem();

        try {
            double discount = Double.parseDouble(memberRegistrationView.getMembershipDiscountTextBox().getText());
            if (!isEmptyFields(name, phone, cellphone, address)) {

                sendMemberDataToManager(name, phone, cellphone, address, membershipType, discount);
            } else {
                notification.showWarningMessage("Advertencia", "Por favor rellene todos los campos");
            }
        } catch (NumberFormatException ex) {
            notification.showFailMessage("Advertencia", "Ingrese un descuento valido");
        }
    }


    private String getFormattedAddress() {
        String whiteSpace = " ";

        String address = memberRegistrationView.getMemberAddressStreetTextBox().getText() +
                whiteSpace + memberRegistrationView.getMemberAddressNumberTextBox().getText() +
                whiteSpace + memberRegistrationView.getMemberAddressFirstSideTextBox().getText() +
                whiteSpace + memberRegistrationView.getAndWordLabel().getText() +
                whiteSpace + memberRegistrationView.getMemberAddressSecondSideTextBox().getText() +
                whiteSpace + memberRegistrationView.getMemberAddressNeighborTextBox().getText();
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
        MembersManager memberManager = MembersManager.getMembersManager();
        memberManager.addMember(name, phone, address, cellphone, membershipType, discount);
        notification.showSuccessMessage("Agregado", "Miembro agregado correctamente");
        resetFields();
    }

    private void resetFields() {
        String whiteSpace = "";
        String defaultDiscount = "0";
        this.memberRegistrationView.getMemberAddressFirstSideTextBox().setText(whiteSpace);
        this.memberRegistrationView.getMemberAddressNeighborTextBox().setText(whiteSpace);
        this.memberRegistrationView.getMemberAddressNumberTextBox().setText(whiteSpace);
        this.memberRegistrationView.getMemberAddressSecondSideTextBox().setText(whiteSpace);
        this.memberRegistrationView.getMemberAddressStreetTextBox().setText(whiteSpace);
        this.memberRegistrationView.getMemberCellPhoneTextBox().setText(whiteSpace);
        this.memberRegistrationView.getMemberNameTextBox().setText(whiteSpace);
        this.memberRegistrationView.getMemberPhoneTextBox().setText(whiteSpace);
        this.memberRegistrationView.getMembershipDiscountTextBox().setText(defaultDiscount);
    }

    private void CancelButton() {
        memberRegistrationView.dispose();
    }
}
