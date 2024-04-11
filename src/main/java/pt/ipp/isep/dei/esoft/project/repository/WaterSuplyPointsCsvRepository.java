package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WaterSuplyPointsCsvRepository {

    // Para já, considerei que cada edge é uma String e o custo é um Double, podem mudar mais tarde se for o caso.
    private MatrixGraph<String, Double> csvGraph = new MatrixGraph<>(false);

    private boolean addEdjge(String ori, String dest, Double weight) {
        return csvGraph.addEdge(ori, dest, weight);
    }

    public double getEdgeWeight(String ori, String dest) {
        Double temp = csvGraph.edge(ori, dest).getWeight();
        if (temp != null) {
            return temp;
        } else {
            return 0;
        }
    }

    public MatrixGraph<String, Double> getCsvGraphCopy() {
        return csvGraph.clone();
    }

    public boolean loadGraph(String filePath) {
        try {
            File file = new File(filePath);
            String reverseName = reverseString(file.getName());
            String [] tokens = reverseName.split("\\.");
            String extensao = reverseString(tokens[0]);
            if (!extensao.equals("csv")) {
                throw new RuntimeException("Inválid File Format! Should be <.csv>");
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] args = line.split(",");
                if (args.length != 3) {
                    throw new RuntimeException("Invalid Entry: <" + line + ">, Should be <arg, arg, arg");
                } else {
                    String ori = args[0].strip();
                    String dest = args[1].strip();
                    double weight = Double.parseDouble(args[2].strip());
                    csvGraph.addEdge(ori, dest, weight);
                }
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
}
