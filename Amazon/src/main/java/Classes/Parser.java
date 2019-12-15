package Classes;

import Repository.GraphHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;


public class Parser {

    private int id;
    private String ASIN;
    private String title;
    private String group;
    private String salesrank;
    private ArrayList<String> similar;
    private ArrayList<String> categories;
    ReviewContainer reviews = new ReviewContainer();
    public ArrayList<AmazonItems> amazonItems = new ArrayList<>();
    public HashMap<String,ArrayList<String>> customerList = new HashMap<>();
    public GraphHandler graphHandler = new GraphHandler();


    // Reads the file from the specified path into the parseString function
    public void getLines() {
        int i = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get("C:\\Users\\wisne\\Documents\\School\\415\\amazon-meta.txt"), StandardCharsets.UTF_8)) {
            for (String line = null; (line = br.readLine()) != null; ) {
                String[] items = line.split(":");
                parseString(items, br);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Very basic parser to take every item and store it into each variable
    public void parseString(String [] stringArray, BufferedReader bufferedReader) throws IOException {
        for (int i = 0; i < stringArray.length; i++){
            stringArray[i] = stringArray[i].trim();
            if (stringArray[i].equals("Id") && tryParse(stringArray[i + 1])){
                id = Integer.parseInt(stringArray[++i].trim());
            }

            else if (stringArray[i].equals("ASIN")){
                ASIN = stringArray[++i].trim();
            }

            else if(stringArray[i].equals("title")){
                title = stringArray[++i].trim();

            }

            else if(stringArray[i].equals("group")){
                group = stringArray[++i].trim();

            }

            else  if(stringArray[i].equals("salesrank")){
                salesrank = stringArray[++i].trim();

            }

            else if(stringArray[i].equals("similar")){
                similar = parseSimilar(stringArray, i);
            }

            else if(stringArray[i].equals("categories")){
                categories = parseCategories(stringArray, i, bufferedReader);
            }

            else if (stringArray[i].equals("reviews")){

                // This is the last item that is parsed so we can create an item and push it
                reviews = parseReviews(stringArray, i, bufferedReader);
                AmazonItems item = new AmazonItems(id, ASIN, title, group, salesrank, similar, categories, reviews);
                reviews = new ReviewContainer();
                graphHandler.pushItem(item);
                amazonItems.add(item);
            }
        }

    }

    // Used to parse each category
    private ArrayList<String> parseCategories(String[] stringArray, int index, BufferedReader bufferedReader) {
        ArrayList<String> returnList = new ArrayList<>();
        for (int i = index; i < stringArray.length; i++){
            if(stringArray[i].length() > 1){
                returnList.add(stringArray[i]);
            }
        }
        return returnList;
    }

    // Parses the data about the reviews and then loops to parse every reviews individual data.
    private ReviewContainer parseReviews(String [] stringArray, int index, BufferedReader bufferedReader) throws IOException {
        Reviews currReview = new Reviews();
        for (int i = index + 1; i < stringArray.length; i++) {
            if (stringArray[i].equals(" total")){
                String [] tempArr = stringArray[++i].split(" ");
                this.reviews.downloaded = Integer.parseInt(tempArr[1].trim());
            }
            else if(stringArray[i].contains(" avg rating")){
                String [] tempArr = stringArray[++i].split(" ");
                this.reviews.avg_rating = Double.parseDouble(tempArr[1].trim());
                String line = bufferedReader.readLine().trim();
                line = line.replaceAll(" +", " ");
                String [] arr = line.split(" ");

                //Loop for adding every specific detail about each review
                while(!line.trim().contains("Id:")) {
                    for (int j = 0; j < arr.length; j++) {

                        if(isValidDate(arr[j])){
                            currReview.date = arr[j];
                        }
                        else if (arr[j].equals("cutomer:")){
                            currReview.customer = arr[++j];
                        }
                        else if (arr[j].equals("rating:")){
                            currReview.rating = Integer.parseInt(arr[++j]);
                        }
                        else if(arr[j].equals("votes:")){
                            currReview.votes = Integer.parseInt(arr[++j]);
                        }
                        else if (arr[j].equals("helpful:")){
                            currReview.helpful = Integer.parseInt(arr[++j]);
                        }
                    }

                    this.reviews.reviews.add(currReview);
                    line = bufferedReader.readLine().trim();
                    line = line.replaceAll(" +", " ");
                    arr = line.split(" ");
                    currReview = new Reviews();
                }
                return this.reviews;
            }
        }
        return null;
    }

    // Check to see if the date passed in is valid
    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    // Parse to get every related ASIN with that item
    private ArrayList<String> parseSimilar(String [] stringArray, int index) {
        ArrayList<String> returnList = new ArrayList<>();
        String [] categoryArray = stringArray[index + 1].split(" ");
        for (int i = 0; i < categoryArray.length; i++){
            if(categoryArray[i].length() > 1){
                returnList.add(categoryArray[i]);
            }
        }
        return returnList;
    }

    // See if the string can be parsed into an int
    private boolean tryParse(String string){
        try{
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    // Constructor
    public Parser() {
    }

}
