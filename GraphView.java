import javax.swing.*;
import java.awt.*;

public class GraphView extends JComponent {
    GraphInfo inf = new GraphInfo();
    Vector2[] firstPos = new Vector2[inf.first.MAX_SIZE];
    Vector2[] secondPos = new Vector2[inf.second.MAX_SIZE];
    Vector2[] edgesStartPoint = new Vector2[inf.edges.MAX_SIZE];
    Vector2[] edgesEndPoint = new Vector2[inf.edges.MAX_SIZE];

    public class Vector2{
        int x,y;
        Vector2(int x, int y){
            this.x=x;
            this.y=y;
        }
    }


    public void paint(Graphics g){
        int countLeft = inf.first.showing;
        int countRight = inf.second.showing;
        int startx = getWidth()/2;
        int starty = getHeight()/2;
        int fullset = getHeight()/inf.first.MAX_SIZE;
        int radius = 3*fullset/4;
        int gap =200;

        int ex1 = countLeft%2 == 0 ? 0 : 1;
        int ex2 = countRight%2 == 0 ? 0 : 1;

        for(int i=-countLeft/2;i<countLeft/2+ex1;i++){
            int x=startx-gap-radius/2;
            int y=starty+i*fullset;
            firstPos[i+countLeft/2]=new Vector2(x,y);
        }
        for(int i=-countRight/2;i<countRight/2+ex2;i++){
            int x=startx+gap-radius/2;
            int y=starty+i*fullset;
            secondPos[i+countRight/2]=new Vector2(x,y);
        }
        for(int i=0;i<inf.edges.showing;i++){
            int tmp;
            tmp = inf.edges.edges[i].first.getSelectedIndex();
            edgesStartPoint[i]=new Vector2(startx-gap,starty+(tmp-countLeft/2)*fullset+radius/2);
            tmp = inf.edges.edges[i].second.getSelectedIndex();
            edgesEndPoint[i]=new Vector2(startx+gap,starty+(tmp-countRight/2)*fullset+radius/2);
        }
        for(int i=0;i<inf.edges.showing;i++){
            g.drawLine(edgesStartPoint[i].x,edgesStartPoint[i].y,edgesEndPoint[i].x,edgesEndPoint[i].y);
        }
        g.setFont(new Font("Arial",Font.PLAIN,18));
        for(int i=0;i<countLeft;i++){
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(firstPos[i].x,firstPos[i].y,radius,radius);
            g.setColor(Color.BLACK);
            g.drawOval(firstPos[i].x,firstPos[i].y,radius,radius);
            String text=inf.first.vertex[i].text.getText();
            g.drawString(text,firstPos[i].x-getFontMetrics(g.getFont()).stringWidth(text)-radius/2,firstPos[i].y+radius/2);
        }
        for(int i=0;i<countRight;i++){
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(secondPos[i].x,secondPos[i].y,radius,radius);
            g.setColor(Color.BLACK);
            g.drawOval(secondPos[i].x,secondPos[i].y,radius,radius);
            String text=inf.second.vertex[i].text.getText();
            g.drawString(text,secondPos[i].x+radius+radius/2,secondPos[i].y+radius/2);
        }
    }

    public GraphView(GraphInfo info){
        inf = info;
    }
}
