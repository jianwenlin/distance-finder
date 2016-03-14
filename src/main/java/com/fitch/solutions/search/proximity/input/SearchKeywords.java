package com.fitch.solutions.search.proximity.input;

public interface SearchKeywords {
    void getKeywordsListFromString(SearchInput searchInput, String document,
            KeywordsAppearedInDocument keywordsAppearedInPreviousSearch);
}
