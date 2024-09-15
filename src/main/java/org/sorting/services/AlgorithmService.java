package org.sorting.services;

import org.sorting.algorithms.SortingAlgorithm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AlgorithmService {

    private final Map<String, SortingAlgorithm> algorithmMap;

    public AlgorithmService(Map<String, SortingAlgorithm> algorithmMap) {
        this.algorithmMap = new ConcurrentHashMap<String, SortingAlgorithm>(algorithmMap);
    }

    public SortingAlgorithm getAlgorithmByName(String name) {

        return algorithmMap.get(name);
    }

//    public List<SortingAlgorithm> getRelatedAlgorithms(SortingAlgorithm algorithm) {
//        // Logic to fetch related algorithms, possibly based on tags or types
//        return algorithm.getRelatedAlgorithms(); // Assuming this method exists
//    }
}
