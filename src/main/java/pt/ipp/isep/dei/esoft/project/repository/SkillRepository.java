
package pt.ipp.isep.dei.esoft.project.repository;
import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.IllegalArgumentException;

public class SkillRepository implements Serializable {


    private final List<Skill> skills;
    private List<Skill> selectedSkillsList;

    /**
     * Constructs a new SkillRepository object.
     * Initializes the list of skills.
     */
    public SkillRepository() {
        skills = new ArrayList<>();
    }

    /**
     * Adds a new skill to the repository.
     * @param skill The skill to be added.
     * @return An optional containing the newly added skill, or empty if the operation fails.
     */
    public Optional<Skill> add(Skill skill) {
        Optional<Skill> newSkill = Optional.empty();
        boolean operationSuccess = false;
        if (validateSkill(skill)) {
            newSkill = Optional.of(skill.clone());
            operationSuccess = skills.add(newSkill.get());
        }
        if (!operationSuccess) {
            newSkill = Optional.empty();
        }
        return newSkill;
    }

    /**
     * Validates a skill before adding it to the repository.
     * @param skill The skill to be validated.
     * @return True if the skill is valid and can be added, false otherwise.
     */
    private boolean validateSkill(Skill skill) {
        return !skills.contains(skill);
    }

    /**
     * Retrieves a list of all skills stored in the repository.
     * @return A list of all skills.
     */
    public List<Skill> getSkills() {
        // This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(skills);
    }

    /**
     * Creates a temporary list of skills by cloning the existing skills list.
     *
     * @return A new list containing clones of the skills from the original list.
     */
    public List<Skill> createTempSkillsList(){
        List<Skill> tempSkillsList = new ArrayList<>();

        for (Skill skill : skills) {
            tempSkillsList.add(skill.clone());
        }

        return tempSkillsList;
    }

    /**
     * Creates a new list to store selected skills.
     */
    public void createSelectedSkillsList() {
        selectedSkillsList = new ArrayList<>();
    }

    /**
     * Adds a skill to the list of selected skills.
     * @param name The name of the skill to be added.
     */
    public void addSelectedSkill(String name) {
        Skill skill = getSkillByName(name);
        selectedSkillsList.add(skill);
    }

    /**
     * Retrieves a skill by its name.
     * @param skillName The name of the skill to be retrieved.
     * @return The skill with the specified name.
     * @throws IllegalArgumentException If the skill with the specified name does not exist.
     */
    public Skill getSkillByName(String skillName) {
        Skill newSkill = new Skill(skillName);
        Skill skill = null;
        if (skills.contains(newSkill)) {
            skill = skills.get(skills.indexOf(newSkill));
        }
        if (skill == null) {
            throw new IllegalArgumentException(
                    "Skill requested for [" + skillName + "] does not exist.");
        }
        return skill;
    }

    /**
     * Retrieves the list of selected skills.
     * @return The list of selected skills.
     */
    public List<Skill> getSelectedSkillsList() {
        return selectedSkillsList;
    }
}
