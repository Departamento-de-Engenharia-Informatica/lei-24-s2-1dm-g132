package pt.ipp.isep.dei.esoft.project.domain;

public class Skill {

    private String name;
    public Skill(String name) {
        this.name = name;
    }
    private void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Skill skill = (Skill) o;
        return name.equals(skill.name);
    }
    @Override
    public Skill clone() {
        return new Skill(this.name);
    }



    public String getName() {
        return name;
    }

}
