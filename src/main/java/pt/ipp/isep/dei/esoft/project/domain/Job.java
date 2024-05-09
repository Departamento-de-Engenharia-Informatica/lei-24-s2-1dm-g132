package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Job {

    private String Jobname;

    public Job(String jobname) {
        this.Jobname = jobname;
    }

    public void setJobName(String jobname) {
        this.Jobname = jobname;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Jobname);
    }
    public boolean hasName(String name) {
        return this.Jobname.equals(name);
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
        return Jobname == job.Jobname;
    }

    @Override
    public Job clone() {
        return new Job(this.Jobname);
    }

    public String getJobName() {
        return Jobname;
    }

}
