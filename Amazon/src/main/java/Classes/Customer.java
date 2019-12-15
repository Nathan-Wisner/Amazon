package Classes;

import java.util.ArrayList;

public class Customer {
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

    public String id;
    public ArrayList<String> itemsPurchased = new ArrayList<>();


}
