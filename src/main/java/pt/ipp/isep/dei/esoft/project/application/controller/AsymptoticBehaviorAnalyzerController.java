package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AsymptoticBehaviorAnalyser;

import java.util.List;
import java.util.Map;

public class AsymptoticBehaviorAnalyzerController {
    public Map<String, Long> generateCsv(List<Integer> listOfSizes, int iterations) {
        AsymptoticBehaviorAnalyser asymptoticBehaviorAnalyser = new AsymptoticBehaviorAnalyser();
        return asymptoticBehaviorAnalyser.startTest(listOfSizes, iterations);
    }
}
