/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Presentation.VisitorManagement;

import closermeapp.Bussiness.MemberManager.MembersManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.logging.Level;

import static java.awt.EventQueue.invokeLater;
import static java.util.logging.Logger.getLogger;
import static javax.swing.BorderFactory.createTitledBorder;
import static javax.swing.GroupLayout.*;
import static javax.swing.LayoutStyle.ComponentPlacement;
import static javax.swing.UIManager.*;

public class MemberRegistrationView extends JFrame {
    private JLabel andWordLabel;
    private JTextField memberAddressFirstSideTextBox;
    private JTextField memberAddressNeighborTextBox;
    private JTextField memberAddressNumberTextBox;
    private JTextField memberAddressSecondSideTextBox;
    private JTextField memberAddressStreetTextBox;
    private JTextField memberCellPhoneTextBox;
    private JTextField memberNameTextBox;
    private JTextField memberPhoneTextBox;
    private JTextField membershipDiscountTextBox;
    private JComboBox<String> membershipTypeComboBox;

    public MemberRegistrationView() {
        initComponents();
        this.membershipDiscountTextBox.setText("0");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initComponents() {

        JPanel memberGeneralDataPanel = new JPanel();
        JLabel memberAddressNeightboorLabel = new JLabel();
        JPanel addressSubPanel = new JPanel();
        JPanel membershipInformationPanel = new JPanel();
        JLabel memberAddressSidesLabel = new JLabel();
        JLabel memberNameLabel = new JLabel();
        JLabel memberPhoneLabel = new JLabel();
        JLabel memberCellphoneLabel = new JLabel();
        JLabel memberAddressStreetLabel = new JLabel();
        JLabel memberAddressNumberLabel = new JLabel();
        JLabel membershipTypeLabel = new JLabel();
        JLabel membershipDiscountLabel = new JLabel();
        JButton addMemberButton = new JButton();
        JButton cancelButton = new JButton();

        this.memberNameTextBox = new JTextField();
        this.membershipDiscountTextBox = new JTextField();
        this.membershipTypeComboBox = new JComboBox<String>();
        this.memberAddressNeighborTextBox = new JTextField();
        this.memberAddressSecondSideTextBox = new JTextField();
        this.memberAddressFirstSideTextBox = new JTextField();
        this.andWordLabel = new JLabel();
        this.memberAddressNumberTextBox = new JTextField();
        this.memberAddressStreetTextBox = new JTextField();
        this.memberCellPhoneTextBox = new JTextField();
        this.memberPhoneTextBox = new JTextField();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        memberGeneralDataPanel.setBorder(createTitledBorder("Datos Generales"));

        memberNameLabel.setText("Nombre:");

        memberPhoneLabel.setText("Telefono: ");

        memberCellphoneLabel.setText("Celular:");

        addressSubPanel.setBorder(createTitledBorder("Dirección"));

        memberAddressStreetLabel.setText("Calle");

        memberAddressNumberLabel.setText("Numero");

        memberAddressSidesLabel.setText("Cruzamientos:");

        andWordLabel.setText("y");

        memberAddressNeightboorLabel.setText("Colonia:");

        GroupLayout addressSubPanelLayout = new GroupLayout(addressSubPanel);
        addressSubPanel.setLayout(addressSubPanelLayout);
        addressSubPanelLayout.setHorizontalGroup(
                addressSubPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(addressSubPanelLayout.createSequentialGroup()
                                .addComponent(memberAddressStreetLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(memberAddressStreetTextBox, PREFERRED_SIZE, 71, PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(memberAddressNumberLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(memberAddressNumberTextBox, PREFERRED_SIZE, 52, PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(memberAddressSidesLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(memberAddressFirstSideTextBox, PREFERRED_SIZE, 73, PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(andWordLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(memberAddressSecondSideTextBox, DEFAULT_SIZE, 72, Short.MAX_VALUE))
                        .addGroup(addressSubPanelLayout.createSequentialGroup()
                                .addComponent(memberAddressNeightboorLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(memberAddressNeighborTextBox))
        );
        addressSubPanelLayout.setVerticalGroup(
                addressSubPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(addressSubPanelLayout.createSequentialGroup()
                                .addGroup(addressSubPanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(memberAddressStreetLabel)
                                        .addComponent(memberAddressStreetTextBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(memberAddressNumberLabel)
                                        .addComponent(memberAddressNumberTextBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(memberAddressSidesLabel)
                                        .addComponent(memberAddressFirstSideTextBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(andWordLabel)
                                        .addComponent(memberAddressSecondSideTextBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(addressSubPanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(memberAddressNeightboorLabel)
                                        .addComponent(memberAddressNeighborTextBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)))
        );

        GroupLayout memberGeneralDataPanelLayout = new GroupLayout(memberGeneralDataPanel);
        memberGeneralDataPanel.setLayout(memberGeneralDataPanelLayout);
        memberGeneralDataPanelLayout.setHorizontalGroup(
                memberGeneralDataPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, memberGeneralDataPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(memberGeneralDataPanelLayout.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(Alignment.LEADING, memberGeneralDataPanelLayout.createSequentialGroup()
                                                .addComponent(memberNameLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(memberNameTextBox))
                                        .addGroup(Alignment.LEADING, memberGeneralDataPanelLayout.createSequentialGroup()
                                                .addComponent(memberPhoneLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(memberPhoneTextBox, PREFERRED_SIZE, 108, PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(memberCellphoneLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(memberCellPhoneTextBox)))
                                .addContainerGap())
                        .addComponent(addressSubPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
        );
        memberGeneralDataPanelLayout.setVerticalGroup(
                memberGeneralDataPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(memberGeneralDataPanelLayout.createSequentialGroup()
                                .addGroup(memberGeneralDataPanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(memberNameLabel)
                                        .addComponent(memberNameTextBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(memberGeneralDataPanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(memberPhoneLabel)
                                        .addComponent(memberPhoneTextBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(memberCellphoneLabel)
                                        .addComponent(memberCellPhoneTextBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(addressSubPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
        );

        membershipInformationPanel.setBorder(createTitledBorder("Membresia"));

        membershipTypeLabel.setText("Tipo:");

        membershipTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[]{"Semanal", "Mensual", "Anual"}));

        membershipDiscountLabel.setText("Descuento:");

        GroupLayout membershipInformationPanelLayout = new GroupLayout(membershipInformationPanel);
        membershipInformationPanel.setLayout(membershipInformationPanelLayout);
        membershipInformationPanelLayout.setHorizontalGroup(
                membershipInformationPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(membershipInformationPanelLayout.createSequentialGroup()
                                .addGroup(membershipInformationPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(membershipInformationPanelLayout.createSequentialGroup()
                                                .addComponent(membershipTypeLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(membershipTypeComboBox, 0, DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(membershipInformationPanelLayout.createSequentialGroup()
                                                .addComponent(membershipDiscountLabel)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(membershipDiscountTextBox, PREFERRED_SIZE, 88, PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        membershipInformationPanelLayout.setVerticalGroup(
                membershipInformationPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(membershipInformationPanelLayout.createSequentialGroup()
                                .addGroup(membershipInformationPanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(membershipTypeLabel)
                                        .addComponent(membershipTypeComboBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(membershipInformationPanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(membershipDiscountLabel)
                                        .addComponent(membershipDiscountTextBox, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)))
        );

        addMemberButton.setText("Agregar");
        addMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                addMember(evt);
            }
        });

        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                CancelButton(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(memberGeneralDataPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(membershipInformationPanel, Alignment.TRAILING, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addMemberButton)
                                                .addPreferredGap(ComponentPlacement.RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addGap(50, 50, 50)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(memberGeneralDataPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(membershipInformationPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(addMemberButton)
                                        .addComponent(cancelButton))
                                .addGap(6, 6, 6))
        );

        pack();
    }

    private void addMember(ActionEvent actionEvent) {
        String name = this.memberNameTextBox.getText();
        String phone = this.memberPhoneTextBox.getText();
        String cellphone = this.memberCellPhoneTextBox.getText();
        String address = getFormattedAddress();
        String membershipType = (String) membershipTypeComboBox.getSelectedItem();
        try {
            double discount = Double.parseDouble(this.membershipDiscountTextBox.getText());
            if (!areEmptyFields(name, phone, cellphone, address)) {

                sendMemberDataToManager(name, phone, cellphone, address, membershipType, discount);
            } else {
                showWarningMessage();
            }
        } catch (NumberFormatException ex) {
            showErrorMessage();
        }
    }


    private String getFormattedAddress() {
        String address
                = this.memberAddressStreetTextBox.getText() + " "
                + this.memberAddressNumberTextBox.getText() + " "
                + this.memberAddressFirstSideTextBox.getText() + ""
                + this.andWordLabel.getText() + ""
                + this.memberAddressSecondSideTextBox.getText() + " "
                + this.memberAddressNeighborTextBox.getText();
        return address;
    }

    private void sendMemberDataToManager(
            String name,
            String phone,
            String cellphone,
            String address,
            String membershipType,
            Double discount
    ) {
        MembersManager memberManager = MembersManager.getInstance();
        memberManager.addMember(name, phone, address, cellphone, membershipType, discount);
        showSuccessMessage();
        resetFields();
    }

    private boolean areEmptyFields(String name, String phone, String cellphone, String address) {
        return (name.isEmpty() || phone.isEmpty() ||
                cellphone.isEmpty() || address.isEmpty());
    }

    private void showSuccessMessage() {
        JOptionPane.showMessageDialog(null, "Miembro agregado correctamente", "Agregado", JOptionPane.PLAIN_MESSAGE);
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(null, "Ingrese un descuento valido", "Advertencia", JOptionPane.WARNING_MESSAGE);

    }

    private void showWarningMessage() {
        JOptionPane.showMessageDialog(null, "Por favor rellene todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
    }

    private void resetFields() {
        this.memberAddressFirstSideTextBox.setText("");
        this.memberAddressNeighborTextBox.setText("");
        this.memberAddressNumberTextBox.setText("");
        this.memberAddressSecondSideTextBox.setText("");
        this.memberAddressStreetTextBox.setText("");
        this.memberCellPhoneTextBox.setText("");
        this.memberNameTextBox.setText("");
        this.memberPhoneTextBox.setText("");
        this.membershipDiscountTextBox.setText("0");
    }

    private void CancelButton(ActionEvent evt) {
        this.dispose();
    }

    public static void main(String args[]) {
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            getLogger(MemberRegistrationView.class.getName()).log(Level.SEVERE, null, ex);
        }

        invokeLater(new Runnable() {
            public void run() {
                new MemberRegistrationView().setVisible(true);
            }
        });
    }

}
