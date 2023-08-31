package Test;

import App.PaginatingDoc;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class PaginatingTest {

    @Test
    void readFileTest(){
        // Test for an empty file
        List<String> lines = PaginatingDoc.readFile("src/Test/testempty.txt");
        assertEquals(lines.size(), 0);

        // Test for a NOT empty file
        lines = PaginatingDoc.readFile("src/Test/testfull.txt");
        assertTrue(lines.size() > 0);
    }

    @Test
    void paginateDocumentTest(){
        // Create test input
        List<String> lines = PaginatingDoc.readFile("src/Test/test1.txt");

        List<String> paginatedDocument = PaginatingDoc.paginateDocument(lines);

        assertNotNull(paginatedDocument);
        assertTrue(paginatedDocument.size() > 0);

        assertEquals("Page 1", paginatedDocument.get(24));
    }
}
