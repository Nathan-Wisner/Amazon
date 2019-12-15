package Classes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ReviewContainer{

    // Variables to denote all the review statistics along with an array list that contains every review
    int downloaded = -1;
    double avg_rating = -1;
    public ArrayList<Reviews> reviews = new ArrayList<>();

        public ReviewContainer(){
        }

        public ReviewContainer(int downloaded, double avg_rating, ArrayList<Reviews> reviews) {
            this.downloaded = downloaded;
            this.avg_rating = avg_rating;
            this.reviews = reviews;
        }

}

