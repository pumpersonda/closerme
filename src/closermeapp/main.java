package closermeapp;

import ch.randelshofer.quaqua.QuaquaManager;
import closermeapp.Presentation.Controllers.CallLogController;
import closermeapp.Presentation.Controllers.EmployeeRegistrationController;
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


            //createMenuMembers();
            // createRegisterMenu();
            //createVisitor();
            createEmployeeMenu();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void createVisitor() {
        VisitorViewController visitorController = new VisitorViewController();
    }

    public static void createMenuMembers() {
        MembersMenuController membersMenuController = new MembersMenuController();
    }

    public static void createRegisterMenu() {
        CallLogController callLogController = new CallLogController();
        callLogController.openWindow();
    }

    public static void createEmployeeMenu() {
        EmployeeRegistrationController employeeRegistrationController = new EmployeeRegistrationController();
        employeeRegistrationController.openWindow();
    }
}

