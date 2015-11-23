package closermeapp;

import closermeapp.Presentation.VisitorManagement.MemberRegistrationController;

import javax.swing.*;


public class main {

    public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            //UIManager.setLookAndFeel(QuaquaManager.getLookAndFeel());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");


            createMemberRegistrationView();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public static void createMemberRegistrationView() {
        MemberRegistrationController memberRegistrationView = new MemberRegistrationController();

    }
}

