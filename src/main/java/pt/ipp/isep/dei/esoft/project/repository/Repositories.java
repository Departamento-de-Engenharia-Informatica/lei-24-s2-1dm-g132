package pt.ipp.isep.dei.esoft.project.repository;

public class Repositories {

    private static Repositories instance;
    private final OrganizationRepository organizationRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final AuthenticationRepository authenticationRepository;
    private final WaterSuplyPointsCsvRepository waterSuplyPointsCsvRepository;
    private final JobRepository jobRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final SkillRepository skillRepository;
    private final TeamRepository teamRepository;

    private Repositories() {
        organizationRepository = new OrganizationRepository();
        taskCategoryRepository = new TaskCategoryRepository();
        authenticationRepository = new AuthenticationRepository();
        waterSuplyPointsCsvRepository = new WaterSuplyPointsCsvRepository(false);
        jobRepository = new JobRepository();
        collaboratorRepository = new CollaboratorRepository();
        skillRepository = new SkillRepository();
        teamRepository = new TeamRepository();
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

    public WaterSuplyPointsCsvRepository getWaterSuplyPointsCsvRepository() {
        return waterSuplyPointsCsvRepository;
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
}