package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.repository.WaterSuplyPointsCsvRepository;

import java.io.*;
import java.util.*;

public class AsymptoticBehaviorAnalyser {
    public String desktopPath;
    private final String folderPath;
    private final String folderName = "output";

    public AsymptoticBehaviorAnalyser() {
        getDesktopPath();
        this.folderPath = desktopPath + File.separator + folderName;
    }

    private void getDesktopPath() {
        try {
            this.desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Long> startTest(List<Integer> listOfSizes, int iterations) throws IOException {
        Map<String, Long> finalMap = new HashMap<>();
        Map<String, Long> iterationMap;
        for (int i = 0; i < iterations; i++) {
            System.out.println("Iteration: " + (i + 1));
            System.out.println("Generating test CSV's...");
            if (!generateCsvs(listOfSizes)) {
                return null;
            } else {
                System.out.println("Analyzing...");
                iterationMap = readCsvs();
            }
            for (Map.Entry<String, Long> a : iterationMap.entrySet()) {
                if (!finalMap.containsKey(a.getKey())){
                    finalMap.put(a.getKey(), a.getValue());
                } else {
                    finalMap.replace(a.getKey(), finalMap.get(a.getKey()) + a.getValue());
                }
            }
        }
        for (Map.Entry<String, Long> a : finalMap.entrySet()){
            finalMap.replace(a.getKey(), a.getValue()/5);
        }

        return finalMap;
    }

    private Map<String, Long> readCsvs() {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            throw new RuntimeException("Inválid path to the folder");
        }

        Map<String, Long> mapToReturn = new HashMap<>();
        File[] files = folder.listFiles();
        String fileName = "";
        try {
            for (File file : files) {
                fileName = file.getName();
                WaterSuplyPointsCsvRepository tempRepo = new WaterSuplyPointsCsvRepository(true);
                if (tempRepo.loadGraph(file.getAbsolutePath())){
                    long begining = System.currentTimeMillis();
                    Algorithms.minDistGraph(tempRepo.getCsvGraphCopy(), Comparator.naturalOrder());
                    long end = System.currentTimeMillis();
                    mapToReturn.put(file.getName(), (end - begining));
                } else {

                }
            }
        }catch (IllegalArgumentException illegalArgumentException){
            System.out.println(illegalArgumentException.getMessage() + ". " + fileName);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Out of memory! File: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapToReturn;
    }

    private String getRandomIntInString() {
        Random rand = new Random();
        // inteiro random entre 1 e 100000
        return String.valueOf((rand.nextInt((100000 - 1) + 1) + 1));
    }

    private boolean generateCsvs(List<Integer> listOfSizes) throws IOException {
        File folder = new File(desktopPath, folderName);

        try {
            if (!folder.exists()) { // Check if the folder doesn't already exist
                boolean created = folder.mkdir(); // Create the folder
                if (!created) {
                    throw new RuntimeException("Failed to create the folder: " + folderName);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        boolean status = false;
        for (Integer inputSize : listOfSizes) {
            String csvFile = folderPath + File.separator + inputSize.toString() + "inputSizeCSV.csv";
            File file = new File(csvFile);
            // Check if the file exists
            if (file.exists()) {
                // Attempt to delete the file
                if (!file.delete()) {
                    String message = file.exists() ? "is in use by another app" : "does not exist";
                    throw new IOException("Cannot delete file "+ file.getName() +" , because file " + message + ".");
                }
            }
            String header = "Water Point X, Water Point Y, Distance\n";
            try (FileWriter writer = new FileWriter(csvFile, true)) {
                writer.write(header);
                for (int i = 0; i < inputSize; i++) {
                    String line = getRandomIntInString() + ", " + getRandomIntInString() + ", " + getRandomIntInString() + "\n";
                    writer.write(line);
                }
                status = true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return status;
    }

    public boolean generateGraph(List<Map.Entry<String, Long>> entryList) {
        System.out.println("Generating Graph...");
        try {
            File tempFile = File.createTempFile("gnuplot-data", "txt");

            try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))){
                for (Map.Entry<String, Long> entry : entryList){
                    writer.println(entry.getKey() + " " + entry.getValue());
                }
            }

            ProcessBuilder processBuilder = new ProcessBuilder("gnuplot");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()))){
                writer.println("set terminal pngcairo");
                writer.println("set output '"+folderPath + File.separator + "graph.png'");
                writer.println("set title 'Execution Time vs Input Size'");
                writer.println("set xlabel 'Input Size (edges)'");
                writer.println("set ylabel 'Execution Time (ms)'");
                writer.println("plot '" + tempFile.getAbsolutePath() + "' using 1:2 with lines title 'Data'");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
            process.waitFor();

            try {
                String command;

                // Check the operating system
                String os = System.getProperty("os.name").toLowerCase();

                if (os.contains("win")) {
                    // Windows
                    command = "cmd.exe /c start ";
                } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                    // Unix or Linux or Mac
                    command = "open ";
                } else {
                    // Unsupported OS
                    System.out.println("Unsupported operating system.");
                    return false;
                }

                // Path to the image file
                String imagePath = folderPath + File.separator + "graph.png";;

                // Execute the command
                Runtime runtime = Runtime.getRuntime();
                Process process2 = runtime.exec(command + imagePath);
                process2.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }


        }catch (Exception exception){
            exception.printStackTrace();
        }
        return true;
    }
}
