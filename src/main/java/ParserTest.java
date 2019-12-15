import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    public void testGetLine() throws IOException {
        Parser parser = new Parser();
        parser.getLines();
        assertNotNull(parser.amazonItems);
    }
}