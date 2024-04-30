package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Skill;
import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

public class RegisterSkillController {
    private SkillRepository skillRepository;

    public RegisterSkillController() {
        getSkillRepository();
    }

    private SkillRepository getSkillRepository() {
        if (skillRepository == null) {
            skillRepository = new SkillRepository();
        }
        return skillRepository;
    }

    public Skill registerSkill(String name) {
        SkillRepository skillRepository = getSkillRepository();

        Skill newSkill = new Skill(name);
        //erro controller, resolver mais tarde

        SkillRepository.add(newSkill);

        return newSkill;
    }
}
