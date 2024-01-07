import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LogButtons extends JPanel {
    public Button BuildGraph = new Button("Build graph");
    public Button Reset = new Button("Reset");
    public Button AlgSimulation = new Button("Algorithm simulation");
    public Button AlgStepFurther = new Button("Step further");
    public Button AlgStepBack = new Button("Step back");
    public Button AlgIterFurther = new Button("Iteration further");
    public Button AlgIterBack = new Button("Iteration back");
    //public JLabel Iteration = new JLabel("Iteration: ");
    public JTextArea LogField = new JTextArea("Log field...");
    public JScrollPane scroll;

    public GraphView graph;
    public GraphInfo info;
    public LogButtons(GraphView g, GraphInfo gi){
        graph=g;
        info=gi;
        LogField.setEditable(false);
        LogField.setLineWrap(true);
        blockUnblock(true);

        this.setBorder(new LineBorder(Color.black,1));
        setLayout(new GridLayout(1,1));
        this.setBackground(Color.white);
        JPanel back =new JPanel();
        back.setBackground(Color.white);
        back.setBorder(new LineBorder(Color.WHITE,10));
        back.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel buttonsUp =new JPanel();

        JPanel buttonsDown = new JPanel();
        buttonsUp.setBackground(Color.WHITE);
        buttonsUp.setLayout(new GridLayout(2,1,10,10));
        buttonsDown.setBackground(Color.WHITE);
        buttonsDown.setLayout(new GridLayout(6,1,10,10));
        ImproveButtonGraphics(BuildGraph);
        buttonsUp.add(BuildGraph);
        ImproveButtonGraphics(Reset);
        buttonsUp.add(Reset);
        ImproveButtonGraphics(AlgSimulation);
        buttonsDown.add(AlgSimulation);
        ImproveButtonGraphics(AlgStepFurther);
        buttonsDown.add(AlgStepFurther);
        ImproveButtonGraphics(AlgStepBack);
        buttonsDown.add(AlgStepBack);
        ImproveButtonGraphics(AlgIterFurther);
        buttonsDown.add(AlgIterFurther);
        ImproveButtonGraphics(AlgIterBack);
        buttonsDown.add(AlgIterBack);

        //Iteration.setFont(new Font("Arial",Font.BOLD,20));

        //buttonsDown.add(Iteration);

        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weightx=2;
        gbc.weighty=2;
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.gridheight=1;
        gbc.gridwidth=1;
        back.add(buttonsUp,gbc);
        gbc.weightx=2;
        gbc.weighty=2;
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.gridheight=1;
        gbc.gridwidth=1;
        back.add(buttonsDown,gbc);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx=8;
        gbc.weighty=8;
        gbc.gridx=1;
        gbc.gridy=0;
        gbc.gridheight=2;
        gbc.gridwidth=3;

        scroll = new JScrollPane(LogField);
        scroll.setBorder(new LineBorder(Color.BLACK,1));
        LogField.setBorder(new LineBorder(Color.WHITE,10));
        back.add(scroll,gbc);

        BuildGraph.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGraph();
            }
        });

        AlgSimulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                offAllButtons();

                Quna.graph = Quna.saver.reSet();

                Timer timer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        Graph tmpGraph = Quna.saver.stepForward();
                        if(tmpGraph == null) {
                            onAllButtons();
                            ((Timer)ev.getSource()).stop();
                        } else {
                            Quna.graph = tmpGraph;
                            Quna.logger.writeLog(Quna.saver.getCurrent().logMes);
                            LogField.setText(Quna.logger.getLogMes().toString());
                            graph.repaint();
                        }
                    }
                });
                timer.start();
            }
        });

        AlgStepFurther.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graph tmpGraph = Quna.saver.stepForward();
                if(tmpGraph != null) {
                    Quna.graph = tmpGraph;
                    Quna.logger.writeLog(Quna.saver.getCurrent().logMes);
                    LogField.setText(Quna.logger.getLogMes().toString());
                }
                graph.repaint();
            }
        });

        AlgStepBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graph tmpGraph = Quna.saver.stepBack();
                if(tmpGraph != null) {
                    Quna.graph = tmpGraph;
                    Quna.logger.writeLog(Quna.saver.getCurrent().logMes);
                    LogField.setText(Quna.logger.getLogMes().toString());
                }
                graph.repaint();
            }
        });

        AlgIterFurther.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graph tmpGraph = Quna.saver.iterForward();
                if(tmpGraph != null) {
                    Quna.graph = tmpGraph;
                    Quna.logger.writeLog(Quna.saver.getCurrent().logMes);
                    LogField.setText(Quna.logger.getLogMes().toString());
                }
                graph.repaint();
            }
        });

        AlgIterBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Graph tmpGraph = Quna.saver.iterBack();
                if(tmpGraph != null) {
                    Quna.graph = tmpGraph;
                    Quna.logger.writeLog(Quna.saver.getCurrent().logMes);
                    LogField.setText(Quna.logger.getLogMes().toString());
                }
                graph.repaint();
            }
        });






        Reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clear();
            }
        });

        add(back);
    }
    void ImproveButtonGraphics(Button b){
        b.setFont(new Font("Arial",Font.BOLD,20));
        b.setBackground(Color.LIGHT_GRAY);
    }

    void setGraph(){ // отвечает за отрисовку графа
        switch(checkInfo()){
            case NO_VERTEX_FIRST:
                JOptionPane.showMessageDialog(null,"There are no vertices in first graph part!","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            case NO_VERTEX_SECOND:
                JOptionPane.showMessageDialog(null,"There are no vertices in second graph part!","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            case EMPTY_VERTEX_FIRST:
                JOptionPane.showMessageDialog(null,"There is an uninitialized vertex in first graph part!","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            case EMPTY_VERTEX_SECOND:
                JOptionPane.showMessageDialog(null,"There is an uninitialized vertex in second graph part!","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            case SIMILAR_IN_BOTH:
                JOptionPane.showMessageDialog(null,"There are similar vertices in both graph parts!","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            case SIMILAR_IN_FIRST:
                JOptionPane.showMessageDialog(null,"There are similar vertices in first graph part!","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            case SIMILAR_IN_SECOND:
                JOptionPane.showMessageDialog(null,"There are similar vertices in second graph part!","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            case NO_EDGES:
                JOptionPane.showMessageDialog(null,"There are no edges in graph!","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            case SIMILAR_EDGES:
                JOptionPane.showMessageDialog(null,"There are similar edges in graph!","Error!",JOptionPane.ERROR_MESSAGE);
                return;
            case NONE:
                break;
        }
        Quna.graph = new Graph();
        ArrayList<Graph.LeftVertex> lv = new ArrayList<Graph.LeftVertex>();
        ArrayList<Graph.RightVertex> rv = new ArrayList<Graph.RightVertex>();
        ArrayList<Edge> ed = new ArrayList<Edge>();
        for(int i=0;i<info.first.showing;i++){
            lv.add(Quna.graph.new LeftVertex(info.first.vertex[i].text.getText()));
        }
        for(int i=0;i<info.second.showing;i++){
            rv.add(Quna.graph.new RightVertex(info.second.vertex[i].text.getText()));
        }

        for(int i=0;i<info.edges.showing;i++){
            int tmp1 = info.edges.edges[i].first.getSelectedIndex();
            int tmp2 = info.edges.edges[i].second.getSelectedIndex();
            Edge e = new Edge(lv.get(tmp1),rv.get(tmp2));
            lv.get(tmp1).appendEdge(e);
            rv.get(tmp2).appendEdge(e);
            ed.add(e);

        }
        Quna.graph = new Graph(lv,rv,ed);
        Quna.algorithm();

        LogField.setText(Quna.logger.getLogMes().toString());
        graph.setVisible(true);
        blockUnblock(false);
        Quna.graph = Quna.saver.reSet();
        graph.repaint();
    }

    void Clear(){
        graph.setVisible(false);
        blockUnblock(true);
    }

    Errors checkInfo(){
        if(info.first.showing==0){
            return Errors.NO_VERTEX_FIRST;
        }
        if(info.second.showing==0){
            return Errors.NO_VERTEX_SECOND;
        }
        if(info.edges.showing==0){
            return Errors.NO_EDGES;
        }
        for(int i=0;i<info.first.showing;i++){
            if(info.first.vertex[i].text.getText().equals("")){
                return Errors.EMPTY_VERTEX_FIRST;
            }
            for(int j=i+1;j<info.first.showing;j++){
                if(info.first.vertex[i].text.getText().equals(info.first.vertex[j].text.getText())){
                    return Errors.SIMILAR_IN_FIRST;
                }
            }
        }
        for(int i=0;i<info.second.showing;i++){
            if(info.second.vertex[i].text.getText().equals("")){
                return Errors.EMPTY_VERTEX_SECOND;
            }
            for(int j=i+1;j<info.second.showing;j++){
                if(info.second.vertex[i].text.getText().equals(info.second.vertex[j].text.getText())){
                    return Errors.SIMILAR_IN_SECOND;
                }
            }
        }
        for(int i=0;i<info.first.showing;i++){
            for(int j=0;j<info.second.showing;j++){
                if(info.first.vertex[i].text.getText().equals(info.second.vertex[j].text.getText())){
                    return Errors.SIMILAR_IN_BOTH;
                }
            }
        }
        for(int i=0;i<info.edges.showing;i++){
            for(int j=i+1;j<info.edges.showing;j++){
                if(info.edges.edges[i].first.getSelectedIndex()==info.edges.edges[j].first.getSelectedIndex()&&
                        info.edges.edges[i].second.getSelectedIndex()==info.edges.edges[j].second.getSelectedIndex()){
                    return Errors.SIMILAR_EDGES;
                }
            }
        }
        return Errors.NONE;
    }

    void blockUnblock(boolean b){
        info.first.num.setEditable(b);
        for(int i=0;i<info.first.MAX_SIZE;i++){
            info.first.vertex[i].text.setEditable(b);
        }
        info.second.num.setEditable(b);
        for(int i=0;i<info.second.MAX_SIZE;i++){
            info.second.vertex[i].text.setEditable(b);
        }
        info.edges.num.setEditable(b);
        for(int i=0;i<info.edges.MAX_SIZE;i++){
            info.edges.edges[i].first.setEnabled(b);
            info.edges.edges[i].second.setEnabled(b);
        }
        AlgSimulation.setEnabled(!b);
        AlgStepFurther.setEnabled(!b);
        AlgStepBack.setEnabled(!b);
        AlgIterFurther.setEnabled(!b);
        AlgIterBack.setEnabled(!b);

        Reset.setEnabled(!b);
        BuildGraph.setEnabled(b);
        if (GUI.fileMenu != null) GUI.fileMenu.setEnabled(b);
    }

    void offAllButtons() {
        AlgSimulation.setEnabled(false);
        AlgStepFurther.setEnabled(false);
        AlgStepBack.setEnabled(false);
        AlgIterFurther.setEnabled(false);
        AlgIterBack.setEnabled(false);
        Reset.setEnabled(false);
        BuildGraph.setEnabled(false);
    }

    void onAllButtons() {
        AlgSimulation.setEnabled(true);
        AlgStepFurther.setEnabled(true);
        AlgStepBack.setEnabled(true);
        AlgIterFurther.setEnabled(true);
        AlgIterBack.setEnabled(true);
        Reset.setEnabled(true);
        BuildGraph.setEnabled(false);
    }

}
