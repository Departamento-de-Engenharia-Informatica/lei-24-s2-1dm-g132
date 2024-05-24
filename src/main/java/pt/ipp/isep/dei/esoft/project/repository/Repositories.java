package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.repository.serialization.CollaboratorRepositoryFile;
import pt.ipp.isep.dei.esoft.project.repository.serialization.JobRepositoryFile;
import pt.ipp.isep.dei.esoft.project.repository.serialization.SkillRepositoryFile;
import pt.ipp.isep.dei.esoft.project.repository.serialization.TeamRepositoryFile;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private CollaboratorRepositoryFile collaboratorRepositoryFile;
    private SkillRepositoryFile skillRepositoryFile;
    private JobRepositoryFile jobRepositoryFile;
    private TeamRepositoryFile teamRepositoryFile;
    private final WaterSupplyPointsRepository waterSupplyPointsRepository;
    private final JobRepository jobRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;
    private final TeamRepository teamRepository;
    private final MeetingPointsRepository meetingPointsRepository;

    private Repositories() {
        skillRepositoryFile = new SkillRepositoryFile();
        jobRepositoryFile = new JobRepositoryFile();
        collaboratorRepositoryFile = new CollaboratorRepositoryFile();
        teamRepositoryFile = new TeamRepositoryFile();
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        waterSupplyPointsRepository = new WaterSupplyPointsRepository(false);
        jobRepository = jobRepositoryFile.read();
        collaboratorRepository = collaboratorRepositoryFile.read();
        skillRepository = skillRepositoryFile.read();
        teamRepository = teamRepositoryFile.read();
        meetingPointsRepository = new MeetingPointsRepository(false);
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
}