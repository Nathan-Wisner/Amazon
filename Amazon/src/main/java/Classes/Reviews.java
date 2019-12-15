package Classes;

public class Reviews {

    public String date = null;
    public String customer = null;
    int rating = -1;
    int votes = -1;
    int helpful = -1;

    public Reviews(){ }

    public Reviews(String date, String customer, int rating, int votes, int helpful) {
        this.date = date;
        this.customer = customer;
        this.rating = rating;
        this.votes = votes;
        this.helpful = helpful;
    }

}
