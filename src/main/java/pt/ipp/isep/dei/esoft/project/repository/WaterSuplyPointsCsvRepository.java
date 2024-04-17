package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.domain.graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WaterSuplyPointsCsvRepository {

    // Para já, considerei que cada edge é uma String e o custo é um Double, podem mudar mais tarde se for o caso.
    private MatrixGraph<Vertice, Double> csvGraph;

    public WaterSuplyPointsCsvRepository(boolean directed) {
        csvGraph = new MatrixGraph<>(directed);
    }

    public WaterSuplyPointsCsvRepository() {
        csvGraph = new MatrixGraph<>(true);
    }

    public boolean addEdge(String ori, String dest, Double weight) {
        return csvGraph.addEdge(new Vertice(ori), new Vertice(dest), weight);
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
            File file = new File(filePath);
            String reverseName = reverseString(file.getName());
            String[] tokens = reverseName.split("\\.");
            String extensao = reverseString(tokens[0]);
            if (!extensao.equals("csv")) {
                throw new RuntimeException("Invalid File Format! Should be <.csv>");
            }
            Scanner scanner = new Scanner(file);
            int i = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] args = line.split(",");
                if (i > 0){
                    if (args.length != 3) {
                        throw new RuntimeException("Invalid Entry: <" + line + ">, Should be <arg, arg, arg");
                    } else {
                        String ori = args[0].strip();
                        String dest = args[1].strip();
                        double weight = Double.parseDouble(args[2].strip());
                        addEdge(ori, dest, weight);
                    }
                }
                i++;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static String reverseString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] charArray = str.toCharArray();

        int left = 0;
        int right = charArray.length - 1;

        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }

        return new String(charArray);
    }

    public List<String> getMinimalCostGraph() {
        System.out.println(csvGraph);
        List<String> listToReturn = new ArrayList<>();
        MatrixGraph<Vertice, Double> graph = Algorithms.minDistGraph(getCsvGraphCopy(), Double::compareTo);
        for (Edge<Vertice, Double> edge : graph.edges()) {
            String entry = edge.getVOrig() + " <-> " + edge.getVDest() + ", cost: " + edge.getWeight();
            if (listToReturn.isEmpty()){
                listToReturn.add(entry);
            } else {
                String entry2 = edge.getVDest() + " <-> " + edge.getVOrig() + ", cost: " + edge.getWeight();
                if (!listToReturn.contains(entry) && !listToReturn.contains(entry2)){
                    listToReturn.add(entry);
                }
            }
        }
        return listToReturn;
    }
}
