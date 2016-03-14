package com.fitch.solutions.search.proximity.input;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InputValidatorTest extends Mockito {
    private InputValidator subject = new InputValidator();

    private SearchInput searchInput;

    @Before
    public void setUp() throws Exception {
        searchInput = new SearchInput();
        searchInput.setDistance(2);
        searchInput.setFileName("one_line_test.txt");
        searchInput.setInputParmNumbers(4);
        searchInput.setKeyword1("the");
        searchInput.setKeyword2("canal");
    }

    @Test
    public void testInvalidSearchInputObject() throws Exception {
        searchInput = null;
        assertEquals(ValidationMessage.INVALID_INPUT_PARM_NUMBER, subject.validateInput(searchInput));
    }

    @Test
    public void testHappyPathEverythingIsGood() throws Exception {
        assertEquals(ValidationMessage.VALID, subject.validateInput(searchInput));
    }

    @Test
    public void testEmptyKeyword1ExpectInvalidKeywordsMessage() throws Exception {
        searchInput.setKeyword1("");
        assertEquals(ValidationMessage.INVALID_KEYWORD1, subject.validateInput(searchInput));
    }

    @Test
    public void testEmptyKeyword2ExpectInvalidKeyword2Message() throws Exception {
        searchInput.setKeyword2("");
        assertEquals(ValidationMessage.INVALID_KEYWORD2, subject.validateInput(searchInput));
    }

    @Test
    public void testInvalidInputParmNumber3ExpectInvalidParmNumberMessage() throws Exception {
        searchInput.setInputParmNumbers(3);
        assertEquals(ValidationMessage.INVALID_INPUT_PARM_NUMBER, subject.validateInput(searchInput));
    }
}
