package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.MemberManager.MembersManager;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.VisitorManagement.MembersMenuView;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;


public class MembersMenuController {
    private MembersMenuView membersMenuView;
    private MemberRegistrationController memberRegistrationController;
    private MembersManager membersManager;
    private Notifier notifier;
    private ArrayList<Member> memberList;
    private TableModel tableModel;

    public MembersMenuController() {
        membersMenuView = new MembersMenuView();
        memberRegistrationController = new MemberRegistrationController(this);
        membersManager = MembersManager.getMembersManager();
        notifier = new Notifier();

        membersMenuView.setLocationRelativeTo(null);
        membersMenuView.setResizable(false);
        membersMenuView.pack();
        membersMenuView.setLocationRelativeTo(null);
        membersMenuView.setVisible(true);

        initTable();
        setEvents();
        updateMemberList();
        loadMembersToTable();
    }

    public void addMemberToTable(Member member) {
        memberList.add(member);
        int lasElementOfList = memberList.size() - 1;

        ArrayList memberDataList = createMemberListData(member, lasElementOfList);
        addRow(memberDataList);
    }

    private void updateMemberList() {
        memberList = membersManager.getMemberList();
    }

    private void loadMembersToTable() {
        resetTable();
        for (int listIndex = 0; listIndex < memberList.size(); listIndex++) {
            Member member = memberList.get(listIndex);
            ArrayList memberDataList = createMemberListData(member, listIndex);
            addRow(memberDataList);
        }
    }

    private void resetTable() {
        tableModel.resetTable();
    }

    private ArrayList<String> createMemberListData(Member member, int listIndex) {
        ArrayList<String> memberDataList = new ArrayList();

        int tablePosition = listIndex + 1;
        memberDataList.add(valueOf(tablePosition));
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
        memberNameToSearch = membersMenuView.getSearchTextField().getText();

        if (memberNameToSearch.isEmpty()) {
            loadMembersToTable();
        } else {
            searchMember(memberNameToSearch);
        }
    }
    //solo buscar o devolver el miembro por el void

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

    private void confirmDelete(Member member, int tablePosition) {
        String messageConfirm = "¿Estas seguro que deseas eliminar a este miembro?";
        int confirmDialog = notifier.showConfirmDialog(messageConfirm);

        if (confirmDialog == notifier.getYES_OPTION()) {

            deleteMember(member, tablePosition);

            String title = "Eliminado";
            String message = "Se ha eliminado con exito";
            notifier.showSuccessMessage(title, message);

        }

    }

    private void deleteMemberToTable() {
        JTable membersTable = membersMenuView.getMembersTable();

        int numberRowSelected = membersTable.getSelectedRow();
        boolean isValidRow = numberRowSelected >= 0;

        if (isValidRow) {
            final int columnId = 0;
            int tablePosition = parseInt((String) membersTable.getValueAt(numberRowSelected, columnId)) - 1;

            Member member = memberList.get(tablePosition);
            confirmDelete(member, tablePosition);
            loadMembersToTable();
        }
    }


    private void deleteMember(Member member, int tablePosition) {
        membersManager.deleteMember(member);
        memberList.remove(tablePosition);
    }

    private void openRegisterWindow() {
        memberRegistrationController.openWindow();
    }

    private void initTable() {
        String[] headers = {"", "Name", "Cellphone", "Phone", "Membership"};
        tableModel = new TableModel(headers);
        JTable membersTable = membersMenuView.getMembersTable();
        membersTable.setModel(tableModel);
        membersTable.getTableHeader().setReorderingAllowed(false);

        TableColumnModel columnModel = membersTable.getColumnModel();
        int firstColumn = 0;
        int sizeColumn = 1;
        columnModel.getColumn(firstColumn).setPreferredWidth(sizeColumn);
    }

    private void setEvents() {
        membersMenuView.getAddButton().addActionListener(actionEvent -> openRegisterWindow());
        membersMenuView.getSearchButton().addActionListener(actionEvent -> updateTable());
        membersMenuView.getDeleteButton().addActionListener(actionEvent -> deleteMemberToTable());
    }

}
