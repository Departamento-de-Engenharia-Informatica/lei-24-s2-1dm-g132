package pt.ipp.isep.dei.esoft.project.domain.graph;


import pt.ipp.isep.dei.esoft.project.domain.graph.matrix.MatrixGraph;

import java.util.*;
import java.util.function.BinaryOperator;

public class Algorithms {

    /**
     * Performs breadth-first search of a Graph starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex that will be the source of the search
     * @return a LinkedList with the vertices of breadth-first search
     */
    public static <V, E> LinkedList<V> BreadthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }

        LinkedList<V> result = new LinkedList<>();
        boolean[] visited = new boolean[g.vertices().size()];
        LinkedList<V> queue = new LinkedList<>();

        queue.add(vert);
        visited[g.vertices().indexOf(vert)] = true;

        while (!queue.isEmpty()) {
            V currentVertex = queue.poll();
            result.add(currentVertex);

            for (V neighbor : g.adjVertices(currentVertex)) {
                if (!visited[g.vertices().indexOf(neighbor)]) {
                    visited[g.vertices().indexOf(neighbor)] = true;
                    queue.add(neighbor);
                }
            }
        }
        return result;

    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g       Graph instance
     * @param vOrig   vertex of graph g that will be the source of the search
     * @param visited set of previously visited vertices
     * @param qdfs    return LinkedList with vertices of depth-first search
     */
    private static <V, E> void DepthFirstSearch(Graph<V, E> g, V vOrig, boolean[] visited, LinkedList<V> qdfs) {
        if (visited[g.key(vOrig)]) {
            return;
        }
        qdfs.add(vOrig);
        visited[g.key(vOrig)] = true;
        for (V vAdj : g.adjVertices(vOrig)) {
            if (!visited[g.key(vAdj)]) {
                DepthFirstSearch(g, vAdj, visited, qdfs);
            }
        }
    }

    /**
     * Performs depth-first search starting in a vertex
     *
     * @param g    Graph instance
     * @param vert vertex of graph g that will be the source of the search
     * @return a LinkedList with the vertices of depth-first search
     */
    public static <V, E> LinkedList<V> DepthFirstSearch(Graph<V, E> g, V vert) {
        if (!g.validVertex(vert)) {
            return null;
        }
        boolean[] visited = new boolean[g.numVertices()];
        LinkedList<V> qdfs = new LinkedList<>();
        DepthFirstSearch(g, vert, visited, qdfs);
        return qdfs;
    }

    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g       Graph instance
     * @param vOrig   Vertex that will be the source of the path
     * @param vDest   Vertex that will be the end of the path
     * @param visited set of discovered vertices
     * @param path    stack with vertices of the current path (the path is in reverse order)
     * @param paths   ArrayList with all the paths (in correct order)
     */
    private static <V, E> void allPaths(Graph<V, E> g, V vOrig, V vDest, boolean[] visited, LinkedList<V> path, ArrayList<LinkedList<V>> paths) {
        int vOrigKey = g.key(vOrig);

        visited[vOrigKey] = true;
        path.addLast(vOrig);

        if (vOrig.equals(vDest)) {
            paths.add(new LinkedList<>(path));
        } else {
            for (V neighbor : g.adjVertices(vOrig)) {
                int neighborKey = g.key(neighbor);
                if (!visited[neighborKey]) {
                    allPaths(g, neighbor, vDest, visited, path, paths);
                }
            }
        }

        visited[vOrigKey] = false;
        path.removeLast();
    }


    /**
     * Returns all paths from vOrig to vDest
     *
     * @param g     Graph instance
     * @param vOrig information of the Vertex origin
     * @param vDest information of the Vertex destination
     * @return paths ArrayList with all paths from vOrig to vDest
     */
    public static <V, E> ArrayList<LinkedList<V>> allPaths(Graph<V, E> g, V vOrig, V vDest) {

        if (g == null || !g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return null;
        }

        ArrayList<LinkedList<V>> paths = new ArrayList<>();
        boolean[] visited = new boolean[g.numVertices()];
        LinkedList<V> path = new LinkedList<>();

        allPaths(g, vOrig, vDest, visited, path, paths);

        return paths;
    }

    /**
     * Computes shortest-path distance from a source vertex to all reachable
     * vertices of a graph g with non-negative edge weights
     * This implementation uses Dijkstra's algorithm
     *
     * @param g        Graph instance
     * @param vOrig    Vertex that will be the source of the path
     * @param visited  set of previously visited vertices
     * @param pathKeys minimum path vertices keys
     * @param dist     minimum distances
     */
    private static <V, E> void shortestPathDijkstra(Graph<V, E> g, V vOrig, Comparator<E> ce, BinaryOperator<E> sum, E zero, boolean[] visited, V[] pathKeys, E[] dist) {
        int vKey = g.key(vOrig);
        dist[vKey] = zero;
        pathKeys[vKey] = vOrig;
        while (vOrig != null) {
            vKey = g.key(vOrig);
            visited[vKey] = true;
            for (Edge<V, E> edge : g.outgoingEdges(vOrig)) {
                int vKeyAdj = g.key(edge.getVDest());
                if (!visited[vKeyAdj]) {
                    E s = sum.apply(dist[vKey], edge.getWeight());
                    if (dist[vKeyAdj] == null || ce.compare(dist[vKeyAdj], s) > 0) {
                        dist[vKeyAdj] = s;
                        pathKeys[vKeyAdj] = vOrig;
                    }
                }
            }
            E minDist = null;
            vOrig = null;
            for (V vert : g.vertices()) {
                int i = g.key(vert);
                if (!visited[i] && (dist[i] != null) && ((minDist == null) || ce.compare(dist[i], minDist) < 0)) {
                    minDist = dist[i];
                    vOrig = vert;
                }
            }
        }
    }

    private static <V, E> V getVertMinDist(Graph<V, E> g, E[] dist, boolean[] visited, Comparator<E> ce) {
        V minVertex = null;
        E minValue = null;

        for (V vertex : g.vertices()) {
            if (!visited[g.key(vertex)] && (minValue == null || ce.compare(dist[g.key(vertex)], minValue) < 0)) {
                minValue = dist[g.key(vertex)];
                minVertex = vertex;
            }
        }

        return minVertex;
    }


    /**
     * Shortest-path between two vertices
     *
     * @param g         graph
     * @param vOrig     origin vertex
     * @param vDest     destination vertex
     * @param ce        comparator between elements of type E
     * @param sum       sum two elements of type E
     * @param zero      neutral element of the sum in elements of type E
     * @param shortPath returns the vertices which make the shortest path
     * @return if vertices exist in the graph and are connected, true, false otherwise
     */
    @SuppressWarnings("unchecked")
    public static <V, E> E shortestPath(Graph<V, E> g, V vOrig, V vDest, Comparator<E> ce, BinaryOperator<E> sum, E zero, LinkedList<V> shortPath) {

        if (!g.validVertex(vOrig) || !g.validVertex(vDest)) {
            return null;
        }

        if (vOrig == vDest) {
            shortPath.add(vOrig);
            return zero;
        }

        shortPath.clear();
        int numVerts = g.numVertices();
        boolean[] visited = new boolean[numVerts];
        V[] pathKeys = (V[]) new Object[numVerts];
        E[] dist = (E[]) new Object[numVerts];
        initializePathDist(numVerts, pathKeys, dist);

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        E lenghtPath = dist[g.key(vDest)];
        if (lenghtPath != null) {
            getPath(g, vOrig, vDest, pathKeys, shortPath);
            if (lenghtPath.equals(zero)) {
                return null;
            } else {
                return lenghtPath;
            }
        }

        return null;
    }

    public static <V, E> void initializePathDist(int numVerts, V[] pathKeys, E[] dist) {
        for (int i = 0; i < numVerts; i++) {
            dist[i] = null;
            pathKeys[i] = null;
        }
    }


    /**
     * Shortest-path between a vertex and all other vertices
     *
     * @param g     graph
     * @param vOrig start vertex
     * @param ce    comparator between elements of type E
     * @param sum   sum two elements of type E
     * @param zero  neutral element of the sum in elements of type E
     * @param paths returns all the minimum paths
     * @param dists returns the corresponding minimum distances
     * @return if vOrig exists in the graph true, false otherwise
     */
    @SuppressWarnings("unchecked")
    public static <V, E> boolean shortestPaths(Graph<V, E> g, V vOrig,
                                               Comparator<E> ce, BinaryOperator<E> sum, E zero,
                                               ArrayList<LinkedList<V>> paths, ArrayList<E> dists) {

        if (!g.validVertex(vOrig)) {
            return false;
        }

        paths.clear();
        dists.clear();

        int numVerts = g.numVertices();
        boolean[] visited = new boolean[numVerts];

        V[] pathKeys = (V[]) new Object[numVerts];

        E[] dist = (E[]) new Object[numVerts];

        initializePathDist(numVerts, pathKeys, dist);

        shortestPathDijkstra(g, vOrig, ce, sum, zero, visited, pathKeys, dist);

        dists.clear();
        paths.clear();
        for (int i = 0; i < numVerts; i++) {
            paths.add(null);
            dists.add(null);
        }
        for (V vDst : g.vertices()) {
            int i = g.key(vDst);
            if (dist[i] != null) {
                LinkedList<V> shortPath = new LinkedList<>();
                getPath(g, vOrig, vDst, pathKeys, shortPath);
                paths.set(i, shortPath);
                dists.set(i, dist[i]);
            }
        }
        return true;
    }

    /**
     * Extracts from pathKeys the minimum path between voInf and vdInf
     * The path is constructed from the end to the beginning
     *
     * @param g        Graph instance
     * @param vOrig    information of the Vertex origin
     * @param vDest    information of the Vertex destination
     * @param pathKeys minimum path vertices keys
     * @param path     stack with the minimum path (correct order)
     */
    private static <V, E> void getPath(Graph<V, E> g, V vOrig, V vDest, V[] pathKeys, LinkedList<V> path) {

        if (vOrig.equals(vDest)) {
            path.push(vDest);
        } else {
            path.push(vDest);
            int keyVDest = g.key(vDest);
            vDest = pathKeys[keyVDest];
            getPath(g, vOrig, vDest, pathKeys, path);
        }

    }

    /**
     * Calculates the minimum distance graph using Kruskal. Time complexity of O(E*log(E)) where E are the number of
     * Edges of the graph.
     * @param g   initial graph
     * @param ce  comparator between elements of type E
     * @return the minimum distance graph
     */
    public static <V, E> MatrixGraph<V, E> minDistGraph(Graph<V, E> g, Comparator<E> ce) {
        return kruskal(g, ce);
    }

    private static <V, E> MatrixGraph<V, E> kruskal(Graph<V, E> graph, Comparator<E> ce) {
        // Grafo Vazio
        MatrixGraph<V, E> graphToReturn = new MatrixGraph<>(graph.isDirected());

        // Obtém todas as arestas do gráfico e ordena-as por peso
        List<Edge<V, E>> edges = (List<Edge<V, E>>) graph.edges();
        edges.sort((e1, e2) -> ce.compare(e1.getWeight(), e2.getWeight()));

        // Cria um mapa para guardar os componentes conectados
        Map<V, Set<V>> components = new HashMap<>();
        for (V v : graph.vertices()) {
            Set<V> component = new HashSet<>();
            component.add(v);
            components.put(v, component);
        }

        // Itera sobre as arestas ordenadas
        for (Edge<V, E> edge : edges) {
            // Verifica se os vértices estão em diferentes componentes conectados
            if (!components.get(edge.getVOrig()).equals(components.get(edge.getVDest()))) {
                // Se estiverem, adiciona a aresta à MST e funde os componentes
                graphToReturn.addEdge(edge.getVOrig(), edge.getVDest(), edge.getWeight());
                Set<V> mergedComponent = new HashSet<>();
                mergedComponent.addAll(components.get(edge.getVOrig()));
                mergedComponent.addAll(components.get(edge.getVDest()));
                for (V v : mergedComponent) {
                    components.put(v, mergedComponent);
                }
            }
        }

        return graphToReturn;
    }

}