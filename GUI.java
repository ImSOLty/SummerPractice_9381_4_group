import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class GUI extends JFrame{
    GraphInfo info=new GraphInfo();
    GraphView g;
    LogButtons lb;
    public GUI(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Exiting the program, when X pressed
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);//Fullscreen without window pales
        Container container=this.getContentPane();//Container, that involves the content in frame

        g=new GraphView(info);
        lb = new LogButtons(g,info);
        //Left both panels
        JPanel leftpanel = new JPanel();
        leftpanel.setBackground(Color.WHITE);
        leftpanel.setLayout(new GridLayout(2,1,30,30));
        leftpanel.add(info);
        leftpanel.add(lb);

        //Contains everything
        JPanel back = new JPanel();
        back.setBackground(Color.white);
        back.setBorder(new LineBorder(Color.white,30));
        back.setLayout(new GridLayout(1,2,30,30));
        back.add(leftpanel);
        back.add(g);

        container.add(back);

        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open Graph");
        JMenuItem save = new JMenuItem("Save Graph");

        fileMenu.add(open);
        fileMenu.add(save);

        JMenu algorithmMenu = new JMenu("Algorithm");
        JMenuItem algorithmDescription = new JMenuItem("Algorithm description");

        algorithmMenu.add(algorithmDescription);

        JMenu infoMenu = new JMenu("Info");
        JMenuItem authors = new JMenuItem("Authors");
        JMenuItem instructions = new JMenuItem("How to use");

        infoMenu.add(instructions);
        infoMenu.add(authors);

        algorithmDescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Algorithm();
            }
        });

        authors.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Authors();
            }
        });

        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Instructions();
            }
        });

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OpenFile();
            }
        });

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveFile();
            }
        });

        menuBar.add(fileMenu);
        menuBar.add(algorithmMenu);
        menuBar.add(infoMenu);

    }

    void Algorithm(){
        JLabel algInfo = new JLabel();
        algInfo.setText("<html>" +
                "    <h1>Kuhn's algorithm.</h1>" +
                "    <h2><b><i>The algorithm searches for maximal pairwise combination in bipartite graph G[X, Y].</i></b></h2>" +
                "    <p>The algorithm uses the theorem about maximal pair-combination and complementary chains:</b></p>" +
                "    <p>A pairwise combination M in a bipartite graph G is maximal if and only if there is no complementary chain in G.</p>" +
                "    <h2>Algorithm:</h2>" +
                "    <ol>" +
                "        <li>Choose an arbitrary vertex x ∈ X that is not covered by any of the edges of a pairwise combination M," +
                "            and start a depth search by constructing M-interleaved paths from this vertex</li>" +
                "        <li>If at some point there is at least one M complementary path and not all vertices of X" +
                "            are covered by edges of the path - stop the search and invert all edges in the path found, " +
                "            go to step 1, otherwise go to step 3.</li>" +
                "        <li>The maximal pairing is found - the edges included in the pairwise combination M.</li>" +
                "    </ol>" +
                "    <h2>Complexity of the algorithm:</h2>" +
                "    <p>From each vertex we have to traverse all edges - the complexity is O(EV)</p>" +
                "</html>");
        JOptionPane.showMessageDialog(null,algInfo,"Algorithm",JOptionPane.INFORMATION_MESSAGE);
    }

    void Authors(){
        JLabel authorsInfo = new JLabel();
        authorsInfo.setText("<html>\n" +
                "<p><b>The algorithm was implemented by students of St. Petersburg State Electrotechnical University \"LETI\", group 9381:</b></p>\n" +
                "<ul>\n" +
                "    <li>Ignashov Vadim Maksimovich</li>\n" +
                "    <li>Matveev Andrey Nikolaevich</li>\n" +
                "    <li>Semenov Alexander Nikolaevich</li>\n" +
                "</ul>\n" +
                "</html>");
        JOptionPane.showMessageDialog(null,authorsInfo,"Authors",JOptionPane.INFORMATION_MESSAGE);
    }

    void Instructions(){
        JLabel instructionsInfo = new JLabel();
        instructionsInfo.setText("<html>\n" +
                "<h1><b>Instructions for using the program:</b></h1>\n" +
                "<ol>\n" +
                "    <li>\n" +
                "        Fill in the information about the bipartite graph in the upper-left window:\n" +
                "        <ul>\n" +
                "            <li>In the text field to the right of \"First graph part\" specify the number of vertices\n" +
                "                in the left part of the graph, and then name the vertices in the drop-down fields.</li>\n" +
                "            <li>In the text field to the right of \"Second graph part\" specify the number of vertices\n" +
                "                in the right part of the graph, and then name the vertices in the drop-down fields.</li>\n" +
                "            <li>Specify the number of graph edges in the text field to the right of \"Graph edges\", and\n" +
                "                then select the beginning of an edge from the first part of the graph and the end of an\n" +
                "                edge from the second part of the graph in the drop-down fields</li>\n" +
                "        </ul>\n" +
                "        </br>\n" +
                "        <ul>\n" +
                "            <li>To build the graph press the \"Build graph\" button. After that the graph will be built in\n" +
                "                the right part of the application screen and you will not be able to change it anymore.</li>\n" +
                "            <li>If necessary - press \"Reset\" button to reset the built graph.</li>\n" +
                "        </ul>\n" +
                "        <p><b>Warning! You can use up to 20 vertices in each part of graph, the maximum possible number of edges\n" +
                "            is the product of the number of vertices in each part of graph!</b></p>\n" +
                "        <br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Use the following buttons to execute the algorithm:\n" +
                "        <ul>\n" +
                "            <li>\"Algorithm simulation\" - the consecutive execution of the algorithm from the beginning to the end</li>\n" +
                "            <li>\"Step further\" - executing the step of the algorithm</li>\n" +
                "            <li>\"Step back\" - the return of the execution of the step of algorithm</li>\n" +
                "            <li>\"Iteration further\" - the execution of the iteration of algorithm</li>\n" +
                "            <li>\"Iteration back\" - return the execution of the iteration of the algorithm</li>\n" +
                "        </ul>\n" +
                "        <p>Below, in the \"Iteration:\" window the number of executed iterations is specified</p>\n" +
                "        <p>The box to the right of the buttons displays logs of the performed actions</p>\n" +
                "        <br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Use the context menu to open/save the graph:\n" +
                "        <ul>\n" +
                "            <li>\"File\" -> \"Open graph\"</li>\n" +
                "            <li>\"File\" -> \"Save graph\"</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Use the context menu to get information about the algorithm description:\n" +
                "        <ul>\n" +
                "            <li>\"Algorithm\" -> \"Algorithm description\"</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        To get the instructions on using the program, use the context menu:\n" +
                "        <ul>\n" +
                "            <li>\"Info\" -> \"How to use\"</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        To get the information about the authors of the application, use the context menu:\n" +
                "        <ul>\n" +
                "            <li>\"Info\" -> \"Authors\"</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <h2>Thank you for using this application! Have a nice day!</h2>\n" +
                "</ol>\n" +
                "</html>");
        JOptionPane.showMessageDialog(null,instructionsInfo,"Instructions",JOptionPane.INFORMATION_MESSAGE);
    }

    void SaveFile(){
        if(Quna.graph==null){
            return;
        }
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelect = fileChooser.showSaveDialog(null);
        if(userSelect == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            Quna.outputToFile(file.getAbsolutePath());
        }else{
            return;
        }
    }

    void OpenFile(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to open");
        int userSelect = fileChooser.showOpenDialog(null);
        if(userSelect == JFileChooser.APPROVE_OPTION){
            File file = fileChooser.getSelectedFile();
            Quna.graph = Quna.inputGraphFromFile(file.getAbsolutePath());
        }else{
            return;
        }

        ArrayList<Graph.LeftVertex> tmpLeft = Quna.graph.getLeftVertexes();
        ArrayList<Graph.RightVertex> tmpRight = Quna.graph.getRightVertexes();
        ArrayList<Edge> tmpEdges = Quna.graph.getEdges();

        info.first.num.setText(Integer.toString(tmpLeft.size()));
        info.second.num.setText(Integer.toString(tmpRight.size()));
        for(int i=0;i<info.first.showing;i++){
            info.first.vertex[i].text.setText(tmpLeft.get(i).name);
        }
        for(int i=0;i<info.second.showing;i++){
            info.second.vertex[i].text.setText(tmpRight.get(i).name);
        }
        info.edges.num.setText(Integer.toString(tmpEdges.size()));
        for(int i=0;i<info.edges.showing;i++){
            for(int j=0;j<info.first.showing;j++){
                if(tmpEdges.get(i).getLeftVertex().name.equals(info.first.vertex[j].text.getText())) {
                    info.edges.edges[i].first.setSelectedIndex(j);
                    break;
                }
            }
            for(int j=0;j<info.second.showing;j++){
                if(tmpEdges.get(i).getRightVertex().name.equals(info.second.vertex[j].text.getText())) {
                    info.edges.edges[i].second.setSelectedIndex(j);
                    break;
                }
            }
        }
        lb.LogField.setText(Quna.logger.getLogMes().toString());
    }
}
