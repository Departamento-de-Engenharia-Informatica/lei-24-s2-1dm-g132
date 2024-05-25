package pt.ipp.isep.dei.esoft.project.repository;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import pt.ipp.isep.dei.esoft.project.domain.GraphPngAndCsvGenerator;
import pt.ipp.isep.dei.esoft.project.domain.graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.domain.graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

    public boolean getShortestPathsToMeetingPoint(Vertice meetingPoint){
        Set<Pair<Double, LinkedList<Vertice>>> distanceAndPathSetToReturn = new HashSet<>();
        for (Vertice vertice : graph.vertices()){
            if (!vertice.equals(meetingPoint)){
                LinkedList<Vertice> shortestPath = new LinkedList<>();
                Double distance = Algorithms.shortestPath(getCsvGraphCopy(), vertice, meetingPoint,
                        Comparator.naturalOrder(), Double::sum, 0.0, shortestPath);

                if (distance != null){
                    distanceAndPathSetToReturn.add(new ImmutablePair<>(distance, shortestPath));
                }
            }
        }

        generateFiles(getCsvGraphCopy(), "FullGraph.png");
        MatrixGraph<Vertice, Double> graph = new MatrixGraph<>(false);
        for (Pair<Double, LinkedList<Vertice>> pair : distanceAndPathSetToReturn){
            LinkedList<Vertice> path = pair.getRight();
            double distance = pair.getLeft();
            for (int i = 1; i < path.size(); i++){
                if (graph.edge(path.get(i), path.get(i-1)) == null &&
                        graph.edge(path.get(i-1), path.get(i)) == null){
                    graph.addEdge(path.get(i-1), path.get(i), distance);
                }
            }
        }
        graph.setName(this.graph.getName());
        generateFiles(graph, "PathsToMeetingPointGraph.png");
        generatePathsCsv(distanceAndPathSetToReturn, graph.getName() + "-PathsToMeetingPoint.csv");

        return true;
    }

    private boolean generatePathsCsv(Set<Pair<Double, LinkedList<Vertice>>> distanceAndPathSet, String fileName){
        String outputFolder = getDesktopPath() + File.separator + "output" + File.separator + "us18";
        File graphFolder = new File(outputFolder);
        try {
            if (!graphFolder.exists()) {
                if (!graphFolder.mkdirs()) {
                    throw new RuntimeException("Failed to create folder: " + outputFolder);
                } else {
                    System.out.println("Created: " + outputFolder);
                }
            } else {
                File a = new File(outputFolder + File.separator + fileName);
                if (a.exists()) {
                    if (!a.delete()) {
                        throw new RuntimeException("Failed to delete file: " + a.getAbsolutePath());
                    } else {
                        System.out.println("Deleted: " + a.getAbsolutePath());
                    }

                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        FileWriter fileWriter = null;
        File csvFile = new File(outputFolder + File.separator + fileName);
        try {
            fileWriter = new FileWriter(csvFile);

            fileWriter.append("(");
            for (Pair<Double, LinkedList<Vertice>> pair : distanceAndPathSet){
                for (Vertice vertice : pair.getRight()){
                    fileWriter.append(vertice.getNome())
                            .append(",");
                }
                fileWriter.append(")")
                        .append(";")
                        .append(pair.getLeft().toString())
                        .append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                    System.out.println("CSV file '" + csvFile.getAbsolutePath() + "' created successfully.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    private boolean generateFiles(MatrixGraph<Vertice, Double> graph, String fileName) {
        GraphPngAndCsvGenerator graphPngAndCsvGenerator = new GraphPngAndCsvGenerator();
        String outputFolder = "output" + File.separator + "us18";
        return graphPngAndCsvGenerator.generate(graph, fileName, outputFolder);
    }

    private String getDesktopPath() {
        try {
            return System.getProperty("user.home") + File.separator + "Desktop";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private String getSeparator(String line) {
        if (line.contains(";")) {
            return ";";
        } else {
            return ",";
        }
    }
}
