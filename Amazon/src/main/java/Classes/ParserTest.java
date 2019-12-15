package Classes;

import Repository.GraphHandler;
import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class ParserTest {

    @Test
    public void testGetLine() throws IOException {
        Parser parser = new Parser();
        parser.getLines();
        parser.graphHandler.closeSession();
        assertNotNull(parser.amazonItems);
    }
}