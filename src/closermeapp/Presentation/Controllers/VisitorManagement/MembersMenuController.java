package closermeapp.Presentation.Controllers.VisitorManagement;

import closermeapp.Bussiness.Entities.Member;
import closermeapp.Bussiness.MemberManager.MembersManager;
import closermeapp.Presentation.Controllers.AbstractViewController;
import closermeapp.Presentation.Util.TableModel;
import closermeapp.Presentation.Views.VisitorManager.MembersMenuView;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.util.ArrayList;

import static java.lang.String.valueOf;


public class MembersMenuController extends AbstractViewController {
    private MembersMenuView membersMenuView;
    private MemberRegistrationController memberRegistrationController;
    private ArrayList<Member> memberList;
    private JTable membersTable;
    private TableModel tableModel;

    public MembersMenuController() {
        setMembersMenuView( new MembersMenuView() );
        setMemberRegistrationController( new MemberRegistrationController( this ) );
        setMembersTable( getMembersMenuView().getMembersTable() );

        initializeView();
    }

    @Override
    public void openWindow() {
        getMembersMenuView().setVisible( true );
    }

    public void addMemberToTable(Member member) {
        getMemberList().add( member );
        int lasElementOfList = getMemberList().size() - 1;

        ArrayList memberDataList = createMemberListData(member, lasElementOfList);
        addRow(memberDataList);
    }

    @Override
    protected void initializeView() {
        configureWindow( getMembersMenuView() );
        initializeTable();
        setEvents();
        updateMemberList();
        loadMembersToTable();
    }

    @Override
    protected void setEvents() {
        getMembersMenuView().getAddButton().addActionListener( actionEvent -> openRegisterWindow() );
        getMembersMenuView().getSearchButton().addActionListener( actionEvent -> updateTable() );
        getMembersMenuView().getDeleteButton().addActionListener( actionEvent -> deleteMember() );
    }

    private void updateMemberList() {
        MembersManager membersManager = MembersManager.GetInstance();
        setMemberList( membersManager.getMemberList() );
    }

    private void loadMembersToTable() {
        resetTable();
        for (int listIndex = 0; listIndex < getMemberList().size(); listIndex++) {
            Member member = getMemberList().get( listIndex );
            ArrayList memberDataList = createMemberListData(member, listIndex);
            addRow(memberDataList);
        }
    }

    private void resetTable() {
        getTableModel().resetTable();
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
        getTableModel().addRow( list );
    }

    private void updateTable() {
        resetTable();
        String memberNameToSearch;
        memberNameToSearch = getMembersMenuView().getSearchTextField().getText();

        if (memberNameToSearch.isEmpty()) {
            loadMembersToTable();
        } else {
            searchMember(memberNameToSearch);
        }
    }

    private void searchMember(String memberNameToSearch) {
        int foundMembersNumber = 0;

        for (int listIndex = 0; listIndex < getMemberList().size(); listIndex++) {

            Member foundMember = getMemberList().get( listIndex );
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
        getNotifier().showSuccessMessage( title, message );
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

        int numberRowSelected = getMembersTable().getSelectedRow();
        boolean validRow = numberRowSelected >= 0;

        if (validRow && isDeletionConfirmed()) {

            final int columnId = 0;
            String tablePosition = (String) getMembersTable().getValueAt( numberRowSelected, columnId );
            int memberListPosition = Integer.parseInt(tablePosition) - 1;

            Member member = getMemberList().get( memberListPosition );
            MembersManager membersManager = MembersManager.GetInstance();
            membersManager.deleteMember(member);
            getMemberList().remove( memberListPosition );

            String title = "Miembro borrado";
            String message = "Se ha borrado con exito";
            getNotifier().showSuccessMessage( title, message );
            loadMembersToTable();
        }
    }


    private boolean isDeletionConfirmed() {
        String messageConfirm = "¿Estas seguro que deseas eliminar a este miembro?";
        int optionSelected = getNotifier().showConfirmDialog( messageConfirm );
        return optionSelected == getNotifier().getYES_OPTION();
    }
    private void openRegisterWindow() {
        getMemberRegistrationController().openWindow();
    }

    private void initializeTable() {
        String[] headers = {"", "Name", "Cellphone", "Phone", "Membership"};
        setTableModel( new TableModel( headers ) );

        getMembersTable().setModel( getTableModel() );
        getMembersTable().getTableHeader().setReorderingAllowed( false );

        TableColumnModel columnModel = getMembersTable().getColumnModel();
        int firstColumn = 0;
        int sizeColumn = 1;
        columnModel.getColumn(firstColumn).setPreferredWidth(sizeColumn);
    }


    public void setMemberList(ArrayList<Member> memberList) {
        this.memberList = memberList;
    }

    public void setMemberRegistrationController(MemberRegistrationController memberRegistrationController) {
        this.memberRegistrationController = memberRegistrationController;
    }

    public void setMembersMenuView(MembersMenuView membersMenuView) {
        this.membersMenuView = membersMenuView;
    }

    public void setMembersTable(JTable membersTable) {
        this.membersTable = membersTable;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
    }

    public ArrayList<Member> getMemberList() {
        return memberList;
    }

    public MemberRegistrationController getMemberRegistrationController() {
        return memberRegistrationController;
    }

    public MembersMenuView getMembersMenuView() {
        return membersMenuView;
    }

    public JTable getMembersTable() {
        return membersTable;
    }

    public TableModel getTableModel() {
        return tableModel;
    }
}
