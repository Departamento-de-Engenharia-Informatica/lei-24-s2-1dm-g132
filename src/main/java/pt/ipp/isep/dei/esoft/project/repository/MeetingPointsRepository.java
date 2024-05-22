package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MeetingPointsRepository {

    private MatrixGraph<Vertice, Double> graph;

    public MeetingPointsRepository(boolean directed){
        this.graph = new MatrixGraph<>(directed);
    }

    public boolean addEdge(String ori, String dest, Double weight) {
        return addEdge(new Vertice(ori), new Vertice(dest), weight);
    }

    public boolean addEdge(Vertice ori, Vertice dest, Double weight) {
        return graph.addEdge(ori, dest, weight);
    }

    public double getEdge(Vertice ori, Vertice dest) {
        Double temp = graph.edge(ori, dest).getWeight();
        if (temp != null) {
            return temp;
        } else {
            return 0;
        }
    }

    public MatrixGraph<Vertice, Double> getCsvGraphCopy() {
        return graph.clone();
    }

    public boolean loadGraph(String filePath) {
        try {
            if (graph.numEdges() != 0) {
                graph = new MatrixGraph<>(graph.isDirected());
            }
            File file = new File(filePath);
            graph.setName(file.getName());
            if (!file.getName().endsWith("csv")) {
                if (file.getName().endsWith("png")) {
                    return false;
                }
                throw new IllegalArgumentException("Invalid File Format! Should be <.csv>");
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String separator = getSeparator(line);
                String[] args = line.split(separator);
                if (args.length != 3) {
                    throw new RuntimeException("Invalid Entry: <" + line + ">, Should be <arg, arg, arg");
                } else {
                    String ori = args[0].strip();
                    String dest = args[1].strip();
                    double weight = Double.parseDouble(args[2].strip());
                    addEdge(ori, dest, weight);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    private String getSeparator(String line) {
        if (line.contains(";")) {
            return ";";
        } else {
            return ",";
        }
    }
}
