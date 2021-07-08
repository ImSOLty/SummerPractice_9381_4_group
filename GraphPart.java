import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class GraphPart extends JPanel {
    public static int MAX_SIZE =20;
    public GraphEdges edges;
    public JLabel name = new JLabel("First graph part");
    public JTextField num = new JTextField(8);
    public Vertex[] vertex = new Vertex[MAX_SIZE];
    public int showing = 0;
    public JScrollPane scrollPane;


    public class Vertex extends JPanel{
        public JTextField text = new JTextField(14);
        public Vertex(){
            text.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    clearEdges();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    clearEdges();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    clearEdges();
                }
            });
            add(text);
        }
    }

    public GraphPart(String n){
        name.setText(n);
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

        c.weighty=1;
        c.anchor=GridBagConstraints.NORTH;
        helperPanel.add(scrollingField,c);
        scrollPane = new JScrollPane(helperPanel);


        //Adding and resizing visible vertex
        for(int i=0;i<MAX_SIZE;i++){
            vertex[i]=new Vertex();
            scrollingField.add(vertex[i]);
        }
        resize(showing);

        add(upper);
        add(scrollPane);

        //Adding changeListener to num field
        num.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                showing = stringToPossibleInt(num.getText());
                resize(showing);
                clearEdges();
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                showing = stringToPossibleInt(num.getText());
                resize(showing);
                clearEdges();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                showing = stringToPossibleInt(num.getText());
                resize(showing);
                clearEdges();
            }
        });

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
        if(Integer.parseInt(s)>MAX_SIZE){
            return -1;
        }
        return Integer.parseInt(s);
    }

    public void resize(int x){
        if(x<0){
            x=0;
        }
        for (int i=0;i<x;i++){
            vertex[i].setVisible(true);
        }
        for (int i=x;i<MAX_SIZE;i++){
            vertex[i].setVisible(false);
        }
    }

    public void clearEdges(){
        edges.num.setText("");
        edges.showing=0;
    }
}
