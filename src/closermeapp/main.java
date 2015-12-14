package closermeapp;

import ch.randelshofer.quaqua.QuaquaManager;
import closermeapp.Presentation.Controllers.EventViewerViewController;
import closermeapp.Presentation.Controllers.PrincipalMenuController;

import javax.swing.*;


public class main {

    public static void main(String[] args) {
        try {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
            UIManager.setLookAndFeel(QuaquaManager.getLookAndFeel());
            // UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");

            //createEvent();
            createPrincipalMenu();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }


    public static void createPrincipalMenu() {
        PrincipalMenuController principalMenuController = new PrincipalMenuController();
        principalMenuController.openWindow();
    }

    public static void createEventViewer() {
        EventViewerViewController eventViewerViewController = new EventViewerViewController();
        eventViewerViewController.openWindow();
    }


}

