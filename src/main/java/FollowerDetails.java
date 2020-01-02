import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FollowerDetails {
    int countR = 0;
    int countS =0;
    int countFg =0;
    int countFr =0;
    int n;
    int i = 0;
    String id;
    ArrayList<FollowerDetails> f = new ArrayList<FollowerDetails>( );

    FollowerDetails() {
    }


    FollowerDetails(int n,String id, int countR, int countFr,int countS, int countFg) {
        this.n=n;
        this.id = id;
        this.countR = countR;
        this.countFr = countFr;
        this.countS = countS;
        this.countFg = countFg;
    }

    public int getN() {
        return n;
    }

    public String getId() {
        return id;
    }

    public int getCountR() {
        return countR;
    }

    public int getCountFg() {
        return countFg;
    }

    public int getCountFr() {
        return countFr;
    }

    public int getCountS() {
        return countS;
    }

    public void listFollower(int n,String id, int countR,int countFg, int countFr, int countS) throws IOException {
        FollowerDetails ff = new FollowerDetails(n,id,countR,countFr,countS,countFg);
        f.add(ff);
        i++;
    }

    public void FollowerList() {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook( );

            XSSFSheet sheet = workbook.createSheet("follower details list");// creating a blank sheet

            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            Cell cell1 = row.createCell(1);
            Cell cell2 = row.createCell(2);
            Cell cell3 = row.createCell(3);
            Cell cell4 = row.createCell(4);
            Cell cell5 = row.createCell(5);
            cell.setCellValue("No");
            cell1.setCellValue("Login ID");
            cell2.setCellValue("Number of repositories");
            cell3.setCellValue("Number of Following");
            cell4.setCellValue("Number of Followers");
            cell5.setCellValue("Number of Stars");
            int rownum = 1;
            for (FollowerDetails listf : f) {
                Row row1 = sheet.createRow(rownum++);
                createList(listf, row1);

            }

            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\HALIMAH\\IdeaProjects\\255227-STIW3054-A191-A2\\excel\\Report.xlsx")); // file name with path
            for(int j =0; j<7;j++){
                sheet.autoSizeColumn(j);
            }

            workbook.write(out);
            out.close( );

        } catch (Exception e) {
            e.printStackTrace( );
        }
    }

    public static void createList(FollowerDetails f, Row row) // creating cells for each row
    {

        Cell cell = row.createCell(0);
        Cell cell1 = row.createCell(1);
        Cell cell2 = row.createCell(2);
        Cell cell3 = row.createCell(3);
        Cell cell4 = row.createCell(4);
        Cell cell5 = row.createCell(5);

        cell.setCellValue(f.getN( ));
        cell1.setCellValue(f.getId( ));
        cell2.setCellValue(f.getCountR( ));
        cell3.setCellValue(f.getCountFg( ));
        cell4.setCellValue(f.getCountFr( ));
        cell5.setCellValue(f.getCountS( ));
    }

    }

