package closermeapp;

import ch.randelshofer.quaqua.QuaquaManager;
import closermeapp.Presentation.Controllers.MembersMenuController;
import closermeapp.Presentation.Controllers.VisitorController;

import javax.swing.*;


public class main {

    public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel(QuaquaManager.getLookAndFeel());
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");


            createMenuMembers();
            createVisitor();

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void createVisitor() {
        VisitorController visitorController = new VisitorController();
    }

    public static void createMenuMembers() {
        MembersMenuController membersMenuController = new MembersMenuController();
    }
}

