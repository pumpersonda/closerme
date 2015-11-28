package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.MemberManager.MembersManager;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.VisitorManagement.MembersMenuView;

import java.util.ArrayList;


public class MembersMenuController {
    private MembersMenuView membersMenu;
    private MemberRegistrationController memberRegistrationController;
    private MembersManager membersManager;
    private Notifier notification;
    private ArrayList<Member> memberList;
    private TableModel tableModel;

    public MembersMenuController() {
        membersMenu = new MembersMenuView();
        memberRegistrationController = new MemberRegistrationController(this);
        membersManager = MembersManager.getMembersManager();
        notification = new Notifier();
        updateListMember();

        initTable();

        membersMenu.setLocationRelativeTo(null);
        membersMenu.setResizable(false);
        membersMenu.pack();
        membersMenu.setLocationRelativeTo(null);
        membersMenu.setVisible(true);
        setEvents();
        loadMembersToTable();
    }

    public void addMemberToTable() {
        updateListMember();
        int lasElementOfList = memberList.size() - 1;
        int lastElementId = memberList.get(lasElementOfList).getId();

        Member member = membersManager.getMember(lastElementId);

        ArrayList memberDataList = createMemberListData(member);
        addRow(memberDataList);
    }

    public void resetTable() {
        tableModel.resetTable();
    }

    private void loadMembersToTable() {
        resetTable();
        for (Member aMemberList : memberList) {
            ArrayList memberDataList = createMemberListData(aMemberList);
            addRow(memberDataList);
        }
    }

    private void addRow(ArrayList list) {
        tableModel.addRow(list);
    }

    private void openRegisterWindow() {
        memberRegistrationController.openWindow();
    }

    private void searchMember(String memberName) {
        int foundMembers = 0;
        for (Member aMemberList : memberList) {
            if ((aMemberList.getName().toLowerCase()).contains(memberName.toLowerCase())) {
                foundMembers++;
                showSearchResult(aMemberList);
            }
        }
        showFoundMembersMessage(foundMembers);
    }

    private void showFoundMembersMessage(int foundMembers) {
        String title = "Miembros encontrados";
        String message = getFoundMembersMessage(foundMembers);
        notification.showSuccessMessage(title, message);
    }

    private String getFoundMembersMessage(int foundMembers) {

        if (foundMembers == 1) {
            return "Se ha encontrado 1 miembro";

        } else if (foundMembers > 1) {
            return "Se han encontrado " + foundMembers + " miembros";

        } else {
            return "No se encontraron miembros";

        }
    }

    private void updateTable() {
        resetTable();
        String memberName;
        memberName = membersMenu.getSearchTextField().getText();

        if (memberName.isEmpty()) {
            loadMembersToTable();
        } else {
            searchMember(memberName);
        }
    }

    private void setEvents() {
        membersMenu.getAddButton().addActionListener(actionEvent -> openRegisterWindow());
        membersMenu.getSearchButton().addActionListener(actionEvent -> updateTable());
        membersMenu.getDeleteButton().addActionListener(actionEvent -> deleteMember());
    }

    private void updateListMember() {
        memberList = membersManager.getMemberList();
    }

    private void initTable() {
        String[] headers = {"Name", "Cellphone", "Phone", "Membership"};
        tableModel = new TableModel(headers);
        membersMenu.getMembersTable().setModel(tableModel);
        membersMenu.getMembersTable().getTableHeader().setReorderingAllowed(false);
    }

    private void showSearchResult(Member member) {
        ArrayList memberListData = createMemberListData(member);
        addRow(memberListData);
    }

    private ArrayList<String> createMemberListData(Member member) {
        ArrayList<String> memberDataList = new ArrayList();

        memberDataList.add(member.getName());
        memberDataList.add(member.getCellphone());
        memberDataList.add(member.getPhone());
        memberDataList.add(member.getMembership().getMembershipType());

        return memberDataList;
    }

    private void deleteMember() {
        int numberRow = membersMenu.getMembersTable().getSelectedRow();
        boolean validIndex = numberRow >= 0;

        if (validIndex) {
            tableModel.deleteRow(numberRow);
            membersManager.deleteMember(memberList.get(numberRow));
            updateListMember();

        }


    }

}
