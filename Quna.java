import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Quna {
    static MyLogger logger = new MyLogger();
    static Saver saver = new Saver();
    static Graph graph;

    public static void algorithm(){
        if(graph == null) System.exit(0); // для Вадика

        saver.appendGraph(graph, false);
        for (Graph.Vertex v : graph.getLeftVertexes()) {
            if (v.getMatchingEdge() == null) {
                ArrayList<Edge> list = new ArrayList<Edge>();
                v.setResearchingNow();
                //saver.appendGraph(graph, false);
                if (getAlternateWay(v, list)) {
                    for (int i = 0; i < list.size() / 2; i++) {
                        list.get(i * 2 + 1).doExcludeFromMatching();
                    }
                    for (int i = 0; i < list.size() / 2 + 1; i++) {
                        list.get(i * 2).doIncludeInMatching();
                    }

                }
                saver.appendGraph(graph, true);
                graph.updatePasses();
            }
        }
        logger.writeLog("Max matching is found. End of algorithm\n");
        System.out.print(logger.getLogMes());
        graph.printMatching();

    }


    static boolean getAlternateWay(Graph.Vertex vertex, ArrayList<Edge> list) { // поиск в глубину слева направо
        if (vertex.getPassed()) return false;
        vertex.setPassTrue();
        saver.appendGraph(graph, false);
        for (Edge e : vertex.edges) {
            if (!e.isIncludeMatching()) {
                list.add(e);
                saver.appendGraph(graph, false);
                logger.writeLog("Adding edge " + e + " to current matching\n");
                if (getAlternateWay_2(e.go(vertex), list)) return true;
                list.get(list.size() - 1).unsetResearchingNow(); // выключаем
                list.remove(list.size() - 1);
                saver.appendGraph(graph, false);
                logger.writeLog("Delete " + e + "\n" );
            }
        }
        return false;
    }

    static boolean getAlternateWay_2(Graph.Vertex vertex, ArrayList<Edge> list) { // поиск в глубину справа налево
        if (vertex.getPassed()) return false;
        vertex.setPassTrue();
        saver.appendGraph(graph, false);
        if (vertex.getMatchingEdge() == null)  return true;
        list.add(vertex.getMatchingEdge());
        //vertex.getMatchingEdge().setResearchingNow(); // включаем
        logger.writeLog("Adding edge " + vertex.getMatchingEdge() + " to current matching\n");
        if (getAlternateWay(vertex.getMatchingEdge().go(vertex), list)) return true;
        list.get(list.size() - 1).unsetResearchingNow(); // выключаем
        list.remove(list.size() - 1);
        saver.appendGraph(graph, false);
        logger.writeLog("Delete " + vertex.getMatchingEdge() + " \n");
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
            e.printStackTrace();
            return null;
        }
        catch (IOException e){
            logger.writeLog("Cannot open file\n");
            System.out.print(logger.getLogMes());
            e.printStackTrace();
            return null;
        }
        catch(NumberFormatException e) {
            logger.writeLog("File data is incorrect\n");
            System.out.print(logger.getLogMes());
            e.printStackTrace();
            return null;
        }
        return graph;
    }


    static void printGraph(Graph graph) {
        for (Graph.Vertex v : graph.getLeftVertexes()) {
            System.out.print(v.getName() + ": ");
            for (Edge e : v.edges) {
                System.out.print(e.go(v).getName() + ", ");
            }

            System.out.println();
        }
    }


}





