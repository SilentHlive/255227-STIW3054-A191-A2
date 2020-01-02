import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Main extends Thread{

    public static String fid;
    public static int x=1,countR, countFg, countFr, countS;
    public static FollowerDetails follower = new FollowerDetails();
    public static void main(String[] args) {
        Main ts = new Main();
        Thread t = new Thread(ts);
        System.out.printf("| %-5s| %-40s| %-25s| %-25s| %-25s| %-25s|\n", "No", "Login ID", "Number of Repositories", "Number of Following","Number of Followers","Number of Stars");
        System.out.printf("| %-5s| %-40s| %-25s| %-25s| %-25s| %-25s|\n", "-----", "----------------------------------", "----------------------", "---------------------", "----------------------", "-----------------------");
        t.start();

    }

    public void run() {
        synchronized (this) {
            try
            {
                File file = new File("C:\\Users\\HALIMAH\\IdeaProjects\\255227-STIW3054-A191-A2\\excel\\List of Follower.xlsx");   //creating a new file instance
                FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
                //creating Workbook instance that refers to .xlsx file
                XSSFWorkbook wb = new XSSFWorkbook(fis);
                XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object
                Iterator<Row> itr = sheet.iterator();    //iterating over excel file
                while (itr.hasNext())
                {
                    Row row = itr.next();
                    Iterator<Cell> cellIterator = row.cellIterator();   //iterating over each column
                    while (cellIterator.hasNext())
                    {
                        Cell cell = cellIterator.next();
                        //System.out.print(cell.getStringCellValue() + "\t");
                        fid = cell.getStringCellValue();
                       // System.out.println(fid);
                        // System.out.println("Repositories");
                        countR = readRepo(fid);
                        //System.out.println("Following");
                       countFg = readFollowing(fid);
                        // System.out.println("Follower");
                       countFr = readFollower(fid);
                       //System.out.println("Stars");
                       countS = readStars(fid);

                    }
                    System.out.printf("| %-5s| %-40s| %-25s| %-25s| %-25s| %-25s|\n", x, fid, countR, countFg,countFr,countS);
                    follower.listFollower(x, fid, countR, countFg,countFr,countS);
                    x++;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        follower.FollowerList();
    }

    static int readFollower(String fid) throws IOException, InterruptedException {
        //System.out.println(fid);
        String href = "https://github.com/"+fid+"?tab=followers";
        String href2="";
        String l = href;
        String id;
        int m =0;
        int count = 0;
        int n=0;
        do {
            Element html = Jsoup.connect(l).get( ).body( ).getElementById("js-pjax-container");
            Elements div = html.select("div.position-relative");
            Elements list = div.select("a");
            for (Element link : list) {
                Thread.sleep(100);
                href = link.attr("href");
                if (href.contains("%") || (href.contains("?")) || (href.contains("/pricing")) )
                    link.remove( );
                else if (href.equals(href2)) link.remove( );
                else {
                    id = href.replace("/", "");
                    if (id.equals("https:guides.github.comactivitiessocialize")||(id.contains("https:github."))) {m=0; //System.out.println(m);
                        }
                    else {
                        n++;
                             }
                    href2 = href;
                }

                if (href.contains("https://github.com/" + fid+ "?before=")) { l =href;}
                if (href.contains("https://github.com/" + fid+ "?after=")) l = href;
                else { l =null; }
            }}
        while (l != null) ;
        count = m +(n);
        //System.out.println(count);
        return count;
    }

    static int readFollowing(String fid) throws IOException, InterruptedException {
        String href = "https://github.com/"+fid+"?tab=following";
        String href2="";
        String l = href;
        String id;
        int m =0;
        int count = 0;
        int n=0;
        do {
            Element html = Jsoup.connect(l).get( ).body( ).getElementById("js-pjax-container");
            Elements div = html.select("div.position-relative");
            Elements list = div.select("a");
            for (Element link : list) {
                Thread.sleep(100);
                href = link.attr("href");
                if (href.contains("%") || (href.contains("?")) || (href.contains("/pricing")) )
                    link.remove( );
                else if (href.equals(href2)) link.remove( );
                else {
                    id = href.replace("/", "");
                    if (id.equals("https:guides.github.comactivitiessocialize")||(id.contains("https:github."))) {m=0; //System.out.println(m);
                    }
                    else {
                        n++;
                    }
                    href2 = href;
                }

                if (href.contains("https://github.com/" + fid+ "?before=")) { l =null;}
                if (href.contains("https://github.com/" + fid+ "?after=")) l = href;
                else { l =null; }
            }}
        while (l != null) ;
        count = m +(n);
        //System.out.println(count);
        return count;
    }

    static int readRepo(String fid) throws IOException, InterruptedException {
        String href = "https://github.com/"+fid+"?tab=repositories";
        String href2="";
        String l = href;
        int count = 0;
        int n=0;
        do {
            Element html = Jsoup.connect(l).get().body().getElementById("user-repositories-list");
            Elements list = html.select("ul");
            Elements h = list.select("h3");
            Elements hh= html.getElementsByClass("blankslate mt-5").select("h4");
            Elements ll = h.select("a");
            if(!hh.isEmpty()){  n=0; l=null; break; }
            else{
            for (Element link : ll) {
                Thread.sleep(100);
                href = link.attr("href");
                if(href.contains("%") || (href.contains("?")) || (href.contains("/pricing")))
                    link.remove();
                else if (href.equals(href2)) link.remove();
                else if (href.isEmpty()) n=0;
                else {
                    n++;
                    href2=href;
                }
            }}
            Elements next = html.select("a");
            for (Element link : next){
                href = link.attr("href");
                }
            if (href.contains("https://github.com/"+fid+"?before=")) l = null;
            if (href.contains("https://github.com/"+fid+"?after=")){ l=href;
            //System.out.println(l);
            }
            else {l =null;}
            }
        while (l != null) ;
        count =n;
        //System.out.println(count);
        return count;
    }

    static int readStars(String fid) throws IOException, InterruptedException {
        String href = "https://github.com/"+fid+"?tab=stars";
        String href2="";
        String l = href;
        int m =0;
        int count = 0;
        int n=0;
        do {
            Element html = Jsoup.connect(l).get( ).body( ).getElementById("js-pjax-container");
            Elements div = html.select("div.position-relative");
            Elements div2 = div.select("div.col-lg-9");
            Elements hh= html.getElementsByClass("blankslate mt-5").select("h4");
            Elements h = div.select("h3");
            Elements list = h.select("a");
            if(!hh.isEmpty()){  n=0; l=null; break; }
            else{
            for (Element link : list) {
                Thread.sleep(100);
                href = link.attr("href");
                if (href.equals(href2)) link.remove( );
                else {
                        n++;
                       // System.out.println(n+ ""+href);
                    href2 = href;
                }
            }}
            Elements next = div.select("a");
            for (Element link : next){
                href = link.attr("href");
            }
            Elements next2 = div2.select("a");
            for (Element link : next2){
                href = link.attr("href");
            }

            if (href.contains("https://github.com/"+fid+"?before=")) l = null;
            if (href.contains("https://github.com/"+fid+"?after=")){ l=href;
                //System.out.println(l);
            }
            else {l =null;}
        }
        while (l != null) ;
        count = m +(n);
        //System.out.println(count);
        return count;
    }

}

