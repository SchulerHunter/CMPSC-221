package java2ddrawingapplication;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;

public class MyRectangle extends MyBoundedShapes{
    
    public MyRectangle(Point pntA, Point pntB, Paint paint, Stroke strk, boolean filled)
    {
        super(pntA, pntB, paint, strk, filled);
    }
    
    @Override   
    public void draw(Graphics2D g2d)
    {
        g2d.setPaint(getPaint());
        g2d.setStroke(getStroke());
        if(isFilled())
        {
            g2d.fillRect(getTopLeftX(), getTopLeftY(), getWidth(), getHeight());
        }
        else
        {
            g2d.drawRect(getTopLeftX(), getTopLeftY(), getWidth(), getHeight());
        }
        
    }
    
}
