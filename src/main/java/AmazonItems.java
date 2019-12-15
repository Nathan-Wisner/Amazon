import java.awt.*;
import java.util.ArrayList;

public class AmazonItems {
    private int id;
    private String ASIN;
    public String title;
    private String group;
    private String salesrank;
    private ArrayList<String> similar;
    private ArrayList<String> categories;
    Reviews reviews;

    public AmazonItems(int id, String ASIN, String title, String group, String salesRank, ArrayList<String> similar, ArrayList<String> categories, Reviews reviews){
        this.id = id;
        this.ASIN = ASIN;
        this.title = title;
        this.group = group;
        this.similar = similar;
        this.categories = categories;
        this.reviews = reviews;
    }
}
