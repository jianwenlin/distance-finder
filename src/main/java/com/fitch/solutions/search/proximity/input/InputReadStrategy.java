package com.fitch.solutions.search.proximity.input;

import java.io.IOException;
import java.net.URISyntaxException;

/**
 * @author FQM832 -
 * @since Mar 13, 2016
 */
public interface InputReadStrategy {
    KeywordsAppearedInDocument readInput(SearchInput searchInput) throws IOException, URISyntaxException;
}
