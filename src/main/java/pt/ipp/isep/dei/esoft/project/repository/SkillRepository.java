package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillRepository {
    private final List<Skill> skills;

    private List<String> selectedSkillNamesList;

    private List<Skill> selectedSkillsList;

    public SkillRepository() {
        skills = new ArrayList<>();
    }

    public void add(Skill skill) {
        skills.add(skill);
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

    public List<Skill> getSkills() {
        //This is a defensive copy, so that the repository cannot be modified from the outside.
        return List.copyOf(skills);
    }

    public void createSelectedSkillNamesList()
    {
        selectedSkillNamesList = new ArrayList<>();
    }

    public void addSelectedSkillName(String name){
        if(!selectedSkillNamesList.contains(name))
        {
            selectedSkillNamesList.add(name);
        }
    }

    public List<Skill> createSelectedSkillsList(){
        selectedSkillsList = new ArrayList<>();
        for(String selectedSkillName: selectedSkillNamesList){
            selectedSkillsList.add(getSkillByName(selectedSkillName));
        }
        return selectedSkillsList;
    }

}
