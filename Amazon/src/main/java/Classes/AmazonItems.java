package Classes;

import scala.math.Ordering;

import java.util.ArrayList;

public class AmazonItems {

    // Container for all items inside the amazon description
    public int getId() {
        return id;
    }

    public String getASIN() {
        return ASIN;
    }

    public String getTitle() {
        return title;
    }

    public String getGroup() {
        return group;
    }

    public String getSalesrank() {
        return salesrank;
    }

    public ArrayList<String> getSimilar() {
        return similar;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public ReviewContainer getReviews() {
        return reviews;
    }

    // Take the text from a review and return it in a writeable form
    public String getReviewText(){
        StringBuilder sb = new StringBuilder();
        for(Reviews view: reviews.reviews){
            sb.append(view.customer);
            sb.append(',');
        }
        sb.delete(sb.length(), sb.length());
        return sb.toString();
    }

    private int id;
    private String ASIN;
    public String title;
    private String group;
    private String salesrank;
    private ArrayList<String> similar;
    private ArrayList<String> categories;
    ReviewContainer reviews;

    // Constructor
    public AmazonItems(int id, String ASIN, String title, String group, String salesRank, ArrayList<String> similar, ArrayList<String> categories, ReviewContainer reviews){
        this.id = id;
        this.ASIN = ASIN;
        this.title = title;
        this.group = group;
        this.similar = similar;
        this.categories = categories;
        this.reviews = reviews;
        this.salesrank = salesRank;
    }
}
