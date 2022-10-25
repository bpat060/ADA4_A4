package A4;

import java.util.Set;

//using interface from chapter 6 of the manual for the graph
public interface Edge<E> {
    // returns the two end vertices (poss same) for this edge as array

    public Vertex<E>[] endVertices();
    // returns the end vertex opposite the specified vertex

    public Vertex<E> oppositeVertex(Vertex<E> vertex);
}
