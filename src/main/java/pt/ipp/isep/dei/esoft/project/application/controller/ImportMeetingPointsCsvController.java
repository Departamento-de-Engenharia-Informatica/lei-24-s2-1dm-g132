package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.MeetingPointsRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class ImportMeetingPointsCsvController {
    private MeetingPointsRepository meetingPointsRepository;

    public ImportMeetingPointsCsvController() {
        getMeetingPointsRepository();
    }

    public MeetingPointsRepository getMeetingPointsRepository() {
        Repositories repositories = Repositories.getInstance();
        meetingPointsRepository = repositories.getMeetingPointsRepository();
        return meetingPointsRepository;
    }

    public boolean loadGraph(String filePath) {
        return meetingPointsRepository.loadGraph(filePath);
    }
}
