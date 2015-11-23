package closermeapp;

import closermeapp.Presentation.VisitorManagement.MembersMenuController;

import javax.swing.*;


public class main {

    public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            //UIManager.setLookAndFeel(QuaquaManager.getLookAndFeel());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");


            createMenuMembers();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public static void createMenuMembers() {
        MembersMenuController membersMenuController = new MembersMenuController();
    }
}

