package pt.ipp.isep.dei.esoft.project.domain;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
import pt.ipp.isep.dei.esoft.project.domain.graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GraphPngGenerator {

    public boolean generateGraphSim(MatrixGraph<Vertice, Double> graph, String fileName) {
        String graphFolderPath = getDesktopPath() + File.separator + "output" + File.separator + "us13";
        String graphPath = graphFolderPath + File.separator + graph.getName() + "-" + fileName;
        setupDirs(graph, fileName, graphFolderPath);

        System.setProperty("org.graphstream.ui", "swing");
        Graph tempGraph = new SingleGraph("Water Supply Points");
        tempGraph.addAttribute("ui.quality");
        tempGraph.addAttribute("ui.antialias");
        tempGraph.addAttribute("layout.quality", 4);
        tempGraph.addAttribute("layout.stabilization-limit", 1);

        for (Edge<Vertice, Double> edge : graph.edges()) {
            String ori = edge.getVOrig().getNome();
            String dest = edge.getVDest().getNome();
            double price = edge.getWeight(); // Assuming the weight represents the price

            Node oriNode;
            if (tempGraph.getNode(ori) == null) {
                oriNode = tempGraph.addNode(ori);
                oriNode.setAttribute("ui.label", ori); // Display node identifier
                oriNode.setAttribute("layout.weight", 10);
            }

            Node destNode;
            if (tempGraph.getNode(dest) == null) {
                destNode = tempGraph.addNode(dest);
                destNode.setAttribute("ui.label", dest); // Display node identifier
                destNode.setAttribute("layout.weight", 10);
            }

            org.graphstream.graph.Edge e = tempGraph.addEdge(ori + "-" + dest, ori, dest, true);
            e.setAttribute("ui.label", price); // Remove decimals
            e.setAttribute("layout.weight", 4);
        }

        tempGraph.setAttribute("ui.stylesheet", "node { fill-color: red; size: 20px; text-size: 16; text-color: black; } " +
                "edge { fill-color: black; size: 2px; text-size: 16; text-color: black; }");

        Viewer viewer = tempGraph.display();
        viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.HIDE_ONLY);
        ViewPanel viewPanel = viewer.getDefaultView();
        viewPanel.setLayout(new BorderLayout());

        JButton button = new JButton("Save Image");
        button.addActionListener(e -> {
            System.out.println("Button clicked!");
            tempGraph.addAttribute("ui.screenshot", graphPath);
            System.out.println("Image saved in: " + graphPath);
        });
        button.setMaximumSize(new Dimension(150, 30));
        button.setHorizontalAlignment(SwingConstants.CENTER);
        viewPanel.add(button, BorderLayout.NORTH);

        JLabel customTextLabel = new JLabel("Cost " + graph.cost());
        customTextLabel.setHorizontalAlignment(SwingConstants.LEFT);
        customTextLabel.setVerticalAlignment(SwingConstants.TOP);
        viewPanel.add(customTextLabel, BorderLayout.SOUTH);


        return true;
    }

    private boolean setupDirs(MatrixGraph<Vertice, Double> graph, String fileName, String graphFolderPath) {
        // setup directories
        File graphFolder = new File(graphFolderPath);
        try {
            if (!graphFolder.exists()) {
                if (!graphFolder.mkdir()) {
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

    private String getDesktopPath() {
        try {
            return System.getProperty("user.home") + File.separator + "Desktop";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
