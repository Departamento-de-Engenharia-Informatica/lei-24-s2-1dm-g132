package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Skill {

    private String name;
    public Skill(String name) {
        this.name = name;
    }
    private void setName(String name) {
        this.name = name;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    public boolean hasName(String name) {
        return this.name.equals(name);
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

    @Override
    public String toString() {
        return String.format("%s", this.name);
    }
}
