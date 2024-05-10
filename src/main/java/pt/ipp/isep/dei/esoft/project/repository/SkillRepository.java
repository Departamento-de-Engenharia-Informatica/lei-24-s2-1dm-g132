package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class SkillRepository {
    private final List<Skill> skills;
    private List<Skill> selectedSkillsList;


    public SkillRepository() {
        skills = new ArrayList<>();
    }


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


    private boolean validateSkill(Skill skill) {
        boolean isValid = !skills.contains(skill);
        return isValid;
    }

    public List<Skill> getSkills() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(skills);
    }

    public void createSelectedSkillsList()
    {
        selectedSkillsList = new ArrayList<>();
    }

    public void addSelectedSkill(String name){
        Skill skill = getSkillByName(name);
        selectedSkillsList.add(skill);
    }

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

    public List<Skill> getSelectedSkillsList(){
        return selectedSkillsList;
    }
}
