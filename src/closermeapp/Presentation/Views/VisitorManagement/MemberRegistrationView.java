/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package closermeapp.Presentation.Views.VisitorManagement;


import javax.swing.*;


public class MemberRegistrationView extends javax.swing.JFrame {

    /**
     * Creates new form MemberRegistrationView
     */
    public MemberRegistrationView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        memberGeneralDataPanel = new javax.swing.JPanel();
        memberNameLabel = new javax.swing.JLabel();
        memberNameTextBox = new javax.swing.JTextField();
        memberPhoneLabel = new javax.swing.JLabel();
        memberPhoneTextBox = new javax.swing.JTextField();
        memberCelPhoneLabel = new javax.swing.JLabel();
        memberCellPhoneTextBox = new javax.swing.JTextField();
        addressSubPanel = new javax.swing.JPanel();
        memberAddressStreetLabel = new javax.swing.JLabel();
        memberAddressStreetTextBox = new javax.swing.JTextField();
        memberAddressNumberLabel = new javax.swing.JLabel();
        memberAddressNumberTextBox = new javax.swing.JTextField();
        memberAddressSidesLabel = new javax.swing.JLabel();
        memberAddressFirstSideTextBox = new javax.swing.JTextField();
        andWordLabel = new javax.swing.JLabel();
        memberAddressSecondSideTextBox = new javax.swing.JTextField();
        MemberAddressNeighborLabel = new javax.swing.JLabel();
        memberAddressNeighborTextBox = new javax.swing.JTextField();
        membershipInformationPanel = new javax.swing.JPanel();
        membershipTypeLabel = new javax.swing.JLabel();
        membershipTypeComboBox = new javax.swing.JComboBox();
        membershipDiscountLabel = new javax.swing.JLabel();
        membershipDiscountTextBox = new javax.swing.JTextField();
        RegisterMemberButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        memberGeneralDataPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Generales"));

        memberNameLabel.setText("Nombre:");

        memberPhoneLabel.setText("Telefono: ");

        memberCelPhoneLabel.setText("Celular:");

        addressSubPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dirección"));

        memberAddressStreetLabel.setText("Calle");

        memberAddressNumberLabel.setText("Numero");

        memberAddressSidesLabel.setText("Cruzamientos:");

        andWordLabel.setText("y");

        MemberAddressNeighborLabel.setText("Colonia:");

