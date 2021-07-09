
class Edge {
    private Graph.LeftVertex leftV;
    private Graph.RightVertex rightV;
    private boolean includeMatching; // флаг входит ли ребро в паросочетание
    private boolean isResearchingNow;

    public Edge(Graph.LeftVertex leftV, Graph.RightVertex rightV) {
        this.leftV = leftV;
        this.rightV = rightV;
        this.includeMatching = false;
        this.isResearchingNow = false;
    }

    public Edge(Edge toBeCopied, Graph.LeftVertex leftV, Graph.RightVertex rightV) {
        this.leftV = leftV;
        this.rightV = rightV;
        this.includeMatching = toBeCopied.includeMatching;
        this.isResearchingNow = toBeCopied.isResearchingNow;
    }

    public Graph.Vertex go(Graph.Vertex preV) {
        this.setResearchingNow(); //  помечаем текущее ребро как просматриваемое сейчас
        preV.unsetResearchingNow(); // начальную вершину текущего ребра отмечаем как непросматриваемую сейчас
        Graph.Vertex result = preV.getNeedV(leftV, rightV); // получаем конечную вершину текущего ребра
        result.setResearchingNow(); // конечную вершину текущего ребра отмечаем как просматриваемую сейчас
        return result;
    }

    public boolean isIncludeMatching() {
        return includeMatching;
    }

    public void doIncludeInMatching() {
        includeMatching = true;
        leftV.setMatchingEdge(this);
        rightV.setMatchingEdge(this);
    }


    public void doExcludeFromMatching() {
        includeMatching = false;
        leftV.setMatchingEdge(null);
        rightV.setMatchingEdge(null);
    }

    public String toString() {
        return leftV.getName() + "--" + rightV.getName();
    }
    public void setResearchingNow(){
        this.isResearchingNow = true;
    }
    public void unsetResearchingNow(){
        this.isResearchingNow =false;
    }

    public Graph.Vertex getLeftVertex(){
        return this.leftV;
    }
    public Graph.Vertex getRightVertex(){
        return this.rightV;
    }
}
