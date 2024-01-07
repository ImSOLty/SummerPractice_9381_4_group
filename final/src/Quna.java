import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Quna { // если
    static MyLogger logger = new MyLogger();
    static Saver saver;
    static Graph graph;
    static ArrayList<Edge> reserved;

    public static void algorithm(){
        saver = new Saver();
        saver.appendGraph(graph, true, "Getting of intitial graph\n");

        int k = 0;
        for (Graph.Vertex v : graph.getLeftVertexes()) {
            k++;
            if (v.getMatchingEdge() == null) {
                ArrayList<Edge> list = new ArrayList<Edge>();
                v.setResearchingNow();
                if (getAlternateWay(v, list)) {
                    for (int i = 0; i < list.size() / 2; i++) {
                        list.get(i * 2 + 1).doExcludeFromMatching();
                    }
                    for (int i = 0; i < list.size() / 2 + 1; i++) {
                        list.get(i * 2).doIncludeInMatching();
                    }

                    saver.appendGraph(graph, true, "Doing flasher\n");

                }
                graph.updatePasses();

                if (k < graph.getLeftVertexes().size()) saver.appendGraph(graph, true, null);
                else saver.appendGraph(graph, true, "Max matching is found. End of algorithm\n");

            }
        }
    }




    static boolean getAlternateWay(Graph.Vertex vertex, ArrayList<Edge> list) { // поиск в глубину слева направо
        if (vertex.edges.size() == 0) saver.appendGraph(graph, true, "Viewing vertex " + vertex.getName() + ". This vertex is isolated, therefore it is impossible to build an alternating chain from it\n");
        else {
            if(list.size() == 0)
                saver.appendGraph(graph, false, "Viewing vertex " + vertex.getName() + "\n");
            else
                saver.appendGraph(graph, false, "Adding a new edge " + list.get(list.size()-1) + " to current way\n");
        }
        if (vertex.getPassed()) return false;
        vertex.setPassTrue();
        for (Edge e : vertex.edges) {
            if (!e.isIncludeMatching()) {
                list.add(e);
                Graph.Vertex nextV = e.go(vertex, true);
                if (getAlternateWay_2(nextV, list)) return true;
                e.go(nextV, false);
                list.remove(list.size() - 1);
                if (list.size() == 0) saver.appendGraph(graph, true, "Fails to build an increasing chain from the vertex: " + vertex.getName() + "\n");
                else saver.appendGraph(graph, false, "Delete " + e + " from current way\n");
            }
        }
        vertex.setPassFalse();
        return false;
    }

    static boolean getAlternateWay_2(Graph.Vertex vertex, ArrayList<Edge> list) { // поиск в глубину справа налево
        if (vertex.getPassed()) {
            saver.appendGraph(graph, false, "Adding a new edge " + list.get(list.size()-1) + " to current way." + " Came to deadlock\n");
            return false;
        }
        vertex.setPassTrue();
        if (vertex.getMatchingEdge() == null) {
            saver.appendGraph(graph, false, "Adding a new edge " + list.get(list.size()-1) + " to current way." + " Increasing chain if found\n");
            return true;
        }
        saver.appendGraph(graph, false, "Adding a new edge " + list.get(list.size()-1) + " to current way\n");
        list.add(vertex.getMatchingEdge());
        Graph.Vertex nextV = vertex.getMatchingEdge().go(vertex, true);
        if (getAlternateWay(nextV, list)) return true;
        vertex.getMatchingEdge().go(nextV, false);
        list.remove(list.size() - 1);
        saver.appendGraph(graph, false, "Delete " + vertex.getMatchingEdge() + " from current way\n");
        vertex.setPassFalse();
        return false;
    }

    static void outputToFile(String pathTo)  {
        try {
            File file = new File(pathTo);
            logger.writeLog("Writing to file " + pathTo + "\n");
            PrintWriter pw = new PrintWriter(file); // создаем объект FileReader для объекта File
            //создаем BufferedReader с существующего FileReader для построчного считывания
            pw.println(graph.getLeftVertexes().size());
            for (Graph.Vertex v : graph.getLeftVertexes())
                pw.println(v.getName());
            pw.println(graph.getRightVertexes().size());
            for (Graph.Vertex v : graph.getRightVertexes())
                pw.println(v.getName());
            pw.println(graph.getEdges().size());
            for (Edge e : graph.getEdges()) {
                pw.println(e.getLeftVertex().getName());
                pw.println(e.getRightVertex().getName());
            }
            pw.close();
        }
        catch (FileNotFoundException e){
            logger.writeLog("Cannot find path\n");
        }

    }

    static Graph inputGraphFromFile(String pathFrom){
        logger.writeLog("Creating a graph\n");
        Graph graph = new Graph();

       /* String pathTo = scanner.next();
        scanner.nextLine();*/
        int leftShareCount;
        int rightShareCount;
        int edgesCount;
        try {
            File file = new File(pathFrom);
            logger.writeLog("Reading from file " + pathFrom + "\n");
            FileReader fr = new FileReader(file); //создаем объект FileReader для объекта File
            BufferedReader reader = new BufferedReader(fr); //создаем BufferedReader с существующего FileReader для построчного считывания
            String line = reader.readLine(); // считаем сначала первую строку

            if(line == null) throw new IOException();
            leftShareCount = Integer.parseInt (line);

            for (int i = 0; i < leftShareCount; i++) { // вводим и инициализируем вершины левой доли
                line = reader.readLine();
                if (line == null) throw new EOFException("File data is incorrect");
                Graph.LeftVertex leftV = graph.new LeftVertex(line);
                graph.appendVertex(leftV);
                logger.writeLog("Left part vertex " + line + " is added to graph\n");
            }

            line = reader.readLine();
            if (line == null) throw new EOFException("File data is incorrect");
            rightShareCount = Integer.parseInt(line);

            for (int i = 0; i < rightShareCount; i++) { // вводим и инициализируем вершины правой доли
                line = reader.readLine();
                if (line == null) throw new EOFException("File data is incorrect");
                Graph.RightVertex rightV = graph.new RightVertex(line);
                graph.appendVertex(rightV);
                logger.writeLog("Right part vertex " + line + " is added to graph\n");
            }

            line = reader.readLine();
            if (line == null) throw new EOFException("File data is incorrect");
            edgesCount = Integer.parseInt(line);


            Graph.LeftVertex tmpLeftV;
            Graph.RightVertex tmpRightV;
            for (int i = 0; i < edgesCount; i++) { // вводим ребра
                String left = reader.readLine();
                if (left == null) throw new EOFException("File data is incorrect");
                tmpLeftV = graph.findLeftVertex(left);
                if (tmpLeftV == null) throw new NumberFormatException("Error when reading edge\n");

                String right = reader.readLine();
                if (right == null) throw new EOFException("File data is incorrect");
                tmpRightV = graph.findRightVertex(right);
                if (tmpRightV == null) throw new NumberFormatException("Error when reading edge\n");

                /*// проверка на то что вершины в разных долях
                if(!graph.checkShares(tmpLeftV,tmpRightV)) throw new ExceptionInInitializerError("Not allowed edge\n");*/
                Edge edge = new Edge(tmpLeftV , tmpRightV);
                tmpLeftV.appendEdge(edge);
                tmpRightV.appendEdge(edge);
                graph.appendEdge(edge);
                logger.writeLog("Edge " + tmpLeftV.name + "--" + tmpRightV.name + " is added to graph\n");
            }
        }
        catch (EOFException e){
            logger.writeLog("File data is incorrect. End of file was reached but not all the needed data are got\n");
            System.out.print(logger.getLogMes());
            //e.printStackTrace();
            return null;
        }
        catch (IOException e){
            logger.writeLog("Cannot open file\n");
            System.out.print(logger.getLogMes());
            //e.printStackTrace();
            return null;
        }
        catch(NumberFormatException e) {
            logger.writeLog("File data is incorrect\n");
            System.out.print(logger.getLogMes());
            //e.printStackTrace();
            return null;
        }
        return graph;
    }

}





