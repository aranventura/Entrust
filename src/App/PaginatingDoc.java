package App;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PaginatingDoc {

    /************************************************************************************
     *
     * @Objective: This method is used to read all the information from the original document and store
     *              the infomation.
     *
     * @Parameters:
     *              String filePath   =   string where the file is stored and the name we gave it
     *
     * @Return:
     *              List(String)   =   a list of the lines from the original text
     *
     ************************************************************************************/
    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /************************************************************************************
     *
     * @Objective: This method is used to paginate all the information according to the specifications:
     *             - 80 chars x line unless in the 80 character we are still reading a word, then we keep it in the same line
     *             - 25 lines per page counting the number of the page as a line
     *
     * @Parameters:
     *              String filePath   =   string where the file is stored and the name we gave it
     *
     * @Return:
     *              List(String)   =   a list of the lines from the original text
     *
     ************************************************************************************/
    public static List<String> paginateDocument(List<String> lines) {
        List<String> paginatedDocument = new ArrayList<>();
        StringBuilder currentPageContent = new StringBuilder();
        int countChars = 0;
        int lineNumber = 0;
        int currentPage = 1;

        // Iterate through all the lines we reeded in the original document
        for (String originalLine : lines) {
            // Iterate through every character we encounter in the line to keep track of the number of chars we are using, also lines
            for (int currentChar = 0; currentChar < originalLine.length(); currentChar++){
                if (lineNumber == 24) {
                    paginatedDocument.add("Page " + currentPage);
                    paginatedDocument.add("");
                    currentPage++;
                    lineNumber = 0;
                }
                if (countChars < 79){
                    currentPageContent.append(originalLine.charAt(currentChar));
                    countChars++;
                } else if ((originalLine.charAt(currentChar) == ' ' && countChars == 79)){
                    paginatedDocument.add(currentPageContent.toString().trim());
                    currentPageContent = new StringBuilder();
                    lineNumber++;
                    countChars = 0;
                } else {
                    if (originalLine.charAt(currentChar) != ' '){
                        currentPageContent.append(originalLine.charAt(currentChar));
                        countChars++;
                    } else {
                        paginatedDocument.add(currentPageContent.toString().trim());
                        currentPageContent = new StringBuilder();
                        lineNumber++;
                        countChars = 0;
                    }
                }
            }
        }
        paginatedDocument.add(currentPageContent.toString().trim());
        if (lineNumber != 0){
            paginatedDocument.add("Page " + currentPage);
            paginatedDocument.add("");
        }
        return paginatedDocument;
    }

    /************************************************************************************
     *
     * @Objective: This method is used to paginate all the information according to the specifications:
     *             - 80 chars x line unless in the 80 character we are still reading a word, then we keep it in the same line
     *             - 25 lines per page counting the number of the page as a line
     *
     * @Parameters:
     *              List<String> paginatedDocument   =
     *              String filePath   =   string that gives name to the new document.
     *
     * @Return:
     *
     ************************************************************************************/
    public static void writePaginatedDocumentToFile(List<String> paginatedDocument, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (String line : paginatedDocument) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
