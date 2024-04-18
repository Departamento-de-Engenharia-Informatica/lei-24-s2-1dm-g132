package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AsymptoticBehaviorAnalyser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AsymptoticBehaviorAnalyzerController {
    public Map<String, Long> generateCsv(List<Integer> listOfSizes, int iterations) throws IOException {
        AsymptoticBehaviorAnalyser asymptoticBehaviorAnalyser = new AsymptoticBehaviorAnalyser();
        return asymptoticBehaviorAnalyser.startTest(listOfSizes, iterations);
    }

    public boolean generateGraph(List<Map.Entry<String, Long>> entryList) {
        AsymptoticBehaviorAnalyser asymptoticBehaviorAnalyser = new AsymptoticBehaviorAnalyser();
        return asymptoticBehaviorAnalyser.generateGraph(entryList);
    }
}
