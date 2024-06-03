package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.repository.serialization.*;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private CollaboratorRepositoryFile collaboratorRepositoryFile;
    private SkillRepositoryFile skillRepositoryFile;
    private JobRepositoryFile jobRepositoryFile;
    private TeamRepositoryFile teamRepositoryFile;
    private GreenSpaceRepositoryFile greenSpaceRepositoryFile;
    private ToDoListFile toDoListFile;
    private AgendaFile agendaFile;
    private final WaterSupplyPointsRepository waterSupplyPointsRepository;
    private final JobRepository jobRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;
    private final TeamRepository teamRepository;
    private final MeetingPointsRepository meetingPointsRepository;
    private final GreenSpaceRepository greenSpaceRepository;
    private final ToDoList toDoList;
    private final Agenda agenda;

    private Repositories() {
        skillRepositoryFile = new SkillRepositoryFile();
        jobRepositoryFile = new JobRepositoryFile();
        collaboratorRepositoryFile = new CollaboratorRepositoryFile();
        teamRepositoryFile = new TeamRepositoryFile();
        greenSpaceRepositoryFile = new GreenSpaceRepositoryFile();
        toDoListFile = new ToDoListFile();
        agendaFile = new AgendaFile();
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        waterSupplyPointsRepository = new WaterSupplyPointsRepository(true);
        jobRepository = jobRepositoryFile.read();
        collaboratorRepository = collaboratorRepositoryFile.read();
        skillRepository = skillRepositoryFile.read();
        teamRepository = teamRepositoryFile.read();
        meetingPointsRepository = new MeetingPointsRepository(false);
        greenSpaceRepository = greenSpaceRepositoryFile.read();
        toDoList = toDoListFile.read();
        agenda = agendaFile.read();
    }

    public static Repositories getInstance() {
        if (instance == null) {
            synchronized (Repositories.class) {
                instance = new Repositories();
            }
        }
        return instance;
    }

    public OrganizationRepository getOrganizationRepository() {
        return organizationRepository;
    }

    public TaskCategoryRepository getTaskCategoryRepository() {
        return taskCategoryRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public WaterSupplyPointsRepository getWaterSupplyPointsRepository() {
        return waterSupplyPointsRepository;
    }

    public JobRepository getJobRepository() {
        return jobRepository;
    }

    public CollaboratorRepository getCollaboratorRepository() {
        return collaboratorRepository;
    }

    public SkillRepository getSkillRepository(){
        return skillRepository;
    }

    public TeamRepository getTeamRepository() {return teamRepository;}

    public MeetingPointsRepository getMeetingPointsRepository() {
        return meetingPointsRepository;
    }

    public GreenSpaceRepository getGreenSpaceRepository() {
        return greenSpaceRepository;
    }

    public ToDoList getToDoList() { return toDoList; }

    public Agenda getAgenda() { return agenda; }
}