public class Step {
    public Graph graph;
    public boolean isEndOfIteration;
    Step(Graph graph, boolean isEndOfIteration){
        this.graph = graph;
        this.isEndOfIteration = isEndOfIteration;
    }
}
