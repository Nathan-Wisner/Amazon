package Classes;

import java.util.ArrayList;

public class Customer {

    // getters and setters for the customer class items
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getItemsPurchased() {
        return itemsPurchased;
    }

    public void setItemsPurchased(ArrayList<String> itemsPurchased) {
        this.itemsPurchased = itemsPurchased;
    }

    // Variables with the id of the customer and the list of items they have purchased
    public String id;
    public ArrayList<String> itemsPurchased = new ArrayList<>();


}
