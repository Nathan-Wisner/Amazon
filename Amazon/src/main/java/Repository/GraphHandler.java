package Repository;


import Classes.AmazonItems;
import Classes.Reviews;
import org.neo4j.driver.*;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphHandler {

    Driver driver;
    Session session;

    // Create a driver and login using out username and password
    public GraphHandler(){
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "no"));
        createGraph();
    }

    // See if the driver is open for creating a session
    public void createGraph() {
        try (Session session = driver.session()) {
            createSession();
        }
    }

    // Push the item and all its variables to our Neo4J session / database
    public void pushItem(AmazonItems amazonItem){
        session.run("CREATE (test: test" + amazonItem.getId() + " {reviews: \"" + amazonItem.getReviewText() +"\",id: \"" + amazonItem.getId() + "\", asin: \"" + amazonItem.getASIN() + "\", title: \"" +amazonItem.getTitle().replaceAll("[^a-zA-Z0-9]", "")
                + "\", group: \"" + amazonItem.getGroup().trim()+"\", salesrank: \"" +amazonItem.getSalesrank().trim() + "\", similar: \"" + String.join(", ",  amazonItem.getSimilar()) + "\" }) RETURN "
                + amazonItem.getId());

        createCustomer(amazonItem);
    }

    // For every customer we found, add it to the database
    public void createCustomer(AmazonItems amazonItems){
        for (Reviews review: amazonItems.getReviews().reviews) {
            session.run("MATCH (customer {customer: \"" + review.customer + "\"}) CREATE ({customer:\"" + review.customer + "\", asin:\"" + amazonItems.getASIN() +"\"}) RETURN " + amazonItems.getId());
        }

    }

    // Create a Neo4J session
    public void createSession(){
        session = driver.session();
    }

    // Close a session on Neo4J
    public void closeSession(){
        session.close();
        driver.close();
    }
}
