package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.repository.MeetingPointsRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class ImportMeetingPointsCsvController {
    private MeetingPointsRepository meetingPointsRepository;

    private MeetingPointsRepository getMeetingPointsRepository(){
        if (meetingPointsRepository == null){
            Repositories repositories = Repositories.getInstance();

            meetingPointsRepository = repositories.getMeetingPointsRepository();
        }
        return this.meetingPointsRepository;
    }

    public boolean loadGraph(String filePath) {
        return meetingPointsRepository.loadGraph(filePath);
    }
}
