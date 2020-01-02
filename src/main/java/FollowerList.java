import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class FollowerList {
    public static String href = "https://github.com/zhamri?tab=followers";
    public static String href2;
    public static int n=0;
    public static int count=0;
    public static String id;
    //public static String[] list;
    public static Follower follower = new Follower();



    public static void main(String[] args) throws IOException{
        do {
            href = link(href);
        }while (href != null);
        follower.FollowerList();
}

 static String link(String href) throws IOException {
     Element html = Jsoup.connect(FollowerList.href).get().body().getElementById("js-pjax-container");
     Elements div = html.select("div.position-relative");
     Elements list = div.select("a");
     for (Element link : list) {
         href = link.attr("href");
         if(href.contains("%") || (href.contains("?")) || (href.contains("/pricing")))
            link.remove();
        else if (href.equals(href2)) link.remove();
        else {
             n++;
             id = href.replace("/","");
             //System.out.println(n+" " + id);
            follower.listFollower(id);
            href2=href;
        }
     }
     if (href.contains("https://github.com/zhamri?before=")) href = null;
     if (href == null){ return href;}
     else {return href;}
 }

}