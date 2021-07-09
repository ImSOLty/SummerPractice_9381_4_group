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
                "    <h1>Алгоритм Куна.</h1>" +
                "    <h2><b><i>Алгоритм выполняет поиск максимального паросочетания в двудольном графе G[X, Y].</i></b></h2>" +
                "    <p>Алгоритм использует теорему о максимальном паросочетании и дополняющих цепях:</b></p>" +
                "    <p>Паросочетание M в двудольном графе G является максимальным тогда и только тогда, когда в G нет дополняющей цепи.</p>" +
                "    <h2>Алгоритм:</h2>" +
                "    <ol>" +
                "        <li>Выбираем произвольную вершину x ∈ X, не покрытую никаким из ребер паросочетания М," +
                "            и запускаем поиск в глубину, строя из этой вершины M-чередующиеся пути</li>" +
                "        <li>Если в какой-то момент находится хотя бы один M-дополняющий путь и не все вершины X" +
                "            покрыты ребрами паросочетания - останавливаем поиск и инвертируем все рёбра в найденном пути, " +
                "            переходим к пункту 1. Иначе - переходим к пункту 3.</li>" +
                "        <li>Максимальное паросочетание найдено - ребра входящие в паросочетание M.</li>" +
                "    </ol>" +
                "    <h2>Сложность алгоритма:</h2>" +
                "    <p>Из каждой вершины мы должны пройти по всем ребрам – следовательно, сложность O(EV)</p>" +
                "</html>");
        JOptionPane.showMessageDialog(null,algInfo,"Algorithm",JOptionPane.INFORMATION_MESSAGE);
    }

    void Authors(){
        JLabel authorsInfo = new JLabel();
        authorsInfo.setText("<html>\n" +
                "<p><b>Программа была выполнена студентами группы 9381 СПбГЭУ \"ЛЭТИ\":</b></p>\n" +
                "<ul>\n" +
                "    <li>Игнашов Вадим Максимович</li>\n" +
                "    <li>Матвеев Андрей Николаевич</li>\n" +
                "    <li>Семенов Александр Николаевич</li>\n" +
                "</ul>\n" +
                "</html>");
        JOptionPane.showMessageDialog(null,authorsInfo,"Authors",JOptionPane.INFORMATION_MESSAGE);
    }

    void Instructions(){
        JLabel instructionsInfo = new JLabel();
        instructionsInfo.setText("<html>\n" +
                "<h1><b>Инструкция по пользованию программой:</b></h1>\n" +
                "<ol>\n" +
                "    <li>\n" +
                "        Заполните информацию о двудольном графе в левом-верхнем окне:\n" +
                "        <ul>\n" +
                "            <li>В текстовом поле справа от \"First graph part\" укажите количество вершин в левой доле графа, после чего в выпадающих полях проименуйте вершины.</li>\n" +
                "            <li>В текстовом поле справа от \"Second graph part\" укажите количество вершин в правой доле графа, после чего в выпадающих полях проименуйте вершины.</li>\n" +
                "            <li>В текстовом поле справа от \"Graph edges\" укажите количество ребер графа, после чего в выпадающих полях выберите начало ребра из первой доли и конец ребра из второй доли</li>\n" +
                "        </ul>\n" +
                "        </br>\n" +
                "        <ul>\n" +
                "            <li>Для построения введенного графа - нажмите на кнопку \"Build graph\", после чего в правой части экрана приложения будет построен введенный граф и его изменение будет недоступно.</li>\n" +
                "            <li>В случае необходимости - нажмите кнопку \"Reset\" для сброса построенного графа.</li>\n" +
                "        </ul>\n" +
                "        <p><b>Внимание! Можно использовать до 20 вершин в каждой доле графа, максимально возможное количество ребер - произведение количества вершин в каждой доле!</b></p>\n" +
                "        <br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Для выполнения алгоритма воспользуйтесь следующими кнопками:\n" +
                "        <ul>\n" +
                "            <li>\"Algorithm simulation\" - последовательное выполнение алгоритма от начала до конца</li>\n" +
                "            <li>\"Step further\" - выполнение шага алгоритма</li>\n" +
                "            <li>\"Step back\" - возврат выполнения шага алгоритма</li>\n" +
                "            <li>\"Iteration further\" - выполнение итерации алгоритма</li>\n" +
                "            <li>\"Iteration back\" - возврат выполнения итерации алгоритма</li>\n" +
                "        </ul>\n" +
                "        <p>Ниже, в окошке \"Iteration: \" указывается количество выполненных итераций</p>\n" +
                "        <p>В окошке справа от кнопок выводятся логи выполненных действий</p>\n" +
                "        <br>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Для открытия/сохранения графа воспользуйтесь контекстными меню:\n" +
                "        <ul>\n" +
                "            <li>\"File -> Open graph\"</li>\n" +
                "            <li>\"File -> Save graph\"</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Для получения информации об описании алгоритма воспользуйтесь контекстным меню:\n" +
                "        <ul>\n" +
                "            <li>\"Algorithm -> Algorithm description\"</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Для получения инструкции по пользованию программой воспользуйтесь контекстным меню:\n" +
                "        <ul>\n" +
                "            <li>\"Info -> How to use\"</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <li>\n" +
                "        Для получения информации об авторах приложения воспользуйтесь контекстным меню:\n" +
                "        <ul>\n" +
                "            <li>\"Info -> Authors\"</li>\n" +
                "        </ul>\n" +
                "    </li>\n" +
                "    <h2>Приносим благодарность за использование данного приложения! Приятной работы!</h2>\n" +
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
