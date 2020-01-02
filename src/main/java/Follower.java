import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Follower {
    int i = 0;
    String id;
    ArrayList<Follower> f = new ArrayList<Follower>( );

    Follower() {
    }


    Follower(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void listFollower(String id) throws IOException {
        Follower ff = new Follower(id);
        f.add(ff);
        i++;
    }

    public void FollowerList() {

        for (Follower flist : f) {
            System.out.println(flist.getId( ));
        }
        try {
            XSSFWorkbook workbook = new XSSFWorkbook( );

            XSSFSheet sheet = workbook.createSheet("follower list");// creating a blank sheet
            int rownum = 0;
            for (Follower listf : f) {
                Row row = sheet.createRow(rownum++);
                createList(listf, row);

            }
            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\HALIMAH\\IdeaProjects\\255227-STIW3054-A191-A2\\excel\\List of Follower.xlsx")); // file name with path
            workbook.write(out);
            out.close( );

        } catch (Exception e) {
            e.printStackTrace( );
        }
    }

    public static void createList(Follower f, Row row) // creating cells for each row
    {
        Cell cell = row.createCell(0);
        cell.setCellValue(f.getId( ));
    }

    public void FollowerL() {
        for (Follower flist : f) {
            System.out.println(flist.getId( ));
        }
    }

}