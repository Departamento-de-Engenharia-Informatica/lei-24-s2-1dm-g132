package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.AsymptoticBehaviorAnalyzerController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AsymptoticBehaviorAnalyzerUI implements Runnable {

    private AsymptoticBehaviorAnalyzerController controller;

    public AsymptoticBehaviorAnalyzerUI() {
        this.controller = new AsymptoticBehaviorAnalyzerController();
    }

    @Override
    public void run() {
        try {
            int iterations;
            int temp = 0;
            do {
                iterations = Utils.readIntegerFromConsole("Number of iterations (0+): ");
                temp++;
                if (temp == 5){
                    System.out.println("5 wrong attempts!");
                }
            }while (iterations <= 0 && temp < 5);
            List<Integer> listOfSizes = new ArrayList<>();
            for (int i = 1; i <= 3; i++){
                listOfSizes.add((int) Math.pow(10, i));
            }
            listOfSizes.add(3000);
            listOfSizes.add(4000);
            // Tamanhos usados:
            //    10
            //   100
            // 1 000
            // 3 000
            // 4 000
            Map<String, Long> resultMap = controller.generateCsv(listOfSizes, iterations);
            List<Map.Entry<String, Long>> entryList = new ArrayList<>(resultMap.entrySet());
            entryList.sort(Comparator.comparingLong(Map.Entry::getValue));
            System.out.println("\n-------------------");
            System.out.println("\nResults:");
            for (Map.Entry<String, Long> a : entryList){
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(a.getKey());
                if (matcher.find()) {
                    // Retrieve the matched number
                    String size = matcher.group();
                    System.out.println("\nSize: " + size);
                    System.out.println("Time: " + a.getValue() + " ms");
                }
            }
        } catch (Exception e) {

        }
    }
}
