package closermeapp.Bussiness.Reports;

import closermeapp.Bussiness.ChargesRegister.EnterpriseChargesRegister;
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
            List<MemberChargesRegister> memberListOfMonth,
            List<EnterpriseChargesRegister> enterpriseListOfMonth,
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

            int totalMembers = memberListOfMonth.size();
            saveTotalMember(sheet, totalMembers);

            int totalEnterprises = enterpriseListOfMonth.size();
            saveTotalEnterprise(sheet, totalEnterprises);

            int totalEmployees = 0;
            boolean isValidList = enterpriseListOfMonth.size() > 0;
            if (isValidList) {
                int positionList = 0;
                totalEmployees = enterpriseListOfMonth.get(positionList).getEmployeesNumber();

            }
            saveTotalEmployee(sheet, totalEmployees);

            saveMemberList(sheet, memberListOfMonth);
            saveEnterpriseList(sheet, enterpriseListOfMonth);

            writableWorkbook.write();
            writableWorkbook.close();


        } catch (IOException | WriteException | BiffException exception) {
            exception.printStackTrace();
        }


    }

    public void generateTodayReport(
            List<MemberChargesRegister> memberListOfMonth,
            List<EnterpriseChargesRegister> enterpriseListOfMonth,
            double totalGain,
            String date
    ) {

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

    private void saveMemberList(
            WritableSheet sheet,
            List<MemberChargesRegister> memberListOfMonth
    ) throws WriteException {
        int column = 0;
        int row = 11;

        for (int i = 0; i < memberListOfMonth.size(); i++) {
            Label label = new Label(column, row, memberListOfMonth.get(i).toString());
            sheet.addCell(label);
            row++;
        }
    }

    private void saveEnterpriseList(
            WritableSheet sheet,
            List<EnterpriseChargesRegister> enterpriseListOfMonth
    ) throws WriteException {
        int column = 12;
        int row = 11;

        for (int i = 0; i < enterpriseListOfMonth.size(); i++) {
            Label label = new Label(column, row, enterpriseListOfMonth.get(i).toString());
            sheet.addCell(label);
            row++;
        }
    }

    private void saveNumericData(
            int column,
            int row,
            WritableSheet sheet,
            int numericData
    ) throws WriteException {

        Label label = new Label(column, row, String.valueOf(numericData));
        sheet.addCell(label);
    }


}
