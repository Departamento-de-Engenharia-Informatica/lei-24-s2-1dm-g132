package pt.ipp.isep.dei.esoft.project.repository;

import org.junit.jupiter.api.Test;
import pt.ipp.isep.dei.esoft.project.domain.graph.Algorithms;
import pt.ipp.isep.dei.esoft.project.domain.graph.Edge;
import pt.ipp.isep.dei.esoft.project.domain.graph.Vertice;
import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import static org.junit.jupiter.api.Assertions.*;

public class WaterSuplyPointsCsvRepositoryTest{

    @Test
    void getMinimalCostGraphTest(){
        // Retirado do exercicio 18 a da tp2 de mdisc 23/24
        MatrixGraph<Vertice, Double> inicialGraph = new MatrixGraph<>(false);
        inicialGraph.addEdge(new Vertice("a"), new Vertice("b"), 4.0);
        inicialGraph.addEdge(new Vertice("b"), new Vertice("c"), 2.0);
        inicialGraph.addEdge(new Vertice("c"), new Vertice("d"), 4.0);
        inicialGraph.addEdge(new Vertice("a"), new Vertice("e"), 2.0);
        inicialGraph.addEdge(new Vertice("b"), new Vertice("e"), 1.0);
        inicialGraph.addEdge(new Vertice("c"), new Vertice("f"), 3.0);
        inicialGraph.addEdge(new Vertice("c"), new Vertice("g"), 2.0);
        inicialGraph.addEdge(new Vertice("d"), new Vertice("g"), 3.0);
        inicialGraph.addEdge(new Vertice("e"), new Vertice("f"), 2.0);
        inicialGraph.addEdge(new Vertice("f"), new Vertice("g"), 2.0);
        inicialGraph.addEdge(new Vertice("e"), new Vertice("h"), 1.0);
        inicialGraph.addEdge(new Vertice("f"), new Vertice("h"), 3.0);
        inicialGraph.addEdge(new Vertice("g"), new Vertice("i"), 1.0);
        inicialGraph.addEdge(new Vertice("h"), new Vertice("i"), 2.0);

        MatrixGraph<Vertice, Double> controlGraph = new MatrixGraph<>(false);
        controlGraph.addEdge(new Vertice("b"), new Vertice("e"), 1.0);
        controlGraph.addEdge(new Vertice("b"), new Vertice("c"), 2.0);
        controlGraph.addEdge(new Vertice("e"), new Vertice("h"), 1.0);
        controlGraph.addEdge(new Vertice("e"), new Vertice("a"), 2.0);
        controlGraph.addEdge(new Vertice("e"), new Vertice("f"), 2.0);
        controlGraph.addEdge(new Vertice("g"), new Vertice("d"), 3.0);
        controlGraph.addEdge(new Vertice("g"), new Vertice("i"), 1.0);
        controlGraph.addEdge(new Vertice("g"), new Vertice("c"), 2.0);

        MatrixGraph<Vertice, Double> resultGraph = Algorithms.minDistGraph(inicialGraph, Double::compareTo);

        assertEquals(controlGraph.numEdges(), resultGraph.numEdges());
        for (Edge<Vertice, Double> edge : resultGraph.edges()){
            assertTrue(controlGraph.edges().contains(edge));

        }

    }
}
