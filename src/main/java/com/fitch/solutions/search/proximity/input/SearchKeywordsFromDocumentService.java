package com.fitch.solutions.search.proximity.input;

public class SearchKeywordsFromDocumentService implements SearchKeywords {

    @Override
    public void getKeywordsListFromString(final SearchInput searchInput, final String document,
            final KeywordsAppearedInDocument keywordsAppearedInPreviousSearch) {

        if (null == document || document.isEmpty()) {
            return;
        }

        String[] words = document.split(" ");
        int wordCount = keywordsAppearedInPreviousSearch.getWordCount();

        for (String word : words) {
            if (null == word || word.trim().isEmpty()) {
                continue;
            }
            if (searchInput.getKeyword1().equalsIgnoreCase(word)) {
                keywordsAppearedInPreviousSearch.getKeywords1().add(wordCount++);
            }
            else if (searchInput.getKeyword2().equalsIgnoreCase(word)) {
                keywordsAppearedInPreviousSearch.getKeywords2().add(wordCount++);
            }
            else {
                wordCount++;
            }
        }
        keywordsAppearedInPreviousSearch.setWordCount(wordCount);
    }
}
