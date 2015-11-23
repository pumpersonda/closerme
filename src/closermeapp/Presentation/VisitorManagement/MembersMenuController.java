package closermeapp.Presentation.VisitorManagement;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.MemberManager.MembersManager;
import closermeapp.Presentation.Util.TableModel;

import java.util.ArrayList;

/**
 * Created by André on 22/11/2015.
 */
public class MembersMenuController {
    private MembersMenuView membersMenu;
    private MembersManager membersManager;
    private TableModel tableModel;

    public MembersMenuController() {
        membersMenu = new MembersMenuView();
        membersManager = MembersManager.getMembersManager();


        String[] headers = {"Name", "Cellphone", "Phone", "Membership"};
        tableModel = new TableModel(headers);
        membersMenu.getMembersTable().setModel(tableModel);
        membersMenu.getMembersTable().getTableHeader().setReorderingAllowed(false);

        membersMenu.setLocationRelativeTo(null);
        membersMenu.setResizable(false);
        membersMenu.pack();
        membersMenu.setLocationRelativeTo(null);
        membersMenu.setVisible(true);
        createEvents();
        loadMembersToTable();
    }

    public void addRow(ArrayList list) {
        tableModel.addRow(list);
    }

    public void resetTable() {
        tableModel.resetTable();
    }

    public void loadMembersToTable() {
        ArrayList<Member> memberList = membersManager.getMemberList();

        for (Member aMemberList : memberList) {
            ArrayList<String> memberDataList = new ArrayList<String>();

            memberDataList.add(aMemberList.getName());
            memberDataList.add(aMemberList.getCellphone());
            memberDataList.add(aMemberList.getPhone());
            memberDataList.add(aMemberList.getMembership().getMembershipType());
            addRow(memberDataList);
        }
    }

    private void openRegisterWindow() {
        MemberRegistrationController memberRegistrationController = new MemberRegistrationController(this);
    }

    private void createEvents() {
        membersMenu.getAddButton().addActionListener(actionEvent -> openRegisterWindow());
    }

}
