package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.domain.graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import static org.junit.jupiter.api.Assertions.*;

public class WaterSuplyPointsCsvRepositoryTest{

    @Test
    void getMinimalCostGraphTest(){
        // Retirado do exercicio 18 a da tp2 de mdisc 23/24
        MatrixGraph<String, Double> inicialGraph = new MatrixGraph<>(false);
        inicialGraph.addEdge("a", "b", 4.0);
        inicialGraph.addEdge("b", "c", 2.0);
        inicialGraph.addEdge("c", "d", 4.0);
        inicialGraph.addEdge("a", "e", 2.0);
        inicialGraph.addEdge("b", "e", 1.0);
        inicialGraph.addEdge("c", "f", 3.0);
        inicialGraph.addEdge("c", "g", 2.0);
        inicialGraph.addEdge("d", "g", 3.0);
        inicialGraph.addEdge("e", "f", 2.0);
        inicialGraph.addEdge("f", "g", 2.0);
        inicialGraph.addEdge("e", "h", 1.0);
        inicialGraph.addEdge("f", "h", 3.0);
        inicialGraph.addEdge("g", "i", 1.0);
        inicialGraph.addEdge("h", "i", 2.0);

        MatrixGraph<String, Double> controlGraph = new MatrixGraph<>(false);
        controlGraph.addEdge("b", "e", 1.0);
        controlGraph.addEdge("b", "c", 2.0);
        controlGraph.addEdge("e", "h", 1.0);
        controlGraph.addEdge("e", "a", 2.0);
        controlGraph.addEdge("e", "f", 2.0);
        controlGraph.addEdge("g", "d", 3.0);
        controlGraph.addEdge("g", "i", 1.0);
        controlGraph.addEdge("g", "c", 2.0);

        MatrixGraph<String, Double> resultGraph = Algorithms.minDistGraph(inicialGraph, Double::compareTo);

        assertEquals(controlGraph.numEdges(), resultGraph.numEdges());
        for (Edge<String, Double> edge : resultGraph.edges()){
            assertTrue(controlGraph.edges().contains(edge));

        }

    }
}
