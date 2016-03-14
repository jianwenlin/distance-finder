package com.fitch.solutions.search.proximity.input;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;

public class SearchKeywordsFromDocumentServiceTest {

    private SearchKeywordsFromDocumentService subject = new SearchKeywordsFromDocumentService();

    private SearchInput searchInput;

    private KeywordsAppearedInDocument keywordsAppearedInPreviousSearch;

    @Captor
    private ArgumentCaptor<KeywordsAppearedInDocument> keywordsAppearedInPreviousSearchtCaptor;

    @Before
    public void setUp() throws Exception {
        setupTestingData();
    }

    @Test
    public void testOneLineInputExpectValidListsOfKeywordsAndTotal() {

        // given input one line string
        String document = givenInputDocumentwithOneLine();

        // when search proximity keywords in document
        subject.getKeywordsListFromString(searchInput, document, keywordsAppearedInPreviousSearch);

        // then expect correct keyword lists and total words in the line
        List<Integer> keywords1FirstTest = Arrays.asList(0, 3, 5, 10, 14, 16);
        List<Integer> keywords2FirstTest = Arrays.asList(1, 4, 17);
        long totalWordsFirstTest = 18;

        assertEquals(keywords1FirstTest, keywordsAppearedInPreviousSearch.getKeywords1());
        assertEquals(keywords2FirstTest, keywordsAppearedInPreviousSearch.getKeywords2());
        assertEquals(totalWordsFirstTest, keywordsAppearedInPreviousSearch.getWordCount());
    }

    @Test
    public void testNullInputAndExpectNullOut() {

        // given input one line string
        String document = null;

        // when search proximity keywords in document
        subject.getKeywordsListFromString(searchInput, document, keywordsAppearedInPreviousSearch);

        // then expect no keywords out and word count zero

        assertTrue(keywordsAppearedInPreviousSearch.getKeywords1().size() == 0);
        assertTrue(keywordsAppearedInPreviousSearch.getKeywords2().size() == 0);
        assertTrue(keywordsAppearedInPreviousSearch.getWordCount() == 0);

    }

    @Test
    public void testTwoLineInputExpectValidListsOfKeywordsAndTotal() {

        // given input one line string
        String document = givenInputDocumentwithOneLine();

        // when search proximity keywords in document
        subject.getKeywordsListFromString(searchInput, document, keywordsAppearedInPreviousSearch);

        // given input another line of string
        String documentLineTwo = givenInputDocumentwithLineTwo();

        // when search proximity keywords with another line
        subject.getKeywordsListFromString(searchInput, documentLineTwo, keywordsAppearedInPreviousSearch);

        // then expect correct added up keyword lists and total words for two lines

        List<Integer> keywords1SecondTest = Arrays.asList(0, 3, 5, 10, 14, 16, 20, 28);
        List<Integer> keywords2SecondTest = Arrays.asList(1, 4, 17, 19, 22, 25);

        long totalWordsSecondTest = 29;
        assertEquals(keywords1SecondTest, keywordsAppearedInPreviousSearch.getKeywords1());
        assertEquals(keywords2SecondTest, keywordsAppearedInPreviousSearch.getKeywords2());
        assertEquals(totalWordsSecondTest, keywordsAppearedInPreviousSearch.getWordCount());

    }

    String givenInputDocumentwithOneLine() {
        return "the canal testing the canal the testing is good in the canal. really in the canal? the canal";
    }

    String givenInputDocumentwithLineTwo() {
        return "abc canal the new canal testing in canal good one the";
    }

    void setupTestingData() {
        searchInput = new SearchInput();
        searchInput.setKeyword1("the");
        searchInput.setKeyword2("canal");
        keywordsAppearedInPreviousSearch = new KeywordsAppearedInDocument();
    }
}
