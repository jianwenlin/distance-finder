package com.fitch.solutions.search.proximity.input;

public enum InputCategory implements InputReadStrategyResolver {
    LARGE_FILE('L'),
    SMALL_FILE('S');

    private char value;

    private InputCategory(char value) {
        this.value = value;
    }

    public InputReadStrategy getInputReadStrategy() {
        switch (value) {
            case 'L':
                return new ReadLargeFileService();

            case 'S':
                return new ReadSmallFileService();

            default:
                throw new UnsupportedOperationException("FILE Size not specified and read strategy not supported");

        }
    }
}
