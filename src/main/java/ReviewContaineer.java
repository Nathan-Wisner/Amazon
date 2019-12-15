public class ReviewContaineer {
    public class ReviewContainer {
        int downloaded = -1;
        int avg_rating = -1;
        Reviews[] reviews = null;

        public ReviewContainer(int downloaded, int avg_rating, Reviews[] reviews) {
            this.downloaded = downloaded;
            this.avg_rating = avg_rating;
            this.reviews = reviews;
        }
    }
}
