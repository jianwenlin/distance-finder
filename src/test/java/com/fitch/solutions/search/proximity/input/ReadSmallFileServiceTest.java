package com.fitch.solutions.search.proximity.input;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReadSmallFileServiceTest extends Mockito {

    @Mock
    private SearchKeywords searchKeywords;

    @Mock
    private SearchInput searchInput;

    @Captor
    private ArgumentCaptor<KeywordsAppearedInDocument> keywordsAppearedInPreviousSearchtCaptor;

    @Captor
    private ArgumentCaptor<SearchInput> searchInputCaptor;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @InjectMocks
    private ReadSmallFileService subject;

    @Before
    public void setUp() throws Exception {
        when(searchInput.getFileName()).thenReturn("one_line_test.txt");
    }

    @Test
    public void testSearchWithValidFileOneLineInClassPathExpectSuccess() throws Exception {

        KeywordsAppearedInDocument keywordsAppearedInDocument = null;

        keywordsAppearedInDocument = subject.readInput(searchInput);

        assertNotNull(keywordsAppearedInDocument);
        verify(searchKeywords, times(1)).getKeywordsListFromString(searchInputCaptor.capture(), stringCaptor.capture(),
                keywordsAppearedInPreviousSearchtCaptor.capture());
        verifyNoMoreInteractions(searchKeywords);
    }

    @Test(expected = Exception.class)
    public void testSearchWithInValidFileInClassPathExpectIOException() throws Exception {

        when(searchInput.getFileName()).thenReturn("test2.txt");
        KeywordsAppearedInDocument keywordsAppearedInDocument = null;

        keywordsAppearedInDocument = subject.readInput(searchInput);

        assertNotNull(keywordsAppearedInDocument);
        verify(searchKeywords, times(0)).getKeywordsListFromString(searchInputCaptor.capture(), stringCaptor.capture(),
                keywordsAppearedInPreviousSearchtCaptor.capture());
        verifyNoMoreInteractions(searchKeywords);
    }

    @Test
    public void testSearchWithValidFileFiveLinesInClassPathExpectSearch5Times() throws Exception {

        KeywordsAppearedInDocument keywordsAppearedInDocument = null;
        when(searchInput.getFileName()).thenReturn("five_lines_test.txt");

        keywordsAppearedInDocument = subject.readInput(searchInput);

        assertNotNull(keywordsAppearedInDocument);
        verify(searchKeywords, times(5)).getKeywordsListFromString(searchInputCaptor.capture(), stringCaptor.capture(),
                keywordsAppearedInPreviousSearchtCaptor.capture());
        verifyNoMoreInteractions(searchKeywords);
    }

    @Test
    public void testSearchWithValidFileEmptyExpectNoSearch() throws Exception {

        KeywordsAppearedInDocument keywordsAppearedInDocument = null;
        when(searchInput.getFileName()).thenReturn("empty_test.txt");

        keywordsAppearedInDocument = subject.readInput(searchInput);

        assertNotNull(keywordsAppearedInDocument);
        verify(searchKeywords, times(0)).getKeywordsListFromString(searchInputCaptor.capture(), stringCaptor.capture(),
                keywordsAppearedInPreviousSearchtCaptor.capture());
        verifyNoMoreInteractions(searchKeywords);
    }
}
