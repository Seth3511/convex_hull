import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class ConvexHullCanvas extends JApplet
{
   private HullPoint[] points;
   private HullPoint[] edge;
   private double scale;
   private double yMax;
   
   public ConvexHullCanvas(HullPoint[] points, HullPoint[] edge, double scale, double yMax)
   {
      super();
      this.points=points;
      this.edge=edge;
      this.scale=scale;
      this.yMax=yMax*scale+20;
   }
   
   public void paint(Graphics g) 
   {
      Graphics2D g2 = (Graphics2D) g;
      super.paint(g);
      
      for(int i=0;i<points.length;i++)
      {
         Point2D.Double p = new Point2D.Double((points[i].getX()*scale+10), (yMax-(points[i].getY()*scale))-10);
         g2.fill(new Ellipse2D.Double(p.x-3,p.y-3,7,7));
      }
      
      for(int i=0;i<edge.length-1;i++)
      {
         g2.draw(new Line2D.Double((edge[i].getX()*scale+10), (yMax-(edge[i].getY()*scale))-10,(edge[i+1].getX()*scale+10), (yMax-(edge[i+1].getY()*scale))-10));   
      }
      g2.draw(new Line2D.Double((edge[0].getX()*scale+10), (yMax-(edge[0].getY()*scale))-10,(edge[edge.length-1].getX()*scale+10), (yMax-(edge[edge.length-1].getY()*scale))-10));
   }
}