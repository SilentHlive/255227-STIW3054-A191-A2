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

    public String getId() {
        return id;
    }

    public void listFollower(int n,String id, int countR,int countFg, int countFr, int countS) throws IOException {
        FollowerDetails ff = new FollowerDetails(n,id,countR,countFr,countS,countFg);
        f.add(ff);
        i++;
    }

    public void FollowerList() {

        for (FollowerDetails flist : f) {
            System.out.println(flist.getId( ));
        }
        try {
            XSSFWorkbook workbook = new XSSFWorkbook( );

            XSSFSheet sheet = workbook.createSheet("follower details list");// creating a blank sheet
            int rownum = 0;
            for (FollowerDetails listf : f) {
                Row row = sheet.createRow(rownum++);
                createList(listf, row);

            }
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\HALIMAH\\IdeaProjects\\255227-STIW3054-A191-A2\\Report.xlsx")); // file name with path
            workbook.write(out);
            out.close( );

        } catch (Exception e) {
            e.printStackTrace( );
        }
    }

    public static void createList(FollowerDetails f, Row row) // creating cells for each row
    {
        Cell cell = row.createCell(0);
        cell.setCellValue(f.getId( ));
    }

    public void FollowerL() {
        for (FollowerDetails flist : f) {
            System.out.println(flist.getId( ));
        }
    }

}