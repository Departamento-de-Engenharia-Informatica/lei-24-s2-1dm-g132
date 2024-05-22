package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.WaterSupplyPointsRepository;

public class ImportWaterSupplyPointsCsvController {
    private WaterSupplyPointsRepository waterSupplyPointsRepository;

    public ImportWaterSupplyPointsCsvController() {
        getWaterSuplyPointsCsvRepository();
    }

    private WaterSupplyPointsRepository getWaterSuplyPointsCsvRepository() {
        if (waterSupplyPointsRepository == null){
            Repositories repositories = Repositories.getInstance();

            waterSupplyPointsRepository = repositories.getWaterSuplyPointsCsvRepository();
        }
        return waterSupplyPointsRepository;
    }

    public boolean loadGraph(String filePath){
        return waterSupplyPointsRepository.loadGraph(filePath);
    }
}
