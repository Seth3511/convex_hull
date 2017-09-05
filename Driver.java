import java.util.Scanner;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class Driver
{
   public static void main(String [] args)throws IOException
   {
      Scanner kb=new Scanner(System.in);
      System.out.print("Name of file to read: ");
      String fileName=kb.nextLine();
      
      ConvexHullBuilder builder=new ConvexHullBuilder(fileName);
      builder.sort();
      builder.build();
      
      System.out.print("Enter scale factor: ");
      double scale=kb.nextDouble();
      System.out.println("");
      
      JFrame f = new JFrame("ConvexHullCanvas");
      f.addWindowListener(new WindowAdapter() {
      
      public void windowClosing(WindowEvent e) {System.exit(0);}
      });
      
      JApplet applet = new ConvexHullCanvas(builder.getHullPoints(),builder.getEdge(),scale,builder.getYMax());
      f.getContentPane().add("Center", applet);
      applet.init();
      f.pack();
      f.setSize(new Dimension((int)Math.round(scale*builder.getXMax()+scale),(int)Math.round(scale*builder.getYMax()+scale)));
      f.setVisible(true);
      
      builder.print();
   }
}