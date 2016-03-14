package com.fitch.solutions.search.proximity.search;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fitch.solutions.search.proximity.input.InputCategory;
import com.fitch.solutions.search.proximity.input.InputReadStrategyResolver;
import com.fitch.solutions.search.proximity.input.InputValidator;
import com.fitch.solutions.search.proximity.input.KeywordsAppearedInDocument;
import com.fitch.solutions.search.proximity.input.SearchInput;
import com.fitch.solutions.search.proximity.input.ValidationMessage;
import com.fitch.solutions.search.proximity.main.DistanceFinder;

public class SearchOrchestrator {

    private static int FILE_SIZE_MAX_FOR_ONE_READ = 1024 * 1024 * 100; // 100M

    public void searchProximity(String... input) {

        SearchInput searchInput = formatInput(input);
        InputValidator inputValidator = new InputValidator();

        ValidationMessage message = inputValidator.validateInput(searchInput);

        if (message != ValidationMessage.VALID) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, message.getErrorMessage());
            System.exit(1);
        }

        long numberOfProximity = delegateSearch(delegateReadInput(searchInput), searchInput);

        Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                "Total number from proximity search: " + numberOfProximity);

    }

    private SearchInput formatInput(String... input) {

        SearchInput searchInput = new SearchInput();
        searchInput.setInputParmNumbers(input.length);
        searchInput.setKeyword1(input[0]);
        searchInput.setKeyword2(input[1]);
        int distance = 0;
        try {
            distance = Integer.parseInt(input[2]);
        }
        catch (Exception ex) {
            Logger.getLogger(DistanceFinder.class.getName()).log(Level.SEVERE, "Invalid distance input:",
                    ValidationMessage.INVALID_DISTANCE.getErrorMessage());
        }

        if (distance == 0) {
            searchInput.setDistance(null);
        }
        else {
            searchInput.setDistance(distance);
        }

        searchInput.setFileName(input[3]);

        return searchInput;
    }

    private KeywordsAppearedInDocument delegateReadInput(SearchInput searchInput) {

        Path path = null;
        long fileSize = 0;
        try {
            path = Paths.get(ClassLoader.getSystemResource(searchInput.getFileName()).toURI());
            fileSize = Files.size(path);
        }
        catch (Exception e1) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Invalid file or path.", e1);
            System.exit(2);
        }

        InputReadStrategyResolver inputReadStrategyResolver;

        if (fileSize > FILE_SIZE_MAX_FOR_ONE_READ)
            inputReadStrategyResolver = InputCategory.LARGE_FILE;
        else
            inputReadStrategyResolver = InputCategory.SMALL_FILE;

        KeywordsAppearedInDocument keywords = null;

        try {
            keywords = inputReadStrategyResolver.getInputReadStrategy().readInput(searchInput);
        }
        catch (Exception e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "Issue in reading and parsing document");
            System.exit(2);
        }

        return keywords;

    }

    private long delegateSearch(KeywordsAppearedInDocument keywordsAppearedInDocument, SearchInput input) {

        List<Integer> keywords1 = keywordsAppearedInDocument.getKeywords1();
        List<Integer> keywords2 = keywordsAppearedInDocument.getKeywords2();

        int lengthOfKeyword1 = keywords1.size();
        int lengthOfKeyword2 = keywords2.size();
        if (0 == lengthOfKeyword1 || 0 == lengthOfKeyword2) {
            return 0;
        }

        List<Integer> keyWordsToBuildBinarySerachTree = (lengthOfKeyword1 > lengthOfKeyword1) ? keywords2 : keywords1;
        List<Integer> keywordsToBeUsedAsSearchBounderies = (lengthOfKeyword1 > lengthOfKeyword1) ? keywords1
                : keywords2;

        SortedArrayToBinarySearchTreeService sortedArrayToBinarySearchTreeService = new SortedArrayToBinarySearchTreeService();

        RangeSearchOnBinarySearchTree rangeSearchOnBinarySearchTree = new RangeSearchOnBinarySearchTree();

        TreeNode binarySearchTree = sortedArrayToBinarySearchTreeService.sortedArrayToBST(SearchUtility
                .toIntArray(keyWordsToBuildBinarySerachTree));

        int totalNumber = 0;
        int distance = input.getDistance();

        for (Integer position : keywordsToBeUsedAsSearchBounderies) {
            totalNumber += rangeSearchOnBinarySearchTree.rangeSearch(binarySearchTree, position - distance, position
                    + distance);
        }
        return totalNumber;
    }
}