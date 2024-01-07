public class Step {
    public Graph graph;
    public boolean isEndOfIteration;
    String logMes;
    Step(Graph graph, boolean isEndOfIteration, String logMes){
        this.graph = graph;
        this.isEndOfIteration = isEndOfIteration;
        this.logMes = logMes;
    }
}
