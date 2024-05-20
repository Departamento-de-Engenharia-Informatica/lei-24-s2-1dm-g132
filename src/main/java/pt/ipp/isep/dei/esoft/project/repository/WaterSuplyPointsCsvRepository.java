package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.GraphPngGenerator;
import pt.ipp.isep.dei.esoft.project.domain.graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WaterSuplyPointsCsvRepository {

    // Para já, considerei que cada edge é uma String e o custo é um Double, podem mudar mais tarde se for o caso.
    private MatrixGraph<Vertice, Double> csvGraph;

    public WaterSuplyPointsCsvRepository(boolean directed) {
        csvGraph = new MatrixGraph<>(directed);
    }

    public WaterSuplyPointsCsvRepository() {
        csvGraph = new MatrixGraph<>(false);
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
            if (csvGraph.numEdges() != 0){
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

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                generateGraphPNG(getCsvGraphCopy(), "FullGraph.png");
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                generateGraphPNG(graph, "MinimalCostGraph.png");
            }
        };
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();


        try {
            Thread.sleep(1000);  // Pause for 1 second to ensure rendering
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean generateGraphPNG(MatrixGraph<Vertice, Double> graph, String fileName) {
        GraphPngGenerator graphPngGenerator = new GraphPngGenerator();
        return graphPngGenerator.generateGraphSim(graph, fileName);
    }
}
