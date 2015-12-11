package closermeapp.Presentation.Controllers;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.MemberManager.MembersManager;
import closermeapp.Presentation.Util.Notifier;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.VisitorManagement.MembersMenuView;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

import static java.lang.String.valueOf;


public class MembersMenuController extends AbstractViewController {
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

        initializeView();
    }

    @Override
    public void openWindow() {
        membersMenuView.setVisible(true);
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

    private void deleteMember() {
        JTable membersTable = membersMenuView.getMembersTable();
        int numberRowSelected = membersTable.getSelectedRow();
        boolean validRow = numberRowSelected >= 0;

        if (validRow && isDeletionConfirmed()) {

            final int columnId = 0;
            String tablePosition = (String) membersTable.getValueAt(numberRowSelected, columnId);
            int memberListPosition = Integer.parseInt(tablePosition) - 1;

            Member member = memberList.get(memberListPosition);
            membersManager.deleteMember(member);
            memberList.remove(memberListPosition);

            String title = "Miembro borrado";
            String message = "Se ha borrado con exito";
            notifier.showSuccessMessage(title, message);
            loadMembersToTable();
        }
    }


    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminar a este miembro?";
        int optionSelected = notifier.showConfirmDialog(messageConfirm);
        return optionSelected == notifier.getYES_OPTION();
    }
    private void openRegisterWindow() {
        memberRegistrationController.openWindow();
    }

    private void initializeTable() {
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

    @Override
    protected void initializeView() {
        configureWindow(membersMenuView);
        initializeTable();
        setEvents();
        updateMemberList();
        loadMembersToTable();
    }

    @Override
    protected void setEvents() {
        membersMenuView.getAddButton().addActionListener(actionEvent -> openRegisterWindow());
        membersMenuView.getSearchButton().addActionListener(actionEvent -> updateTable());
        membersMenuView.getDeleteButton().addActionListener(actionEvent -> deleteMember());
    }

}
