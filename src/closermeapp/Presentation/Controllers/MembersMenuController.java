package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.MemberManager.MembersManager;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.VisitorManagement.MembersMenuView;

import java.util.ArrayList;

import static java.lang.String.valueOf;


public class MembersMenuController {
    private MembersMenuView membersMenu;
    private MemberRegistrationController memberRegistrationController;
    private MembersManager membersManager;
    private Notifier notifier;
    private ArrayList<Member> memberList;
    private TableModel tableModel;

    public MembersMenuController() {
        membersMenu = new MembersMenuView();
        memberRegistrationController = new MemberRegistrationController(this);
        membersManager = MembersManager.getMembersManager();
        notifier = new Notifier();
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

        ArrayList memberDataList = createMemberListData(member, lasElementOfList);
        addRow(memberDataList);
    }

    public void resetTable() {
        tableModel.resetTable();
    }

    private void loadMembersToTable() {
        resetTable();
        for (int indexList = 0; indexList < memberList.size(); indexList++) {
            Member aMemberList = memberList.get(indexList);
            ArrayList memberDataList = createMemberListData(aMemberList, indexList);
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
        for (int indexList = 0; indexList < memberList.size(); indexList++) {
            Member aMemberList = memberList.get(indexList);
            if ((aMemberList.getName().toLowerCase()).contains(memberName.toLowerCase())) {
                foundMembers++;
                showSearchResult(aMemberList, indexList);
            }
        }
        showFoundMembersMessage(foundMembers);
    }

    private void showFoundMembersMessage(int foundMembers) {
        String title = "Miembros encontrados";
        String message = getFoundMembersMessage(foundMembers);
        notifier.showSuccessMessage(title, message);
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
        membersMenu.getDeleteButton().addActionListener(actionEvent -> confirmDelete());
    }

    private void updateListMember() {
        memberList = membersManager.getMemberList();
    }

    private void initTable() {
        String[] headers = {"", "Name", "Cellphone", "Phone", "Membership"};
        tableModel = new TableModel(headers);
        membersMenu.getMembersTable().setModel(tableModel);
        membersMenu.getMembersTable().getTableHeader().setReorderingAllowed(false);
    }

    private void showSearchResult(Member member, int index) {
        ArrayList memberListData = createMemberListData(member, index);
        addRow(memberListData);
    }

    private ArrayList<String> createMemberListData(Member member, int index) {
        ArrayList<String> memberDataList = new ArrayList();

        memberDataList.add(valueOf(index));
        memberDataList.add(member.getName());
        memberDataList.add(member.getCellphone());
        memberDataList.add(member.getPhone());
        memberDataList.add(member.getMembership().getMembershipType());

        return memberDataList;
    }

    private void deleteMemberToTable() {

        int numberRowSelected = membersMenu.getMembersTable().getSelectedRow();
        int columnId = 0;
        int rowId = Integer.parseInt((String) membersMenu.getMembersTable().getValueAt(numberRowSelected, columnId));

        boolean isValidIndex = rowId >= 0;

        if (isValidIndex) {
            tableModel.deleteRow(numberRowSelected);
            deleteMember(rowId);
        }
    }

    private void deleteMember(int rowId) {

        membersManager.deleteMember(memberList.get(rowId));
        updateListMember();
    }

    private void confirmDelete() {
        String messageConfirm = "Estas seguro que deseas eliminar a este miembro";
        int confirmDialog = notifier.showConfirmDialog(messageConfirm);

        if (confirmDialog == notifier.getYES_OPTION()) {
            deleteMemberToTable();
            String title = "Eliminado";
            String message = "Se ha eliminado con exito";
            notifier.showSuccessMessage(title, message);
        }

    }

}