        javax.swing.GroupLayout addressSubPanelLayout = new javax.swing.GroupLayout(addressSubPanel);
        addressSubPanel.setLayout(addressSubPanelLayout);
        addressSubPanelLayout.setHorizontalGroup(
                addressSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addressSubPanelLayout.createSequentialGroup()
                                .addComponent(memberAddressStreetLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memberAddressStreetTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memberAddressNumberLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memberAddressNumberTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memberAddressSidesLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memberAddressFirstSideTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(andWordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memberAddressSecondSideTextBox, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
                        .addGroup(addressSubPanelLayout.createSequentialGroup()
                                .addComponent(MemberAddressNeighborLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(memberAddressNeighborTextBox))
        );
        addressSubPanelLayout.setVerticalGroup(
                addressSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addressSubPanelLayout.createSequentialGroup()
                                .addGroup(addressSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(memberAddressStreetLabel)
                                        .addComponent(memberAddressStreetTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(memberAddressNumberLabel)
                                        .addComponent(memberAddressNumberTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(memberAddressSidesLabel)
                                        .addComponent(memberAddressFirstSideTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(andWordLabel)
                                        .addComponent(memberAddressSecondSideTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(addressSubPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(MemberAddressNeighborLabel)
                                        .addComponent(memberAddressNeighborTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout memberGeneralDataPanelLayout = new javax.swing.GroupLayout(memberGeneralDataPanel);
        memberGeneralDataPanel.setLayout(memberGeneralDataPanelLayout);
        memberGeneralDataPanelLayout.setHorizontalGroup(
                memberGeneralDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memberGeneralDataPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(memberGeneralDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, memberGeneralDataPanelLayout.createSequentialGroup()
                                                .addComponent(memberNameLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(memberNameTextBox))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, memberGeneralDataPanelLayout.createSequentialGroup()
                                                .addComponent(memberPhoneLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(memberPhoneTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(memberCelPhoneLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(memberCellPhoneTextBox)))
                                .addContainerGap())
                        .addComponent(addressSubPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        memberGeneralDataPanelLayout.setVerticalGroup(
                memberGeneralDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(memberGeneralDataPanelLayout.createSequentialGroup()
                                .addGroup(memberGeneralDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(memberNameLabel)
                                        .addComponent(memberNameTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(memberGeneralDataPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(memberPhoneLabel)
                                        .addComponent(memberPhoneTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(memberCelPhoneLabel)
                                        .addComponent(memberCellPhoneTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(addressSubPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        membershipInformationPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Membresia"));

        membershipTypeLabel.setText("Tipo:");

        membershipTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Semanal", "Mensual", "Anual"}));

        membershipDiscountLabel.setText("Descuento:");

        javax.swing.GroupLayout membershipInformationPanelLayout = new javax.swing.GroupLayout(membershipInformationPanel);
        membershipInformationPanel.setLayout(membershipInformationPanelLayout);
        membershipInformationPanelLayout.setHorizontalGroup(
                membershipInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(membershipInformationPanelLayout.createSequentialGroup()
                                .addGroup(membershipInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(membershipInformationPanelLayout.createSequentialGroup()
                                                .addComponent(membershipTypeLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(membershipTypeComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(membershipInformationPanelLayout.createSequentialGroup()
                                                .addComponent(membershipDiscountLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(membershipDiscountTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        membershipInformationPanelLayout.setVerticalGroup(
                membershipInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(membershipInformationPanelLayout.createSequentialGroup()
                                .addGroup(membershipInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(membershipTypeLabel)
                                        .addComponent(membershipTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(membershipInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(membershipDiscountLabel)
                                        .addComponent(membershipDiscountTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        RegisterMemberButton.setText("Registrar");

        cancelButton.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(memberGeneralDataPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(membershipInformationPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(RegisterMemberButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(cancelButton)
                                                .addGap(34, 34, 34)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(memberGeneralDataPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(membershipInformationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(RegisterMemberButton)
                                        .addComponent(cancelButton))
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MemberRegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MemberRegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MemberRegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MemberRegistrationView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MemberRegistrationView().setVisible(true);
            }
        });
    }

    public JLabel getMemberAddressNeighborLabel() {
        return MemberAddressNeighborLabel;
    }

    public JButton getRegisterMemberButton() {
        return RegisterMemberButton;
    }

    public JPanel getAddressSubPanel() {
        return addressSubPanel;
    }

    public JLabel getAndWordLabel() {
        return andWordLabel;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JTextField getMemberAddressFirstSideTextBox() {
        return memberAddressFirstSideTextBox;
    }

    public JTextField getMemberAddressNeighborTextBox() {
        return memberAddressNeighborTextBox;
    }

    public JLabel getMemberAddressNumberLabel() {
        return memberAddressNumberLabel;
    }

    public JTextField getMemberAddressNumberTextBox() {
        return memberAddressNumberTextBox;
    }

    public JTextField getMemberAddressSecondSideTextBox() {
        return memberAddressSecondSideTextBox;
    }

    public JLabel getMemberAddressSidesLabel() {
        return memberAddressSidesLabel;
    }

    public JLabel getMemberAddressStreetLabel() {
        return memberAddressStreetLabel;
    }

    public JTextField getMemberAddressStreetTextBox() {
        return memberAddressStreetTextBox;
    }

    public JLabel getMemberCelPhoneLabel() {
        return memberCelPhoneLabel;
    }

    public JTextField getMemberCellPhoneTextBox() {
        return memberCellPhoneTextBox;
    }

    public JPanel getMemberGeneralDataPanel() {
        return memberGeneralDataPanel;
    }

    public JLabel getMemberNameLabel() {
        return memberNameLabel;
    }

    public JTextField getMemberNameTextBox() {
        return memberNameTextBox;
    }

    public JLabel getMemberPhoneLabel() {
        return memberPhoneLabel;
    }

    public JTextField getMemberPhoneTextBox() {
        return memberPhoneTextBox;
    }

    public JLabel getMembershipDiscountLabel() {
        return membershipDiscountLabel;
    }

    public JTextField getMembershipDiscountTextBox() {
        return membershipDiscountTextBox;
    }

    public JPanel getMembershipInformationPanel() {
        return membershipInformationPanel;
    }

    public JComboBox getMembershipTypeComboBox() {
        return membershipTypeComboBox;
    }

    public JLabel getMembershipTypeLabel() {
        return membershipTypeLabel;
    }


    // Variables declaration - do not modify
    private javax.swing.JLabel MemberAddressNeighborLabel;
    private javax.swing.JButton RegisterMemberButton;
    private javax.swing.JPanel addressSubPanel;
    private javax.swing.JLabel andWordLabel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JTextField memberAddressFirstSideTextBox;
    private javax.swing.JTextField memberAddressNeighborTextBox;
    private javax.swing.JLabel memberAddressNumberLabel;
    private javax.swing.JTextField memberAddressNumberTextBox;
    private javax.swing.JTextField memberAddressSecondSideTextBox;
    private javax.swing.JLabel memberAddressSidesLabel;
    private javax.swing.JLabel memberAddressStreetLabel;
    private javax.swing.JTextField memberAddressStreetTextBox;
    private javax.swing.JLabel memberCelPhoneLabel;
    private javax.swing.JTextField memberCellPhoneTextBox;
    private javax.swing.JPanel memberGeneralDataPanel;
    private javax.swing.JLabel memberNameLabel;
    private javax.swing.JTextField memberNameTextBox;
    private javax.swing.JLabel memberPhoneLabel;
    private javax.swing.JTextField memberPhoneTextBox;
    private javax.swing.JLabel membershipDiscountLabel;
    private javax.swing.JTextField membershipDiscountTextBox;
    private javax.swing.JPanel membershipInformationPanel;
    private javax.swing.JComboBox membershipTypeComboBox;
    private javax.swing.JLabel membershipTypeLabel;
    // End of variables declaration
}