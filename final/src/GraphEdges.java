import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class GraphEdges extends JPanel {
    static int MAX_SIZE =400;
    public JLabel name = new JLabel("Graph edges");
    public JTextField num = new JTextField(8);

    public Edge[] edges = new Edge[MAX_SIZE];
    public int showing=0;

    public GraphPart firstGraphPart;
    public GraphPart secondGraphPart;

    public JScrollPane scrollPane;

    public class Edge extends JPanel{
        public String[] firstStrings = new String[GraphPart.MAX_SIZE+1];
        public JComboBox first = new JComboBox(firstStrings);
        public int firstShowing=0;

        public JLabel arrow = new JLabel("--->");

        public String[] secondStrings = new String[GraphPart.MAX_SIZE+1];
        public JComboBox second = new JComboBox(secondStrings);
        public int secondShowing=0;
        public Edge(){

            for(int i=0;i<GraphPart.MAX_SIZE+1;i++){
                firstStrings[i] = new String();
                secondStrings[i] = new String();
            }

            first.setMaximumRowCount(0);
            second.setMaximumRowCount(0);

            add(first);
            add(arrow);
            add(second);
        }
        public void resize(){
            first.setMaximumRowCount(firstShowing);
            second.setMaximumRowCount(secondShowing);
        }
        public void fillWithStrings(){
            first.removeAllItems();
            for(int i=0;i<firstShowing;i++){
                first.addItem(firstStrings[i]);
            }
            second.removeAllItems();
            for(int i=0;i<secondShowing;i++){
                second.addItem(secondStrings[i]);
            }
        }
    }

    public GraphEdges(GraphPart a, GraphPart b){
        firstGraphPart=a;
        secondGraphPart=b;

        //Creating upper field
        JPanel upper = new JPanel();
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        upper.add(name);
        upper.add(num);
        num.setMaximumSize(num.getPreferredSize());
        upper.setMaximumSize(upper.getPreferredSize());

        //Adding scrollfield
        JPanel helperPanel =new JPanel();
        helperPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel scrollingField =new JPanel();
        scrollingField.setLayout(new BoxLayout(scrollingField,BoxLayout.PAGE_AXIS));
        scrollingField.add(Box.createVerticalGlue());

        for(int i=0;i<MAX_SIZE;i++){
            edges[i]=new Edge();
            scrollingField.add(edges[i]);
        }
        resize(showing);


        c.weighty=1;
        c.anchor=GridBagConstraints.NORTH;
        helperPanel.add(scrollingField,c);
        scrollPane = new JScrollPane(helperPanel);

        num.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                actionOnChanging();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                actionOnChanging();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                actionOnChanging();
            }
        });

        add(upper);
        add(scrollPane);
        this.setBorder(new LineBorder(Color.black,1));
    }

    public int stringToPossibleInt(String s){
        if(s.length()==0){
            return 0;
        }
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)<48||s.charAt(i)>57){
                return -1;
            }
        }
        if(firstGraphPart.showing<0||secondGraphPart.showing<0||Integer.parseInt(s)> firstGraphPart.showing* secondGraphPart.showing){
            return -1;
        }
        return Integer.parseInt(s);
    }

    public void resize(int x){
        if(x<0){
            x=0;
        }
        for (int i=0;i<x;i++){
            edges[i].setVisible(true);
        }
        for (int i=x;i<MAX_SIZE;i++){
            edges[i].setVisible(false);
        }
    }

    public void actionOnChanging(){
        showing = stringToPossibleInt(num.getText());
        resize(showing);
        for(int i=0;i<showing;i++){
            edges[i].firstShowing= firstGraphPart.showing;
            edges[i].secondShowing= secondGraphPart.showing;
            edges[i].resize();
            for(int j=0;j<edges[i].firstShowing;j++){
                edges[i].firstStrings[j]=firstGraphPart.vertex[j].text.getText();
            }
            for(int j=0;j<edges[i].secondShowing;j++){
                edges[i].secondStrings[j]=secondGraphPart.vertex[j].text.getText();
            }
            edges[i].fillWithStrings();
        }
    }
}
