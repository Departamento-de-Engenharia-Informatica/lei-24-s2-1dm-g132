package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.domain.graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;
import pt.ipp.isep.dei.esoft.project.repository.WaterSupplyPointsRepository;

import java.io.*;
import java.util.*;

public class AsymptoticBehaviorAnalyser {

    public boolean start(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.isDirectory()) {
            throw new IllegalArgumentException("Invalid folder path!: " + folderPath);
        }
        File [] fileArr = folder.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".csv");
            }
        });
        if (fileArr == null) {
            throw new RuntimeException("Folder is empty");
        } else if (fileArr.length != 30) {
            throw new RuntimeException("Folder does not have 30 files!");
        } else {
            List<File> fileList = new ArrayList<>(List.of(fileArr));
            fileList.sort(Comparator.comparing(File::getName, new IntuitiveStringComparator<>()));
            return readCsvs(fileList);
        }
    }

    private boolean readCsvs(List<File> fileList) {
        File folder = fileList.get(0).getParentFile().getAbsoluteFile();
        List<ExecutionTimeInfo> executionTimeInfoList = new ArrayList<>();
        String fileName = "";
        try {
            for (File file : fileList) {
                fileName = file.getName();
                System.out.println("Reading file: " + fileName);
                WaterSupplyPointsRepository tempRepo = new WaterSupplyPointsRepository(true);
                if (tempRepo.loadGraph(file.getAbsolutePath())) {
                    long begining = System.currentTimeMillis();
                    MatrixGraph<Vertice, Double> graph = Algorithms.minDistGraph(tempRepo.getCsvGraphCopy(), Comparator.naturalOrder());
                    long end = System.currentTimeMillis();
                    ExecutionTimeInfo executionTimeInfo = new ExecutionTimeInfo(
                            fileName,
                            graph.numEdges(),
                            graph.numVertices(),
                            end - begining);
                    executionTimeInfoList.add(executionTimeInfo);
                } else {
                    throw new RuntimeException("Error parsing file! " + file.getAbsolutePath());
                }
            }
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println(illegalArgumentException.getMessage() + ". " + fileName);
        } catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Out of memory! File: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        generateFiles(executionTimeInfoList);
        return true;
    }

    private boolean generateFiles(List<ExecutionTimeInfo> executionTimeInfoList) {
        File baseFolder = new File(getDesktopPath());
        if (setup(baseFolder)) {
            String header = "File Name;Edges;Vertices;Execution Time (ms)\n";
            return generateCsv(executionTimeInfoList, baseFolder, header) && generateGraphPng(executionTimeInfoList, baseFolder);
        } else {
            return false;
        }
    }

    private boolean setup(File baseFolder) {
        String outputFolderPath = baseFolder.getAbsolutePath() + File.separator + "output" + File.separator + "us14";
        File outputFolder = new File(outputFolderPath);
        try {
            if (!outputFolder.exists()) {
                boolean ok = outputFolder.mkdirs();
                if (!ok) {
                    throw new RuntimeException("Error creating " + outputFolderPath);
                } else {
                    System.out.println("Created: " + outputFolderPath);
                }
            } else {
                if (outputFolder.listFiles() != null) {
                    for (File a : outputFolder.listFiles()) {
                        boolean ok = a.delete();
                        if (!ok) {
                            throw new RuntimeException("Error deleting " + a.getAbsolutePath() +
                                    "\nMaybe check if the file is open by another program...");
                        } else {
                            System.out.println("Deleted: " + a.getAbsolutePath());
                        }
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

    private boolean generateCsv(List<ExecutionTimeInfo> executionTimeInfoList, File baseFolder, String header) {
        String outputFolderPath = baseFolder.getAbsolutePath() + File.separator + "output" + File.separator + "us14";
        String csvPath = outputFolderPath + File.separator + "AsymptoticBehavior.csv";
        File csvFile = new File(csvPath);
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.write(header);
            for (ExecutionTimeInfo a : executionTimeInfoList) {
                writer.write(a.getFilename() + ";" + a.getNumEdges() + ";" + a.getNumVertices() + ";" + a.getExecutionTime() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        openFile(csvPath);
        return true;
    }

    public boolean generateGraphPng(List<ExecutionTimeInfo> executionTimeInfos, File baseFolder) {
        String outputFolderPath = baseFolder.getAbsolutePath() + File.separator + "output" + File.separator + "us14";
        String imagePath = outputFolderPath + File.separator + "AsymptoticBehaviorGraph.png";

        System.out.println("Generating Graph PNG...");
        try {
            File tempFile = File.createTempFile("gnuplot-data", "txt");

            try (PrintWriter writer = new PrintWriter(new FileWriter(tempFile))) {
                for (ExecutionTimeInfo entry : executionTimeInfos) {
                    writer.println(entry.numEdges + " " + entry.getExecutionTime());
                }
            }

            ProcessBuilder processBuilder = new ProcessBuilder("gnuplot");
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            try (PrintWriter writer = new PrintWriter(new OutputStreamWriter(process.getOutputStream()))) {
                writer.println("set terminal pngcairo");
                writer.println("set output '" + imagePath + "'");
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

            openFile(imagePath);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return true;
    }

    private static int extractNumericValue(String fileName) {
        // Extract the numeric part (e.g., "file11" -> 11)
        String numericPart = fileName.replaceAll("[^0-9]", "");
        return Integer.parseInt(numericPart);
    }

    private boolean openFile(String filePath) {

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

            // Execute the command
            Runtime runtime = Runtime.getRuntime();
            Process process2 = runtime.exec(command + filePath);
            process2.waitFor();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    private String getDesktopPath() {
        try {
            return System.getProperty("user.home") + File.separator + "Desktop";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static class ExecutionTimeInfo {
        protected String filename;
        protected int numEdges;
        protected int numVertices;
        protected long executionTime;

        public ExecutionTimeInfo(String filename, int numEdges, int numVertices, long executionTime) {
            this.filename = filename;
            this.numEdges = numEdges;
            this.numVertices = numVertices;
            this.executionTime = executionTime;
        }

        public String getFilename() {
            return filename;
        }

        public int getNumEdges() {
            return numEdges;
        }

        public int getNumVertices() {
            return numVertices;
        }

        public long getExecutionTime() {
            return executionTime;
        }
    }
}
