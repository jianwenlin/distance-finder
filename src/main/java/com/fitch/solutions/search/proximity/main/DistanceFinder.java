package com.fitch.solutions.search.proximity.main;

import com.fitch.solutions.search.proximity.search.SearchOrchestrator;

public class DistanceFinder {

    public static void main(String... args) {

        SearchOrchestrator searchOrchestrator = new SearchOrchestrator();

        searchOrchestrator.searchProximity(args);
    }
}
