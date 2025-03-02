
/**

 This class represents the controller for registering a new skill.
 It interacts with the skill repository to store and retrieve skill information.
 */
package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;
import pt.ipp.isep.dei.esoft.project.repository.serialization.SkillRepositoryFile;

import java.lang.IllegalArgumentException;

import java.util.List;

/**
 * Controller class for registering skills.
 * Provides methods to interact with the skill repository and to register new skills.
 */
public class RegisterSkillController {

    /**
     * The skill repository instance used to manage skills.
     */
    private SkillRepository skillRepository;

    /**
     * The file instance used to manage the serialization of the skill repository.
     */
    private SkillRepositoryFile skillRepositoryFile;

    /**
     * Constructs a new RegisterSkillController object.
     * Initializes the skill repository by obtaining it from the Repositories singleton instance.
     */
    public RegisterSkillController() {
        getSkillRepository();
        skillRepositoryFile = new SkillRepositoryFile();
    }

    /**
     * Constructs a new RegisterSkillController object with a specified skill repository.
     * @param skillRepository The skill repository to be used by the controller.
     */
    public RegisterSkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    /**
     * Retrieves the skill repository, either by initializing it from the Repositories singleton instance
     * or using the one provided in the constructor.
     * @return The skill repository.
     */
    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();
            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    /**
     * Retrieves the list of skills from the skill repository.
     * @return The list of skills.
     */
    public List<Skill> getSkills() {
        SkillRepository skillRepository = getSkillRepository();
        return skillRepository.getSkills();
    }

    /**
     * Registers a new skill with the given name.
     * Validates the input name and adds the skill to the repository.
     * @param name The name of the skill to be registered.
     * @return The newly created skill.
     * @throws IllegalArgumentException If the name is null or empty.
     */
    public Skill registerSkill(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Skill name cannot be null or empty");
        }
        SkillRepository skillRepository = getSkillRepository();
        Skill newSkill = new Skill(name);
        skillRepository.add(newSkill);
        if(!skillRepositoryFile.save(skillRepository))
        {
            System.out.println("Error while saving Skill Repository in external file!");
        }
        return newSkill;
    }
}







