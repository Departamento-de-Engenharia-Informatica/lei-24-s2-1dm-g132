package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;

import java.util.Objects;

public class AssemblyPoint extends Vertice {

    private boolean isAssemblyPoint;

    public AssemblyPoint(String nome, boolean isAssemblyPoint) {
        super(nome);
        this.isAssemblyPoint = isAssemblyPoint;
    }

    public boolean isAssemblyPoint() {
        return isAssemblyPoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AssemblyPoint that = (AssemblyPoint) o;
        return isAssemblyPoint == that.isAssemblyPoint;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isAssemblyPoint);
    }

    @Override
    public String toString() {
        return "MeetingPoint{" +
                "isAssemblyPoint=" + isAssemblyPoint +
                '}';
    }
}
