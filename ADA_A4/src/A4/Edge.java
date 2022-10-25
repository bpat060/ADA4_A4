package A4;

/**
 * An interface that represents an undirected and unweighted edge in a graph
 * which holds elements of type E in its vertices
 *
 * @see GraphADT.java
 */
import java.util.Set;

//using interface from chapter 6 of the manual for the graph
public interface Edge<E> {
    // returns the two end vertices (poss same) for this edge as array

    public Vertex<E>[] endVertices();
    // returns the end vertex opposite the specified vertex

    public Vertex<E> oppositeVertex(Vertex<E> vertex);
}
