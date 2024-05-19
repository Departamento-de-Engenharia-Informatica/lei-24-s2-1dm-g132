package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AsymptoticBehaviorAnalyser;

public class AsymptoticBehaviorAnalyzerController {
    public boolean startTest(String folderPath) {
        AsymptoticBehaviorAnalyser asymptoticBehaviorAnalyser = new AsymptoticBehaviorAnalyser();
        return asymptoticBehaviorAnalyser.start(folderPath);
    }
}
