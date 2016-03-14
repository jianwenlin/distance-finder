package com.fitch.solutions.search.proximity.input;

import java.util.ArrayList;
import java.util.List;

public class KeywordsAppearedInDocument {

    private List<Integer> keywords1;

    private List<Integer> keywords2;

    private int wordCount;

    public KeywordsAppearedInDocument() {
        keywords1 = new ArrayList<Integer>();
        keywords2 = new ArrayList<Integer>();
    }

    public List<Integer> getKeywords1() {
        return keywords1;
    }

    public void setKeywords1(List<Integer> keywords1) {
        this.keywords1 = keywords1;
    }

    public List<Integer> getKeywords2() {
        return keywords2;
    }

    public void setKeywords2(List<Integer> keywords2) {
        this.keywords2 = keywords2;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

}
