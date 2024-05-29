package pt.ipp.isep.dei.esoft.project.domain.graph;

import java.util.Comparator;
import java.util.Objects;

public class Vertice implements Comparable<Vertice> {
    private String nome;

    public Vertice(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Vertice{" +
                "nome='" + nome + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertice vertice = (Vertice) o;
        return Objects.equals(nome, vertice.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nome);
    }

    @Override
    public int compareTo(Vertice o) {
        return this.nome.compareTo(o.nome);
    }
}
