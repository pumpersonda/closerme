package closermeapp.Presentation.Controllers;

import closermeapp.Presentation.VisitorManagement.VisitorView;

import javax.swing.*;

/**
 * Created by Andr� on 24/11/2015.
 */
public class VisitorController extends AbstractController {
    private VisitorView visitorView;


    public VisitorController() {
        visitorView = new VisitorView();

        visitorView.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        visitorView.setLocationRelativeTo(null);
        visitorView.setResizable(false);
        visitorView.pack();
        visitorView.setLocationRelativeTo(null);
        visitorView.setVisible(true);

    }

    @Override
    protected void setEvents() {
        visitorView.getAddButton().addActionListener(actionEvent -> addVisitor());

    }

    private void addVisitor() {

    }

}
