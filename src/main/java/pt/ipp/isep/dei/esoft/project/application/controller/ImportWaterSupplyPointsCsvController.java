package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.WaterSuplyPointsCsvRepository;

public class ImportWaterSupplyPointsCsvController {
    private WaterSuplyPointsCsvRepository waterSuplyPointsCsvRepository;

    public ImportWaterSupplyPointsCsvController() {
        getWaterSuplyPointsCsvRepository();
    }

    private WaterSuplyPointsCsvRepository getWaterSuplyPointsCsvRepository() {
        if (waterSuplyPointsCsvRepository == null){
            Repositories repositories = Repositories.getInstance();

            waterSuplyPointsCsvRepository = repositories.getWaterSuplyPointsCsvRepository();
        }
        return waterSuplyPointsCsvRepository;
    }

    public boolean loadGraph(String filePath){
        return waterSuplyPointsCsvRepository.loadGraph(filePath);
    }
}
