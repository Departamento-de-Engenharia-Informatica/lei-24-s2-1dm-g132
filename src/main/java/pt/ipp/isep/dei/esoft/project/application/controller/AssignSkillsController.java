package pt.ipp.isep.dei.esoft.project.application.controller;


import pt.ipp.isep.dei.esoft.project.domain.Collaborator;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.util.List;
import java.util.Optional;

public class AssignSkillsController {

    private CollaboratorRepository collaboratorRepository;
    private SkillRepository skillRepository;

    public AssignSkillsController(){
        getCollaboratorRepository();
        getSkillRepository();
    }

    private CollaboratorRepository getCollaboratorRepository() {
        if (collaboratorRepository == null) {
            Repositories repositories = Repositories.getInstance();
            collaboratorRepository = repositories.getCollaboratorRepository();
        }
        return collaboratorRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }


    public Optional<Collaborator> assignSkills(String collaboratorIdNumber){
        List<Skill> selectedSkillsList = getSelectedSkillsList();

        Collaborator collaborator = getCollaboratorByIdNumber(collaboratorIdNumber);

        Optional<CollaboratorRepository> collaboratorRepository = Optional.ofNullable(getCollaboratorRepository());

        Optional<Collaborator> updatedCollaborator = Optional.empty();

        if (collaboratorRepository.isPresent()) {
            updatedCollaborator = collaboratorRepository.get()
                    .assignSkill(collaborator, selectedSkillsList);
        }

        return updatedCollaborator;
    }

    private List<Skill> getSelectedSkillsList(){
        return skillRepository.getSelectedSkillsList();
    }

    private Collaborator getCollaboratorByIdNumber(String collaboratorIdNumber){
        return collaboratorRepository.getCollaboratorByIdNumber(collaboratorIdNumber);
    }

    public List<Collaborator> getCollaborators() {
        CollaboratorRepository collaboratorRepository = getCollaboratorRepository();
        return collaboratorRepository.getCollaborators();
    }

    public List<Skill> getSkills() {
        SkillRepository skillRepository = getSkillRepository();
        skillRepository.createSelectedSkillsList();
        return skillRepository.getSkills();
    }

    public void addSelectedSkillName(String name){
        skillRepository.addSelectedSkill(name);
    }

}
