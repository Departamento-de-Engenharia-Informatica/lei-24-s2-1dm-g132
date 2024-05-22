package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private final WaterSupplyPointsRepository waterSupplyPointsRepository;
    private final JobRepository jobRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;
    private final TeamRepository teamRepository;
    private final MeetingPointsRepository meetingPointsRepository;

    private Repositories() {
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        waterSupplyPointsRepository = new WaterSupplyPointsRepository(false);
        jobRepository = new JobRepository();
        collaboratorRepository = new CollaboratorRepository();
        skillRepository = new SkillRepository();
        teamRepository = new TeamRepository();
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