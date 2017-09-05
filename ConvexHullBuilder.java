import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.io.*;

public class ConvexHullBuilder
{
   private HullPoint [] points;
   private HullPoint [] edge;
   private double xMax;
   private double yMax;
   
   public ConvexHullBuilder(String fileName)throws IOException
   {
      xMax=0;
      yMax=0;
      ArrayList list=new ArrayList();
      Scanner f=new Scanner(new File(fileName));
      while(f.hasNextLine())
      {
         double x,y;
         
         x=f.nextDouble();
         if(xMax<x)
            xMax=x;
         
         y=f.nextDouble();
         if(yMax<y)
            yMax=y;
         
         f.nextLine();
         
         HullPoint p=new HullPoint(x,y);
         list.add(p);
      }
      f.close();
      
      list.trimToSize();
      points=(HullPoint[])list.toArray(new HullPoint[list.size()]);
   }
   
   public void sort()
   {
      for(int i=0;i<points.length-1;i++)
         for(int j=i+1;j<points.length;j++)
         {
            if(points[j].getX()<points[i].getX())
               swap(i,j);
            else if(points[j].getX()==points[i].getX()&&points[j].getY()<points[i].getY())
               swap(i,j);
         }
   }
   
   public void swap(int i, int j)
   {
      HullPoint temp=points[i];
      points[i]=points[j];
      points[j]=temp;
   }
   
   public void build()
   {
      HullPoint[] upperEdge=new HullPoint[points.length];
      upperEdge[0]=points[0];
      upperEdge[1]=points[1];
      int edgeLength=2;
      
      for(int i=2;i<points.length;i++)
      {
         upperEdge[edgeLength]=points[i];
         edgeLength++;
         
         while(edgeLength>2&&(!testRightTurn(upperEdge[edgeLength-3],upperEdge[edgeLength-2],upperEdge[edgeLength-1])))
         {
            upperEdge[edgeLength-2]=upperEdge[edgeLength-1];
            upperEdge[edgeLength-1]=null;
            edgeLength--;
         }
      }
      ArrayList<HullPoint> list=new ArrayList<HullPoint>(Arrays.asList(upperEdge));
      list.trimToSize();
      upperEdge=(HullPoint [])list.toArray(new HullPoint[list.size()]);
      
      HullPoint [] lowerEdge=new HullPoint[points.length];
      lowerEdge[0]=points[points.length-1];
      lowerEdge[1]=points[points.length-2];
      edgeLength=2;
      
      for(int i=2;i<points.length;i++)
      {
         lowerEdge[edgeLength]=points[points.length-(i+1)];
         edgeLength++;
         
         while(edgeLength>2&&(!testRightTurn(lowerEdge[edgeLength-3],lowerEdge[edgeLength-2],lowerEdge[edgeLength-1])))
         {
            lowerEdge[edgeLength-2]=lowerEdge[edgeLength-1];
            lowerEdge[edgeLength-1]=null;
            edgeLength--;
         }
      }
      lowerEdge[0]=null;
      lowerEdge[edgeLength-1]=null;
      list=new ArrayList<HullPoint>(Arrays.asList(lowerEdge));
      list.trimToSize();
      lowerEdge=(HullPoint [])list.toArray(new HullPoint[list.size()]);
      
      list=new ArrayList<HullPoint>(Arrays.asList(upperEdge));
      list.addAll(Arrays.asList(lowerEdge));
      list.trimToSize();
      list.removeAll(Collections.singleton(null));
      edge=(HullPoint [])list.toArray(new HullPoint[list.size()]);     
   }
   
   public boolean testRightTurn(HullPoint a, HullPoint b, HullPoint c)
   {
      double det=((b.getX()-a.getX())*(c.getY()-a.getY()))-((b.getY()-a.getY())*(c.getX()-a.getX()));
      return(det<0);
   }
   
   public void print()
   {
      System.out.println("Points that make up the convex hull:");
      for(int i=0;i<edge.length;i++)
         System.out.println(edge[i]);
   }
   
   public HullPoint[] getHullPoints()
   {
      return points;
   }
   
   public HullPoint[] getEdge()
   {
      return edge;
   }
   
   public double getXMax()
   {
      return xMax;
   }
   
   public double getYMax()
   {
      return yMax;
   }
}