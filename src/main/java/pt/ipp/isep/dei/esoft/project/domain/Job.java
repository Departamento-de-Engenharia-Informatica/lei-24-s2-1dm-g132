package pt.ipp.isep.dei.esoft.project.domain;

public class Job {

    private String name;

    public Job(String name) {
        this.name = name;
    }

    public void setName(String name) {
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
        Job job = (Job) o;
        return name == job.name;
    }

    @Override
    public Job clone() {
        return new Job(this.name);
    }

    public String getName() {
        return name;
    }

}
