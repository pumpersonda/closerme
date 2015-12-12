package closermeapp;

import ch.randelshofer.quaqua.QuaquaManager;
import closermeapp.Presentation.Controllers.CallLogController;
import closermeapp.Presentation.Controllers.EnterpriseMenuController;
import closermeapp.Presentation.Controllers.MembersMenuController;
import closermeapp.Presentation.Controllers.VisitorViewController;

import javax.swing.*;


public class main {

    public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel(QuaquaManager.getLookAndFeel());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");


            //createMembersMenu();
            //createRegisterMenu();
            //createVisitor();
            //createEmployeeRegistration();
            createEnterpriseMenu();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void createVisitor() {
        VisitorViewController visitorController = new VisitorViewController();
    }

    public static void createMembersMenu() {
        MembersMenuController membersMenuController = new MembersMenuController();
        membersMenuController.openWindow();
    }

    public static void createRegisterMenu() {
        CallLogController callLogController = new CallLogController();
        callLogController.openWindow();
    }


    public static void createEnterpriseMenu() {
        EnterpriseMenuController enterpriseMenuController = new EnterpriseMenuController();
        enterpriseMenuController.openWindow();
    }
}

