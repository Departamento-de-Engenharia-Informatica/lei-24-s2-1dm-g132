package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.WaterSuplyPointsCsvRepository;

import java.util.List;

public class MinimalAcumulatedCostGraphController {
    private WaterSuplyPointsCsvRepository waterSuplyPointsCsvRepository;

    public MinimalAcumulatedCostGraphController() {
        getWaterSuplyPointsCsvRepository();
    }

    private WaterSuplyPointsCsvRepository getWaterSuplyPointsCsvRepository() {
        if (waterSuplyPointsCsvRepository == null){
            Repositories repositories = Repositories.getInstance();

            waterSuplyPointsCsvRepository = repositories.getWaterSuplyPointsCsvRepository();
        }
        return waterSuplyPointsCsvRepository;
    }

    public boolean getMinimalCostGraph(){
        return waterSuplyPointsCsvRepository.getMinimalCostGraph();
    }
}
