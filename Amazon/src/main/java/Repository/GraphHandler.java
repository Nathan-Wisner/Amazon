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

    public GraphHandler(){
        driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "no"));
        createGraph();
    }

    public void createGraph() {
        try (Session session = driver.session()) {
            createSession();
        }
    }

    public void pushItem(AmazonItems amazonItem){
        session.run("CREATE (test: test" + amazonItem.getId() + " {reviews: \"" + amazonItem.getReviewText() +"\",id: \"" + amazonItem.getId() + "\", asin: \"" + amazonItem.getASIN() + "\", title: \"" +amazonItem.getTitle().replaceAll("[^a-zA-Z0-9]", "")
                + "\", group: \"" + amazonItem.getGroup().trim()+"\", salesrank: \"" +amazonItem.getSalesrank().trim() + "\", similar: \"" + String.join(", ",  amazonItem.getSimilar()) + "\" }) RETURN "
                + amazonItem.getId());

        createCustomer(amazonItem);
    }

    public void createCustomer(AmazonItems amazonItems){
        for (Reviews review: amazonItems.getReviews().reviews) {
            session.run("MATCH (customer {customer: \"" + review.customer + "\"}) CREATE ({customer:\"" + review.customer + "\", asin:\"" + amazonItems.getASIN() +"\"}) RETURN " + amazonItems.getId());
        }

    }

    public void createSession(){
        session = driver.session();
    }

    public void closeSession(){
        session.close();
        driver.close();
    }

    public void createRelationships(ArrayList<AmazonItems> arrayList){
        for (AmazonItems item: arrayList) {
            for (String asin: item.getSimilar()) {
                if (findASIN(arrayList ,asin) != null){
                    session.run("MATCH (item:i0) WHERE ");
                }
            }
        }
    }

    public AmazonItems findASIN(ArrayList<AmazonItems> arrayList, String ASIN){
        for (AmazonItems item: arrayList) {
            if(item.getASIN().equals(ASIN)){
                return item;
            }
        }

        return null;
    }


}
