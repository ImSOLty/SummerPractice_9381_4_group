import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GraphInfo extends JPanel {
    public GraphPart first=new GraphPart("First graph part");
    public GraphPart second=new GraphPart("Second graph part");
    public GraphEdges edges =new GraphEdges(first,second);
    public GraphInfo(){
        first.edges=edges;
        second.edges=edges;

        JPanel back=new JPanel();
        back.setBackground(Color.white);
        back.setBorder(new LineBorder(Color.white,10));

        JPanel parts =new JPanel();
        parts.setLayout(new GridLayout(1,2,10,10));
        back.setLayout(new GridLayout(1,2,10,10));
        parts.add(first);
        parts.add(second);
        back.add(parts);
        back.add(edges);

        //adding content to this panel
        add(back);
        setLayout(new GridLayout(1,1));//For resizing fields
        this.setBorder(new LineBorder(Color.black,1));
    }
}
