import App.PaginatingDoc;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = "document.txt";
        List<String> lines = PaginatingDoc.readFile(inputFilePath);
        List<String> paginatedDocument = PaginatingDoc.paginateDocument(lines);
        PaginatingDoc.writePaginatedDocumentToFile(paginatedDocument, "paginatedDocumentOutput.txt");
    }
}
