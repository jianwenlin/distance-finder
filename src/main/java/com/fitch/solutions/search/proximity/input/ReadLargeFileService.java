package com.fitch.solutions.search.proximity.input;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReadLargeFileService implements InputReadStrategy {

    private SearchKeywords searchKeywords = new SearchKeywordsFromDocumentService();

    @Override
    public KeywordsAppearedInDocument readInput(final SearchInput searchInput) throws IOException, URISyntaxException {

        KeywordsAppearedInDocument keywordsAppearedInPreviousSearch = new KeywordsAppearedInDocument();
        Path path;
        try {
            path = Paths.get(ClassLoader.getSystemResource(searchInput.getFileName()).toURI());
        }
        catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                    "File Input is wrong: " + searchInput.getFileName() == null ? "" : searchInput.getFileName());
            throw e;
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(path, StandardCharsets.UTF_8.name());
            while (scanner.hasNextLine()) {
                searchKeywords.getKeywordsListFromString(searchInput, scanner.nextLine(),
                        keywordsAppearedInPreviousSearch);
            }
        }
        finally {
            if (null != scanner) {
                scanner.close();
            }
        }

        return keywordsAppearedInPreviousSearch;
    }
}
