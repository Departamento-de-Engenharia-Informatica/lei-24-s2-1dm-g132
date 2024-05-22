package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GraphPngGenerator;
import pt.ipp.isep.dei.esoft.project.domain.graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.domain.graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WaterSupplyPointsRepository {

    // Para já, considerei que cada edge é uma String e o custo é um Double, podem mudar mais tarde se for o caso.
    private MatrixGraph<Vertice, Double> csvGraph;

    public WaterSupplyPointsRepository(boolean directed) {
        csvGraph = new MatrixGraph<>(directed);
    }

    public boolean addEdge(String ori, String dest, Double weight) {
        return addEdge(new Vertice(ori), new Vertice(dest), weight);
    }

    public boolean addEdge(Vertice ori, Vertice dest, Double weight) {
        return csvGraph.addEdge(ori, dest, weight);
    }

    public double getEdge(Vertice ori, Vertice dest) {
        Double temp = csvGraph.edge(ori, dest).getWeight();
        if (temp != null) {
            return temp;
        } else {
            return 0;
        }
    }

    public MatrixGraph<Vertice, Double> getCsvGraphCopy() {
        return csvGraph.clone();
    }

    public boolean loadGraph(String filePath) {
        try {
            if (csvGraph.numEdges() != 0) {
                csvGraph = new MatrixGraph<>(csvGraph.isDirected());
            }
            File file = new File(filePath);
            csvGraph.setName(file.getName());
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

    public boolean getMinimalCostGraph() {
        MatrixGraph<Vertice, Double> graph = Algorithms.minDistGraph(getCsvGraphCopy(), Double::compareTo);
        graph.setName(getCsvGraphCopy().getName());

        generateGraphPNG(getCsvGraphCopy(), "FullGraph.png");
        if (!graph.isDirected()) {
            generateGraphPNG(removeDups(graph), "MinimalCostGraph.png");
        } else {
            generateGraphPNG(graph, "MinimalCostGraph.png");
        }

        return true;
    }

    private MatrixGraph<Vertice, Double> removeDups(MatrixGraph<Vertice, Double> graph) {
        MatrixGraph<Vertice, Double> newGraph = new MatrixGraph<>(graph.isDirected());
        newGraph.setName(graph.getName());
        for (Edge<Vertice, Double> edge : graph.edges()) {
            if (newGraph.edge(edge.getVOrig(), edge.getVDest()) == null &&
                    newGraph.edge(edge.getVDest(), edge.getVOrig()) == null) {
                newGraph.addEdge(edge.getVOrig(), edge.getVDest(), edge.getWeight());
            }
        }
        return newGraph;
    }

    private boolean generateGraphPNG(MatrixGraph<Vertice, Double> graph, String fileName) {
        GraphPngGenerator graphPngGenerator = new GraphPngGenerator();
        return graphPngGenerator.generateGraphSim(graph, fileName);
    }
}
