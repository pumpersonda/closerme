package closermeapp.Bussiness.Reports;

import closermeapp.Bussiness.ChargesRegister.EnterpriseChargesRegister;
import closermeapp.Bussiness.ChargesRegister.EventChargeRegister;
import closermeapp.Bussiness.ChargesRegister.MemberChargesRegister;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by André on 12/12/2015.
 */
public class ExcelFileHandle {
    private static final ExcelFileHandle excelFileHandle = new ExcelFileHandle();


    private ExcelFileHandle() {
    }

    public static ExcelFileHandle GetInstance() {
        return excelFileHandle;
    }

    public void saveActivityLog(
            List<MemberChargesRegister> memberList,
            List<EnterpriseChargesRegister> enterpriseList,
            List<EventChargeRegister> eventList,
            double totalGain,
            String date
    ) {

        try {

            Workbook workbook;
            File file = new File("src/formato.xls");
            workbook = Workbook.getWorkbook(file);

            WritableWorkbook writableWorkbook;
            file = new File("src/Nuevo_Reporte_" + date + ".xls");
            writableWorkbook = Workbook.createWorkbook(file, workbook);

            int numberSheet = 0;
            WritableSheet sheet = writableWorkbook.getSheet(numberSheet);

            saveDate(sheet, date);
            saveTotalGain(sheet, totalGain);

            int totalMembers = memberList.size();
            saveTotalMember(sheet, totalMembers);

            int totalEnterprises = enterpriseList.size();
            saveTotalEnterprise(sheet, totalEnterprises);

            int totalEvent = eventList.size();
            saveTotalEvent( sheet, totalEvent );

            int totalEmployees = 0;
            boolean isValidList = enterpriseList.size() > 0;
            if (isValidList) {
                int positionList = 0;
                totalEmployees = enterpriseList.get( positionList ).getEmployeesNumber();
            }

            saveTotalEmployee(sheet, totalEmployees);

            saveMemberList( sheet, memberList );
            saveEnterpriseList( sheet, enterpriseList );
            saveEventList( sheet, eventList );

            writableWorkbook.write();
            writableWorkbook.close();


        } catch (IOException | WriteException | BiffException exception) {
            exception.printStackTrace();
        }


    }


    private void saveDate(WritableSheet sheet, String date) throws WriteException {
        int column = 1;
        int row = 2;
        Label label = new Label(column, row, date);
        sheet.addCell(label);
    }

    private void saveTotalGain(WritableSheet sheet, double totalGain) throws WriteException {
        int column = 6;
        int row = 2;
        Label label = new Label(column, row, String.valueOf(totalGain));
        sheet.addCell(label);
    }

    private void saveTotalMember(WritableSheet sheet, int totalMembers) throws WriteException {
        int column = 2;
        int row = 4;
        Label label = new Label(column, row, String.valueOf(totalMembers));
        sheet.addCell(label);
    }

    private void saveTotalEnterprise(WritableSheet sheet, int totalEnterprises) throws WriteException {
        int column = 2;
        int row = 5;
        Label label = new Label(column, row, String.valueOf(totalEnterprises));
        sheet.addCell(label);
    }

    private void saveTotalEmployee(WritableSheet sheet, int totalEmployees) throws WriteException {
        int column = 2;
        int row = 6;
        Label label = new Label(column, row, String.valueOf(totalEmployees));
        sheet.addCell(label);
    }

    private void saveTotalEvent(WritableSheet sheet, int totalEvent) throws WriteException {
        int column = 2;
        int row = 7;
        Label label = new Label( column, row, String.valueOf( totalEvent ) );
        sheet.addCell( label );
    }

    private void saveMemberList(
            WritableSheet sheet,
            List<MemberChargesRegister> memberList
    ) throws WriteException {

            int column = 0;
            int row = 11;

            for (int index = 0; index < memberList.size(); index++) {
                Label label = new Label( column, row, memberList.get( index ).toString() );
                sheet.addCell( label );
                row++;
            }

    }

    private void saveEnterpriseList(
            WritableSheet sheet,
            List<EnterpriseChargesRegister> enterpriseList
    ) throws WriteException {

            int column = 12;
            int row = 11;

            for (int index = 0; index < enterpriseList.size(); index++) {
                Label label = new Label( column, row, enterpriseList.get( index ).toString() );
                sheet.addCell( label );
                row++;
            }

    }

    private void saveEventList(
            WritableSheet sheet,
            List<EventChargeRegister> eventList
    ) throws WriteException {


        int column = 25;
        int row = 11;

        for (int index = 0; index < eventList.size(); index++) {
            Label label = new Label( column, row, eventList.get( index ).toString() );
            sheet.addCell( label );
            row++;
        }

    }


}
