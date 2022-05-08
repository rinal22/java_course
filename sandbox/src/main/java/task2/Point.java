package task2;

public class Point {
  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public static double distance(Point p1, Point p2) {
    return Math.sqrt((p1.x-p2.x) * (p1.x-p2.x) + (p1.y-p2.y) * (p1.y-p2.y));
  }

  public static void printDistance (Point p1, Point p2) {
    System.out.println("Расстояние между точками x1("+p1.x+","+p1.y+") и x2("+p2.x+","+p2.y+") = " + p2.distance(p1,p2));
  }

  public double distance(Point p1) {
    return Math.sqrt((p1.x-this.x) * (p1.x-this.x) + (p1.y-this.y) * (p1.y-this.y));
  }

  public void printDistance (Point p1) {
    System.out.println("Расстояние между точками x1("+p1.x+","+p1.y+") и x2("+this.x+","+this.y+") = " + this.distance(p1));
  }

}
