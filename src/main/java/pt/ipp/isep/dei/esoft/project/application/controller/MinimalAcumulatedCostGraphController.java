package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.WaterSupplyPointsRepository;

public class MinimalAcumulatedCostGraphController {
    private WaterSupplyPointsRepository waterSupplyPointsRepository;

    public MinimalAcumulatedCostGraphController() {
        getWaterSuplyPointsCsvRepository();
    }

    private WaterSupplyPointsRepository getWaterSuplyPointsCsvRepository() {
        if (waterSupplyPointsRepository == null){
            Repositories repositories = Repositories.getInstance();

            waterSupplyPointsRepository = repositories.getWaterSuplyPointsCsvRepository();
        }
        return waterSupplyPointsRepository;
    }

    public boolean getMinimalCostGraph(){
        return waterSupplyPointsRepository.getMinimalCostGraph();
    }
}
