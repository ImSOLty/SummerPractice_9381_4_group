import java.util.ArrayList;

public class Saver  {
    private ArrayList<Step> steps;
    private int index;

    public Saver() {
        steps = new ArrayList<Step>();
        index = 0;
    }

    public void appendGraph(Graph graph, boolean isEndOfIteration, String logMes){
        steps.add(new Step(graph.copyGraph(), isEndOfIteration, logMes));
    }

    public Graph stepForward(){
        if (index == steps.size() - 1) return null;
        return steps.get(++index).graph;
    }

    public Graph stepBack(){
        if(index == 0) return null;
        return steps.get(--index).graph;
    }

    public Step getCurrent(){
        return steps.get(index);
    }

    public Graph iterForward(){
        do {
            if (stepForward() == null) return null;
        } while(!getCurrent().isEndOfIteration);
        return getCurrent().graph;
    }

    public Graph iterBack(){
        do {
            if (stepBack() == null) return null;
        } while(!getCurrent().isEndOfIteration && index > 0);
        return getCurrent().graph;
    }

    public Graph reSet() {
        index = 0;
        return steps.get(index).graph;
    }

    public Graph moveToEnd() {
        index = steps.size() - 1;
        return steps.get(index).graph;
    }

}
