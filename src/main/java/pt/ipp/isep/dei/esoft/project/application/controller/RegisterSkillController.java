package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.domain.TaskCategory;
import pt.ipp.isep.dei.esoft.project.repository.*;

import java.util.List;

public class RegisterSkillController {
    private SkillRepository skillRepository;

    public RegisterSkillController() {
        getSkillRepository();

    }
    public RegisterSkillController(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            Repositories repositories = Repositories.getInstance();

            skillRepository = repositories.getSkillRepository();
        }
        return skillRepository;
    }

    private Skill getSkillByName(String skillName) {
        SkillRepository skillRepository = getSkillRepository();

        //Get the Skill by its name
        Skill skillByName = skillRepository.getSkillByName(skillName);
        return skillByName;
    }
    public List<Skill> getSkills() {
        SkillRepository skillRepository = getSkillRepository();
        return skillRepository.getSkills();
    }








    public Skill registerSkill(String name) {
        SkillRepository skillRepository = getSkillRepository();

        Skill newSkill = new Skill(name);


        skillRepository.add(newSkill);

        return newSkill;
    }
}
