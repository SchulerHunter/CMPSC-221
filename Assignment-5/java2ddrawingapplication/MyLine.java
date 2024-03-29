package java2ddrawingapplication;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

public class MyLine extends MyShapes{
    
    public MyLine(Point pntA, Point pntB, Paint paint, Stroke strk)
    {
        super(pntA, pntB, paint, strk);
    }
    
    @Override
    public void draw(Graphics2D g2d){
        g2d.setPaint(getPaint());
        g2d.setStroke(getStroke());
        g2d.drawLine((int)(getStartPoint().getX()), (int)(getStartPoint().getY()), (int)(getEndPoint().getX()), (int)(getEndPoint().getY()));
    }
    
}
