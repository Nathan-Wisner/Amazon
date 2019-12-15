import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static com.sun.org.apache.xpath.internal.XPath.MATCH;


public class Parser {

    private int id;
    private String ASIN;
    private String title;
    private String group;
    private String salesrank;
    private ArrayList<String> similar;
    private ArrayList<String> categories;
    Reviews reviews;
    public ArrayList<AmazonItems> amazonItems = new ArrayList<>();

    public void getLines() {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("C:\\Users\\wisne\\Documents\\School\\415\\amazon-meta.txt"), StandardCharsets.UTF_8)) {
            for (String line = null; (line = br.readLine()) != null; ) {
                String[] items = line.split(":");
                parseString(items, br);
            }
            System.out.println(amazonItems.size());
            System.out.println(amazonItems.get(10000).title);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void parseString(String [] stringArray, BufferedReader bufferedReader){
        for (int i = 0; i < stringArray.length; i++){
            stringArray[i] = stringArray[i].trim();
            if (stringArray[i].equals("Id") && tryParse(stringArray[i + 1])){
                id = Integer.parseInt(stringArray[++i].trim());
            }

            else if (stringArray[i].equals("ASIN")){
                ASIN = stringArray[i].trim();
            }

            else if(stringArray[i].equals("title")){
                title = stringArray[i].trim();

            }

            else if(stringArray[i].equals("group")){
                group = stringArray[i].trim();

            }

            else  if(stringArray[i].equals("salesrank")){
                salesrank = stringArray[i].trim();

            }

            else if(stringArray[i].equals("similar")){
                similar = parseSimilar(stringArray, i);
            }

            else if(stringArray[i].equals("categories")){
                categories = parseCategories(stringArray, i, bufferedReader);
            }

            else if (stringArray[i].equals("reviews")){
                reviews = parseReviews();
                amazonItems.add(new AmazonItems(id, ASIN, title, group, salesrank, similar, categories, reviews));
            }
        }

    }

    private ArrayList<String> parseCategories(String[] stringArray, int index, BufferedReader bufferedReader) {
        ArrayList<String> returnList = new ArrayList<>();
        for (int i = index; i < stringArray.length; i++){
            if(stringArray[i].length() > 1){
                returnList.add(stringArray[i]);
            }
        }
        return returnList;
    }

    private Reviews parseReviews() {
        return null;
    }

    private ArrayList<String> parseSimilar(String [] stringArray, int index) {
        ArrayList<String> returnList = new ArrayList<>();
        String [] categoryArray = stringArray[index + 1].split(" ");
        for (int i = 0; i < categoryArray.length; i++){
            if(categoryArray[i].length() > 1){
                returnList.add(categoryArray[i]);
                System.out.println(categoryArray[i]);
            }
        }
        return returnList;
    }

    private boolean tryParse(String string){
        try{
            Integer.parseInt(string);
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }

    private void addItemsToGraph(){
        for (AmazonItems item: amazonItems) {
        }
    }

    public Parser() throws IOException {
    }
}
