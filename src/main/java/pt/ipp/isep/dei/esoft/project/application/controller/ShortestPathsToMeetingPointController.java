package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.repository.MeetingPointsRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class ShortestPathsToMeetingPointController {

    private MeetingPointsRepository meetingPointsRepository;

    public ShortestPathsToMeetingPointController() {
        getMeetingPointsRepository();
    }

    public MeetingPointsRepository getMeetingPointsRepository() {
        Repositories repositories = Repositories.getInstance();
        meetingPointsRepository = repositories.getMeetingPointsRepository();
        return meetingPointsRepository;
    }

    public boolean getShortestPathsToMeetingPoint(){
        return meetingPointsRepository.getShortestPathsToMeetingPoint();
    }

    public List<Vertice> getAllMeetingPoints(){
        return meetingPointsRepository.getCsvGraphCopy().vertices();
    }
}
