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

        membersMenu.setLocationRelativeTo(null);
        membersMenu.setResizable(false);
        membersMenu.pack();
        membersMenu.setLocationRelativeTo(null);
        membersMenu.setVisible(true);

        initTable();
        setEvents();
        updateMemberList();
        loadMembersToTable();
    }

    public void addMemberToTable() {
        updateMemberList();
        int lasElementOfList = memberList.size() - 1;
        int lastElementId = memberList.get(lasElementOfList).getId();

        Member member = membersManager.getMember(lastElementId);

        ArrayList memberDataList = createMemberListData(member, lasElementOfList);
        addRow(memberDataList);
    }

    private void updateMemberList() {
        memberList = membersManager.getMemberList();
    }

    private void loadMembersToTable() {
        resetTable();
        for (int listIndex = 0; listIndex < memberList.size(); listIndex++) {
            Member aMemberList = memberList.get(listIndex);
            ArrayList memberDataList = createMemberListData(aMemberList, listIndex);
            addRow(memberDataList);
        }
    }

    private void resetTable() {
        tableModel.resetTable();
    }

    private ArrayList<String> createMemberListData(Member member, int listIndex) {
        ArrayList<String> memberDataList = new ArrayList();

        memberDataList.add(valueOf(listIndex));
        memberDataList.add(member.getName());
        memberDataList.add(member.getCellphone());
        memberDataList.add(member.getPhone());
        memberDataList.add(member.getMembership().getMembershipType());

        return memberDataList;
    }

    private void addRow(ArrayList list) {
        tableModel.addRow(list);
    }

    private void updateTable() {
        resetTable();
        String memberNameToSearch;
        memberNameToSearch = membersMenu.getSearchTextField().getText();

        if (memberNameToSearch.isEmpty()) {
            loadMembersToTable();
        } else {
            searchMember(memberNameToSearch);
        }
    }

    private void searchMember(String memberNameToSearch) {
        int foundMembersNumber = 0;

        for (int listIndex = 0; listIndex < memberList.size(); listIndex++) {

            Member foundMember = memberList.get(listIndex);
            String foundMemberName = foundMember.getName().toLowerCase();
            memberNameToSearch = memberNameToSearch.toLowerCase();

            if ((foundMemberName).contains(memberNameToSearch)) {
                foundMembersNumber++;
                showSearchResult(foundMember, listIndex);
            }
        }
        showMembersFoundMessage(foundMembersNumber);
    }

    private void showSearchResult(Member member, int listIndex) {
        ArrayList memberListData = createMemberListData(member, listIndex);
        addRow(memberListData);
    }

    private void showMembersFoundMessage(int foundMembers) {
        String title = "Miembros encontrados";
        String message = getMembersFoundMessage(foundMembers);
        notifier.showSuccessMessage(title, message);
    }

    private String getMembersFoundMessage(int foundMembersNumber) {

        if (foundMembersNumber == 1) {
            return "Se ha encontrado 1 miembro";

        } else if (foundMembersNumber > 1) {
            return "Se han encontrado " + foundMembersNumber + " miembros";

        } else {
            return "No se encontraron miembros";

        }
    }

    private void confirmDelete() {
        String messageConfirm = "¿Estas seguro que deseas eliminar a este miembro?";
        int confirmDialog = notifier.showConfirmDialog(messageConfirm);

        if (confirmDialog == notifier.getYES_OPTION()) {
            deleteMemberToTable();

            String title = "Eliminado";
            String message = "Se ha eliminado con exito";
            notifier.showSuccessMessage(title, message);
        }

    }

    private void deleteMemberToTable() {

        int numberRowSelected = membersMenu.getMembersTable().getSelectedRow();
        int columnId = 0;
        int rowId = Integer.parseInt((String) membersMenu.getMembersTable().getValueAt(numberRowSelected, columnId));

        boolean isValidIndex = rowId >= 0;

        if (isValidIndex) {
            deleteMember(rowId);
        }
        loadMembersToTable();
    }

    private void deleteMember(int rowId) {

        membersManager.deleteMember(memberList.get(rowId));
        updateMemberList();
    }

    private void openRegisterWindow() {
        memberRegistrationController.openWindow();
    }

    private void initTable() {
        String[] headers = {"", "Name", "Cellphone", "Phone", "Membership"};
        tableModel = new TableModel(headers);
        membersMenu.getMembersTable().setModel(tableModel);
        membersMenu.getMembersTable().getTableHeader().setReorderingAllowed(false);
    }

    private void setEvents() {
        membersMenu.getAddButton().addActionListener(actionEvent -> openRegisterWindow());
        membersMenu.getSearchButton().addActionListener(actionEvent -> updateTable());
        membersMenu.getDeleteButton().addActionListener(actionEvent -> confirmDelete());
    }

}
