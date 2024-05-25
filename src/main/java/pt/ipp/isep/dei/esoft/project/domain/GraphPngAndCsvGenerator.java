package pt.ipp.isep.dei.esoft.project.domain;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import pt.ipp.isep.dei.esoft.project.domain.graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphPngAndCsvGenerator {

    public boolean generate(MatrixGraph<Vertice, Double> graph, String fileName, String desktopOutputFolder) {
        String graphFolderPath = getDesktopPath() + File.separator + desktopOutputFolder;
        String graphPath = graphFolderPath + File.separator + graph.getName() + "-" + fileName;
        setupDirs(graph, fileName, graphFolderPath);
        generateCsvOfGraph(graph, desktopOutputFolder,graph.getName()+"-"+fileName.replace(".png", ""));

        System.setProperty("org.graphstream.ui", "javafx");
        //System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

        Graph tempGraph = new SingleGraph("Water Supply Points");
        tempGraph.setAttribute("ui.quality");
        tempGraph.setAttribute("ui.antialias");
        tempGraph.setAttribute("layout.quality", 4);
        tempGraph.setAttribute("layout.stabilization-limit", 1);

        for (Edge<Vertice, Double> edge : graph.edges()) {
            String ori = edge.getVOrig().getNome();
            String dest = edge.getVDest().getNome();
            double price = edge.getWeight();

            Node oriNode;
            if (tempGraph.getNode(ori) == null) {
                oriNode = tempGraph.addNode(ori);
                oriNode.setAttribute("ui.label", ori);
                oriNode.setAttribute("layout.weight", 20);
            }

            Node destNode;
            if (tempGraph.getNode(dest) == null) {
                destNode = tempGraph.addNode(dest);
                destNode.setAttribute("ui.label", dest);
                destNode.setAttribute("layout.weight", 20);
            }

            org.graphstream.graph.Edge e = tempGraph.addEdge(ori + "-" + dest, ori, dest, true);
            e.setAttribute("ui.label", price); // Remove decimals
            e.setAttribute("layout.weight", 4);
        }

        tempGraph.setAttribute("ui.stylesheet", "node { fill-color: red; size: 20px; text-size: 16; text-color: red; } " +
                "edge { fill-color: black; size: 2px; text-size: 16; text-color: black; }");


        JFrame frame = new JFrame(fileName + " - " + graph.getName());
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setSize(new Dimension(600, 600));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Viewer viewer = new Viewer(tempGraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();
        View view = viewer.addDefaultView(false);
        frame.add((Component) view, BorderLayout.CENTER);

        JButton button = new JButton("Save Image");
        button.addActionListener(e -> {
            System.out.println("Button clicked!");
            tempGraph.setAttribute("ui.screenshot", graphPath);
            System.out.println("Image saved in: " + graphPath);
        });
        button.setMaximumSize(new Dimension(150, 30));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(button, BorderLayout.NORTH);

        JLabel customTextLabel = new JLabel("Cost " + graph.cost());
        customTextLabel.setHorizontalAlignment(SwingConstants.LEFT);
        customTextLabel.setVerticalAlignment(SwingConstants.TOP);
        frame.add(customTextLabel, BorderLayout.SOUTH);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                viewer.close();
                System.out.println("Press Enter To Continue...");
            }
        });

        frame.setVisible(true);
        return true;
    }

    private boolean setupDirs(MatrixGraph<Vertice, Double> graph, String fileName, String graphFolderPath) {
        // setup directories
        File graphFolder = new File(graphFolderPath);
        try {
            if (!graphFolder.exists()) {
                if (!graphFolder.mkdirs()) {
                    throw new RuntimeException("Failed to create folder: " + graphFolderPath);
                } else {
                    System.out.println("Created: " + graphFolderPath);
                }
            } else {
                File a = new File(graphFolderPath + File.separator + graph.name + "-" + fileName);
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
        return true;
    }

    private boolean generateCsvOfGraph(MatrixGraph<Vertice, Double> graph, String desktopOutputFolder, String fileName) {
        String graphFolderPath = getDesktopPath() + File.separator + desktopOutputFolder;
        File graphFolder = new File(graphFolderPath);
        try {
            if (!graphFolder.exists()) {
                if (!graphFolder.mkdirs()) {
                    throw new RuntimeException("Failed to create folder: " + graphFolderPath);
                } else {
                    System.out.println("Created: " + graphFolderPath);
                }
            } else {
                File a = new File(graphFolderPath + File.separator + fileName);
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
        File csvFile = new File(graphFolderPath + File.separator + fileName + ".csv");
        try {
            fileWriter = new FileWriter(csvFile);
            for (Edge<Vertice, Double> edge : graph.edges()){
                fileWriter.append(edge.getVOrig().getNome())
                        .append(";")
                        .append(edge.getVDest().getNome())
                        .append(";")
                        .append(String.valueOf(edge.getWeight()))
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

    private String getDesktopPath() {
        try {
            return System.getProperty("user.home") + File.separator + "Desktop";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
