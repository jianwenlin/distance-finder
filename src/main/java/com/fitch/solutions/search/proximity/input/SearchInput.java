package com.fitch.solutions.search.proximity.input;

public class SearchInput {

    private String fileName;

    private String keyword1;

    private String keyword2;

    private Integer distance;

    private Integer inputParmNumber;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public Integer getInputParmNumbers() {
        return inputParmNumber;
    }

    public void setInputParmNumbers(Integer inputParmNumbers) {
        this.inputParmNumber = inputParmNumbers;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public Integer getInputParmNumber() {
        return inputParmNumber;
    }

    public void setInputParmNumber(Integer inputParmNumber) {
        this.inputParmNumber = inputParmNumber;
    }
}
