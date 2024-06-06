package pt.ipp.isep.dei.esoft.project.repository;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import pt.ipp.isep.dei.esoft.project.domain.AssemblyPoint;
import pt.ipp.isep.dei.esoft.project.domain.GraphPngAndCsvGenerator;
import pt.ipp.isep.dei.esoft.project.domain.graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.domain.graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeetingPointsRepository {

    private MatrixGraph<Vertice, Double> graph;
    private List<AssemblyPoint> assemblyPointsList;

    public MeetingPointsRepository(boolean directed) {
        this.graph = new MatrixGraph<>(directed);
        this.assemblyPointsList = new ArrayList<>();
    }

    public boolean addEdge(String ori, String dest, Double weight, boolean isAssemblyPoint1, boolean isAssemblyPoint2) {
        return addEdge(new AssemblyPoint(ori, isAssemblyPoint1), new AssemblyPoint(dest, isAssemblyPoint2), weight);
    }

    public boolean addEdge(AssemblyPoint ori, AssemblyPoint dest, Double weight) {
        return graph.addEdge(ori, dest, weight);
    }

    public double getEdge(AssemblyPoint ori, AssemblyPoint dest) {
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

    public List<AssemblyPoint> getAssemblyPoints() {
        return assemblyPointsList;
    }

    public boolean loadGraph(String MeetingPointsFilePath, String distancesMatrixFilePath) {
        List<AssemblyPoint> listOfAssemblyPoints = loadParkLocationPoints(MeetingPointsFilePath);
        return loadGraphEdges(distancesMatrixFilePath, listOfAssemblyPoints);
    }

    private List<AssemblyPoint> loadParkLocationPoints(String parkLocationsFilePath) {
        List<AssemblyPoint> parkLocationsList = new ArrayList<>();
        try {
            if (graph.numEdges() != 0) {
                graph = new MatrixGraph<>(graph.isDirected());
            }
            File file = new File(parkLocationsFilePath);
            if (!file.getName().endsWith("csv")) {
                throw new IllegalArgumentException("Invalid File Format! Should be <.csv>");
            }
            graph.setName(file.getName());
            Scanner scanner = new Scanner(file);
            String pattern = "AP\\d*";
            Pattern regex = Pattern.compile(pattern);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String seperator = getSeparator(line);
                String[] locations = line.split(seperator);
                for (String location : locations) {
                    Matcher matcher = regex.matcher(location);
                    boolean isAssemblyPoint = matcher.find();
                    AssemblyPoint assemblyPoint = new AssemblyPoint(location.strip(), isAssemblyPoint);
                    if (isAssemblyPoint) {
                        assemblyPointsList.add(assemblyPoint);
                    }
                    graph.addVertex(assemblyPoint);
                    parkLocationsList.add(assemblyPoint);
                }
            }
        } catch (Exception e) {
            graph = new MatrixGraph<>(graph.isDirected());
            e.printStackTrace();

        }
        return parkLocationsList;
    }

    public boolean loadGraphEdges(String distancesMatrixFilePath, List<AssemblyPoint> listOfParkLocations) {
        try {
            if (graph.numEdges() != 0) {
                graph = new MatrixGraph<>(graph.isDirected());
            }
            File file = new File(distancesMatrixFilePath);
            if (!file.getName().endsWith("csv")) {
                throw new IllegalArgumentException("Invalid File Format! Should be <.csv>");
            }
            Scanner scanner = new Scanner(file);
            int lineNumber = 0;
            scanner.reset();
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                lineNumber++;
                String line = scanner.nextLine();
                String separator = getSeparator(line);
                String[] arr = line.split(separator);
                if (arr.length != listOfParkLocations.size()) {
                    throw new IllegalArgumentException("Invalid line entry! Size " + arr.length + ". Line: " + lineNumber);
                }
                for (int i = 0; i < listOfParkLocations.size(); i++) {
                    int distance = Integer.parseInt(extractNumericChars(arr[i]));
                    if (distance != 0) {
                        addEdge(listOfParkLocations.get(lineNumber - 1), listOfParkLocations.get(i), (double) distance);
                    }
                }
            }
            if (lineNumber > listOfParkLocations.size()) {
                throw new IllegalArgumentException("The distance matrix contains more MeetingPoints than the file with the MeetingPoints: " + lineNumber + " > " + listOfParkLocations.size());
            } else if (lineNumber < listOfParkLocations.size()) {
                throw new IllegalArgumentException("The distance matrix contains less MeetingPoints than the file with the MeetingPoints: " + lineNumber + " < " + listOfParkLocations.size());
            }
            graph.setName(file.getName());

        } catch (Exception e) {
            graph = new MatrixGraph<>(graph.isDirected());
            e.printStackTrace();

        }
        return true;
    }

    private String extractNumericChars(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    public boolean getShortestPathsToMeetingPoint() {
        List<Pair<Double, LinkedList<Vertice>>> distanceAndPathSetToReturn = new ArrayList<>();
        for (Vertice vertice : graph.vertices()) {
            double dist = Double.POSITIVE_INFINITY;
            LinkedList<Vertice> shortestPath = new LinkedList<>();
            for (AssemblyPoint assemblyPoint : assemblyPointsList) {
                dist = Double.POSITIVE_INFINITY;
                shortestPath = new LinkedList<>();
                if (!vertice.equals(assemblyPoint)) {
                    LinkedList<Vertice> tempShortestPath = new LinkedList<>();
                    Double tempDist = Algorithms.shortestPath(getCsvGraphCopy(), vertice, assemblyPoint,
                            Comparator.naturalOrder(), Double::sum, 0.0, tempShortestPath);

                    if (tempDist != null) {
                        if (tempDist < dist) {
                            dist = tempDist;
                            shortestPath = tempShortestPath;
                        }
                        distanceAndPathSetToReturn.add(new ImmutablePair<>(dist, shortestPath));
                    }
                }
            }
        }
        distanceAndPathSetToReturn.sort(Comparator.comparing((Pair<Double, LinkedList<Vertice>> p) -> p.getRight().size())
                .thenComparing(Pair::getLeft)
        );
        generateFiles(getCsvGraphCopy(), "FullGraph.png");
        GraphPngAndCsvGenerator.generatePathsCsv(distanceAndPathSetToReturn, graph.getName() + "-PathsToMeetingPoint.csv");
        MatrixGraph<Vertice, Double> graph = new MatrixGraph<>(true);
        graph.setName(this.graph.getName());
        for (Pair<Double, LinkedList<Vertice>> pair : distanceAndPathSetToReturn) {
            double weight = pair.getLeft();
            LinkedList<Vertice> path = pair.getRight();
            for (int i = 1; i < path.size(); i++) {
                Edge<Vertice, Double> edge0 = this.graph.edge(path.get(i - 1), path.get(i));
                if (edge0 == null) {
                    edge0 = this.graph.edge(path.get(i), path.get(i - 1));
                }
                Edge<Vertice, Double> edge1 = graph.edge(path.get(i - 1), path.get(i));
                Edge<Vertice, Double> edge2 = graph.edge(path.get(i), path.get(i - 1));
                if (edge1 == null && edge2 == null) {
                    graph.addEdge(edge0.getVOrig(), edge0.getVDest(), edge0.getWeight());
                }
                if (edge1 != null && edge1.getWeight() > weight) {
                    graph.addEdge(edge0.getVOrig(), edge0.getVDest(), edge0.getWeight());
                }
            }
        }
        generateFiles(graph, "PathsToMeetingPointGraph.png");

        return true;
    }

    private boolean generateFiles(MatrixGraph<Vertice, Double> graph, String fileName) {
        String outputFolder = "output" + File.separator + "us18";
        return GraphPngAndCsvGenerator.generate(graph, fileName, outputFolder);
    }

    private String getSeparator(String line) {
        if (line.contains(";")) {
            return ";";
        } else {
            return ",";
        }
    }
}
