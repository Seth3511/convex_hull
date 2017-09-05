public class HullPoint
{
   private double x;
   private double y;
   
   public HullPoint(double x, double y)
   {
      this.x=x;
      this.y=y;
   }
   
   public double getX()
   {
      return x;
   }
   
   public double getY()
   {
      return y;
   }
   
   public String toString()
   {
      return x+", "+y;
   }
}