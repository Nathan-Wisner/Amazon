package Classes;

import Repository.GraphHandler;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ParserTest {

    // Tests to see if the parser can correctly write to the amazonItems variable. Only completes when it connects to the database
    // as well
    @Test
    public void testGetLine() throws IOException {
        Parser parser = new Parser();
        parser.getLines();
        parser.graphHandler.closeSession();
        assertNotNull(parser.amazonItems);
    }
}